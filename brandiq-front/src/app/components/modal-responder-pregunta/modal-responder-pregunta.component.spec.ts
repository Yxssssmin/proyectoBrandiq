import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalResponderPreguntaComponent } from './modal-responder-pregunta.component';

describe('ModalResponderPreguntaComponent', () => {
  let component: ModalResponderPreguntaComponent;
  let fixture: ComponentFixture<ModalResponderPreguntaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalResponderPreguntaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalResponderPreguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
