import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../../services/users.service';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit{
  // nickname: string = '';
  // password: string = '';
  loginForm!: FormGroup;
  errorMessage: string = '';

  constructor(private usersService: UsersService, private router: Router, private formBuilder: FormBuilder) {
  }
  
  ngOnInit(): void {
    this.loginForm = new FormGroup({
      nickname: new FormControl('', [
        Validators.required, 
        Validators.minLength(5)
      ]),
      password: new FormControl('', Validators.required),
    })
      
  }

  login() {
    const user = this.loginForm.value;

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
