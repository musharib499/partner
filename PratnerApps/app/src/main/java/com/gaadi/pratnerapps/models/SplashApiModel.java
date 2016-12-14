package com.gaadi.pratnerapps.models;

/**
 * Created by vinodtakhar on 13/6/16.
 */
public class SplashApiModel {
    private SplashConfig item;

    public SplashConfig getItem() {
        return item;
    }

    public void setItem(SplashConfig item) {
        this.item = item;
    }

    public static class SplashConfig{
        private String message;
        private String logo_url;
        private String image_url;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
