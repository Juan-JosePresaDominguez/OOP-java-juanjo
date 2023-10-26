package com.myshoppingcart.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyValues {
    private static PropertyValues instance;
    public static Properties props = new Properties();
    private static String propFileName = "config.properties";

    private PropertyValues() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null) inputStream.close();
        }
    }

    public static PropertyValues getInstance() throws IOException {
        if (instance == null) instance = new PropertyValues();
        return instance;
    }

    public final Properties getPropValues() throws IOException {
        return props;
    }
}