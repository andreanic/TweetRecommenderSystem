import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { UtilService } from 'app/service/util.service';
import { Observable } from 'rxjs';
import { environment } from 'environments/environment';
import { SearchTypeDTO } from 'app/model/SearchTypeDTO';

@Injectable()
export class LuceneRepositoryService {

  constructor(private http: Http,
    private utils: UtilService) { }


  public createIndex(): Observable<string>{
    return this.http.get(`${environment.server.url}/lucene/createIndex`,this.utils.createHeaderOptions())
                    .map((res: Response) => res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }

  public getLuceneSearchTypes(): Observable<SearchTypeDTO[]>{
    return this.http.get(`${environment.server.url}/lucene/getLuceneSearchTypes`,this.utils.createHeaderOptions())
                    .map((res: Response) => res.json().payload)
                    .catch((e: any) => Observable.throw(JSON.parse(e._body)));
  }
}
