package com.gaadi.pratnerapps.models;

import java.io.Serializable;

/**
 * Created by mushareb ali on 4/8/16.
 */
public class UsedCarValuactionModel implements Serializable {



    private String make, version, regyear, carkm, owner, buyer, city;
    public boolean ismRadioGroup() {
        return mRadioGroup;
    }

    public void setmRadioGroup(boolean mRadioGroup) {
        this.mRadioGroup = mRadioGroup;
    }

    private boolean mRadioGroup;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRegyear() {
        return regyear;
    }

    public void setRegyear(String regyear) {
        this.regyear = regyear;
    }

    public String getCarkm() {
        return carkm;
    }

    public void setCarkm(String carkm) {
        this.carkm = carkm;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UsedCarDetailsModel{");
        sb.append("make='").append(make).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", regyear='").append(regyear).append('\'');
        sb.append(", carkm='").append(carkm).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", buyer='").append(buyer).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
