export class UserDTO {
    private username: String;
    private password: String;
    private preferences: String [] = [];
    private tweetsLiked: number[] = [];

    constructor(){};

    /**
     * Getter $username
     * @return {String}
     */
	public get $username(): String {
		return this.username;
	}

    /**
     * Getter $password
     * @return {String}
     */
	public get $password(): String {
		return this.password;
	}

    /**
     * Getter $preferences
     * @return {String []}
     */
	public get $preferences(): String [] {
		return this.preferences;
	}

    /**
     * Setter $username
     * @param {String} value
     */
	public set $username(value: String) {
		this.username = value;
	}

    /**
     * Setter $password
     * @param {String} value
     */
	public set $password(value: String) {
		this.password = value;
	}

    /**
     * Setter $preferences
     * @param {String []} value
     */
	public set $preferences(value: String []) {
		this.preferences = value;
	}


    /**
     * Getter $tweetsLiked
     * @return {number[] }
     */
	public get $tweetsLiked(): number[]  {
		return this.tweetsLiked;
	}

    /**
     * Setter $tweetsLiked
     * @param {number[] } value
     */
	public set $tweetsLiked(value: number[] ) {
		this.tweetsLiked = value;
	}

}