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
            this.router.navigate(['/tablero']);
          },
          error: (error) => {
            console.error('Error en la solicitud:', error);
          },
        });
    } else {
      console.error('El creador de la partida es null.');
    }
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
    // Lógica para unirse a la partida, por ejemplo, redirigir a una ruta específica
    this.router.navigate(['/tablero']);
  }
}
