import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: 'login.component.html'
})
export class LoginComponent {

  constructor(private router: Router) {
  }

  public afterLogin(){
    this.router.navigate(['/dashboard']);
  }

}
