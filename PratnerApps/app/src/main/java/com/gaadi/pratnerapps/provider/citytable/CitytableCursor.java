package com.gaadi.pratnerapps.provider.citytable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code citytable} table.
 */
public class CitytableCursor extends AbstractCursor implements CitytableModel {
    public CitytableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CitytableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code city_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getCityId() {
        Integer res = getIntegerOrNull(CitytableColumns.CITY_ID);
        return res;
    }

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCityName() {
        String res = getStringOrNull(CitytableColumns.CITY_NAME);
        return res;
    }

    /**
     * Get the {@code state_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getStateId() {
        Integer res = getIntegerOrNull(CitytableColumns.STATE_ID);
        return res;
    }

    /**
     * Get the {@code state_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getStateName() {
        String res = getStringOrNull(CitytableColumns.STATE_NAME);
        return res;
    }

    /**
     * Get the {@code pincode} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPincode() {
        String res = getStringOrNull(CitytableColumns.PINCODE);
        return res;
    }

    /**
     * Get the {@code region} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getRegion() {
        String res = getStringOrNull(CitytableColumns.REGION);
        return res;
    }
}
