package com.gaadi.pratnerapps.events;

/**
 * Created by priyarawat on 9/6/16.
 */
public class SwitchFragment {
    boolean isValidate;
    public SwitchFragment(boolean isValidate) {
        this.isValidate = isValidate;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setIsValidate(boolean isValidate) {
        this.isValidate = isValidate;
    }
}
