package com.apitesting.reports;

import com.apitesting.constants.FrameworkConstants;
import com.apitesting.enums.CategoryType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    // to avoid external instantiation
    private ExtentReport() {
    }

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

        // TODO: Enable this when running tests in local
        // It will automatically open latest Extent Report in the browser

        // try {
        // Desktop.getDesktop().browse(new
        // File(FrameworkConstants.getExtentReportPath()).toURI());
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
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
