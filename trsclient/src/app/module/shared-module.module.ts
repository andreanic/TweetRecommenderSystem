import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { LaddaModule } from 'angular2-ladda';
import { DataTableModule } from 'angular2-datatable';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsModalService } from 'ngx-bootstrap/modal/bs-modal.service';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { NgxSelectModule } from 'ngx-select-ex';
import { LoginFormComponent } from 'app/components/login-form/login-form.component';
import { CookieService } from 'ngx-cookie-service';
import { LoginRepositoryService } from 'app/repository/login/login-repository.service';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { itLocale } from 'ngx-bootstrap/locale';
import { BsLocaleService } from 'ngx-bootstrap/datepicker/bs-locale.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToasterModule, ToasterService} from 'angular2-toaster/angular2-toaster';

import { TableFilterPipe } from '../filter/table-filter.pipe';
import { BsDatepickerModule } from '../../../node_modules/ngx-bootstrap';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { UserRepositoryService } from 'app/repository/user/user-repository.service';

defineLocale('it', itLocale);

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpModule,
    LaddaModule,
    DataTableModule,
    ModalModule,
    ChartsModule,
    NgxSelectModule,
    NgbModule,
    ToasterModule,
    BsDatepickerModule.forRoot(),
  ],
  declarations: [
    LoginFormComponent,
    TableFilterPipe,
  ],
  providers: [
    BsModalService,
    CookieService,
    LoginRepositoryService,
    TweetRepositoryService,
    UserRepositoryService,
    BsLocaleService,
    ToasterService 
  ],
  exports: [
    LoginFormComponent,
    CommonModule,
    FormsModule,
    HttpModule,
    LaddaModule,
    DataTableModule,
    ModalModule,
    ChartsModule,
    NgxSelectModule,
    ToasterModule,
    TableFilterPipe,
  ]
})
export class SharedModule { }
