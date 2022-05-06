package com.apitesting.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.apitesting.enums.CategoryType;

/**
 * This function helps us to pass data to extent reports in the annotation form
 * in test methods.
 */

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {

	public String[] author();

	public CategoryType[] category();

}
