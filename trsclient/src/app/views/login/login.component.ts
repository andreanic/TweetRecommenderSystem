import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRepositoryService } from 'app/repository/login/login-repository.service';
import { LoginService } from './login.service';
import { ToasterConfig, ToasterService } from 'angular2-toaster';
import { UtilService } from 'app/service/util.service';

@Component({
  templateUrl: 'login.component.html',
  styleUrls: ['../../../../node_modules/ladda/dist/ladda-themeless.min.css',
    '../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit{


  private isLoggingIn: boolean = false;
  private toasterConfig: ToasterConfig;

  constructor(private router: Router,
              private utils: UtilService,
              private toasterService: ToasterService,
              private loginService: LoginService) {
  }

  public afterLogin(){
    this.router.navigate(['/dashboard']);
  }

  ngOnInit(){
    this.toasterConfig = this.utils.getToasterConfig();
  }
  
  public login(){
    this.isLoggingIn = true;
    this.loginService.login()
    .finally(() => {
      this.isLoggingIn = false;
    }).subscribe(response => {
      this.router.navigate(['/dashboard']);
    },
    err => {
      this.utils.showToastMessage("error","Login Error!",err.payload, this.toasterService);
    });
  }

}
