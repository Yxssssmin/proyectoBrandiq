import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-dice',
  standalone: true,
  imports: [],
  templateUrl: './dice.component.html',
  styleUrls: ['./dice.component.css'],
})
export class DiceComponent {
  @Output() diceRolled = new EventEmitter<number>();

  cubeTransform = 'translateY(400px) rotateX(0deg) rotateY(0deg) rotateZ(0deg)';
  time = 2;

  rollDice() {
    this.cubeTransform =
      'translateY(400px) rotateX(0deg) rotateY(0deg) rotateZ(0deg)';

    // Aumentar el ángulo de rotación más veces antes de llegar al valor final
    let totalRotations = 10; // Puedes ajustar este valor según tus necesidades
    let currentRotation = 0;

    const rotateInterval = setInterval(() => {
      // Incrementar el ángulo de rotación
      currentRotation += 360;

      // Aplicar la nueva transformación con el ángulo actualizado
      this.cubeTransform = `translateY(400px) rotateX(0deg) rotateY(${currentRotation}deg) rotateZ(0deg)`;

      // Verificar si se han completado todas las rotaciones
      if (currentRotation >= totalRotations * 360) {
        clearInterval(rotateInterval);

        // Después de las rotaciones, realizar la lógica original
        const randomValue = this.getRandomNumber(1, 6);
        const finalRotation = this.getRotationForNumber(randomValue);
        this.cubeTransform = `translateY(400px) ${finalRotation}`;
        console.log(randomValue);
        this.diceRolled.emit(randomValue);
      }
    }, 100);
  }

  private getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  private getRotationForNumber(number: number): string {
    const rotations: { [key: number]: string } = {
      1: 'rotateX(0deg)',
      2: 'rotateX(90deg)',
      3: 'rotateY(90deg)',
      4: 'rotateY(-90deg)',
      5: 'rotateX(-90deg)',
      6: 'rotateX(180deg)',
    };

    return rotations[number] || 'rotateZ(0deg)';
  }
}
