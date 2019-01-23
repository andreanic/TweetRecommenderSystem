import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions } from '@angular/http';
import { UtilService } from 'app/service/util.service';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginRepositoryService {

  constructor(private http: Http,
              private utils: UtilService) { }

  public login(data: any){
    return this.http.post(`${environment.server.url}/login`,data,null)
                    .map((res: Response) => res.json())
                    .catch((e: any) => Observable.throw(e));
  }
}
