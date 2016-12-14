package com.gaadi.pratnerapps.annotations;


import com.gaadi.pratnerapps.annotations.rules.Rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ankitgarg on 17/2/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoContactNumber {
    int order();

    int minLength() default 0;

    boolean trim() default true;

    String message() default Rules.EMPTY_STRING;

    int messageResId() default 0;
}
