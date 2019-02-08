import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { UtilService } from 'app/service/util.service';
import { ToasterConfig, ToasterService } from 'angular2-toaster';
import { TweetDTO } from 'app/model/TweetDTO';
import { FunctionService } from './function.service';

@Component({
  templateUrl: 'function.component.html' ,
  styleUrls: ['../../../../node_modules/ladda/dist/ladda-themeless.min.css',
  '../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class FunctionComponent implements OnInit{
  
  private toasterConfig: ToasterConfig;
  private isFunctionRetrieving: boolean = false;
  private isIndexCreating: boolean = false;

  constructor(private toasterService: ToasterService,
              private utils: UtilService,
              private functionService: FunctionService) { }

  ngOnInit(){
    this.toasterConfig = this.utils.getToasterConfig();
    this.functionService.initSelectArray();
  }

  public retrieveTweets(): void{
    this.isFunctionRetrieving = true;
    this.functionService.retrieveTweets().finally(() => {
      this.isFunctionRetrieving = false;
    })
    .subscribe(response => {
      this.utils.showToastMessage("success","Tweets Retrieved!",response,this.toasterService);
      this.functionService.removeCategoryRetrieved();
    }, err => {
      this.utils.showToastMessage("error","Tweets Retrieve Error!",err.payload,this.toasterService);
    });
  }

  public createIndex(): void{
    this.isIndexCreating = true;
    this.functionService.createIndex().finally(() => {
      this.isIndexCreating = false;
    }).subscribe(response => {
      this.utils.showToastMessage("success","Index Creation Success!",response,this.toasterService);
    }, err => {
      this.utils.showToastMessage("error","Index Creation Error!",err.payload,this.toasterService);
    });
  }
}
