import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { UtilService } from 'app/service/util.service';
import { CookieService } from 'ngx-cookie-service';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';

@Injectable()
export class DashboardService {

  private likedTweets: any[] = [];
  private recomandedTweets: any[] = [];

  constructor(private tweetRepository: TweetRepositoryService,
              private utils: UtilService) { }

  public initTweetsArrays(){
    this.tweetRepository.getLikedTweets().subscribe(response => {
      this.likedTweets = response.payload;
    }, err => {
      console.log(err._body.message);
    });

    this.tweetRepository.getRecomandedTweets().subscribe(response => {
      this.recomandedTweets = response.payload;
    }, err => {
      console.log(err._body.message);
    })
  }

}
