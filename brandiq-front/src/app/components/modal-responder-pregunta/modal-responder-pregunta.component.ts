import { Component } from '@angular/core';
import { TableroServiceService } from '../../services/tablero-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-modal-responder-pregunta',
  standalone: true,
  imports: [],
  templateUrl: './modal-responder-pregunta.component.html',
  styleUrl: './modal-responder-pregunta.component.css',
})
export class ModalResponderPreguntaComponent {
  roomId: string | null = null;
  nombreImagen: string | null = null;

  constructor(
    private tableroService: TableroServiceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.roomId = params['id'];
      this.mostrarImagen(this.getNicknameStorage(), this.roomId); // Corregido el uso de localStorage.getItem()
    });
  }

  enviarRespuesta() {
    // Lógica para manejar el evento de enviar respuesta
    // Puedes cerrar el modal o realizar otras acciones aquí
  }
  getNicknameStorage(): string {
    return localStorage.getItem('userNickname')!;
  }
  mostrarImagen(nickname: string, roomId: string | null) {
    const roomIdNumber = parseInt(roomId || '0', 10);  // El segundo parámetro es la base (10 para decimal)
    
    this.tableroService
      .obtenerNombreImagen(nickname, roomIdNumber)
      .subscribe((nombreImagen: string) => {
        console.log('Nombre de la imagen:', nombreImagen);
        this.nombreImagen = nombreImagen;
      });
  }
  
}
