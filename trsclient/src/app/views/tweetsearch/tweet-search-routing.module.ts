import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';

import { TweetSearchComponent } from './tweet-search.component';

const routes: Routes = [
  {
    path: '',
    component: TweetSearchComponent,
    data: {
      title: 'Tweet Search'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TweetSearchRoutingModule {}
