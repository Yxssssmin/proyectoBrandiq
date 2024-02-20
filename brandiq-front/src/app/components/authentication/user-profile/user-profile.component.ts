import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../../services/users.service';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css',
})
export class UserProfileComponent implements OnInit {
  profileForm!: FormGroup;
  userProfile: any = {};
  saveMessage: string = ' ';

  constructor(
    private formBuilder: FormBuilder,
    private usersService: UsersService
  ) {}

  ngOnInit(): void {
    this.obtenerPerfilUsuario();
  }

  obtenerPerfilUsuario(): void {
    const authToken = localStorage.getItem('authToken');
    const nicknameUsuario = localStorage.getItem('userNickname');
    const headers = { Authorization: `Bearer ${authToken}` };

    console.log('Headers:', headers);

    this.usersService.getProfile().subscribe({
      next: (profile: any) => {
        this.userProfile = profile || {};
        this.createProfileForm();
      },
      error: (error) => {
        console.error('Error al obtener el perfil del usuario', error);
        this.userProfile = { nickname: 'Error' };
        this.createProfileForm();
      },
    });
  }

  createProfileForm(): void {
    this.profileForm = this.formBuilder.group({
      nickname: [this.userProfile?.nickname || '', []],
      nombre: [
        this.userProfile?.nombre,
        [
          Validators.required,
          Validators.minLength(4),
          Validators.pattern('.*[a-zA-Z].*'),
        ],
      ],
      email: [this.userProfile?.email, [Validators.required, Validators.email]],
    });
  }
  guardarCambios(): void {
    if (this.profileForm.valid) {
      const { nombre, email } = this.profileForm.value;

      this.usersService
        .updateProfile({
          nombreCompleto: nombre,
          email: email,
          nickname: this.userProfile.nickname,
        })
        .subscribe({
          next: (response) => {
            console.log('Cambios guardados correctamente:', response);
            this.saveMessage = 'Cambios guardados correctamente';
            this.obtenerPerfilUsuario();
          },
          error: (error) => {
            console.error('Error al guardar cambios:', error);
            this.saveMessage = 'Error al guardar cambios';
          },
        });
    }
  }

  eliminarUsuario(): void {
    const confirmacion = confirm(
      '¿Estás seguro de que quieres eliminar este usuario?'
    );
    if (confirmacion) {
      this.usersService.eliminarUsuario().subscribe({
        next: (response) => {
          const Correcto = confirm('Usuario eliminado correctamente');
          console.log('Usuario eliminado correctamente:', response);
          this.usersService.logout();
        },
        error: (error) => {
          console.error('Error al eliminar usuario:', error);
        },
      });
    }
  }

  isInvalidAndTouched(controlName: string): boolean {
    const control = this.profileForm.get(controlName);
    return control!.invalid && (control!.touched || control!.dirty);
  }

  isNullOrWhiteSpace(value: string | null): boolean {
    return value === null || value.trim() === '';
  }
}
