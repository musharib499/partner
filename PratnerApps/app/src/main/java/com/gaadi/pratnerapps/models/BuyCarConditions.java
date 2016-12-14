
package com.gaadi.pratnerapps.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BuyCarConditions implements Serializable {

    @SerializedName("exterior")
    @Expose
    private String exterior;
    @SerializedName("interior")
    @Expose
    private String interior;
    @SerializedName("bodyframe")
    @Expose
    private String bodyframe;
    @SerializedName("etc")
    @Expose
    private String etc;
    @SerializedName("electrical")
    @Expose
    private String electrical;
    @SerializedName("susste")
    @Expose
    private String susste;
    @SerializedName("acheater")
    @Expose
    private String acheater;
    @SerializedName("tires")
    @Expose
    private String tires;
    @SerializedName("breaks")
    @Expose
    private String breaks;
    @SerializedName("suspention_steering")
    @Expose
    private String suspention;
    @SerializedName("overall_condition")
    @Expose
    private String overall_condition;
    @SerializedName("engine_transmission")
    @Expose
    private String engine;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }


    public String getOverall_condition() {
        return overall_condition;
    }

    public void setOverall_condition(String overall_condition) {
        this.overall_condition = overall_condition;
    }


    public String getSuspention() {
        return suspention;
    }

    public void setSuspention(String suspention) {
        this.suspention = suspention;
    }



    /**
     * @return The exterior
     */
    public String getExterior() {
        return exterior;
    }

    /**
     * @param exterior The exterior
     */
    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    /**
     * @return The interior
     */
    public String getInterior() {
        return interior;
    }

    /**
     * @param interior The interior
     */
    public void setInterior(String interior) {
        this.interior = interior;
    }

    /**
     * @return The bodyframe
     */
    public String getBodyframe() {
        return bodyframe;
    }

    /**
     * @param bodyframe The bodyframe
     */
    public void setBodyframe(String bodyframe) {
        this.bodyframe = bodyframe;
    }

    /**
     * @return The etc
     */
    public String getEtc() {
        return etc;
    }

    /**
     * @param etc The etc
     */
    public void setEtc(String etc) {
        this.etc = etc;
    }

    /**
     * @return The electrical
     */
    public String getElectrical() {
        return electrical;
    }

    /**
     * @param electrical The electrical
     */
    public void setElectrical(String electrical) {
        this.electrical = electrical;
    }

    /**
     * @return The susste
     */
    public String getSusste() {
        return susste;
    }

    /**
     * @param susste The susste
     */
    public void setSusste(String susste) {
        this.susste = susste;
    }

    /**
     * @return The acheater
     */
    public String getAcheater() {
        return acheater;
    }

    /**
     * @param acheater The acheater
     */
    public void setAcheater(String acheater) {
        this.acheater = acheater;
    }

    /**
     * @return The tires
     */
    public String getTires() {
        return tires;
    }

    /**
     * @param tires The tires
     */
    public void setTires(String tires) {
        this.tires = tires;
    }

    /**
     * @return The breaks
     */
    public String getBreaks() {
        return breaks;
    }

    /**
     * @param breaks The breaks
     */
    public void setBreaks(String breaks) {
        this.breaks = breaks;
    }

   /* @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
*/
}
