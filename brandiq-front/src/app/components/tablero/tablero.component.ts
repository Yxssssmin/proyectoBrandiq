// tablero.component.ts
import { CommonModule } from '@angular/common';
import { Component, ViewChild, ChangeDetectorRef } from '@angular/core';
import { DadoComponent } from '../dado/dado.component';
import { PlayerListComponent } from '../player-list/player-list.component';
import { TableroServiceService } from '../../services/tablero-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalResponderPreguntaComponent } from '../modal-responder-pregunta/modal-responder-pregunta.component';

@Component({
  selector: 'app-tablero',
  standalone: true,
  imports: [
    CommonModule,
    DadoComponent,
    PlayerListComponent,
    ModalResponderPreguntaComponent,
  ],
  templateUrl: './tablero.component.html',
  styleUrl: './tablero.component.css',
})
export class TableroComponent {
  @ViewChild(DadoComponent) dadoComponent!: DadoComponent;

  userList: any = {};
  rows = 8;
  cols = 8;
  board: any[][] = [];
  colors: string[] = ['red', 'orange', 'green'];
  jugadoresSala: any = [];
  nicknameactivo = this.getNicknameStorage();
  mostrarModalResponderPregunta: boolean = false;
  nombreImagen: string | null = null;
  siguienteTurno: string | null = null;

  estilosSiFalse(): any {
    return {
      position: 'absolute',
      top: '40%',
      left: '50%',
      transform: 'translateX(-50%)',
    };
  }

  estilosSiTrue(): any {
    return {
      'z-index': 0,
      display: 'none',
      position: 'absolute',
      top: '40%',
      left: '50%',
      transform: 'translateX(-50%)',
    };
  }

  roomId: string | null = null;

  constructor(
    private tableroService: TableroServiceService,
    private route: ActivatedRoute
  ) {}

  ngAfterViewInit() {
    this.initializeBoard();
    this.route.params.subscribe((params) => {
      this.roomId = params['id'];
      if (this.roomId) {
        this.obtenerUsuariosSala(this.roomId);
      }
    });
  }

  // ngOnInit() {
  //   this.initializeBoard();
  //   this.route.params.subscribe((params) => {
  //     this.roomId = params['id'];
  //     if (this.roomId) {
  //       this.obtenerUsuariosSala(this.roomId);
  //     }
  //   });
  // }

  getShow(): boolean {
    // Verificar si dadoComponent está definido antes de acceder a su método
    return this.dadoComponent?.getShowModalEmpezar() ?? false;
  }

  getImagen() {
    // Verificar si dadoComponent está definido antes de acceder a su método
    this.nombreImagen = this.dadoComponent?.getnombreImagen() ?? '';
  }
  initializeBoard() {
    for (let i = 0; i < this.rows; i++) {
      this.board[i] = [];
      for (let j = 0; j < this.cols; j++) {
        this.board[i][j] = this.createCell(i, j);
      }
    }
  }

  // En tu componente TableroComponent
  esMiTurno(): boolean {
    const jugadorActual = this.getNicknameStorage();

    const indexJugadorActual = this.jugadoresSala.findIndex(
      (jugador: any) => jugador.id_jugador === jugadorActual
    );

    // Verificar si el jugador actual está en la lista y es su turno
    const turnoDelJugador = this.jugadoresSala.find(
      (jugador: any) =>
        jugador.id_jugador === jugadorActual && jugador.turno === true
    );

    if (indexJugadorActual !== -1) {
      // Calcular el índice del próximo jugador
      const indexProximoJugador =
        (indexJugadorActual + 1) % this.jugadoresSala.length;

      // Asignar el nickname del próximo jugador a la variable siguienteTurno
      this.siguienteTurno = this.jugadoresSala[indexProximoJugador].id_jugador;
      if (this.siguienteTurno !== null) {
        this.tableroService.addSiguienteJugador(this.siguienteTurno);
      }
      // Devolver true si es el turno del jugador actual
      return turnoDelJugador !== undefined;
    } else {
      // Si no se encuentra el jugador actual en la lista, asignar null a la variable siguienteTurno
      this.siguienteTurno = null;
      return false;
    }
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
        this.jugadoresSala = Array.isArray(userList.jugadoresSalaInfoNombres)
          ? userList.jugadoresSalaInfoNombres
          : [];
      });
  }

  movimientos: number[][] = [
    [0, 0],
    [1, 0],
    [2, 0],
    [3, 0],
    [4, 0],
    [5, 0],
    [6, 0],
    [7, 0],
    [7, 1],
    [7, 2],
    [7, 3],
    [7, 4],
    [7, 5],
    [7, 6],
    [7, 7],
    [6, 7],
    [5, 7],
    [4, 7],
    [3, 7],
    [2, 7],
    [1, 7],
    [0, 7],
    [0, 6],
    [0, 5],
    [0, 4],
    [0, 3],
    [0, 2],
    [0, 1],
  ];

  obtenerPosicionActual(jugador: any): number {
    const posicionX = jugador.posicionX;
    const posicionY = jugador.posicionY;

    // Buscar la posición en el array movimientos
    for (let i = 0; i < this.movimientos.length; i++) {
      const [x, y] = this.movimientos[i];
      if (x === posicionX && y === posicionY) {
        return i;
      }
    }

    return -1; // Devuelve -1 si la posición no se encuentra
  }
}
