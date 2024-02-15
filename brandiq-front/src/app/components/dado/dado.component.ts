import { CommonModule } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-dado',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dado.component.html',
  styleUrl: './dado.component.css',
})
export class DadoComponent {
  @ViewChild('dado') dadoElement!: ElementRef;
  @ViewChild('boton') botonElement!: ElementRef;
  @ViewChild('resultado') resultadoElement?: ElementRef;

  isGirando = false;

  ngAfterViewInit() {
    // Verificar si resultadoElement está presente antes de acceder a su propiedad
    if (this.resultadoElement) {
      this.resultadoElement.nativeElement.innerHTML = '';
    }
  }

  girar() {
    this.botonElement.nativeElement.setAttribute('disabled', 'disabled');

    // Verificar si resultadoElement está presente antes de acceder a su propiedad
    if (this.resultadoElement) {
      this.resultadoElement.nativeElement.innerHTML = 'Girando...';
    }

    const dado = this.dadoElement.nativeElement;
    dado.classList.add('girando');
    const rotacionAleatoria = Math.floor(Math.random() * 360) * 50;

    dado.style.transition = `transform 2s ease-in-out`;
    dado.style.transform = `rotateX(0deg) rotateY(180deg) rotateZ(${rotacionAleatoria}deg)`;
    setTimeout(() => this.tirar(), 1000);
  }

  tirar() {
    const dado = this.dadoElement.nativeElement;
    const boton = this.botonElement.nativeElement;

    // Verificar si resultadoElement está presente antes de acceder a su propiedad
    if (this.resultadoElement) {
      this.resultadoElement.nativeElement.innerHTML = '';
    }

    dado.classList.remove('girando');
    boton.removeAttribute('disabled');

    // Calcular el número
    const numero = Math.floor(Math.random() * 6) + 1;

    dado.style.transition = 'transform 2s ease-in-out';
    switch (numero) {
      case 1:
        dado.style.transform = 'rotateX(0deg) rotateY(0deg) rotateZ(0deg)';
        dado.style.backgroundImage = 'url(../../../assets/dado/dado1.jpg)';
        break;
      case 2:
        dado.style.transform = 'rotateX(0deg) rotateY(180deg) rotateZ(0deg)';
        dado.style.backgroundImage = 'url(../../../assets/dado/dado2.jpg)';
        break;
      case 3:
        dado.style.transform = 'rotateX(0deg) rotateY(90deg) rotateZ(0deg)';
        dado.style.backgroundImage = 'url(../../../assets/dado/dado3.jpg)';
        break;
      case 4:
        dado.style.transform = 'rotateX(0deg) rotateY(270deg) rotateZ(0deg)';
        dado.style.backgroundImage = 'url(../../../assets/dado/dado4.jpg)';
        break;
      case 5:
        dado.style.transform = 'rotateX(-90deg) rotateY(0deg) rotateZ(0deg)';
        dado.style.backgroundImage = 'url(../../../assets/dado/dado5.jpg)';
        break;
      case 6:
        dado.style.transform = 'rotateX(90deg) rotateY(0deg) rotateZ(0deg)';
        dado.style.backgroundImage = 'url(../../../assets/dado/dado6.jpg)';
        break;
    }

    if (this.resultadoElement) {
      const resultadoHtml = this.resultadoElement.nativeElement;

      setTimeout(() => {
        // Verificar nuevamente antes de realizar la asignación
        if (resultadoHtml) {
          resultadoHtml.innerHTML = numero.toString();
        }
      }, 1000);
    }
  }
}
