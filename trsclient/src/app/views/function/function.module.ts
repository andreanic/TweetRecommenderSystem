import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';

import { SharedModule } from 'app/module/shared-module.module';
import { FunctionRoutingModule } from './function-routing.module';
import { FunctionComponent } from './function.component';
import { FunctionService } from './function.service';

@NgModule({
  imports: [
    FunctionRoutingModule,
    ChartsModule,
    SharedModule
  ],
  declarations: [ FunctionComponent ],
  providers: [FunctionService]
})
export class FunctionModule { }
