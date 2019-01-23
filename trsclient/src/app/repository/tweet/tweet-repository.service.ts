import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TweetRepositoryService {

  constructor(private http: Http) { }

  public getCategories(): Observable<any>{
    return this.http.get(`${environment.server.url}/tweet/getCategories`)
                    .map((res: Response) => res.json())
                    .catch((e: any) => Observable.throw(e));
  }

  public getRelevantTweets(): Observable<any>{
    return this.http.get(`${environment.server.url}/tweet/getRelevantTweets`)
                    .map((res: Response) => res.json())
                    .catch((e: any) => Observable.throw(e));
  }

  public getLikedTweets(): Observable<any>{
    return this.http.get(`${environment.server.url}/tweet/getLikedTweets`)
                    .map((res: Response) => res.json())
                    .catch((e: any) => Observable.throw(e));
  }

  public getRecomandedTweets(): Observable<any>{
    return this.http.get(`${environment.server.url}/tweet/getRecomandedTweets`)
                    .map((res: Response) => res.json())
                    .catch((e: any) => Observable.throw(e));
  }

}
