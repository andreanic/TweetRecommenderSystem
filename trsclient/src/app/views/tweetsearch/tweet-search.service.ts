import { Injectable } from '@angular/core';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { Observable } from 'rxjs';
import { UserRepositoryService } from 'app/repository/user/user-repository.service';
import { UserDTO } from 'app/model/UserDTO';
import { TweetDTO } from 'app/model/TweetDTO';
import { SearchTypeDTO } from 'app/model/SearchTypeDTO';
import { LuceneRepositoryService } from 'app/repository/lucene/lucene-repository.service';

@Injectable()
export class TweetSearchService {

  private categories: String[] = [];
  private category: String;
  private searchType: number = 0;
  private allCategories: String = "all categories";
  private query: String = "";
  private tweetsFound: TweetDTO[];
  private userPreferencesSearch: boolean = false;

  constructor(private tweetRepository: TweetRepositoryService,
              private userRepository: UserRepositoryService,
              private luceneRepository: LuceneRepositoryService) { }

  public initArrays(): void{
    this.query = "";
    this.category = this.allCategories;
    this.searchType = 0;
    this.tweetsFound = null;
    this.userPreferencesSearch = false;
    this.tweetRepository.getCategories().subscribe(response => {
      this.categories = response;
    }, err => {
      console.log(err.payload);
    });
  }

  public getTweetsByQueryAndCategory(): Observable<TweetDTO[]>{
    return this.tweetRepository.getTweetsByQueryAndCategory(this.createQueryObject()).map(response => {
      this.tweetsFound = response;
      return this.tweetsFound;
    });
  }

  public getTweetsByQueryAndUserPreferences(): Observable<TweetDTO[]>{
    return this.tweetRepository.getTweetsByQueryAndUserPreferences(this.createQueryObject()).map(response => {
      this.tweetsFound = response;
      return this.tweetsFound;
    });
  }

  public addLikeToTweet(index: number): Observable<void>{
    return this.userRepository.addLikeToTweet(this.getSelectedTweet(index));
  }

  private getSelectedTweet(index: number): TweetDTO{
    let tweet = this.tweetsFound[index];

    this.tweetsFound.splice(index, 1);

    return tweet;
  }

  public setUserPreferencesSearch(): void{
    this.userPreferencesSearch = !this.userPreferencesSearch;
  }

  private createQueryObject(): any{
    let queryObject = {
      query: this.query,
      category: this.category == this.allCategories ? null : this.category,
      searchType: this.searchType,
    }

    return queryObject;
  }

   TweetDTO /**
     * Getter $categories
     * @return {String[]}
     */
	public get $categories(): String[] {
		return this.categories;
	}

    /**
     * Getter $category
     * @return {String}
     */
	public get $category(): String {
		return this.category;
	}

    /**
     * Getter $query
     * @return {String}
     */
	public get $query(): String {
		return this.query;
	}

    /**
     * Setter $categories
     * @param {string[]} value
     */
	public set $categories(value: String[]) {
		this.categories = value;
	}

    /**
     * Setter $category
     * @param {string} value
     */
	public set $category(value: String) {
		this.category = value;
	}

    /**
     * Setter $query
     * @param {String} value
     */
	public set $query(value: String) {
		this.query = value;
	}


    /**
     * Getter $allCategories
     * @return {String }
     */
	public get $allCategories(): String  {
		return this.allCategories;
	}

    /**
     * Setter $allCategories
     * @param {String } value
     */
	public set $allCategories(value: String ) {
		this.allCategories = value;
	}


    /**
     * Getter $tweetsFound
     * @return {TweetDTO[] }
     */
	public get $tweetsFound(): TweetDTO[]  {
		return this.tweetsFound;
	}

    /**
     * Setter $tweetsFound
     * @param {TweetDTO[] } value
     */
	public set $tweetsFound(value: TweetDTO[] ) {
		this.tweetsFound = value;
	}

    /**
     * Getter $searchType
     * @return {number}
     */
	public get $searchType(): number {
		return this.searchType;
	}

    /**
     * Getter $userPreferencesSearch
     * @return {boolean }
     */
	public get $userPreferencesSearch(): boolean  {
		return this.userPreferencesSearch;
	}

    /**
     * Setter $searchType
     * @param {number} value
     */
	public set $searchType(value: number) {
		this.searchType = value;
	}

    /**
     * Setter $userPreferencesSearch
     * @param {boolean } value
     */
	public set $userPreferencesSearch(value: boolean ) {
		this.userPreferencesSearch = value;
	}

}
