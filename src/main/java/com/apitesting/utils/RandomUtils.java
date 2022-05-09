package com.apitesting.utils;

import com.github.javafaker.Faker;

public final class RandomUtils {
    // to avoid external instantiation
    private RandomUtils() {
    }

    static Faker faker = new Faker();

    public static int getRandomNumberBetween(int low, int high) {
        return faker.number().numberBetween(low, high);
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomJobTitle() {
        return faker.job().title();
    }

    public static String getRandomText() {
        return faker.lorem().sentence();
    }

    public static String getRandomParagraph() {
        return faker.lorem().paragraph();
    }
}
