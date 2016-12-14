package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 8/6/16.
 */
public class ContactUsApiModel {
    private ArrayList<ContactUsModel> item;

    public ArrayList<ContactUsModel> getItem() {
        return item;
    }

    public void setItem(ArrayList<ContactUsModel> item) {
        this.item = item;
    }

    public static class ContactUsModel{
        private String address;
        private String state;
        private String city;
        private String mobile;
        private String dealer_mobile;
        private String know_v_number;
        private String know_c_number;
        private String email;
        private String mobilesms;
        private String lat;
        private String lang;
        private String facebook_url;
        private String google_url;
        private String twitter_url;

        public String getInstagram_url() {
            return instagram_url;
        }

        public void setInstagram_url(String instagram_url) {
            this.instagram_url = instagram_url;
        }

        private String instagram_url;
        private ArrayList<String> images;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDealer_mobile() {
            return dealer_mobile;
        }

        public void setDealer_mobile(String dealer_mobile) {
            this.dealer_mobile = dealer_mobile;
        }

        public String getKnow_v_number() {
            return know_v_number;
        }

        public void setKnow_v_number(String know_v_number) {
            this.know_v_number = know_v_number;
        }

        public String getKnow_c_number() {
            return know_c_number;
        }

        public void setKnow_c_number(String know_c_number) {
            this.know_c_number = know_c_number;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobilesms() {
            return mobilesms;
        }

        public void setMobilesms(String mobilesms) {
            this.mobilesms = mobilesms;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getFacebook_url() {
            return facebook_url;
        }

        public void setFacebook_url(String facebook_url) {
            this.facebook_url = facebook_url;
        }

        public String getGoogle_url() {
            return google_url;
        }

        public void setGoogle_url(String google_url) {
            this.google_url = google_url;
        }

        public String getTwitter_url() {
            return twitter_url;
        }

        public void setTwitter_url(String twitter_url) {
            this.twitter_url = twitter_url;
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }
    }
}
