import { Injectable } from '@angular/core';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { Observable } from 'rxjs';
import { UserRepositoryService } from 'app/repository/user/user-repository.service';
import { UserDTO } from 'app/model/UserDTO';
import { TweetDTO } from 'app/model/TweetDTO';

@Injectable()
export class TweetSearchService {

  private categories: String[] = [];
  private category: String;
  private allCategories: String = "all categories";
  private query: String = "";
  private tweetsFound: TweetDTO[];

  constructor(private tweetRepository: TweetRepositoryService,
              private userRepository: UserRepositoryService) { }

  public initCategoriesArray(): void{
    this.tweetRepository.getCategories().subscribe(response => {
      this.categories = response;
    }, err => {
      console.log(err.payload);
    });
  }

  public fullTextSearch(): Observable<TweetDTO[]>{
    return this.tweetRepository.fullTextSearch(this.createQueryObject()).map(response => {
      this.tweetsFound = response;
      return this.tweetsFound;
    });
  }

  private createQueryObject(): any{
    let queryObject = {
      query: this.query,
      category: this.category == this.allCategories ? null : this.category,
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

}
