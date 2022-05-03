package com.apitesting.constants;

import lombok.Getter;

@Getter
public class FrameworkConstantsWithSingleton {

    // Singleton -> Single instance for a class at a particular point of time
    // this is one of the creational design pattern
    // don't use singleton pattern when setters are involved

    private static FrameworkConstantsWithSingleton INSTANCE = null;

    // avoid external instantiation
    private FrameworkConstantsWithSingleton() {
    }

    public static synchronized FrameworkConstantsWithSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FrameworkConstantsWithSingleton();
        }
        return INSTANCE;
    }

    // for non-static -> class level
    // else we have to use @Getters on field level
    private final String JSONRequestFolderPath = System.getProperty("user.dir")
            + "/src/test/resources/JSON/";
    private final String JSONResponseFolderPath = System.getProperty("user.dir") + "/output/";

    private static @Getter final String configPropertiesFilePath = System.getProperty("user.dir")
            + "/src/test/resources/config.properties";

}
