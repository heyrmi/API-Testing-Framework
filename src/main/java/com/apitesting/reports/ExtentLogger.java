package com.apitesting.reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public final class ExtentLogger {
    // to avoid external instantiation
    private ExtentLogger() {
    }

    public static void pass(String message) {
        ExtentManager.getTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void info(String message) {
        ExtentManager.getTest().info(message);
    }

    public static void skip(String message) {
        ExtentManager.getTest().skip(message);
    }

    public static void logResponse(String message) {
        ExtentManager.getTest().info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));

    }

}
