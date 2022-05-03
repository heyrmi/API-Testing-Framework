package com.apitesting.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.apitesting.constants.FrameworkConstants;
import com.apitesting.enums.ConfigProperties;

public final class PropertyUtils {
    // to avoid external instantiation
    private PropertyUtils() {
    }

    /**
     * // read the contents form the property files
     * // read and store the data from the Config File, to a hashmap
     */
    private static Properties properties = new Properties();
    private static Map<String, String> map = new HashMap<>();

    // This will run before the main method
    static {

        try (FileInputStream fis = new FileInputStream(FrameworkConstants.getConfigPropertiesFilePath())) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            // if there is an error here we do not want to run the program further
            System.exit(0);
        }

        properties.entrySet().forEach(e -> map.put(String.valueOf(e.getKey()), String.valueOf(e.getValue())));
    }

    /**
     * Return the value for the corresponding key
     * Enums are used so errors can be minimised
     * 
     * @param key
     * @return
     */
    public static String getValue(ConfigProperties key) {
        System.out.println(map.get(key.name().toLowerCase()));
        return map.get(key.name().toLowerCase());

    }
}
