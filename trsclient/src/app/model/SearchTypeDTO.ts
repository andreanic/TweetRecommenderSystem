export class SearchTypeDTO {
    private name: String;
    private value: number;

    /**
     * Getter $name
     * @return {String}
     */
	public get $name(): String {
		return this.name;
	}

    /**
     * Getter $value
     * @return {number}
     */
	public get $value(): number {
		return this.value;
	}

    /**
     * Setter $name
     * @param {String} value
     */
	public set $name(value: String) {
		this.name = value;
	}

    /**
     * Setter $value
     * @param {number} value
     */
	public set $value(value: number) {
		this.value = value;
	}
    
}