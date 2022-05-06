package com.apitesting.reports;

import java.util.Objects;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.apitesting.constants.FrameworkConstants;
import com.apitesting.enums.CategoryType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReport {

    // to avoid external instantiation
    private ExtentReport() {
    }
    /*
     * private static ExtentReports extent;
     * private static ExtentTest test;
     * 
     * public static void initReports() {
     * extent = new ExtentReports();
     * ExtentSparkReporter spark = new ExtentSparkReporter("Reports/Report.html");
     * extent.attachReporter(spark);
     * }
     * 
     * public static void tearDownReports() {
     * extent.flush();
     * }
     * 
     * public static void createTest(String name) {
     * test = extent.createTest(name);
     * ExtentManager.setTest(test);
     * }
     */

    private static ExtentReports extent;

    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportPath());
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("API-Testing");
            spark.config().setReportName("API-Automation");
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        ExtentManager.unload();
        try {
            Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportPath()).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTest(String testcasename) {
        ExtentManager.setTest(extent.createTest(testcasename));
    }

    public static void addAuthors(String[] authors) {
        for (String temp : authors) {
            ExtentManager.getTest().assignAuthor(temp);
        }
    }

    public static void addCategories(CategoryType[] categories) {
        for (CategoryType temp : categories) {
            ExtentManager.getTest().assignCategory(temp.toString());
        }
    }

}
