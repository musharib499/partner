package com.gaadi.pratnerapps.provider.showroomtable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code showroomtable} table.
 */
public class ShowroomtableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ShowroomtableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ShowroomtableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ShowroomtableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ShowroomtableContentValues putShowroomId(@Nullable Integer value) {
        mContentValues.put(ShowroomtableColumns.SHOWROOM_ID, value);
        return this;
    }

    public ShowroomtableContentValues putShowroomIdNull() {
        mContentValues.putNull(ShowroomtableColumns.SHOWROOM_ID);
        return this;
    }

    public ShowroomtableContentValues putName(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.NAME, value);
        return this;
    }

    public ShowroomtableContentValues putNameNull() {
        mContentValues.putNull(ShowroomtableColumns.NAME);
        return this;
    }

    public ShowroomtableContentValues putLatitude(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.LATITUDE, value);
        return this;
    }

    public ShowroomtableContentValues putLatitudeNull() {
        mContentValues.putNull(ShowroomtableColumns.LATITUDE);
        return this;
    }

    public ShowroomtableContentValues putLongitude(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.LONGITUDE, value);
        return this;
    }

    public ShowroomtableContentValues putLongitudeNull() {
        mContentValues.putNull(ShowroomtableColumns.LONGITUDE);
        return this;
    }

    public ShowroomtableContentValues putDealerType(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.DEALER_TYPE, value);
        return this;
    }

    public ShowroomtableContentValues putDealerTypeNull() {
        mContentValues.putNull(ShowroomtableColumns.DEALER_TYPE);
        return this;
    }

    public ShowroomtableContentValues putAddress(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.ADDRESS, value);
        return this;
    }

    public ShowroomtableContentValues putAddressNull() {
        mContentValues.putNull(ShowroomtableColumns.ADDRESS);
        return this;
    }

    public ShowroomtableContentValues putMobile(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.MOBILE, value);
        return this;
    }

    public ShowroomtableContentValues putMobileNull() {
        mContentValues.putNull(ShowroomtableColumns.MOBILE);
        return this;
    }

    public ShowroomtableContentValues putEmail1(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.EMAIL1, value);
        return this;
    }

    public ShowroomtableContentValues putEmail1Null() {
        mContentValues.putNull(ShowroomtableColumns.EMAIL1);
        return this;
    }

    public ShowroomtableContentValues putImages(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.IMAGES, value);
        return this;
    }

    public ShowroomtableContentValues putImagesNull() {
        mContentValues.putNull(ShowroomtableColumns.IMAGES);
        return this;
    }

    public ShowroomtableContentValues putCity(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.CITY, value);
        return this;
    }

    public ShowroomtableContentValues putCityNull() {
        mContentValues.putNull(ShowroomtableColumns.CITY);
        return this;
    }

    public ShowroomtableContentValues putState(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.STATE, value);
        return this;
    }

    public ShowroomtableContentValues putStateNull() {
        mContentValues.putNull(ShowroomtableColumns.STATE);
        return this;
    }

    public ShowroomtableContentValues putPincode(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.PINCODE, value);
        return this;
    }

    public ShowroomtableContentValues putPincodeNull() {
        mContentValues.putNull(ShowroomtableColumns.PINCODE);
        return this;
    }

    public ShowroomtableContentValues putEmail2(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.EMAIL2, value);
        return this;
    }

    public ShowroomtableContentValues putEmail2Null() {
        mContentValues.putNull(ShowroomtableColumns.EMAIL2);
        return this;
    }

    public ShowroomtableContentValues putPhone1(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.PHONE1, value);
        return this;
    }

    public ShowroomtableContentValues putPhone1Null() {
        mContentValues.putNull(ShowroomtableColumns.PHONE1);
        return this;
    }

    public ShowroomtableContentValues putPhone2(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.PHONE2, value);
        return this;
    }

    public ShowroomtableContentValues putPhone2Null() {
        mContentValues.putNull(ShowroomtableColumns.PHONE2);
        return this;
    }

    public ShowroomtableContentValues putLocality(@Nullable String value) {
        mContentValues.put(ShowroomtableColumns.LOCALITY, value);
        return this;
    }

    public ShowroomtableContentValues putLocalityNull() {
        mContentValues.putNull(ShowroomtableColumns.LOCALITY);
        return this;
    }
}
