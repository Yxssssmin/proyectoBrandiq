import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalUnirsePartidaComponent } from './modal-unirse-partida.component';

describe('ModalUnirsePartidaComponent', () => {
  let component: ModalUnirsePartidaComponent;
  let fixture: ComponentFixture<ModalUnirsePartidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalUnirsePartidaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalUnirsePartidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
