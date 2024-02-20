import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalLogosComponent } from './modal-logos.component';

describe('ModalLogosComponent', () => {
  let component: ModalLogosComponent;
  let fixture: ComponentFixture<ModalLogosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalLogosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalLogosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
