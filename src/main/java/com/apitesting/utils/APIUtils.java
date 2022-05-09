package com.apitesting.utils;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

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
     * @return String
     */
    @SneakyThrows
    public static String readJSONAndReturnString(String filePath) {

        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * This function takes the oputput of a request and puts it into a file
     *
     * @param filePath
     * @return String
     */
    @SneakyThrows
    public static void storeStringAsJSONFile(String filePath, Response response) {
        Files.write(Paths.get(filePath), response.asByteArray());
    }
}
