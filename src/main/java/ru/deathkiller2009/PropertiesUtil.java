package ru.deathkiller2009;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesUtil {

    private final Properties properties = new Properties();

    static {
        loadProperties();
    }

    public static String getProperty(String propertyName) {
         return properties.getProperty(propertyName);
    }

    private static void loadProperties() {
        try(InputStream stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
