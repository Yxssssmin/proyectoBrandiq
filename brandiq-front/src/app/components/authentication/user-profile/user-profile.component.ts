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

  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    this.obtenerPerfilUsuario();
  }

  obtenerPerfilUsuario(): void {
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
    this.userProfile.nombre = this.updatedName;
    this.userProfile.email = this.updatedEmail;
    // Lógica para guardar cambios en el perfil
    // this.userService
    //   .updateProfile({
    //     nombreCompleto: this.userProfile.nombreCompleto,
    //     email: this.userProfile.email,
    //   })
    //   .subscribe({
    //     next: (response) => {
    //       console.log('Cambios guardados correctamente:', response);
    //       // Actualizar el perfil después de guardar cambios (opcional)
    //       this.obtenerPerfilUsuario();
    //     },
    //     error: (error) => {
    //       console.error('Error al guardar cambios:', error);
    //       // Manejar el error de acuerdo a tus necesidades
    //     },
    //   });
  }
}
