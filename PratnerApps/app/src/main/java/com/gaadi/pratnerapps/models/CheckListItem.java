package com.gaadi.pratnerapps.models;

/**
 * Created by lakshaygirdhar on 7/4/16.
 */
public class CheckListItem {

    private String id;
    private String displayName;

    public CheckListItem(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
