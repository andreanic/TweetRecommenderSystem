import { NgModule } from '@angular/core';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { SharedModule } from 'app/module/shared-module.module';
import { DashboardService } from 'app/views/dashboard/dashboard.service';
import { BsDatepickerModule } from '../../../../node_modules/ngx-bootstrap';
import { RegistrationService } from '../registration/registration.service';

@NgModule({
  imports: [
    DashboardRoutingModule,
    BsDatepickerModule.forRoot(),
    SharedModule
  ],
  declarations: [ 
    DashboardComponent,
  ],
  providers: [ DashboardService, RegistrationService ]
})
export class DashboardModule { }
