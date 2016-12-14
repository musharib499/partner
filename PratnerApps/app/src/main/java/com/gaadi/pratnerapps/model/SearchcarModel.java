package com.gaadi.pratnerapps.model;

/**
 * Created by kanishroshan on 4/4/16.
 */
public class SearchcarModel {

    String price, kiloMeter;
    String year;

    public SearchcarModel(String kiloMeter, String price, String year) {
        this.kiloMeter = kiloMeter;
        this.price = price;
        this.year = year;
    }

    public String getKiloMeter() {
        return kiloMeter;
    }

    public void setKiloMeter(String kiloMeter) {
        this.kiloMeter = kiloMeter;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}



