import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-estadisticas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './estadisticas.component.html',
  styleUrl: './estadisticas.component.css'
})
export class EstadisticasComponent implements OnInit {

  nickname: string = "";
  victorias: number = 0;
  derrotas: number = 0;
  puntos_totales: number = 0;
  partidas_jugadas: number = 0;

  constructor(private http: HttpClient, private usersService: UsersService) {}

  ngOnInit(): void {
      this.obtenerEstadisticas();
  }

  obtenerEstadisticas(): void {

    const jugadores = {
      nickname: this.nickname,
      victorias: this.victorias,
      derrotas:  this.derrotas,
      puntos_totales: this.puntos_totales,
      partidas_jugadas: this.partidas_jugadas,
    };

    this.usersService.obtenerEstadisticas().subscribe({
      next: (data) => {
        console.log('respuesta del servidor: ', data)
      //ordenar jugadores por victorias de forma descendente
      // this.jugadores = data.sort((a: { victorias: number; },b: { victorias: number; }) => b.victorias - a.victorias);
    }, 
    error: (error) => {
      console.error('Error al obtener datos de jugadores: ', error);
    }
    });
  }

}
