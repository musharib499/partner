package com.gaadi.pratnerapps.annotations;


import com.gaadi.pratnerapps.annotations.rules.Rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ankit on 16/10/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumber {
    int order();

    int minLength() default 12;

    int maxLength() default 16;

    boolean trim() default true;

    String message() default Rules.EMPTY_STRING;

    int messageResId() default 0;
}
