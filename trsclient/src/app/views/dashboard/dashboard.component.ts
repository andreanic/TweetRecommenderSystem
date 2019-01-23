import { Component, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DashboardService } from 'app/views/dashboard/dashboard.service';
import { OnInit, OnDestroy } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UtilService } from 'app/service/util.service';
import { BehaviorSubject, Subscription } from '../../../../node_modules/rxjs';
import { BsModalRef, BsModalService } from '../../../../node_modules/ngx-bootstrap';
import { ToasterService, ToasterConfig } from 'angular2-toaster';
import { environment } from 'environments/environment';
import { RegistrationService } from '../registration/registration.service';

@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['../../../../node_modules/spinkit/css/spinkit.css',
  'dashboard.component.scss',
  '../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class DashboardComponent implements OnInit{
  private toasterConfig: ToasterConfig;

  constructor(private utils: UtilService,
              private registrationService: RegistrationService){}

  ngOnInit(){
    this.toasterConfig = this.utils.getToasterConfig();
  }
}
