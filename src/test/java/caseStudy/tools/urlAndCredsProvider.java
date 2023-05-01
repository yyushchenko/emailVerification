package caseStudy.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class urlAndCredsProvider {

    public static Properties loadProperties(){
        Properties properties = new Properties();
        FileInputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/config.properties");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find properties file");
            throw new RuntimeException(e);
        }

        try {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Can't load properties");
            throw new RuntimeException(e);
        }

        try {
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }
}

