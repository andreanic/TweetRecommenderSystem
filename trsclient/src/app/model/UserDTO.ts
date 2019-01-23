export class UserDTO {
    private username: String;
    private password: String;
    private preferences: String [] = [];

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


}