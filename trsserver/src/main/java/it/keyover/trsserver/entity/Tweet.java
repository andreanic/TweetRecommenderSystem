package it.keyover.trsserver.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import lombok.Data;

@Data
@Document
public class Tweet{
	@Id
	private Long id;
	private String twitterid;
	@TextIndexed
	private String text;
	private TwitterUser twitterUser;
	private List<Hashtag> hashtags; 
	private String category;
}
