/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.gaadi.pratnerapps.annotations;


import com.gaadi.pratnerapps.annotations.rules.Rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Number rule annotation. Allows a specific primitive type contained in {@link com.nightstay.android.app.annotations.NumberRule.NumberType}.
 * Additional options such as greater than (>), less than (<) and equals (==) are available.
 *
 * @author Ankit Garg <ankit.garg@jeevansathi.com>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberRule {
    int order();

    NumberType type();

    double gt() default Double.MAX_VALUE;

    double lt() default Double.MIN_VALUE;

    double eq() default Double.MAX_VALUE;

    String message() default Rules.EMPTY_STRING;

    boolean isNull() default false;

    int messageResId() default 0;

    enum NumberType {
        INTEGER, LONG, FLOAT, DOUBLE
    }
}
