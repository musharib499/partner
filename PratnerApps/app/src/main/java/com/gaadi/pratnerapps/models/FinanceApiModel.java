package com.gaadi.pratnerapps.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 3/8/16.
 */
public class FinanceApiModel extends BaseResponseModel{
    private String heroImage;
    private ArrayList<PartnersModel> partners;

    public String getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public ArrayList<PartnersModel> getPartners() {
        return partners;
    }

    public void setPartners(ArrayList<PartnersModel> partners) {
        this.partners = partners;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FinanceApiModel{");
        sb.append("heroImage='").append(heroImage).append('\'');
        sb.append(", partners=").append(partners);
        sb.append('}');
        return sb.toString();
    }
    public static class PartnersModel{
        @SerializedName("icon_url")
        private String image;
        private String name;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return image;
        }
    }
}

