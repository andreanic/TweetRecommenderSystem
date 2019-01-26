package it.keyover.trsserver.util;

import java.util.List;
import java.util.ResourceBundle;

public class PropertyReader {
	private final static String TWITTER_PROPERTIES = "twitter";
	private final static String MONGODB_PROPERTIES = "mongodb";
	private final static String MESSAGES_PROPERTIES = "messages";
	private final static String CATEGORIES_PROPERTIES = "categories";
	
    public static String getTwitterProperties(String name){
        return PropertyReader.getProperty(TWITTER_PROPERTIES, name);
    }
    
    public static String getMongoProperties(String name) {
    	return PropertyReader.getProperty(MONGODB_PROPERTIES, name);
    }
    
    public static String getMessagesProperties(String name) {
    	return PropertyReader.getProperty(MESSAGES_PROPERTIES, name);
    }
    
    public static String getProperty(String file, String name) {
    	return ResourceBundle.getBundle(file).getString(name);	
    }
    
    public static String[] getCategories(){
    	return PropertyReader.getProperty(CATEGORIES_PROPERTIES, CATEGORIES_PROPERTIES).split(",");
    }
}
