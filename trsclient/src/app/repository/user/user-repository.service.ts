import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { UserDTO } from 'app/model/UserDTO';

@Injectable()
export class UserRepositoryService {

  constructor(private http: Http) { }

  public saveUser(data: UserDTO): Observable<any>{
    return this.http.post(`${environment.server.url}/user/save`,data,null)
                    .map((res: Response) => res.json())
                    .catch((e: any) => Observable.throw(e));
  }
}
