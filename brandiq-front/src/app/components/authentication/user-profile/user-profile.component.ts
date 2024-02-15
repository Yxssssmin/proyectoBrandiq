import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../../services/users.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css',
})
export class UserProfileComponent implements OnInit {
  userProfile: any = {};
  updatedName: string = '';
  updatedEmail: string = '';
  saveMessage: string = ' ';

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
  eliminarUsuario(): void {
    const confirmacion = confirm(
      '¿Estás seguro de que quieres eliminar este usuario?'
    );
    if (confirmacion) {
      this.userService.eliminarUsuario().subscribe({
        next: (response) => {
          const Correcto = confirm('Usuario eliminado correctamente');
          console.log('Usuario eliminado correctamente:', response);
          this.userService.logout();
          // Puedes redirigir a la página de inicio o realizar otras acciones después de eliminar el usuario
        },
        error: (error) => {
          console.error('Error al eliminar usuario:', error);
          // Puedes mostrar un mensaje de error o manejar la situación según tus necesidades
        },
      });
    }
  }
}
