import { TestBed, inject } from '@angular/core/testing';

import { TweetRepositoryService } from './tweet-repository.service';

describe('TweetRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TweetRepositoryService]
    });
  });

  it('should be created', inject([TweetRepositoryService], (service: TweetRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
