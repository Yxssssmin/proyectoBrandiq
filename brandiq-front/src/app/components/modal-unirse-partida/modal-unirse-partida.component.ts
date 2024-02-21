import { Component, EventEmitter, Output } from '@angular/core';
import { TableroServiceService } from '../../services/tablero-service.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modal-unirse-partida',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modal-unirse-partida.component.html',
  styleUrl: './modal-unirse-partida.component.css',
})
export class ModalUnirsePartidaComponent {
  @Output() closeModalEvent = new EventEmitter();
  tituloPartida: string = '';
  tableros: any[] = [];

  constructor(
    private tableroService: TableroServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.listarTableros();
  }

  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }

  closeModal() {
    this.closeModalEvent.emit();
  }

  listarTableros(): void {
    this.tableroService.listarTableros().subscribe({
      next: (tableros) => {
        console.log('Tableros obtenidos:', tableros);
        this.tableros = tableros; // Asigna los tableros a la propiedad del componente
      },
      error: (error) => {
        console.error('Error al obtener los tableros:', error);
      },
    });
  }

  unirsePartida(tableroId: number): void {
    const nombreJugador = this.getNicknameStorage();

    if (nombreJugador !== null) {
      this.tableroService.unirsePartida(tableroId, nombreJugador).subscribe({
        next: (data) => {
          console.log('Respuesta del servidor al unirse a la partida:', data);
          this.router.navigate(['/salaEspera/' + tableroId]);
          // Puedes agregar cualquier lógica adicional aquí
        },
        error: (error) => {
          console.error('Error al unirse a la partida:', error);
        },
      });
    } else {
      console.error('El nombre del jugador es null.');
    }
  }
}
