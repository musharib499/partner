
package com.gaadi.pratnerapps.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class BuyCarIInfo implements Serializable {

    @SerializedName("_meta")
    @Expose
    private BuyCarMeta meta;
    @SerializedName("items")
    @Expose
    private ArrayList<BuyCarDetails> items = new ArrayList<BuyCarDetails>();

    /**
     *
     * @return
     *     The meta
     */
    public BuyCarMeta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     *     The _meta
     */
    public void setMeta(BuyCarMeta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     *     The items
     */
    public ArrayList<BuyCarDetails> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(ArrayList<BuyCarDetails> items) {
        this.items = items;
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
*/
}
