import { TestBed, inject } from '@angular/core/testing';

import { LuceneRepositoryService } from './lucene-repository.service';

describe('LuceneRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LuceneRepositoryService]
    });
  });

  it('should be created', inject([LuceneRepositoryService], (service: LuceneRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
