import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modal-logos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modal-logos.component.html',
  styleUrl: './modal-logos.component.css'
})
export class ModalLogosComponent {


  @Input() imagenDeserializada: any;
  @Input() nombreImagen!: string;

  nombreInput: string = '';

  @Output() nombreValidado: EventEmitter<string> = new EventEmitter<string>();

  validarNombre(): void {

     // Lógica de validación aquí
     const nombreValido: boolean = this.nombreInput.toLowerCase() === this.nombreImagen.toLowerCase();

     this.nombreValidado.emit(nombreValido ? 'true' : 'false');
  }



}
