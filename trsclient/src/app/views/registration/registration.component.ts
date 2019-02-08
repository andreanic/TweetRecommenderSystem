import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { UtilService } from 'app/service/util.service';
import { ToasterConfig, ToasterService } from 'angular2-toaster';
import { environment } from '../../../environments/environment';
import { RegistrationService } from './registration.service';

@Component({
  templateUrl: 'registration.component.html',
  styleUrls: ['../../../../node_modules/ladda/dist/ladda-themeless.min.css',
    '../../../scss/vendors/toastr/toastr.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RegistrationComponent implements OnInit {

  private toasterConfig: ToasterConfig;
  private isRegistrationLoading: boolean = false;

  constructor(private registrationService: RegistrationService,
    private toasterService: ToasterService,
    private utils: UtilService,
    private router: Router) {

    this.toasterConfig = this.utils.getToasterConfig();
    this.registrationService.initSelectionArrays();
  }

  ngOnInit() {
  }

  public selectTweet(i: number): void {
    this.registrationService.selectTweet(i);

  }

  public selectCategory(category: string): void {
    this.registrationService.selectCategory(category);
  }

  public isTweetSelected(tweet: any): boolean {
    return this.registrationService.isTweetSelected(tweet);
  }

  public saveUser(): void {
    this.isRegistrationLoading = true;
    this.registrationService.saveUser()
      .finally(() => {
        this.isRegistrationLoading = false;
      })
      .subscribe(response => {
        this.utils.showToastMessage("success", "Registration Success!", "You are now registered", this.toasterService);
        this.router.navigate(['/dashboard']);
      }, err => {
        this.utils.showToastMessage("error", "Registration Error!", err.payload, this.toasterService);
      });
  }

  public resetForm(): void {
    this.registrationService.resetForm();
  }
}
