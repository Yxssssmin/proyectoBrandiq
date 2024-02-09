import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../../../interfaces/user';
import { UsersService } from '../../../services/users.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  nickname: string = '';
  nombre: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';

  constructor(private usersService: UsersService, private router: Router) {}

  register() {

      // Verifica que las contraseñas coincidan antes de enviar la solicitud
      if(this.password !== this.confirmPassword) {
        this.errorMessage = 'Las contraseñas no coinciden'
        return;
      }

      const user = { 
        nickname: this.nickname, 
        nombre: this.nombre, 
        email: this.email, 
        password: this.password, 
        // confirmPassword: this.confirmPassword 
      };

          // Utiliza el servicio para realizar la solicitud HTTP y manejar la lógica
          this.usersService.register(user).subscribe({
            next: (data) => {
              console.log('Respuesta del servidor', data);
              this.router.navigate(['/login']);
            },
            error: (error) => {
              console.error('Error en la solicitud:', error);
              this.errorMessage = 'Error en el registro. Verifica tus datos.';
            },
          });
  }
}
