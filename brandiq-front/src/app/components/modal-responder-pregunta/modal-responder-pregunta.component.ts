import { Component, Input } from '@angular/core';
import { TableroServiceService } from '../../services/tablero-service.service';
import { ActivatedRoute } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { switchMap, throwError } from 'rxjs';

@Component({
  selector: 'app-modal-responder-pregunta',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './modal-responder-pregunta.component.html',
  styleUrl: './modal-responder-pregunta.component.css',
})
export class ModalResponderPreguntaComponent {
  roomId: string | null = null;
  nombreImagen: string | null = null;
  respuesta: string = '';
  respuestaForm!: FormGroup;
  siguienteJugador: string | null = null;

  constructor(
    private tableroService: TableroServiceService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    tableroService.getSiguienteJugador();
  }

  ngOnInit() {
    this.respuestaForm = this.formBuilder.group({
      respuesta: ['', Validators.required],
    });
    this.route.params.subscribe((params) => {
      this.roomId = params['id'];
      this.mostrarImagen(this.getNicknameStorage(), this.roomId); // Corregido el uso de localStorage.getItem()
    });

    this.tableroService.getSiguienteJugador();
  }

  enviarRespuesta() {
    const nombreJugador = this.getNicknameStorage();
    const idTablero = parseInt(this.roomId || '0', 10); // Ajusta el ID del tablero según tu lógica
    console.log(this.respuestaForm.get('respuesta')?.value);

    const respuesta = this.respuestaForm.get('respuesta')?.value; // Ajusta la respuesta según tu lógica

    // Realiza la primera consulta y luego la segunda usando switchMap
    this.tableroService
      .comprobarRespuesta(nombreJugador, idTablero, respuesta)
      .pipe(
        switchMap((resultado: string) => {
          // Procesa el resultado según tus necesidades

          // Realiza acciones adicionales para respuesta correcta
          if (resultado === 'Fallo') {
            console.log('Respuesta incorrecta');
            // Puedes lanzar un error o simplemente devolver un observable con un valor específico
            return this.tableroService.cambiarTurnoSiguienteJugador(idTablero);
          } else {
            console.log('Respuesta correcta');
            // Luego de procesar la primera consulta, realiza la segunda
            return this.tableroService.cambiarTurnoSiguienteJugador(idTablero);
          }
        })
      )
      .subscribe((resultado: string) => {
        // Procesa el resultado de la segunda consulta según tus necesidades
        console.log(resultado);

        // Realiza acciones adicionales para respuesta correcta
        if (resultado === 'Error') {
          console.log('Turno no cambiado');
          // Realiza acciones adicionales para respuesta incorrecta
        } else {
          console.log('Turno cambiado'); // Maneja otros casos según sea necesario
        }
      });
  }
  getNicknameStorage(): string {
    return localStorage.getItem('userNickname')!;
  }

  actualizarSiguienteTurno(siguienteTurno: string | null) {
    // Haz lo que necesites con la variable siguienteTurno en este componente
    this.siguienteJugador = siguienteTurno;
    console.log(
      'Siguiente turno en ModalResponderPreguntaComponent:',
      siguienteTurno
    );
  }

  mostrarImagen(nickname: string, roomId: string | null) {
    const roomIdNumber = parseInt(roomId || '0', 10); // El segundo parámetro es la base (10 para decimal)

    this.tableroService
      .obtenerNombreImagen(nickname, roomIdNumber)
      .subscribe((nombreImagen: string) => {
        console.log('Nombre de la imagen:', nombreImagen);
        this.nombreImagen = nombreImagen;
      });
  }
}
