package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 26/5/16.
 */
public class TestimonialApiModel {
    private TestimonialConfigModel config;
    private ArrayList<TestimonialModel> items;

    public TestimonialConfigModel getConfig() {
        return config;
    }

    public void setConfig(TestimonialConfigModel config) {
        this.config = config;
    }

    public ArrayList<TestimonialModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<TestimonialModel> items) {
        this.items = items;
    }

    public class TestimonialConfigModel {
        private String style;
        private String star_rating;
        private String scroll;

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getStar_rating() {
            return star_rating;
        }

        public void setStar_rating(String star_rating) {
            this.star_rating = star_rating;
        }

        public String getScroll() {
            return scroll;
        }

        public void setScroll(String scroll) {
            this.scroll = scroll;
        }
    }

    public static class TestimonialModel {
        private String name;
        private String role;
        private String testimonial;
        private String image_name;
        private int rating;
        private long date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getTestimonial() {
            return testimonial;
        }

        public void setTestimonial(String testimonial) {
            this.testimonial = testimonial;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }
    }
}
