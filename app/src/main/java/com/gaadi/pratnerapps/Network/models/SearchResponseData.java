package com.gaadi.pratnerapps.Network.models;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 18/4/16.
 */
public class SearchResponseData implements Serializable {

    private String min_registration_year;

    private String pFrom2;

    private String max_registration_year;

    private String pFrom3;

    private String max_kms_run;

    private String pFrom1;

    private String total_count;

    private String min_kms_run;

    private String nearbycity_gen;

    private String from2;

    private String min_price;

    private String from3;

    private String max_price;

    private String from1;

    public String getMin_registration_year ()
    {
        return min_registration_year;
    }

    public void setMin_registration_year (String min_registration_year)
    {
        this.min_registration_year = min_registration_year;
    }

    public String getPFrom2 ()
    {
        return pFrom2;
    }

    public void setPFrom2 (String pFrom2)
    {
        this.pFrom2 = pFrom2;
    }

    public String getMax_registration_year ()
    {
        return max_registration_year;
    }

    public void setMax_registration_year (String max_registration_year)
    {
        this.max_registration_year = max_registration_year;
    }

    public String getPFrom3 ()
    {
        return pFrom3;
    }

    public void setPFrom3 (String pFrom3)
    {
        this.pFrom3 = pFrom3;
    }

    public String getMax_kms_run ()
    {
        return max_kms_run;
    }

    public void setMax_kms_run (String max_kms_run)
    {
        this.max_kms_run = max_kms_run;
    }

    public String getPFrom1 ()
    {
        return pFrom1;
    }

    public void setPFrom1 (String pFrom1)
    {
        this.pFrom1 = pFrom1;
    }

    public String getTotal_count ()
    {
        return total_count;
    }

    public void setTotal_count (String total_count)
    {
        this.total_count = total_count;
    }

    public String getMin_kms_run ()
    {
        return min_kms_run;
    }

    public void setMin_kms_run (String min_kms_run)
    {
        this.min_kms_run = min_kms_run;
    }

    public String getNearbycity_gen ()
    {
        return nearbycity_gen;
    }

    public void setNearbycity_gen (String nearbycity_gen)
    {
        this.nearbycity_gen = nearbycity_gen;
    }

    public String getFrom2 ()
    {
        return from2;
    }

    public void setFrom2 (String from2)
    {
        this.from2 = from2;
    }

    public String getMin_price ()
    {
        return min_price;
    }

    public void setMin_price (String min_price)
    {
        this.min_price = min_price;
    }

    public String getFrom3 ()
    {
        return from3;
    }

    public void setFrom3 (String from3)
    {
        this.from3 = from3;
    }

    public String getMax_price ()
    {
        return max_price;
    }

    public void setMax_price (String max_price)
    {
        this.max_price = max_price;
    }

    public String getFrom1 ()
    {
        return from1;
    }

    public void setFrom1 (String from1)
    {
        this.from1 = from1;
    }

    public String getpFrom2() {
        return pFrom2;
    }

    public void setpFrom2(String pFrom2) {
        this.pFrom2 = pFrom2;
    }

    public String getpFrom3() {
        return pFrom3;
    }

    public void setpFrom3(String pFrom3) {
        this.pFrom3 = pFrom3;
    }

    public String getpFrom1() {
        return pFrom1;
    }

    public void setpFrom1(String pFrom1) {
        this.pFrom1 = pFrom1;
    }

    @Override
    public String toString() {
        return "SearchResponseData{" +
                "min_registration_year='" + min_registration_year + '\'' +
                ", pFrom2='" + pFrom2 + '\'' +
                ", max_registration_year='" + max_registration_year + '\'' +
                ", pFrom3='" + pFrom3 + '\'' +
                ", max_kms_run='" + max_kms_run + '\'' +
                ", pFrom1='" + pFrom1 + '\'' +
                ", total_count='" + total_count + '\'' +
                ", min_kms_run='" + min_kms_run + '\'' +
                ", nearbycity_gen='" + nearbycity_gen + '\'' +
                ", from2='" + from2 + '\'' +
                ", min_price='" + min_price + '\'' +
                ", from3='" + from3 + '\'' +
                ", max_price='" + max_price + '\'' +
                ", from1='" + from1 + '\'' +
                '}';
    }
}

