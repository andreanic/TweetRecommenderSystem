package it.keyover.trsserver.util;

import java.util.ResourceBundle;

public class PropertyReader {
    public static String getTwitterProperties(String name){
        return ResourceBundle.getBundle("twitter").getString(name);	
    }
}
