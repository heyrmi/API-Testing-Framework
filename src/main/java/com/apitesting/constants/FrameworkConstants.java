package com.apitesting.constants;

import lombok.Getter;

public final class FrameworkConstants {
        // to avoid external instantiation
        // will use only static methods
        private FrameworkConstants() {
        }

        // for non static -> class level
        // else we have to use @Getters on field level
        private static @Getter final String JSONRequestFolderPath = System.getProperty("user.dir")
                        + "/src/test/resources/JSON/";
        private static @Getter final String JSONResponseFolderPath = System.getProperty("user.dir") + "/output/";

        private static @Getter final String configPropertiesFilePath = System.getProperty("user.dir")
                        + "/src/test/resources/config.properties";
        private static @Getter final String extentReportPath = System.getProperty("user.dir")
                        + "/Reports/latestreport/index.html";

}
