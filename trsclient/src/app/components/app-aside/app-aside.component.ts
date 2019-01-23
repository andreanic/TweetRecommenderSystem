import { Component } from '@angular/core';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';
import { UtilService } from '../../service/util.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-aside',
  templateUrl: './app-aside.component.html'
})
export class AppAsideComponent{

  constructor() {
  }


}
