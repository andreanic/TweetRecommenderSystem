import { Injectable } from '@angular/core';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { Observable } from 'rxjs';
import { UserRepositoryService } from 'app/repository/user/user-repository.service';
import { UserDTO } from 'app/model/UserDTO';

@Injectable()
export class RegistrationService {

  private tweets: any[];
  private categories: String[];
  private selectedTweets: number[] = [];
  private selectedCategories: String[] = [];
  private username: String = "";
  private password: String = "";

  constructor(private tweetRepository: TweetRepositoryService,
              private userRepository: UserRepositoryService,) { }

  public initSelectionArrays(): void{
    this.username = "";
    this.password = "";
    this.selectedCategories = [];
    this.selectedTweets = [];
    this.tweetRepository.getCategories().subscribe(response => {
      this.categories = response;
    }, err => {
      console.log(err.payload);
    });

    this.tweetRepository.getOneTweetByCategory().subscribe(response => {
      this.tweets = response;
    }, err => {
      console.log(err.payload);
    })
  }

  public selectTweet(i: number): void{
    const index = this.selectedTweets.indexOf(i);
    if (index > -1) {
      this.selectedTweets.splice(index, 1);
    }
    else{
      this.selectedTweets.push(i);
    }    
    
  }

  public selectCategory(category: string): void{
    const index = this.selectedCategories.indexOf(category);
    if (index > -1) {
      this.selectedCategories.splice(index, 1);
    }
    else{
      this.selectedCategories.push(category);
    } 
  }

  public isTweetSelected(tweet: any): boolean{
    return this.selectedTweets.includes(tweet.id);
  }

  public resetForm(): void{
    this.username = "";
    this.password = "";
    this.selectedCategories = [];
    this.selectedTweets = [];
  }

  public saveUser(): Observable<any>{
    return this.userRepository.saveUser(this.createUser());
  }

  public createUser(): UserDTO{
    let user = new UserDTO();

    user.$username = this.username;
    user.$password = this.password;

    user.$preferences = user.$preferences.concat(this.selectedCategories);

    this.selectedTweets.forEach(index => {
      if(!user.$preferences.includes(this.tweets[index].category)){
        user.$preferences.push(this.tweets[index].category);
      }

      user.$tweetsLiked.push(this.tweets[index].twitterid);
    });
    
    return user;
  }

    /**
     * Getter $tweets
     * @return {any[] }
     */
	public get $tweets(): any[]  {
		return this.tweets;
	}

    /**
     * Setter $tweets
     * @param {any[] } value
     */
	public set $tweets(value: any[] ) {
		this.tweets = value;
	}


    /**
     * Getter $categories
     * @return {String[] }
     */
	public get $categories(): String[]  {
		return this.categories;
	}

    /**
     * Setter $categories
     * @param {String[] } value
     */
	public set $categories(value: String[] ) {
		this.categories = value;
	}

    /**
     * Getter $selectedTweets
     * @return {number[] }
     */
	public get $selectedTweets(): number[]  {
		return this.selectedTweets;
	}

    /**
     * Getter $selectedCategories
     * @return {String[] }
     */
	public get $selectedCategories(): String[]  {
		return this.selectedCategories;
	}

    /**
     * Setter $selectedTweets
     * @param {number[] } value
     */
	public set $selectedTweets(value: number[] ) {
		this.selectedTweets = value;
	}

    /**
     * Setter $selectedCategories
     * @param {String[] } value
     */
	public set $selectedCategories(value: String[] ) {
		this.selectedCategories = value;
  }
  

    /**
     * Getter $username
     * @return {String }
     */
	public get $username(): String  {
		return this.username;
	}

    /**
     * Getter $password
     * @return {String }
     */
	public get $password(): String  {
		return this.password;
	}

    /**
     * Setter $username
     * @param {String } value
     */
	public set $username(value: String ) {
		this.username = value;
	}

    /**
     * Setter $password
     * @param {String } value
     */
	public set $password(value: String ) {
		this.password = value;
	}


}
