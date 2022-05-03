package com.apitesting.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.SneakyThrows;

//do not want others to extend this class
public final class APIUtils {

    // to avoid external instantiation
    // want only to expose Static Methods
    private APIUtils() {
    }

    /**
     * This function takes in the filePath and returns the JSON body as String
     * 
     * @param filePath
     * @return
     */
    @SneakyThrows
    public static String readJSONAndReturnString(String filePath) {

        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
