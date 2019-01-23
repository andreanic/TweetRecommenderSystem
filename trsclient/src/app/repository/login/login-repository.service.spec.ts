import { TestBed, inject } from '@angular/core/testing';

import { LoginRepositoryService } from './login-repository.service';

describe('LoginRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoginRepositoryService]
    });
  });

  it('should be created', inject([LoginRepositoryService], (service: LoginRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
