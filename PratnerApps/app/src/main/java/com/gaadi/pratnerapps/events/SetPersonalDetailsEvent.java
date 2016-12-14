package com.gaadi.pratnerapps.events;

/**
 * Created by priyarawat on 8/6/16.
 */
public class SetPersonalDetailsEvent {
    private String name;
    private String email;
    private String contactNum;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SetPersonalDetailsEvent(String name, String email, String contactNum, String location) {
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
        this.location = location;
    }
}
