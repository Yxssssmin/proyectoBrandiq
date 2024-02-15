import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TableroServiceService } from '../../services/tablero-service.service';

@Component({
  selector: 'app-modal-empezar-partida',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './modal-empezar-partida.component.html',
  styleUrl: './modal-empezar-partida.component.css',
})
export class ModalEmpezarPartidaComponent {
  @Output() closeModalEvent = new EventEmitter();
  tituloPartida: string = '';

  constructor(private tableroService: TableroServiceService) {}

  crearPartida(event: any) {
    event.preventDefault();

    const creadorPartida = this.getNicknameStorage();

    if (creadorPartida !== null) {
      this.tableroService
        .crearPartida(creadorPartida, this.tituloPartida)
        .subscribe({
          next: (data) => {
            console.log('Respuesta del servidor:', data);
            this.closeModal();
          },
          error: (error) => {
            console.error('Error en la solicitud:', error);
          },
        });
    } else {
      console.error(
        'El creador de la partida es null. Manejar este caso según tus necesidades.'
      );
    }
  }

  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }

  closeModal() {
    this.closeModalEvent.emit();
  }
}
