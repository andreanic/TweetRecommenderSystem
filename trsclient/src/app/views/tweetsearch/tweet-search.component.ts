import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { TweetSearchService } from './tweet-search.service';
import { UtilService } from 'app/service/util.service';
import { ToasterConfig, ToasterService } from 'angular2-toaster';
import { TweetDTO } from 'app/model/TweetDTO';

@Component({
  templateUrl: 'tweet-search.component.html' ,
  styleUrls: ['../../../../node_modules/ladda/dist/ladda-themeless.min.css',
  '../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class TweetSearchComponent implements OnInit{

  private isQueryLoading: boolean = false;
  private toasterConfig: ToasterConfig;
  private tweetsFound: TweetDTO[];

  constructor(private tweetSearchService: TweetSearchService,
              private toasterService: ToasterService,
              private utils: UtilService) { }

  ngOnInit(){
    this.toasterConfig = this.utils.getToasterConfig();
    this.tweetSearchService.initCategoriesArray();
  }

  public fullTextSearch(){
    this.isQueryLoading=true;
    this.tweetSearchService.fullTextSearch()
    .finally(() => {
      this.isQueryLoading = false;
      this.tweetsFound = this.tweetSearchService.$tweetsFound;
    }).subscribe(response => {
      this.utils.showToastMessage("success","Query Success!","Retrieved "+ response.length + " tweets",this.toasterService);
    }, err => {
      this.utils.showToastMessage("error","Query Error!",err.payload,this.toasterService);
    })
  }
}
