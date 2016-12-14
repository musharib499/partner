package com.gaadi.pratnerapps.Network.models;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 18/4/16.
 */
public class VDPData implements Serializable {

    private VDPResponseData response;

    private String responsecode;

    private String success;

    public VDPResponseData getResponse ()
    {
        return response;
    }

    public void setResponse (VDPResponseData response)
    {
        this.response = response;
    }

    public String getResponsecode ()
    {
        return responsecode;
    }

    public void setResponsecode (String responsecode)
    {
        this.responsecode = responsecode;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", responsecode = "+responsecode+", success = "+success+"]";
    }
}
