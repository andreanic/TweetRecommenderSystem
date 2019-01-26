import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions } from '@angular/http';
import { UtilService } from 'app/service/util.service';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class LoginRepositoryService {

  constructor(private http: Http,
              private utils: UtilService,
              private cookieService: CookieService) { }

  public login(data: any): Observable<any>{
    return this.http.post(`${environment.server.url}/user/login`,data,this.utils.createHeaderOptions()).map(response => {
                      let token = response.headers.get("authorization");
                      if(token != null){
                        this.cookieService.set("token",token);
                      }                
                    })
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }
}
