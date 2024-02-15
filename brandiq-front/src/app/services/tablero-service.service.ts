import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TableroServiceService {
  constructor(private http: HttpClient, private router: Router) {}

  crearPartida(creador: string, titulo: string): Observable<any> {
    // Realiza la llamada HTTP para crear la partida utilizando el creador y el título
    console.log('Hemos llegado');

    return this.http.post(environment.URL_SPRING + 'api/v1/crear-tablero', {
      creador,
      titulo,
    });
  }
}
