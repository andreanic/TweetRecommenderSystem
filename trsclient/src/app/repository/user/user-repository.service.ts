import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { UserDTO } from 'app/model/UserDTO';
import { UtilService } from 'app/service/util.service';
import { TweetDTO } from 'app/model/TweetDTO';

@Injectable()
export class UserRepositoryService {

  constructor(private http: Http,
              private utils: UtilService) { }

  public saveUser(data: UserDTO): Observable<any>{
    return this.http.post(`${environment.server.url}/user/save`,data,this.utils.createHeaderOptions())
                    .map((res: Response) => res.json())
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }

  public addLikeToTweet(data: TweetDTO): Observable<void>{
    return this.http.post(`${environment.server.url}/user/addLikeToTweet`,data,this.utils.createHeaderOptions())
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }
}
