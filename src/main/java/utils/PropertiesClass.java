package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClass {
    private static Properties prop = new Properties();

    static{
        FileInputStream fileInputStream = null;
        prop = new Properties();
        try {
            fileInputStream = new FileInputStream("credentials.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }

}
