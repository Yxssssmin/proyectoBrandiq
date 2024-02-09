import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RankingService {
  constructor(private http: HttpClient) {}

  getJugadores(): Observable<any> {
    return this.http.get(environment.URL_SPRING + 'api/v1/ranking');
  }
}
