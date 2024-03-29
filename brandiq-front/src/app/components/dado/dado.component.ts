import { CommonModule } from '@angular/common';
import {
  Component,
  ElementRef,
  EventEmitter,
  Output,
  ViewChild,
} from '@angular/core';
import { TableroServiceService } from '../../services/tablero-service.service';
import { ActivatedRoute } from '@angular/router';
import { ModalEmpezarPartidaComponent } from '../modal-empezar-partida/modal-empezar-partida.component';
import { ModalResponderPreguntaComponent } from '../modal-responder-pregunta/modal-responder-pregunta.component';

@Component({
  selector: 'app-dado',
  standalone: true,
  imports: [CommonModule, ModalResponderPreguntaComponent],
  templateUrl: './dado.component.html',
  styleUrl: './dado.component.css',
})
export class DadoComponent {
  @ViewChild('dado') dadoElement!: ElementRef;
  @ViewChild('boton') botonElement!: ElementRef;
  @ViewChild('resultado') resultadoElement?: ElementRef;
  roomId: string | null = null;
  nombreImagen: string | null = null;
  isGirando = false;
  showModalEmpezar = false; // Variable para controlar la visibilidad del modal
  @Output() mostrarModalResponderPregunta = new EventEmitter<boolean>();

  constructor(
    private tableroService: TableroServiceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.roomId = params['id'];
    });
  }

  showStartGameForm() {
    this.showModalEmpezar = true; // Mostrar el modal al hacer clic en "Empezar partida"
  }

  closeModal() {
    this.showModalEmpezar = false; // Cerrar el modal
  }

  ngAfterViewInit() {
    // Verificar si resultadoElement está presente antes de acceder a su propiedad
    if (this.resultadoElement) {
      this.resultadoElement.nativeElement.innerHTML = '';
    }
  }
  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }

  girar() {
    this.botonElement.nativeElement.setAttribute('disabled', 'disabled');
    if (this.resultadoElement) {
      this.resultadoElement.nativeElement.innerHTML = 'Girando...';
    }

    const dado = this.dadoElement.nativeElement;
    dado.classList.add('girando');
    const rotacionAleatoria = Math.floor(Math.random() * 360) * 50;

    dado.style.transition = `transform 2s ease-in-out`;
    dado.style.transform = `rotateX(0deg) rotateY(180deg) rotateZ(${rotacionAleatoria}deg)`;
    setTimeout(() => this.tirar(), 2000);
  }

  tirar() {
    const nickname = this.getNicknameStorage() ?? '';
    const roomId = parseInt(this.roomId ?? '0', 10);
    const dado = this.dadoElement.nativeElement;
    this.tableroService
      .tirarDado(nickname, roomId)
      .subscribe((numero: number) => {
        dado.style.transition = 'transform 2s ease-in-out';
        switch (numero) {
          case 1:
            dado.style.transform = 'rotateX(0deg) rotateY(0deg) rotateZ(0deg)';
            dado.style.backgroundImage = 'url(../../../assets/dado/dado1.jpg)';
            break;
          case 2:
            dado.style.transform =
              'rotateX(0deg) rotateY(180deg) rotateZ(0deg)';
            dado.style.backgroundImage = 'url(../../../assets/dado/dado2.jpg)';
            break;
          case 3:
            dado.style.transform = 'rotateX(0deg) rotateY(90deg) rotateZ(0deg)';
            dado.style.backgroundImage = 'url(../../../assets/dado/dado3.jpg)';
            break;
          case 4:
            dado.style.transform =
              'rotateX(0deg) rotateY(270deg) rotateZ(0deg)';
            dado.style.backgroundImage = 'url(../../../assets/dado/dado4.jpg)';
            break;
          case 5:
            dado.style.transform =
              'rotateX(-90deg) rotateY(0deg) rotateZ(0deg)';
            dado.style.backgroundImage = 'url(../../../assets/dado/dado5.jpg)';
            break;
          case 6:
            dado.style.transform = 'rotateX(90deg) rotateY(0deg) rotateZ(0deg)';
            dado.style.backgroundImage = 'url(../../../assets/dado/dado6.jpg)';
            break;
        }

        if (this.resultadoElement) {
          const resultadoHtml = this.resultadoElement.nativeElement;

          setTimeout(() => {
            if (resultadoHtml) {
              resultadoHtml.innerHTML = numero.toString();
            }
          }, 1000);
        }

        this.botonElement.nativeElement.removeAttribute('disabled');
        dado.classList.remove('girando');
      });

    setTimeout(() => this.mostrarImagen(nickname, roomId), 3000);
  }
  mostrarImagen(nickname: string, roomId: number) {
    this.tableroService
      .obtenerNombreImagen(nickname, roomId)
      .subscribe((nombreImagen: string) => {
        this.showModalEmpezar = true;
        this.nombreImagen = nombreImagen;
      });
  }

  getShowModalEmpezar() {
    return this.showModalEmpezar;
  }

  getnombreImagen() {
    return this.nombreImagen;
  }
}

// girar() {
//   this.botonElement.nativeElement.setAttribute('disabled', 'disabled');

//   // Verificar si resultadoElement está presente antes de acceder a su propiedad
//   if (this.resultadoElement) {
//     this.resultadoElement.nativeElement.innerHTML = 'Girando...';
//   }

//   const dado = this.dadoElement.nativeElement;
//   dado.classList.add('girando');
//   const rotacionAleatoria = Math.floor(Math.random() * 360) * 50;

//   dado.style.transition = `transform 2s ease-in-out`;
//   dado.style.transform = `rotateX(0deg) rotateY(180deg) rotateZ(${rotacionAleatoria}deg)`;
//   setTimeout(() => this.tirar(), 1000);
// }

//   tirar() {
//     const dado = this.dadoElement.nativeElement;
//     const boton = this.botonElement.nativeElement;

//     // Verificar si resultadoElement está presente antes de acceder a su propiedad
//     if (this.resultadoElement) {
//       this.resultadoElement.nativeElement.innerHTML = '';
//     }

//     dado.classList.remove('girando');
//     boton.removeAttribute('disabled');

//     // Calcular el número
//     const numero = Math.floor(Math.random() * 6) + 1;

//     dado.style.transition = 'transform 2s ease-in-out';
//     switch (numero) {
//       case 1:
//         dado.style.transform = 'rotateX(0deg) rotateY(0deg) rotateZ(0deg)';
//         dado.style.backgroundImage = 'url(../../../assets/dado/dado1.jpg)';
//         break;
//       case 2:
//         dado.style.transform = 'rotateX(0deg) rotateY(180deg) rotateZ(0deg)';
//         dado.style.backgroundImage = 'url(../../../assets/dado/dado2.jpg)';
//         break;
//       case 3:
//         dado.style.transform = 'rotateX(0deg) rotateY(90deg) rotateZ(0deg)';
//         dado.style.backgroundImage = 'url(../../../assets/dado/dado3.jpg)';
//         break;
//       case 4:
//         dado.style.transform = 'rotateX(0deg) rotateY(270deg) rotateZ(0deg)';
//         dado.style.backgroundImage = 'url(../../../assets/dado/dado4.jpg)';
//         break;
//       case 5:
//         dado.style.transform = 'rotateX(-90deg) rotateY(0deg) rotateZ(0deg)';
//         dado.style.backgroundImage = 'url(../../../assets/dado/dado5.jpg)';
//         break;
//       case 6:
//         dado.style.transform = 'rotateX(90deg) rotateY(0deg) rotateZ(0deg)';
//         dado.style.backgroundImage = 'url(../../../assets/dado/dado6.jpg)';
//         break;
//     }

//     if (this.resultadoElement) {
//       const resultadoHtml = this.resultadoElement.nativeElement;

//       setTimeout(() => {
//         // Verificar nuevamente antes de realizar la asignación
//         if (resultadoHtml) {
//           resultadoHtml.innerHTML = numero.toString();
//         }
//       }, 1000);
//     }
//   }
// }
