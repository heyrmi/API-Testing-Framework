package com.apitesting.listeners;

import com.apitesting.annotation.FrameworkAnnotation;
import com.apitesting.enums.ConfigProperties;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.reports.ExtentReport;
import com.apitesting.utils.JiraUtils;
import com.apitesting.utils.PropertyUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
        ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author());
        ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName() + " is passed.");
        // If the test passes there will not be any throwable
        // thus below statement is not required
        // ExtentLogger.pass(String.valueOf(result.getThrowable()));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentLogger.failWithExtentColor(String.valueOf(result.getThrowable()));
        ExtentLogger.fail(result.getMethod().getMethodName() + " is failed.");

        // If configured then log issue in Jira
        if (PropertyUtils.getValue(ConfigProperties.CREATEISSUEINJIRA).equalsIgnoreCase("yes")) {
            String issueInJira = JiraUtils.createIssueInJira(String.valueOf(result.getThrowable()));
            ExtentLogger.fail("Issue created in Jira: " + PropertyUtils.getValue(ConfigProperties.CREATEISSUEINJIRA) + issueInJira);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(String.valueOf(result.getThrowable()));
        ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped.");

    }
}
