package com.gaadi.pratnerapps.models;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 18/4/16.
 */
public class DataObject implements Serializable {

    private SearchResponseData response;

    private String status;

    public SearchResponseData getResponse() {
        return response;
    }

    public void setResponse(SearchResponseData response) {
        this.response = response;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return " [response = "+response+", status = "+status+"]";
    }
}
