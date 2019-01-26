import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';

import { FunctionComponent } from './function.component';

const routes: Routes = [
  {
    path: '',
    component: FunctionComponent,
    data: {
      title: 'Tweet Search'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FunctionRoutingModule {}
