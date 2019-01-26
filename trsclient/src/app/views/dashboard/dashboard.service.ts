import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { UtilService } from 'app/service/util.service';
import { CookieService } from 'ngx-cookie-service';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { TweetDTO } from 'app/model/TweetDTO';

@Injectable()
export class DashboardService {

  private likedTweets: TweetDTO[] = [];
  private recommandedTweets: TweetDTO[] = [];

  constructor(private tweetRepository: TweetRepositoryService,
              private utils: UtilService) { }

  public initTweetsArrays(){
    this.tweetRepository.getLikedTweets().subscribe(response => {
      this.likedTweets = response;
    }, err => {
      console.log(err.payload);
    });

    this.tweetRepository.getRecommandedTweets().subscribe(response => {
      this.recommandedTweets = response;
    }, err => {
      console.log(err.payload);
    })
  }

    /**
     * Getter $likedTweets
     * @return {any[] }
     */
	public get $likedTweets(): any[]  {
		return this.likedTweets;
	}

    /**
     * Getter $recommandedTweets
     * @return {any[] }
     */
	public get $recommandedTweets(): any[]  {
		return this.recommandedTweets;
	}

    /**
     * Setter $likedTweets
     * @param {any[] } value
     */
	public set $likedTweets(value: any[] ) {
		this.likedTweets = value;
	}

    /**
     * Setter $recommandedTweets
     * @param {any[] } value
     */
	public set $recommandedTweets(value: any[] ) {
		this.recommandedTweets = value;
	}

}
