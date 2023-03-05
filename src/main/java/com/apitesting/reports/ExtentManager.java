package com.apitesting.reports;

import com.aventstack.extentreports.ExtentTest;

import java.util.Objects;

public final class ExtentManager {

    // to avoid external instantiation
    private ExtentManager() {
    }

    // ThreadLocal variable to safeguard the ExtentTest variable
    // It gives access to the thread that stated the execution,
    // thus ensures thread safety

    private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

    static ExtentTest getTest() {
        return extTest.get();
    }

    static void setTest(ExtentTest test) {
        if (Objects.nonNull(test)) {
            extTest.set(test);
        }
    }

    static void unload() {
        extTest.remove();
    }

}
