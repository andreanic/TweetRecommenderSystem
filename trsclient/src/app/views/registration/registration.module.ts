import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';

import { RegistrationComponent } from './registration.component';
import { RegistrationRoutingModule } from './registration-routing.module';
import { SharedModule } from 'app/module/shared-module.module';
import { RegistrationService } from './registration.service';

@NgModule({
  imports: [
    RegistrationRoutingModule,
    ChartsModule,
    SharedModule
  ],
  declarations: [ RegistrationComponent ],
  providers: [RegistrationService]
})
export class RegistrationModule { }
