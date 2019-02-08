package it.keyover.trsserver.lucene.model;

import it.keyover.trsserver.util.LuceneConstants;
import lombok.Data;

@Data
public class SearchTypeDTO {
	private String name;
	private LuceneConstants value;
}
