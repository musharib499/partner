package com.gaadi.pratnerapps.Network.models;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 13/4/16.
 */
public class SearchResponse implements Serializable {

    private boolean success;
    private DataObject data;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataObject getData() {
        return data;
    }

    public void setData(DataObject data) {
        this.data = data;
    }
}
