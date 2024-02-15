import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalEmpezarPartidaComponent } from './modal-empezar-partida.component';

describe('ModalEmpezarPartidaComponent', () => {
  let component: ModalEmpezarPartidaComponent;
  let fixture: ComponentFixture<ModalEmpezarPartidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalEmpezarPartidaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalEmpezarPartidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
