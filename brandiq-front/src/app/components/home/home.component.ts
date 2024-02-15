import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UsersService } from '../../services/users.service';
import { ModalEmpezarPartidaComponent } from '../modal-empezar-partida/modal-empezar-partida.component';
import { ModalUnirsePartidaComponent } from '../modal-unirse-partida/modal-unirse-partida.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    ModalEmpezarPartidaComponent,
    ModalUnirsePartidaComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  showModalEmpezar = false; // Variable para controlar la visibilidad del modal
  showModalUnirse = false; // Variable para controlar la visibilidad del modal

  constructor(public userService: UsersService) {}

  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }

  showStartGameForm() {
    this.showModalEmpezar = true; // Mostrar el modal al hacer clic en "Empezar partida"
  }

  closeModal() {
    this.showModalEmpezar = false; // Cerrar el modal
  }

  showJoinGameForm() {
    this.showModalUnirse = true; // Mostrar el modal al hacer clic en "Empezar partida"
  }

  closeModalJoin() {
    this.showModalUnirse = false; // Cerrar el modal
  }
}
