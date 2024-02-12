import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../../services/users.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css',
})
export class UserProfileComponent implements OnInit {
  userProfile: any = {};
  updatedName: string = '';
  updatedEmail: string = '';
  saveMessage: string = '';

  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    this.obtenerPerfilUsuario();
  }

  obtenerPerfilUsuario(): void {
    const authToken = localStorage.getItem('authToken');
    const nicknameUsuario = localStorage.getItem('userNickname');
    const headers = { Authorization: `Bearer ${authToken}` };

    console.log('Headers:', headers); // Verifica que el token se esté pasando correctamente

    this.userService.getProfile().subscribe({
      next: (profile: any) => {
        this.userProfile = profile || {};
      },
      error: (error) => {
        console.error('Error al obtener el perfil del usuario', error);
        this.userProfile = { nickname: 'Error' };
      },
    });
  }
  guardarCambios(): void {
    this.userService
      .updateProfile({
        nombreCompleto: this.userProfile.nombre,
        email: this.userProfile.email,
        nickname: this.userProfile.nickname,
      })
      .subscribe({
        next: (response) => {
          console.log('Cambios guardados correctamente:', response);
          this.saveMessage = 'Cambios guardados correctamente'; // Actualizar el mensaje
          // Actualizar el perfil después de guardar cambios (opcional)
          this.obtenerPerfilUsuario();
        },
        error: (error) => {
          console.error('Error al guardar cambios:', error);
          this.saveMessage = 'Error al guardar cambios'; // Actualizar el mensaje

          // Manejar el error de acuerdo a tus necesidades
        },
      });
  }
}
