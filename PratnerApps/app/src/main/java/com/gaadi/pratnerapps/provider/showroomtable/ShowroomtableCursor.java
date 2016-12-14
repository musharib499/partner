package com.gaadi.pratnerapps.provider.showroomtable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code showroomtable} table.
 */
public class ShowroomtableCursor extends AbstractCursor implements ShowroomtableModel {
    public ShowroomtableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ShowroomtableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code showroom_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getShowroomId() {
        Integer res = getIntegerOrNull(ShowroomtableColumns.SHOWROOM_ID);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(ShowroomtableColumns.NAME);
        return res;
    }

    /**
     * Get the {@code latitude} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLatitude() {
        String res = getStringOrNull(ShowroomtableColumns.LATITUDE);
        return res;
    }

    /**
     * Get the {@code longitude} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLongitude() {
        String res = getStringOrNull(ShowroomtableColumns.LONGITUDE);
        return res;
    }

    /**
     * Get the {@code dealer_type} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDealerType() {
        String res = getStringOrNull(ShowroomtableColumns.DEALER_TYPE);
        return res;
    }

    /**
     * Get the {@code address} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getAddress() {
        String res = getStringOrNull(ShowroomtableColumns.ADDRESS);
        return res;
    }

    /**
     * Get the {@code mobile} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMobile() {
        String res = getStringOrNull(ShowroomtableColumns.MOBILE);
        return res;
    }

    /**
     * Get the {@code email1} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getEmail1() {
        String res = getStringOrNull(ShowroomtableColumns.EMAIL1);
        return res;
    }

    /**
     * Get the {@code images} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImages() {
        String res = getStringOrNull(ShowroomtableColumns.IMAGES);
        return res;
    }

    /**
     * Get the {@code city} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCity() {
        String res = getStringOrNull(ShowroomtableColumns.CITY);
        return res;
    }

    /**
     * Get the {@code state} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getState() {
        String res = getStringOrNull(ShowroomtableColumns.STATE);
        return res;
    }

    /**
     * Get the {@code pincode} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPincode() {
        String res = getStringOrNull(ShowroomtableColumns.PINCODE);
        return res;
    }

    /**
     * Get the {@code email2} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getEmail2() {
        String res = getStringOrNull(ShowroomtableColumns.EMAIL2);
        return res;
    }

    /**
     * Get the {@code phone1} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPhone1() {
        String res = getStringOrNull(ShowroomtableColumns.PHONE1);
        return res;
    }

    /**
     * Get the {@code phone2} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPhone2() {
        String res = getStringOrNull(ShowroomtableColumns.PHONE2);
        return res;
    }

    /**
     * Get the {@code locality} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocality() {
        String res = getStringOrNull(ShowroomtableColumns.LOCALITY);
        return res;
    }
}
