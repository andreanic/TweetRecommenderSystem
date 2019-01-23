import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import {
  FullLayoutComponent,
  SimpleLayoutComponent
} from './containers';

export const routes: Routes = [  
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: '',
    component: FullLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: './views/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'tweetsearch',
        loadChildren: './views/tweetsearch/tweet-search.module#TweetSearchModule'
      },
    ]
  },
  {
    path: '',
    component: SimpleLayoutComponent,
    data: {
      title: 'Login'
    },
    children: [
      {
        path: 'login',
        loadChildren: './views/login/login.module#LoginModule',
      },
      {
        path: 'registration',
        loadChildren: './views/registration/registration.module#RegistrationModule',
      },
    ]
  },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
