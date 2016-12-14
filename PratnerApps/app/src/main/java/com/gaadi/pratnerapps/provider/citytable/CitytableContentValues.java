package com.gaadi.pratnerapps.provider.citytable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code citytable} table.
 */
public class CitytableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CitytableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CitytableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CitytableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CitytableContentValues putCityId(@Nullable Integer value) {
        mContentValues.put(CitytableColumns.CITY_ID, value);
        return this;
    }

    public CitytableContentValues putCityIdNull() {
        mContentValues.putNull(CitytableColumns.CITY_ID);
        return this;
    }

    public CitytableContentValues putCityName(@Nullable String value) {
        mContentValues.put(CitytableColumns.CITY_NAME, value);
        return this;
    }

    public CitytableContentValues putCityNameNull() {
        mContentValues.putNull(CitytableColumns.CITY_NAME);
        return this;
    }

    public CitytableContentValues putStateId(@Nullable Integer value) {
        mContentValues.put(CitytableColumns.STATE_ID, value);
        return this;
    }

    public CitytableContentValues putStateIdNull() {
        mContentValues.putNull(CitytableColumns.STATE_ID);
        return this;
    }

    public CitytableContentValues putStateName(@Nullable String value) {
        mContentValues.put(CitytableColumns.STATE_NAME, value);
        return this;
    }

    public CitytableContentValues putStateNameNull() {
        mContentValues.putNull(CitytableColumns.STATE_NAME);
        return this;
    }

    public CitytableContentValues putPincode(@Nullable String value) {
        mContentValues.put(CitytableColumns.PINCODE, value);
        return this;
    }

    public CitytableContentValues putPincodeNull() {
        mContentValues.putNull(CitytableColumns.PINCODE);
        return this;
    }

    public CitytableContentValues putRegion(@Nullable String value) {
        mContentValues.put(CitytableColumns.REGION, value);
        return this;
    }

    public CitytableContentValues putRegionNull() {
        mContentValues.putNull(CitytableColumns.REGION);
        return this;
    }
}
