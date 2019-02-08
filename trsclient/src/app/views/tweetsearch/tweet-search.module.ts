import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';

import { TweetSearchComponent } from './tweet-search.component';
import { TweetSearchRoutingModule } from './tweet-search-routing.module';
import { SharedModule } from 'app/module/shared-module.module';
import { TweetSearchService } from './tweet-search.service';
import { DashboardService } from '../dashboard/dashboard.service';

@NgModule({
  imports: [
    TweetSearchRoutingModule,
    ChartsModule,
    SharedModule
  ],
  declarations: [ TweetSearchComponent ],
  providers: [TweetSearchService, DashboardService]
})
export class TweetSearchModule { }
