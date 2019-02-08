import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { TweetSearchService } from './tweet-search.service';
import { UtilService } from 'app/service/util.service';
import { ToasterConfig, ToasterService } from 'angular2-toaster';
import { TweetDTO } from 'app/model/TweetDTO';
import { DashboardService } from '../dashboard/dashboard.service';

@Component({
  templateUrl: 'tweet-search.component.html' ,
  styleUrls: ['../../../../node_modules/ladda/dist/ladda-themeless.min.css',
  '../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class TweetSearchComponent implements OnInit{

  private isQueryLoading: boolean = false;
  private toasterConfig: ToasterConfig;

  constructor(private tweetSearchService: TweetSearchService,
              private dashboardService: DashboardService,
              private toasterService: ToasterService,
              private utils: UtilService) { 
                this.toasterConfig = this.utils.getToasterConfig();
                this.tweetSearchService.initArrays();
              }

  ngOnInit(){

  }

  public getTweets(){
    this.isQueryLoading=true;
    if(this.tweetSearchService.$userPreferencesSearch){
      this.getTweetsByQueryAndUserPreferences();
    }
    else{
      this.getTweetsByQueryAndCategory();
    }
  }

  public getTweetsByQueryAndCategory(): void{
    this.tweetSearchService.getTweetsByQueryAndCategory()
    .finally(() => {
      this.isQueryLoading = false;
    }).subscribe(response => {
      this.utils.showToastMessage("success","Query By Cateogy Success!","Retrieved "+ response.length + " tweets",this.toasterService);
    }, err => {
      this.utils.showToastMessage("error","Query Error!",err.payload,this.toasterService);
    })
  }

  public getTweetsByQueryAndUserPreferences(): void{
    this.tweetSearchService.getTweetsByQueryAndUserPreferences()
    .finally(() => {
      this.isQueryLoading = false;
    }).subscribe(response => {
      this.utils.showToastMessage("success","Query By User Success!","Retrieved "+ response.length + " tweets",this.toasterService);
    }, err => {
      this.utils.showToastMessage("error","Query Error!",err.payload,this.toasterService);
    })
  }

  public setUserPreferencesSearch(): void{
    this.tweetSearchService.setUserPreferencesSearch();
  }

  public addLikeToTweet(index:number): void{
    this.tweetSearchService.addLikeToTweet(index).subscribe(response => {
      this.dashboardService.initTweetsArrays();
    }, err => {
      console.error(err);
    });
  }
}
