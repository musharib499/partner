package com.gaadi.pratnerapps.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author vinodtakhar
 * @version 1.0
 * @since 5/9/16
 */
public class ServicesApiModel extends BaseResponseModel{
    @SerializedName("services_provided")
    private ArrayList<ParameterApiModel.KeyValueModel> services;

    private String home_pickup_available;

    public ArrayList<ParameterApiModel.KeyValueModel> getServices() {
        return services;
    }

    public void setServices(ArrayList<ParameterApiModel.KeyValueModel> services) {
        this.services = services;
    }
    public String getHome_pickup_available() {
        return home_pickup_available;
    }

    public void setHome_pickup_available(String home_pickup_available) {
        this.home_pickup_available = home_pickup_available;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServicesApiModel{");
        sb.append("services=").append(services);
        sb.append(", showPick=").append(home_pickup_available);
        sb.append('}');
        return sb.toString();
    }
}
