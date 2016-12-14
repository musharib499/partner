package com.gaadi.pratnerapps.model;

import java.util.HashMap;

/**
 * Created by vinodtakhar on 30/5/16.
 */
public class DealerTheme {
    private HashMap<String,String> values = new HashMap<>();

    public String getProperty(String property) {
        return values.get(property);
    }

    public void setThemeValues(HashMap<String, String> values) {
        this.values = values;
    }

    public HashMap<String, String> getThemeValues() {
        return values;
    }
}
