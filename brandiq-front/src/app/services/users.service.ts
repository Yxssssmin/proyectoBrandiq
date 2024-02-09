// users.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, map, tap } from 'rxjs';
import { User } from '../interfaces/user';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private isLoggedInSubject: BehaviorSubject<boolean> =
    new BehaviorSubject<boolean>(false);
  isLoggedIn$: Observable<boolean> = this.isLoggedInSubject.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router // private jwtHelper: JwtHelperService
  ) {}

  // Método para verificar si el usuario está logueado al iniciar la aplicación
  checkLoggedInStatus(): void {
    // Lógica para verificar el estado de autenticación (puedes realizar una solicitud HTTP aquí si es necesario)
    // Por ejemplo, puedes hacer una solicitud al servidor para comprobar si hay una sesión activa.

    // Ejemplo de lógica simple (modifica según tus necesidades)
    const storedToken = localStorage.getItem('authToken');
    this.isLoggedInSubject.next(!!storedToken);
  }

  login(user: { nickname: string; password: string }): Observable<any> {
    return this.http.post(environment.URL_SPRING + 'auth/login', user).pipe(
      tap((response: any) => {
        // Suponiendo que el servidor devuelve un token en la propiedad 'token'
        const authToken = response.token;
        const nicknameUsuario = response.nickname;
        // Guardar el token en el localStorage
        localStorage.setItem('authToken', authToken);
        localStorage.setItem('userNickname', nicknameUsuario);
        // Actualizar el estado de autenticación
        this.isLoggedInSubject.next(true);
      })
    );
  }

  register(user: {
    nickname: string;
    nombre: string;
    email: string;
    password: string;
    // confirmPassword: string,
  }): Observable<any> {
    return this.http.post(environment.URL_SPRING + 'auth/nuevo', user);
  }

  obtenerEstadisticas(
  ): Observable<any> {

    const token = localStorage.getItem('authToken');
    const nicknameUser = localStorage.getItem('userNickname');

    const headers = { Authorization: `Bearer ${token}` };

    return this.http.get(environment.URL_SPRING + '/api/v1/'+ nicknameUser + '/estadisticas', { headers, },);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }

  isLogged() {
    const token = localStorage.getItem('authToken');
    if (token) {
      // if (!this.jwtHelper.isTokenExpired(token)) {
      //   return true;
      // } else {
      //   return false;
      // }
      return true;
    } else {
      return false;
    }
  }
}
