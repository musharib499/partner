package com.gaadi.pratnerapps.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vaibhav on 6/8/2015.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredName {
    int order();

    boolean trim() default true;

    int minLength() default 0;

    String message() default "Should not contain space and special chars";

    int messageResId() default 0;
}
