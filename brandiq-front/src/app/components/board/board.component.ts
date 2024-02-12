import { Component } from '@angular/core';
import { PlayerListComponent } from '../player-list/player-list.component';
import { DiceComponent } from '../dice/dice.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-board',
  standalone: true,
  imports: [PlayerListComponent,DiceComponent, CommonModule],
  templateUrl: './board.component.html',
  styleUrl: './board.component.css'
})
export class BoardComponent {
  
  diceValue: number = 0;

  onDiceRolled(value: number){
    this.diceValue=value;
    console.log("Valor del dado:", this.diceValue);  
  }

  

}
