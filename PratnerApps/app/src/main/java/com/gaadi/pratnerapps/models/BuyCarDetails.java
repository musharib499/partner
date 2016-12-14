
package com.gaadi.pratnerapps.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuyCarDetails implements Serializable {


    @SerializedName("gaadi_id")
    @Expose
    private Integer gaadiId;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("carversion")
    @Expose
    private String carversion;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("kilometer_driven")
    @Expose
    private String kilometerDriven;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("insurance")
    @Expose
    private String insurance;
    @SerializedName("fuel_type")
    @Expose
    private String fuelType;
    @SerializedName("regno")
    @Expose
    private String regno;
    @SerializedName("regplace")
    @Expose
    private String regplace;
    @SerializedName("variant")
    @Expose
    private String variant;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("colour")
    @Expose
    private String colour;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("car_certification")
    @Expose
    private String carCertification;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("images")
    @Expose
    private ArrayList<String> images = new ArrayList<String>();
    @SerializedName("conditions")
    @Expose
    private BuyCarConditions conditions;
    @SerializedName("features")
    @Expose
    private ArrayList<String> features = new ArrayList<String>();
    @SerializedName("specification")
    @Expose
    private List<Object> specification = new ArrayList<Object>();

    /**
     *
     * @return
     * The gaadiId
     */
    public Integer getGaadiId() {
        return gaadiId;
    }

    /**
     *
     * @param gaadiId
     * The gaadi_id
     */
    public void setGaadiId(Integer gaadiId) {
        this.gaadiId = gaadiId;
    }

    /**
     *
     * @return
     * The make
     */
    public String getMake() {
        return make;
    }

    /**
     *
     * @param make
     * The make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     *
     * @return
     * The model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     * The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return
     * The carversion
     */
    public String getCarversion() {
        return carversion;
    }

    /**
     *
     * @param carversion
     * The carversion
     */
    public void setCarversion(String carversion) {
        this.carversion = carversion;
    }

    /**
     *
     * @return
     * The createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     * The created_date
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     *
     * @return
     * The kilometerDriven
     */
    public String getKilometerDriven() {
        return kilometerDriven;
    }

    /**
     *
     * @param kilometerDriven
     * The kilometer_driven
     */
    public void setKilometerDriven(String kilometerDriven) {
        this.kilometerDriven = kilometerDriven;
    }

    /**
     *
     * @return
     * The month
     */
    public String getMonth() {
        return month;
    }

    /**
     *
     * @param month
     * The month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     *
     * @return
     * The year
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return
     * The insurance
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * @param insurance The insurance
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    /**
     * @return The fuelType
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * @param fuelType The fuel_type
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * @return The regno
     */
    public String getRegno() {
        return regno;
    }

    /**
     * @param regno The regno
     */
    public void setRegno(String regno) {
        this.regno = regno;
    }

    /**
     * @return The regplace
     */
    public String getRegplace() {
        return regplace;
    }

    /**
     * @param regplace The regplace
     */
    public void setRegplace(String regplace) {
        this.regplace = regplace;
    }

    /**
     * @return The variant
     */
    public String getVariant() {
        return variant;
    }

    /**
     * @param variant The variant
     */
    public void setVariant(String variant) {
        this.variant = variant;
    }

    /**
     * @return The tax
     */
    public String getTax() {
        return tax;
    }

    /**
     * @param tax The tax
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * @return The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The colour
     */
    public String getColour() {
        return colour;
    }

    /**
     *
     * @param colour
     * The colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     * The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     * The carCertification
     */
    public String getCarCertification() {
        return carCertification;
    }

    /**
     *
     * @param carCertification
     * The car_certification
     */
    public void setCarCertification(String carCertification) {
        this.carCertification = carCertification;
    }

    /**
     *
     * @return
     * The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     * The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     * The images
     */
    public ArrayList<String> getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The conditions
     */
    public BuyCarConditions getConditions() {
        return conditions;
    }

    /**
     *
     * @param conditions
     * The conditions
     */
    public void setConditions(BuyCarConditions conditions) {
        this.conditions = conditions;
    }

    /**
     *
     * @return
     * The features
     */
    public ArrayList<String> getFeatures() {
        return features;
    }

    /**
     *
     * @param features
     * The features
     */
    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    /**
     *
     * @return
     * The specification
     */
  /*  public ArrayList<Object> getSpecification() {
        return specification;
    }

    *//**
     *
     * @param specification
     * The specification
     *//*
    public void setSpecification(ArrayList<Object> specification) {
        this.specification = specification;
    }*/
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
