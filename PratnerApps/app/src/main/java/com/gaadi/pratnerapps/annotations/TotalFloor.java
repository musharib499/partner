package com.gaadi.pratnerapps.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Priya
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TotalFloor {

    int order();

    boolean trim() default true;

    int minLength() default 0;

    //String message()      default Constants.TOTAL_FLOORS_ERROR_MESSAGE;
    int messageResId() default 0;
}
