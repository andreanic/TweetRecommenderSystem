package it.keyover.trsserver.tweet.model;

import it.keyover.trsserver.util.LuceneConstants;
import lombok.Data;

@Data
public class SearchDTO {
	private String query;
	private String category;
	private LuceneConstants searchType;
}
