import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TableroServiceService } from '../../services/tablero-service.service';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal-empezar-partida',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule, ReactiveFormsModule],
  templateUrl: './modal-empezar-partida.component.html',
  styleUrl: './modal-empezar-partida.component.css',
})
export class ModalEmpezarPartidaComponent {
  @Output() closeModalEvent = new EventEmitter();
  modalPartidaForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private tableroService: TableroServiceService,
    private router: Router
  ) {
    this.createForm();
  }

  createForm() {
    this.modalPartidaForm = this.formBuilder.group({
      tituloPartida: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(10),
        ],
      ],
    });
  }

  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }

  closeModal() {
    this.closeModalEvent.emit();
  }

  crearPartida() {
    if (this.modalPartidaForm.valid) {
      const creadorPartida = this.getNicknameStorage();

      if (creadorPartida !== null) {
        this.tableroService
          .crearPartida(
            creadorPartida,
            this.modalPartidaForm.value.tituloPartida
          )
          .subscribe({
            next: (data) => {
              console.log('Respuesta del servidor:', data);
              this.closeModal();
              this.router.navigate(['/salaEspera']);
            },
            error: (error) => {
              console.error('Error en la solicitud:', error);
            },
          });
      } else {
        console.error(
          'El creador de la partida es null. Manejar este caso según tus necesidades.'
        );
      }
    }
  }

  isInvalidAndTouched(controlName: string): boolean {
    const control = this.modalPartidaForm.get(controlName);
    return control!.invalid && (control!.touched || control!.dirty);
  }

  isNullOrWhiteSpace(value: string | null): boolean {
    return value === null || value.trim() === '';
  }
}
