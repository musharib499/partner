package com.gaadi.pratnerapps.models;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 18/4/16.
 */
public class VDPResponse implements Serializable {

    private VDPData data;

    private String success;

    public VDPData getData ()
    {
        return data;
    }

    public void setData (VDPData data)
    {
        this.data = data;
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
        return "ClassPojo [data = "+data+", success = "+success+"]";
    }
}
