import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    const token = localStorage.getItem('authToken');

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });

    const body = {
      id_jugador: creador,
      titulo: titulo,
      finalizada: '1',
    };

    return this.http.post(
      environment.URL_SPRING + 'api/v1/crear-tablero',
      body,
      { headers: headers }
    );
  }

  listarTableros(): Observable<any[]> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });

    return this.http.get<any[]>(environment.URL_SPRING + 'api/v1/tableros', {
      headers: headers,
    });
  }
}
