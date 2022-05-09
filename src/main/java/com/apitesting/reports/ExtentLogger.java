package com.apitesting.reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Objects;

public final class ExtentLogger {
    // to avoid external instantiation
    private ExtentLogger() {
    }

    public static void pass(String message) {
        ExtentManager.getTest().pass(message);
    }

    public static void failWithExtentColor(String message) {
        ExtentManager.getTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void fail(String message) {
        ExtentManager.getTest().fail(message);
    }


    public static void info(String message) {
        ExtentManager.getTest().info(message);
    }

    public static void skip(String message) {
        ExtentManager.getTest().skip(message);
    }

    public static void logResponse(String message) {
        info("Response Details: ");
        ExtentManager.getTest().info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));

    }

    public static void logRequest(RequestSpecification requestSpecification) {
        // To query on the resoponse
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        info("Request Details: ");
        ExtentManager.getTest().info(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));

        // For logging headers only if they exist
        if (Objects.nonNull(query.getHeaders())) {
            info("Headers: ");
            for (var header : query.getHeaders()) {
                info(header.getName() + ": " + header.getValue());
            }
        }
    }

}
