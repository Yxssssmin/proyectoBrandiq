import { Component, Input, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../../../interfaces/user';
import { UsersService } from '../../../services/users.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  registerForm!: FormGroup;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private usersService: UsersService,
    private router: Router
  ) {
    this.createRegisterForm();
  }

  createRegisterForm() {
    this.registerForm = this.formBuilder.group(
      {
        nickname: [
          '',
          [
            Validators.required,
            Validators.minLength(5),
            Validators.pattern('.*[a-zA-Z].*'),
          ],
        ],
        nombre: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.pattern('.*[a-zA-Z].*'),
          ],
        ],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', [Validators.required, Validators.minLength(8)]],
      },
      { validators: this.passwordMatchValidator.bind(this) }
    );

    // Llama a la función checkPasswordMatch después de que el formulario esté creado
    this.checkPasswordMatch();
  }

  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password === confirmPassword ? null : { passwordMismatch: true };
  }
  register() {
    if (this.registerForm.valid) {
      const user = {
        nickname: this.registerForm.value.nickname,
        nombre: this.registerForm.value.nombre,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password,
      };

      this.usersService.register(user).subscribe({
        next: (data) => {
          console.log('Respuesta del servidor', data);
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.error('Error en la solicitud:', error);
          this.errorMessage = 'Usuario ya existe. Inicia sesion';
        },
      });
    }
  }

  checkPasswordMatch() {
    const passwordControl = this.registerForm.get('password');
    const confirmPasswordControl = this.registerForm.get('confirmPassword');

    if (passwordControl && confirmPasswordControl) {
      const password = passwordControl.value;
      const confirmPassword = confirmPasswordControl.value;

      if (password === confirmPassword) {
        confirmPasswordControl.setErrors(null);
      } else {
        confirmPasswordControl.setErrors({ passwordMismatch: true });
      }
    }
  }

  isInvalidAndTouched(controlName: string): boolean {
    const control = this.registerForm.get(controlName);
    return control!.invalid && (control!.touched || control!.dirty);
  }

  isNullOrWhiteSpace(value: string | null): boolean {
    return value === null || value.trim() === '';
  }
}
