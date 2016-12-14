package com.gaadi.pratnerapps.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vinodtakhar on 6/6/16.
 */
public class ParameterApiModel {
    private ArrayList<KeyValueModel> sorting;
    private ArrayList<KeyValueModel> months;
    private ArrayList<KeyValueModel>  fuel;
    private ArrayList<KeyValueModel>  planning_to_buy;
    private ArrayList<KeyValueModel>  price;
    private ArrayList<KeyValueModel>  price_range;
    private ArrayList<KeyValueModel> colors;
    private ArrayList<KeyValueModel> body_type;
    private HashMap<String,String> theme_light;
    private HashMap<String,String> theme_dark;

    public ArrayList<KeyValueModel> getColors() {
        return colors;
    }

    public void setColors(ArrayList<KeyValueModel> colors) {
        this.colors = colors;
    }

    public ArrayList<KeyValueModel> getBody_type() {
        return body_type;
    }

    public void setBody_type(ArrayList<KeyValueModel> body_type) {
        this.body_type = body_type;
    }

    public HashMap<String, String> getTheme_light() {
        return theme_light;
    }

    public void setTheme_light(HashMap<String, String> theme_light) {
        this.theme_light = theme_light;
    }

    public HashMap<String, String> getTheme_dark() {
        return theme_dark;
    }

    public void setTheme_dark(HashMap<String, String> theme_dark) {
        this.theme_dark = theme_dark;
    }

    public ArrayList<KeyValueModel> getPrice_range() {
        return price_range;
    }

    public void setPrice_range(ArrayList<KeyValueModel> price_range) {
        this.price_range = price_range;
    }

    public ArrayList<KeyValueModel> getSorting() {
        return sorting;
    }

    public void setSorting(ArrayList<KeyValueModel> sorting) {
        this.sorting = sorting;
    }

    public ArrayList<KeyValueModel> getMonths() {
        return months;
    }

    public void setMonths(ArrayList<KeyValueModel> months) {
        this.months = months;
    }

    public ArrayList<KeyValueModel> getFuel() {
        return fuel;
    }

    public void setFuel(ArrayList<KeyValueModel> fuel) {
        this.fuel = fuel;
    }

    public ArrayList<KeyValueModel> getPlanning_to_buy() {
        return planning_to_buy;
    }

    public ArrayList<KeyValueModel> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<KeyValueModel> price) {
        this.price = price;
    }

    public void setPlanning_to_buy(ArrayList<KeyValueModel> planning_to_buy) {
        this.planning_to_buy = planning_to_buy;
    }

    public static class KeyValueModel{
        private String  key;
        private String value;
        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String toString(){return getValue();}
    }
}
