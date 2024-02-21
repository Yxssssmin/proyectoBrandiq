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
export class LoginComponent{

  loginForm!: FormGroup;
  errorMessage: string = '';

  constructor(private usersService: UsersService, private router: Router, private formBuilder: FormBuilder) {
    this.createLoginForm();
  }
  
createLoginForm() {
  
  this.loginForm = this.formBuilder.group(
    {
    nickname: [
      '', 
      [
      Validators.required, 
      Validators.minLength(5),
      Validators.pattern('.*[a-zA-Z].*'),
      ]
    ],
    password: [
      '', 
      [
        Validators.required,
        Validators.minLength(8)
      ],
    ],
  })
}

  login() {
    if (this.loginForm.valid) {
    const user = {
      nickname: this.loginForm.value.nickname,
      password: this.loginForm.value.password,
    };

    // Utiliza el servicio para realizar la solicitud HTTP y manejar la lógica
    this.usersService.login(user).subscribe({
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
  isInvalidAndTouched(controlName: string): boolean {
    const control = this.loginForm.get(controlName);
    return control!.invalid && (control!.touched || control!.dirty);
  }

  isNullOrWhiteSpace(value: string | null): boolean {
    return value === null || value.trim() === '';
  }

}
