import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription, interval } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { TableroServiceService } from '../../services/tablero-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sala-de-espera',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sala-de-espera.component.html',
  styleUrls: ['./sala-de-espera.component.css'],
})
export class SalaDeEsperaComponent implements OnInit, OnDestroy {
  userList: any = {};
  pollSubscription: Subscription | null = null;
  roomId: string | null = null;
  jugadoresSala: any = {};
  usuarioActual: string | null = null;

  constructor(
    private tableroService: TableroServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.usuarioActual = localStorage.getItem('userNickname');
    this.route.params.subscribe((params) => {
      this.roomId = params['id'];
      if (this.roomId) {
        this.obtenerUsuariosSala(this.roomId);
      }
    });
  }
  ngOnDestroy(): void {
    if (this.pollSubscription) {
      this.pollSubscription.unsubscribe();
    }
    this.tableroService.stopPolling(); // Detén el polling en el servicio
  }

  obtenerUsuariosSala(roomId: string): void {
    this.tableroService
      .obtenerUsuarioSala(roomId)
      .subscribe((userList: any) => {
        this.userList = userList;
        this.jugadoresSala = this.userList.jugadoresSalaInfoNombres;
      });
  }

  empezarPartida(): void {
    const creador = this.userList.jugadoresSalaInfoNombres[0].id_jugador;

    // if (this.usuarioActual === creador) {
    // Solo el creador tiene permiso para empezar la partida
    this.router.navigate(['/tablero', this.roomId]);
    // } else {
    //   console.error('No tienes permiso para empezar la partida.');
    // }
  }
}
