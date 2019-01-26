import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { UtilService } from 'app/service/util.service';
import { TweetDTO } from 'app/model/TweetDTO';

@Injectable()
export class TweetRepositoryService {

  constructor(private http: Http,
              private utils: UtilService) { }


  public retrieveTweets(category: String): Observable<string>{
    return this.http.get(`${environment.server.url}/tweet/retrieve/`+category,this.utils.createHeaderOptions())
                    .map((res: Response) => res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }

  public getCategories(): Observable<String[]>{
    return this.http.get(`${environment.server.url}/tweet/getCategories`,this.utils.createHeaderOptions())
                    .map((res: Response) => res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }

  public getOneTweetByCategory(): Observable<TweetDTO[]>{
    return this.http.get(`${environment.server.url}/tweet/getOneTweetByCategory`,this.utils.createHeaderOptions())
                    .map((res: Response) =>  <TweetDTO[]>res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }

  public getLikedTweets(): Observable<TweetDTO[]>{
    return this.http.get(`${environment.server.url}/user/getLikedTweets`,this.utils.createHeaderOptions())
                    .map((res: Response) => <TweetDTO[]>res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }

  public getRecommandedTweets(): Observable<TweetDTO[]>{
    return this.http.get(`${environment.server.url}/user/getRecommandedTweets`,this.utils.createHeaderOptions())
                    .map((res: Response) => <TweetDTO[]>res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }

  public fullTextSearch(data: any): Observable<TweetDTO[]>{
    return this.http.post(`${environment.server.url}/user/getRecommandedTweets`,data,this.utils.createHeaderOptions())
                    .map((res: Response) => <TweetDTO[]>res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }
}
