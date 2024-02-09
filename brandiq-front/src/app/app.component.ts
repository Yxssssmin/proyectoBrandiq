import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { UsersService } from './services/users.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    HeaderComponent,
    MainPageComponent,
    FooterComponent,
    RouterLink,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'brandiq';
  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    // Verificar el estado de autenticación al iniciar la aplicación
    this.userService.checkLoggedInStatus();
    this.userService.isLoggedIn$.subscribe((isLoggedIn) => {
      console.log('Estado de autenticación actualizado:', isLoggedIn);
    });
  }
}
