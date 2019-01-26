package it.keyover.trsserver.mapper;


import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.TwitterUser;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.mapper.exception.SourceNullException;
import twitter4j.Status;

public class StatusToTweetMapper {

	public static Tweet map(Status tweet) throws BaseException{
		return StatusToTweetMapper.map(tweet,null);
	}
	
	public static Tweet map(Status tweet, String category) throws BaseException{
		
		if(tweet == null) {
			throw new SourceNullException(Status.class.getName());
		}
		
		Tweet tweetToMap = new Tweet();
		
		tweetToMap.setId(tweet.getId());
		tweetToMap.setTwitterid(Long.toString(tweet.getId()));
		tweetToMap.setText(tweet.getText());
		tweetToMap.setCategory(category);
		tweetToMap.setTwitterUser(UserToTwitterUserMapper.map(tweet.getUser()));
		
		if(tweet.getHashtagEntities().length > 0) {			
			tweetToMap.setHashtags(HashtagEntityToHashtagMapper.map(tweet.getHashtagEntities()));
		}
	
		return tweetToMap;
	}
}
