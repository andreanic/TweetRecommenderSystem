import { Injectable } from '@angular/core';
import { TweetRepositoryService } from 'app/repository/tweet/tweet-repository.service';
import { Observable } from 'rxjs';

@Injectable()
export class FunctionService {

  private categories: String[] = [];
  private category: String;

  constructor(private tweetRepository: TweetRepositoryService){}

  public initSelectArray(): void{
    this.tweetRepository.getCategories().subscribe(response => {
      this.categories = response;
      this.category = this.categories[0];
    });
  }

  public retrieveTweets(): Observable<string>{
    return this.tweetRepository.retrieveTweets(this.category);
  }

  public removeCategoryRetrieved(): void{
    const index = this.categories.indexOf(this.category);
    if (index > -1) {
      this.categories.splice(index, 1);
      if(this.categories.length > 0){
        this.category = this.categories[0];
      }
    }
  }

    /**
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
     * Setter $categories
     * @param {String[]} value
     */
	public set $categories(value: String[]) {
		this.categories = value;
	}

    /**
     * Setter $category
     * @param {String} value
     */
	public set $category(value: String) {
		this.category = value;
	}

}
