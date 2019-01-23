package it.keyover.trsserver.factory;

import it.keyover.trsserver.util.PropertyReader;
import lombok.Getter;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClientFactory {
	private static Twitter twitter;
	public static Twitter getTwitterClient() {
		if(twitter == null) {
			TwitterFactory factory = new TwitterFactory();
		    AccessToken accessToken = new AccessToken(PropertyReader.getTwitterProperties("accessToken"),PropertyReader.getTwitterProperties("accessTokenSecret"));
		    twitter = factory.getInstance();
		    twitter.setOAuthConsumer(PropertyReader.getTwitterProperties("consumerKey"), PropertyReader.getTwitterProperties("consumerSecret"));
		    twitter.setOAuthAccessToken(accessToken);
			
		}

		return twitter;
	}
}
