// users.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, map, tap } from 'rxjs';
import { User } from '../interfaces/user';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private isLoggedInSubject: BehaviorSubject<boolean> =
    new BehaviorSubject<boolean>(false);
  isLoggedIn$: Observable<boolean> = this.isLoggedInSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  checkLoggedInStatus(): void {
    const storedToken = localStorage.getItem('authToken');
    this.isLoggedInSubject.next(!!storedToken);
  }

  // Nuevo método para verificar la expiración del token
  checkTokenExpiration(): void {
    const authToken = localStorage.getItem('authToken');

    if (authToken) {
      const decodedToken: { exp: number } = jwtDecode(authToken);

      // Verificar si el token ha expirado comparando con la fecha actual
      const tokenExpired = decodedToken.exp < Date.now() / 1000;

      if (tokenExpired) {
        console.log('El token ha expirado. Cerrando sesión.');
        this.logout(); // Llama al método de cierre de sesión
      }
    }
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
        this.checkTokenExpiration();
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

  obtenerEstadisticas(): Observable<any> {
    const token = localStorage.getItem('authToken');
    const nicknameUser = localStorage.getItem('userNickname');

    const headers = { Authorization: `Bearer ${token}` };

    return this.http.get(
      environment.URL_SPRING + 'api/v1/' + nicknameUser + '/estadisticas',
      { headers }
    );
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
  getProfile(): Observable<any> {
    const token = localStorage.getItem('authToken');
    const nicknameUser = localStorage.getItem('userNickname');
    console.log(nicknameUser);

    const headers = { Authorization: `Bearer ${token}` };

    return this.http.get(
      environment.URL_SPRING + 'api/v1/' + nicknameUser + '/profile',
      { headers }
    );
  }
  updateProfile(profileData: {
    nombreCompleto: string;
    email: string;
    nickname: string;
  }): Observable<any> {
    const authToken = localStorage.getItem('authToken');
    const nicknameUsuario = localStorage.getItem('userNickname');

    console.log({ profileData });

    const headers = { Authorization: `Bearer ${authToken}` };

    return this.http.put(
      environment.URL_SPRING + `auth/update/${nicknameUsuario}`,
      {
        nombre: profileData.nombreCompleto,
        email: profileData.email,
        nickname: nicknameUsuario,
      },
      { headers }
    );
  }

  eliminarUsuario(): Observable<any> {
    const authToken = localStorage.getItem('authToken');
    const nicknameUsuario = localStorage.getItem('userNickname');
    const headers = { Authorization: `Bearer ${authToken}` };

    return this.http.delete(
      environment.URL_SPRING + `auth/delete/${nicknameUsuario}`,
      { headers }
    );
  }
}
