export class TweetDTO {
	private id: number;
	private twitterId: String;
	private text: String;
	private twitterUser: any;
	private hashtags: any[]; 
	private category: String;

	// deserialize(input: any): this {
	// 	Object.assign(this, input);
	// 	return this;
	//   }
    /**
     * Getter $id
     * @return {number}
     */
	public get $id(): number {
		return this.id;
	}

    /**
     * Getter $twitterId
     * @return {String}
     */
	public get $twitterId(): String {
		return this.twitterId;
	}

    /**
     * Getter $text
     * @return {String}
     */
	public get $text(): String {
		return this.text;
	}

    /**
     * Getter $twitterUser
     * @return {any}
     */
	public get $twitterUser(): any {
		return this.twitterUser;
	}

    /**
     * Getter $hashtags
     * @return {any[]}
     */
	public get $hashtags(): any[] {
		return this.hashtags;
	}

    /**
     * Getter $category
     * @return {String}
     */
	public get $category(): String {
		return this.category;
	}

    /**
     * Setter $id
     * @param {number} value
     */
	public set $id(value: number) {
		this.id = value;
	}

    /**
     * Setter $twitterId
     * @param {String} value
     */
	public set $twitterId(value: String) {
		this.twitterId = value;
	}

    /**
     * Setter $text
     * @param {String} value
     */
	public set $text(value: String) {
		this.text = value;
	}

    /**
     * Setter $twitterUser
     * @param {any} value
     */
	public set $twitterUser(value: any) {
		this.twitterUser = value;
	}

    /**
     * Setter $hashtags
     * @param {any[]} value
     */
	public set $hashtags(value: any[]) {
		this.hashtags = value;
	}

    /**
     * Setter $category
     * @param {String} value
     */
	public set $category(value: String) {
		this.category = value;
	}

}