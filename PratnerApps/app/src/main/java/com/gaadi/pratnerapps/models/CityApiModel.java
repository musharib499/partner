package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 3/6/16.
 */
public class CityApiModel {
    private ArrayList<StateModel> items;

    public ArrayList<StateModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<StateModel> items) {
        this.items = items;
    }

    public static class StateModel{
        private int state_id;
        private String state_name;
        private ArrayList<CityModel> cities;

        public int getState_id() {
            return state_id;
        }

        public void setState_id(int state_id) {
            this.state_id = state_id;
        }

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
        }

        public ArrayList<CityModel> getCities() {
            return cities;
        }

        public void setCities(ArrayList<CityModel> cities) {
            this.cities = cities;
        }
    }

    public static class CityModel{
        private int city_id;
        private String name;
        private String pin_code;

        public int getCityId() {
            return city_id;
        }

        public void setCityId(int city_id) {
            this.city_id = city_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPin_code() {
            return pin_code;
        }

        public void setPin_code(String pin_code) {
            this.pin_code = pin_code;
        }
    }
}
