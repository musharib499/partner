package com.gaadi.pratnerapps.annotations;


import com.gaadi.pratnerapps.annotations.rules.Rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by priya on 28/05/15.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueChecker {
    int order();

    int minValue() default Integer.MIN_VALUE;

    int maxValue() default Integer.MAX_VALUE;

    boolean trim() default true;

    String replaceChars() default Rules.REPLACE_CHARS;

    String message() default Rules.EMPTY_STRING;

    int messageResId() default 0;
}
