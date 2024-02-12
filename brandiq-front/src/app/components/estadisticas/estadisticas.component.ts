import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-estadisticas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './estadisticas.component.html',
  styleUrl: './estadisticas.component.css',
})
export class EstadisticasComponent implements OnInit {
  nickname: string = '';
  victorias: number = 0;
  derrotas: number = 0;
  puntosTotales: number = 0;
  partidas_jugadas: number = 0;

  constructor(private http: HttpClient, private usersService: UsersService) {}

  ngOnInit(): void {
    this.obtenerEstadisticas();
  }

  obtenerEstadisticas(): void {
    this.usersService.obtenerEstadisticas().subscribe({
      next: (data) => {
        console.log('respuesta del servidor: ', data);
        this.nickname = data.nickname;
        // Asignar otros valores si es necesario
        this.victorias = data.victorias;
        this.derrotas = data.derrotas;
        this.puntosTotales = data.puntosTotales;
        this.partidas_jugadas = data.partidas_jugadas;
        //ordenar jugadores por victorias de forma descendente
        // this.jugadores = data.sort((a: { victorias: number; },b: { victorias: number; }) => b.victorias - a.victorias);
      },
      error: (error) => {
        console.error('Error al obtener datos de jugadores: ', error);
      },
    });
  }

  getNicknameStorage(): string | null {
    return localStorage.getItem('userNickname');
  }
}
