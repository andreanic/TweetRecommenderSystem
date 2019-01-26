package it.keyover.trsserver.mapper;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.mapper.exception.SourceNullException;
import twitter4j.Status;

public class TweetToLuceneDocumentMapper {
	public static final String TWITTERID = "twitterid";
	public static final String TEXT = "text";
	public static final String CATEGORY = "category";
	
	public static Document map(Tweet tweet) throws BaseException{
		if(tweet == null) {
			throw new SourceNullException(Tweet.class.getName());
		}
		
		Document doc = new Document();
		
		doc.add(new StringField(TWITTERID, tweet.getTwitterid(), Field.Store.YES));
		doc.add(new TextField(TEXT, tweet.getText(), Field.Store.NO));
		doc.add(new StringField(CATEGORY, tweet.getCategory(), Field.Store.NO));
			
		return doc;
	}
}
