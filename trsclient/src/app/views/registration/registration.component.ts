import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { UtilService } from 'app/service/util.service';
import { ToasterConfig, ToasterService } from 'angular2-toaster';
import { environment } from '../../../environments/environment';
import { RegistrationService } from './registration.service';

@Component({
  templateUrl: 'registration.component.html',
  styleUrls: ['../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RegistrationComponent implements OnInit{

  private toasterConfig: ToasterConfig;

  constructor(private registrationService: RegistrationService,              
              private toasterService: ToasterService,
              private utils: UtilService) { }

  ngOnInit(){
    this.toasterConfig = this.utils.getToasterConfig();
    this.registrationService.initSelectionArray();
  }

  public selectTweet(tweet: any): void{
    this.registrationService.selectTweet(tweet); 
    
  }

  public selectCategory(category: string): void{
    this.registrationService.selectCategory(category);
  }

  public isTweetSelected(tweet: any): boolean{
    return this.registrationService.isTweetSelected(tweet);
  }

  public saveUser(): void{
    if(this.registrationService.$selectedTweets.length < 1 && this.registrationService.$selectedCategories.length < 1){
      this.utils.showToastMessage("error","Registration Error!",environment.errorMessage.noTweetCategorySelectedException,this.toasterService);
    }
    else{
      this.registrationService.saveUser().subscribe(response => {
        this.utils.showToastMessage("success","Registration Success!","You are now registered",this.toasterService);
      }, err => {
        
      });
      
    }
  }

  public resetForm(): void{
    this.registrationService.resetForm();
  }
}
