package com.apitesting.listeners;

import com.apitesting.reports.ExtentLogger;
import com.apitesting.reports.ExtentReport;

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
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(String.valueOf(result.getThrowable()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(String.valueOf(result.getThrowable()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(String.valueOf(result.getThrowable()));

    }
}
