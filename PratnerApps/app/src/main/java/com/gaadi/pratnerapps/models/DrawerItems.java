package com.gaadi.pratnerapps.models;

/**
 * Created by kanishroshan on 13/5/16.
 */
public class DrawerItems {
    private String Title;
    private int id;

    public DrawerItems(String title, int id) {
        Title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
