package it.keyover.trsserver.util;

import java.util.ResourceBundle;

public class PropertyReader {
    public static String getTwitterProperties(String name){
        return PropertyReader.getProperty("twitter", name);
    }
    
    public static String getMongoProperties(String name) {
    	return PropertyReader.getProperty("mongodb", name);
    }
    
    public static String getProperty(String file, String name) {
    	return ResourceBundle.getBundle(file).getString(name);	
    }
}
