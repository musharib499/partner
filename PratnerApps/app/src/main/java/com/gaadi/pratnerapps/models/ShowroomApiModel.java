package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 2/6/16.
 */
public class ShowroomApiModel {
    private ShowroomConfigModel config;
    private ArrayList<ShowroomModel> items;

    public ShowroomConfigModel getConfig() {
        return config;
    }

    public void setConfig(ShowroomConfigModel config) {
        this.config = config;
    }

    public ArrayList<ShowroomModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<ShowroomModel> items) {
        this.items = items;
    }

    public class ShowroomConfigModel{
        private String slide;

        public String getSlide() {
            return slide;
        }

        public void setSlide(String slide) {
            this.slide = slide;
        }
    }

    public static class ShowroomModel{
        private int id;
        private String name;
        private String lat;
        private String lng;
        private String city;
        private String locality;
        private String state;
        private String address;
        private String pin_code;
        private ArrayList<String> phone;
        private ArrayList<String> email;
        private String mobile;
        private ArrayList<String> images;

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPin_code() {
            return pin_code;
        }

        public void setPin_code(String pin_code) {
            this.pin_code = pin_code;
        }

        public ArrayList<String> getPhone() {
            return phone;
        }

        public void setPhone(ArrayList<String> phone) {
            this.phone = phone;
        }

        public ArrayList<String> getEmail() {
            return email;
        }

        public void setEmail(ArrayList<String> email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }
    }
}
