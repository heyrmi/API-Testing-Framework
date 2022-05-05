package com.apitesting.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReport {

    // to avoid external instantiation
    private ExtentReport() {
    }

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReports() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/Report.html");
        extent.attachReporter(spark);
    }

    public static void tearDownReports() {
        extent.flush();
    }

    public static void createTest(String name) {
        test = extent.createTest(name);
        ExtentManager.setTest(test);
    }
}
