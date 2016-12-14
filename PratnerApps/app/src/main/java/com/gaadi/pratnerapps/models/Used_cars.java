package com.gaadi.pratnerapps.models;

import android.graphics.Color;
import android.location.Location;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 18/4/16.
 */
public class Used_cars implements Serializable {
    private String city_name;

    private Location location;

    private String kms_run;

    private String variant_id;

    private String registration_month;

    private String model_name;

    private String img_count;

    private String city_id;

    private String updated_date;

    private String make_id;

    private String certification_id;

    private String profile_pic;

    private String image_urls_all;

    private String transmission_type;

    private String goodness_score;

    private String created_date;

    private String listing_dealer_info;

    private String color_segment;

    private String body_type;

    private String listing_type;

    private String registration_year;

    private String image_urls;

    private String used_car_id;

    private Make_nested make_nested;

    private String posted_by;

    private Car_name car_name;

    private String make_name;

    private String km_segment;

    private String variant_name;

    private String price_segment;

    private String fuel_type;

    private String price;

    private String images_processed;

    private Color color;

    private String model_id;

    private String age_segment;

    private String owner;

    private IdNameObject locality;

    private String last_updated;

    private String is_premium;

    private String is_trustmark;

    private String image_flag;

    private String price_new;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getKms_run() {
        return kms_run;
    }

    public void setKms_run(String kms_run) {
        this.kms_run = kms_run;
    }

    public String getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(String variant_id) {
        this.variant_id = variant_id;
    }

    public String getRegistration_month() {
        return registration_month;
    }

    public void setRegistration_month(String registration_month) {
        this.registration_month = registration_month;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getImg_count() {
        return img_count;
    }

    public void setImg_count(String img_count) {
        this.img_count = img_count;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getMake_id() {
        return make_id;
    }

    public void setMake_id(String make_id) {
        this.make_id = make_id;
    }

    public String getCertification_id() {
        return certification_id;
    }

    public void setCertification_id(String certification_id) {
        this.certification_id = certification_id;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getImage_urls_all() {
        return image_urls_all;
    }

    public void setImage_urls_all(String image_urls_all) {
        this.image_urls_all = image_urls_all;
    }

    public String getTransmission_type() {
        return transmission_type;
    }

    public void setTransmission_type(String transmission_type) {
        this.transmission_type = transmission_type;
    }

    public String getGoodness_score() {
        return goodness_score;
    }

    public void setGoodness_score(String goodness_score) {
        this.goodness_score = goodness_score;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getListing_dealer_info() {
        return listing_dealer_info;
    }

    public void setListing_dealer_info(String listing_dealer_info) {
        this.listing_dealer_info = listing_dealer_info;
    }

    public String getColor_segment() {
        return color_segment;
    }

    public void setColor_segment(String color_segment) {
        this.color_segment = color_segment;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getListing_type() {
        return listing_type;
    }

    public void setListing_type(String listing_type) {
        this.listing_type = listing_type;
    }

    public String getRegistration_year() {
        return registration_year;
    }

    public void setRegistration_year(String registration_year) {
        this.registration_year = registration_year;
    }

    public String getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(String image_urls) {
        this.image_urls = image_urls;
    }

    public String getUsed_car_id() {
        return used_car_id;
    }

    public void setUsed_car_id(String used_car_id) {
        this.used_car_id = used_car_id;
    }

    public Make_nested getMake_nested() {
        return make_nested;
    }

    public void setMake_nested(Make_nested make_nested) {
        this.make_nested = make_nested;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }

    public Car_name getCar_name() {
        return car_name;
    }

    public void setCar_name(Car_name car_name) {
        this.car_name = car_name;
    }

    public String getMake_name() {
        return make_name;
    }

    public void setMake_name(String make_name) {
        this.make_name = make_name;
    }

    public String getKm_segment() {
        return km_segment;
    }

    public void setKm_segment(String km_segment) {
        this.km_segment = km_segment;
    }

    public String getVariant_name() {
        return variant_name;
    }

    public void setVariant_name(String variant_name) {
        this.variant_name = variant_name;
    }

    public String getPrice_segment() {
        return price_segment;
    }

    public void setPrice_segment(String price_segment) {
        this.price_segment = price_segment;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImages_processed() {
        return images_processed;
    }

    public void setImages_processed(String images_processed) {
        this.images_processed = images_processed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getAge_segment() {
        return age_segment;
    }

    public void setAge_segment(String age_segment) {
        this.age_segment = age_segment;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public IdNameObject getLocality() {
        return locality;
    }

    public void setLocality(IdNameObject locality) {
        this.locality = locality;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(String is_premium) {
        this.is_premium = is_premium;
    }

    public String getIs_trustmark() {
        return is_trustmark;
    }

    public void setIs_trustmark(String is_trustmark) {
        this.is_trustmark = is_trustmark;
    }

    public String getImage_flag() {
        return image_flag;
    }

    public void setImage_flag(String image_flag) {
        this.image_flag = image_flag;
    }

    public String getPrice_new() {
        return price_new;
    }

    public void setPrice_new(String price_new) {
        this.price_new = price_new;
    }

    @Override
    public String toString() {
        return "ClassPojo [city_name = " + city_name + ", location = " + location + ", kms_run = " + kms_run + ", variant_id = " + variant_id + ", registration_month = " + registration_month + ", model_name = " + model_name + ", img_count = " + img_count + ", city_id = " + city_id + ", updated_date = " + updated_date + ", make_id = " + make_id + ", certification_id = " + certification_id + ", profile_pic = " + profile_pic + ", image_urls_all = " + image_urls_all + ", transmission_type = " + transmission_type + ", goodness_score = " + goodness_score + ", created_date = " + created_date + ", listing_dealer_info = " + listing_dealer_info + ", color_segment = " + color_segment + ", body_type = " + body_type + ", listing_type = " + listing_type + ", registration_year = " + registration_year + ", image_urls = " + image_urls + ", used_car_id = " + used_car_id + ", make_nested = " + make_nested + ", posted_by = " + posted_by + ", car_name = " + car_name + ", make_name = " + make_name + ", km_segment = " + km_segment + ", variant_name = " + variant_name + ", price_segment = " + price_segment + ", fuel_type = " + fuel_type + ", price = " + price + ", images_processed = " + images_processed + ", color = " + color + ", model_id = " + model_id + ", age_segment = " + age_segment + ", owner = " + owner + ", locality = " + locality + ", last_updated = " + last_updated + ", is_premium = " + is_premium + ", is_trustmark = " + is_trustmark + ", image_flag = " + image_flag + ", price_new = " + price_new + "]";
    }
}
