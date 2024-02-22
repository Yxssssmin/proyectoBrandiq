import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { CommonModule } from '@angular/common';
import { Chart, ChartDataset, ChartOptions, ChartType} from 'chart.js';

import { NgChartsModule} from 'ng2-charts';


@Component({
  selector: 'app-estadisticas',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.css'],
})
export class EstadisticasComponent {
  
  nickname: string = '';
  victorias: number = 0;
  derrotas: number = 0;
  puntosTotales: number = 0;
  partidas_jugadas: number = 0;

  constructor(private http: HttpClient, private usersService: UsersService) {}
  
  ngOnInit(): void {
    this.obtenerEstadisticas();
  }

  public barChartOptions: ChartOptions = {
    scales: {
      y: {
        beginAtZero: true
      }
    },
    responsive: true
  }

  public barChartLabels: string[] = ['Victorias', 'Derrotas', 'Puntos Totales', 'Partidas Jugadas'];
  public barChartType: ChartType = 'bar';
  public barChartLegend: boolean = true;

  public barChartData: any[] = [];
 
  //events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }

  // public randomize(): void {
  //   let data = [
  //     Math.round(Math.random() * 100),
  //     59,
  //     80,
  //     (Math.random() * 100),
  //     56,
  //     (Math.random() * 100),
  //     40];
  //   let clone = JSON.parse(JSON.stringify(this.barChartData));
  //   clone[0].data = data;
  //   this.barChartData = clone;
  // }

  public randomize(): void {
    console.log('Valores antes de la actualización:', this.barChartData[0].data);
    
    this.barChartData[0].data = [this.victorias, this.derrotas, this.puntosTotales, this.partidas_jugadas];
  
    console.log('Valores después de la actualización:', this.barChartData[0].data);
  
    window.location.reload();
    
  }

  obtenerEstadisticas(): void {
    this.usersService.obtenerEstadisticas().subscribe({
      next: (data) => {
        console.log('respuesta del servidor: ', data);
        this.nickname = data.nickname;
        this.victorias = data.victorias;
        this.derrotas = data.derrotas;
        this.puntosTotales = data.puntosTotales;
        this.partidas_jugadas = data.partidas_jugadas;
        
        this.barChartData = [
          { data: [this.victorias, this.derrotas, this.puntosTotales, this.partidas_jugadas], label: 'Estadísticas' },
      
        ];
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
