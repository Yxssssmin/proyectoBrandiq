import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RankingService } from '../../services/ranking.service';

@Component({
  selector: 'app-ranking',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ranking.component.html',
  styleUrl: './ranking.component.css',
})
export class RankingComponent implements OnInit {
  jugadores: any[] = [];

  constructor(private rankingService: RankingService) {}

  ngOnInit(): void {
    this.obtenerJugadores();
  }

  obtenerJugadores() {
    this.rankingService.getJugadores().subscribe(
      (data: any) => {
        this.jugadores = data; // Asigna los datos obtenidos al arreglo de jugadores
      },
      (error) => {
        console.error('Error al obtener el ranking:', error);
      }
    );
  }
}
