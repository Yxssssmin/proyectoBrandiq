// tablero.component.ts
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { DadoComponent } from '../dado/dado.component';
import { PlayerListComponent } from '../player-list/player-list.component';
import { TableroServiceService } from '../../services/tablero-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tablero',
  standalone: true,
  imports: [CommonModule, DadoComponent, PlayerListComponent],
  templateUrl: './tablero.component.html',
  styleUrl: './tablero.component.css',
})
export class TableroComponent {
  userList: any = {};
  rows = 8;
  cols = 8;
  board: any[][] = [];
  colors: string[] = ['red', 'orange', 'green'];
  jugadoresSala: any = {};
  nicknameactivo = this.getNicknameStorage();

  roomId: string | null = null;

  constructor(
    private tableroService: TableroServiceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.initializeBoard();
    this.route.params.subscribe((params) => {
      this.roomId = params['id'];
      if (this.roomId) {
        this.obtenerUsuariosSala(this.roomId);
      }
    });
  }

  initializeBoard() {
    for (let i = 0; i < this.rows; i++) {
      this.board[i] = [];
      for (let j = 0; j < this.cols; j++) {
        this.board[i][j] = this.createCell(i, j);
      }
    }
  }

  esMiTurno(): boolean {
    const jugadorActual = this.getNicknameStorage();

    // Verificar si el jugador actual está en la lista y es su turno
    const turnoDelJugador = this.jugadoresSala.find(
      (jugador: any) =>
        jugador.id_jugador === jugadorActual && jugador.turno === true
    );

    return turnoDelJugador !== undefined;
  }

  createCell(row: number, col: number): any {
    if (this.isBorderCell({ row, col })) {
      const totalRows = this.rows;
      const totalCols = this.cols;

      // Calcular el número para las celdas del borde
      let number: number | string = 0;

      if (col === 0) {
        // Izquierda
        if (row === 0) {
          // Esquina superior izquierda
          number = 'Start';
        } else {
          number = row;
        }
      } else if (row === totalRows - 1) {
        // Abajo
        number = totalRows + col - 1;
      } else if (col === totalCols - 1) {
        // Derecha
        number = 2 * totalRows + totalCols - row - 2;
      } else if (row === 0) {
        // Arriba
        number = 2 * (totalRows + totalCols) - col - 2;
      }

      return {
        row,
        col,
        color: this.getBorderColor(row, col),
        number,
      };
    } else {
      return null;
    }
  }
  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }
  getBorderColor(row: number, col: number): string {
    // Alternar entre los colores definidos en el array
    return this.colors[(row + col) % this.colors.length];
  }

  isBorderCell(cell: any): boolean {
    return (
      cell &&
      (cell.row === 0 ||
        cell.col === 0 ||
        cell.row === this.rows - 1 ||
        cell.col === this.cols - 1)
    );
  }

  handleCellClick(row: number, col: number) {
    console.log(`Clicked on cell: ${row}, ${col}`);
  }

  obtenerUsuariosSala(roomId: string): void {
    this.tableroService
      .obtenerUsuarioSala(roomId)
      .subscribe((userList: any) => {
        this.userList = userList;
        this.jugadoresSala = this.userList.jugadoresSalaInfoNombres;
      });
  }
}
