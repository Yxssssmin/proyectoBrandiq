import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UsersService } from '../../services/users.service';
import { ModalEmpezarPartidaComponent } from '../modal-empezar-partida/modal-empezar-partida.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink, CommonModule, ModalEmpezarPartidaComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  showModal = false; // Variable para controlar la visibilidad del modal

  constructor(public userService: UsersService) {}

  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }

  showStartGameForm() {
    this.showModal = true; // Mostrar el modal al hacer clic en "Empezar partida"
  }

  closeModal() {
    this.showModal = false; // Cerrar el modal
  }
}
