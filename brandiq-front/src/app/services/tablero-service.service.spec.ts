import { TestBed } from '@angular/core/testing';

import { TableroServiceService } from './tablero-service.service';

describe('TableroServiceService', () => {
  let service: TableroServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TableroServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
