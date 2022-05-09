package com.apitesting.annotation;

import com.apitesting.enums.CategoryType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Here we are making an annotation which will help us to pass data to extent
 * reports in the annotation form in test methods.
 */

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {

    public String[] author() default "Drip Capital";

    public CategoryType[] category() default CategoryType.STORYTESTING;

}
