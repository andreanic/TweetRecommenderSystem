import { Injectable } from '@angular/core';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { Observable } from 'rxjs';
import { UserRepositoryService } from 'app/repository/user/user-repository.service';
import { UserDTO } from 'app/model/UserDTO';

@Injectable()
export class RegistrationService {

  private tweets: any[] = [{
    user:"Carlo Cracco",
    text: "Che bello masterchef 8!",
    imgUrl: "https://pbs.twimg.com/profile_images/833104478328877056/8z-8v1Fw_mini.jpg",
    id: 1,
    category: "Cucina",
  },{
    user:"Carlo Cracco",
    text: "Che bello masterchef 8!",
    imgUrl: "https://pbs.twimg.com/profile_images/833104478328877056/8z-8v1Fw_mini.jpg",
    id: 2,
    category: "Cucina",
  },{
    user:"Carlo Cracco",
    text: "Che bello masterchef 8!",
    imgUrl: "https://pbs.twimg.com/profile_images/833104478328877056/8z-8v1Fw_mini.jpg",
    id: 3,
    category: "Cucina",
  },{
    user:"Carlo Cracco",
    text: "Che bello masterchef 8!",
    imgUrl: "https://pbs.twimg.com/profile_images/833104478328877056/8z-8v1Fw_mini.jpg",
    id: 4,
    category: "Cucina",
  }];

  private categories: string[] = ["prova1","prova2","prova3","prova4","prova5","prova6","prova7"];

  private selectedTweets: number[] = [];
  private selectedCategories: string[] = [];
  private username: string = "";
  private password: string = "";

  constructor(private tweetRepository: TweetRepositoryService,
              private userRepository: UserRepositoryService,) { }

  public initSelectionArray(): void{
    this.tweetRepository.getCategories().subscribe(response => {
      this.categories = response.payload;
    }, err => {
      console.log(err._body.message);
    });

    this.tweetRepository.getRelevantTweets().subscribe(response => {
      this.tweets = response.payload;
    }, err => {
      console.log(err);
    })
  }

  public selectTweet(tweet: any): void{
    const index = this.selectedTweets.indexOf(tweet.id);
    if (index > -1) {
      this.selectedTweets.splice(index, 1);
    }
    else{
      this.selectedTweets.push(tweet.id);
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
    });
    console.log(user);
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
     * @return {string[] }
     */
	public get $categories(): string[]  {
		return this.categories;
	}

    /**
     * Setter $categories
     * @param {string[] } value
     */
	public set $categories(value: string[] ) {
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
     * @return {string[] }
     */
	public get $selectedCategories(): string[]  {
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
     * @param {string[] } value
     */
	public set $selectedCategories(value: string[] ) {
		this.selectedCategories = value;
  }
  

    /**
     * Getter $username
     * @return {string }
     */
	public get $username(): string  {
		return this.username;
	}

    /**
     * Getter $password
     * @return {string }
     */
	public get $password(): string  {
		return this.password;
	}

    /**
     * Setter $username
     * @param {string } value
     */
	public set $username(value: string ) {
		this.username = value;
	}

    /**
     * Setter $password
     * @param {string } value
     */
	public set $password(value: string ) {
		this.password = value;
	}


}
