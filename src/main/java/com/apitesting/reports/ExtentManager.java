package com.apitesting.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

    // to avoid external instantiation
    private ExtentManager() {
    }

    // ThreadLocal variable to safeguard the ExtentTest variable
    // It gives acces to the thread that stated the execution,
    // thus ensures thread safety

    private static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();

    // Getter for test
    static ExtentTest getTest() {
        return threadLocalTest.get();
    }

    // Setter for test
    static void setTest(ExtentTest test) {
        threadLocalTest.set(test);
    }
}
