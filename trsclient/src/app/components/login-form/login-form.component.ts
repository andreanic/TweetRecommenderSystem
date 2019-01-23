import { Component, OnInit, Output, EventEmitter, ViewEncapsulation} from '@angular/core';
import { LoginRepositoryService } from '../../repository/login/login-repository.service';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss',
  '../../../../node_modules/ladda/dist/ladda-themeless.min.css',
  '../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class LoginFormComponent implements OnInit {

  @Output() loginSuccess: EventEmitter<any> = new EventEmitter();

  public username: string;
  public password: string;
  public isLoggingIn: boolean = false;

  constructor(private loginRepository: LoginRepositoryService) {
  }

  ngOnInit() {
  }
  
  public login(){
    this.isLoggingIn = true;
    this.loginRepository.login({
      username: this.username,
      password: this.password,
    }).finally(() => {
      this.isLoggingIn = false;
      this.loginSuccess.emit();
    }).subscribe(response => {
      this.loginSuccess.emit();
    },
    err => {
      console.log(err);
    });
  }
}
