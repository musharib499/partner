package com.gaadi.pratnerapps.models;

import java.util.HashMap;

/**
 * Created by vinodtakhar on 2/6/16.
 */
public class StartupApiModel {
    private VersionModel item;
    private HashMap<String,String> user_colors;

    public VersionModel getVersions() {
        return item;
    }

    public void setVersions(VersionModel item) {
        this.item = item;
    }

    public HashMap<String, String> getUser_colors() {
        return user_colors;
    }

    public void setUser_colors(HashMap<String, String> user_colors) {
        this.user_colors = user_colors;
    }

    public static class VersionModel {
        private int testimonial;
        private int home;
        private int splash;
        private int make;
        private int showroom;
        private int parameters;
        private int city;
        private int contact;
        private int buy_car;
        private int color_theme;
        private int session;
        private int insurance;
        private int finance;
        private int services;
        private int navigations;

        public int getNavigations() {
            return navigations;
        }

        public void setNavigations(int navigations) {
            this.navigations = navigations;
        }

        public int getServices() {
            return services;
        }

        public void setServices(int services) {
            this.services = services;
        }

        public int getSession() {
            return session;
        }

        public void setSession(int session) {
            this.session = session;
        }

        public int getFinance() {
            return finance;
        }

        public void setFinance(int finance) {
            this.finance = finance;
        }

        public int getInsurance() {
            return insurance;
        }

        public void setInsurance(int insurance) {
            this.insurance = insurance;
        }

        public int getColor_theme() {
            return color_theme;
        }

        public void setColor_theme(int color_theme) {
            this.color_theme = color_theme;
        }

        public int getBuy_car() {
            return buy_car;
        }

        public void setBuy_car(int buy_car) {
            this.buy_car = buy_car;
        }

        public int getContact() {
            return contact;
        }

        public void setContact(int contact) {
            this.contact = contact;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getShowroom() {
            return showroom;
        }

        public void setShowroom(int showroom) {
            this.showroom = showroom;
        }

        public int getTestimonial() {
            return testimonial;
        }

        public void setTestimonial(int testimonial) {
            this.testimonial = testimonial;
        }

        public int getHome() {
            return home;
        }

        public void setHome(int home) {
            this.home = home;
        }

        public int getSplash() {
            return splash;
        }

        public void setSplash(int splash) {
            this.splash = splash;
        }

        public int getMake() {
            return make;
        }

        public void setMake(int make) {
            this.make = make;
        }

        public int getParameters() {
            return parameters;
        }

        public void setParameters(int parameters) {
            this.parameters = parameters;
        }
    }
}
