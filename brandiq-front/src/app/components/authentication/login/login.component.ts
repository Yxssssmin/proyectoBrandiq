import { Component } from '@angular/core';
import { UsersService } from '../../../services/users.service';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  nickname: string = '';
  password: string = '';
  errorMessage: string = '';
  constructor(private usersService: UsersService, private router: Router) {}

  login() {
    const user = { nickname: this.nickname, password: this.password };

    // Utiliza el servicio para realizar la solicitud HTTP y manejar la lógica
    let logged = this.usersService.login(user).subscribe({
      next: (data) => {
        console.log('Respuesta del servidor:', data);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Error en la solicitud:', error);
        this.errorMessage =
          'Error en el inicio de sesión. Verifica tus credenciales.';
      },
    });
  }
}
