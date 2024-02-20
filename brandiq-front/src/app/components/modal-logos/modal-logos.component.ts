import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-modal-logos',
  standalone: true,
  imports: [],
  templateUrl: './modal-logos.component.html',
  styleUrl: './modal-logos.component.css'
})
export class ModalLogosComponent {

  @Input() logoSrc: string = '';
  @Input() correctName: string = '';

  enteredName: string = '';

  validateName() {
    return this.enteredName.toLowerCase() === this.correctName.toLowerCase();
  }
}
