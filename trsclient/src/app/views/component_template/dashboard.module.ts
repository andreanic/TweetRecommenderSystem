import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { SharedModule } from 'app/module/shared-module.module';

@NgModule({
  imports: [
    DashboardRoutingModule,
    ChartsModule,
    SharedModule
  ],
  declarations: [ DashboardComponent ]
})
export class DashboardModule { }
