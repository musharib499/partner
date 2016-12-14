
package com.gaadi.pratnerapps.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BuyCarMeta implements Serializable {

    @SerializedName("records_per_page")
    @Expose
    private int recordsPerPage;
    @SerializedName("total_records")
    @Expose
    private int totalRecords;
    @SerializedName("current_page")
    @Expose
    private int currentPage;

    /**
     *
     * @return
     *     The recordsPerPage
     */
    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    /**
     *
     * @param recordsPerPage
     *     The records_per_page
     */
    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    /**
     *
     * @return
     *     The totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     *
     * @param totalRecords
     *     The total_records
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     *
     * @return
     *     The currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     *
     * @param currentPage
     *     The current_page
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

   /* @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
*/
}
