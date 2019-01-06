package it.keyover.trsserver.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Tweet{
	@Id
	private Long id;
	@TextIndexed
	private String text;
	private TwitterUser twitterUser;
	private List<Hashtag> hashtags; 
}
