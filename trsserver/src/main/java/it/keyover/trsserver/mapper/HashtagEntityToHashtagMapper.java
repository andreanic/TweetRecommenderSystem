package it.keyover.trsserver.mapper;

import java.util.ArrayList;
import java.util.List;

import it.keyover.trsserver.entity.Hashtag;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.mapper.exception.SourceNullException;
import twitter4j.HashtagEntity;

public class HashtagEntityToHashtagMapper {

	public static Hashtag map(HashtagEntity hashtag) throws BaseException{
		if(hashtag == null) {
			throw new SourceNullException(HashtagEntity.class.getName());
		}
		Hashtag hashtagToMap = new Hashtag();
		
		hashtagToMap.setValue(hashtag.getText());
		
		return hashtagToMap;
	}
	
	public static List<Hashtag> map(HashtagEntity[] hashtags) throws BaseException{
		if(hashtags == null || hashtags.length == 0) {
			throw new SourceNullException(hashtags.getClass().getName());
		}
		
		List<Hashtag> hashtagsToMap = new ArrayList<Hashtag>();
		for(HashtagEntity hashtagEntity : hashtags) {
			hashtagsToMap.add(HashtagEntityToHashtagMapper.map(hashtagEntity));
		}		
		
		return hashtagsToMap;
	}
}
