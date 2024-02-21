import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {
  Observable,
  Subject,
  Subscription,
  interval,
  startWith,
  switchMap,
  takeUntil,
} from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TableroServiceService {
  jugadoresSalaInfoNombres: any[] = [];

  private pollInterval = 5000; // Intervalo de tiempo en milisegundos (en este caso, 5 segundos)
  private pollSubscription: Subscription | null = null;

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

  obtenerUsuarioSala(roomId: string): Observable<any[]> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });

    // Detenemos la suscripción anterior si existe
    this.stopPolling();

    const destroy$ = new Subject<void>(); // Nuevo observable para detectar onDestroy

    const pollObservable = interval(this.pollInterval).pipe(
      startWith(0),
      switchMap(() => {
        return this.http
          .get<any[]>(
            environment.URL_SPRING + `api/v1/tablero/${roomId}/info`,
            { headers }
          )
          .pipe(takeUntil(destroy$)); // Cancela la solicitud si se destruye el componente
      })
    );

    // Almacenamos la nueva suscripción
    this.pollSubscription = pollObservable.subscribe((users) => {
      this.jugadoresSalaInfoNombres = users || [];
      console.log(
        'Lista de usuarios en la sala:',
        this.jugadoresSalaInfoNombres
      );
    });

    // Devuelve un observable que emite cuando se destruye el componente
    return pollObservable.pipe(takeUntil(destroy$));
  }

  stopPolling(): void {
    // Detiene la suscripción cuando sea necesario
    if (this.pollSubscription) {
      this.pollSubscription.unsubscribe();
      this.pollSubscription = null; // Asignar null después de la cancelación
    }
  }
  unirsePartida(idTablero: number, nombreJugador: string): Observable<any> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });

    const body = {
      id_jugador: nombreJugador,
    };

    return this.http.post(
      `${environment.URL_SPRING}api/v1/unirse-tablero/${idTablero}/${nombreJugador}`,
      body,
      { headers }
    );
  }

  tirarDado(idJugador: string, idTablero: number): Observable<number> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });

    return this.http.put<number>(
      `${environment.URL_SPRING}api/v1/tirardado/${idJugador}/${idTablero}`,
      null,
      { headers }
    );
  }

  getDetallesCasilla(idTablero: number, nombreJugador: string): Observable<any> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });

    return this.http.get<any>(
      `${environment.URL_SPRING}api/v1/mostrarCasillas/${nombreJugador}/${idTablero}`,
      { headers }
    );
  }
  
}
