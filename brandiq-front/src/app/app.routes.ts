import { Routes } from '@angular/router';
import { LoginComponent } from './components/authentication/login/login.component';

import { RegisterComponent } from './components/authentication/register/register.component';
import { UserProfileComponent } from './components/authentication/user-profile/user-profile.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { EstadisticasComponent } from './components/estadisticas/estadisticas.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: UserProfileComponent },
  { path: 'ranking', component: RankingComponent },
  { path: 'estadisticas', component: EstadisticasComponent },
  { path: 'home', component: HomeComponent },
];
