package com.apitesting.tests;

import java.lang.reflect.Method;

import com.apitesting.reports.ExtentLogger;
import com.apitesting.reports.ExtentReport;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setUpSuite() {
        ExtentReport.initReports();
    }

    @AfterSuite
    public void tearDownSuite() {
        ExtentReport.tearDownReports();
    }

    @BeforeMethod
    public void setUpTest(Method m) {
        ExtentReport.createTest(m.getName());
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        if (!result.isSuccess()) {
            ExtentLogger.fail(String.valueOf(result.getThrowable()));
        }
    }
}
