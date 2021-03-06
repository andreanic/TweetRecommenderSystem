import { Injectable } from '@angular/core';
import { RequestOptions, Headers } from '@angular/http';
import { CookieService } from 'ngx-cookie-service';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { BsModalService } from 'ngx-bootstrap/modal/bs-modal.service';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Router } from '@angular/router';
import { ToasterConfig, ToasterService } from 'angular2-toaster';
declare var jsPDF: any;

@Injectable()
export class UtilService {
  public toasterConfig : ToasterConfig = new ToasterConfig({
      tapToDismiss: true,
      timeout: 4000
    });

  constructor(private cookieService: CookieService) { 
  }

  public getToasterConfig(): ToasterConfig{
    return this.toasterConfig;
  }

  public createHeaderOptions(): RequestOptions{
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', this.cookieService.get("token"));

    const options = new RequestOptions({headers: headers, withCredentials: true});

    return options
  }

  public showToastMessage(type: string, title: string, message: string, toasterService: ToasterService): void{
    toasterService.pop(type, title, message);
  }
}
