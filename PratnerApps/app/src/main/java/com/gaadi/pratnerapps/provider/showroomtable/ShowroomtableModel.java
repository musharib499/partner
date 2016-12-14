package com.gaadi.pratnerapps.provider.showroomtable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save showroom list
 */
public interface ShowroomtableModel extends BaseModel {

    /**
     * Get the {@code showroom_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getShowroomId();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code latitude} value.
     * Can be {@code null}.
     */
    @Nullable
    String getLatitude();

    /**
     * Get the {@code longitude} value.
     * Can be {@code null}.
     */
    @Nullable
    String getLongitude();

    /**
     * Get the {@code dealer_type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDealerType();

    /**
     * Get the {@code address} value.
     * Can be {@code null}.
     */
    @Nullable
    String getAddress();

    /**
     * Get the {@code mobile} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMobile();

    /**
     * Get the {@code email1} value.
     * Can be {@code null}.
     */
    @Nullable
    String getEmail1();

    /**
     * Get the {@code images} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImages();

    /**
     * Get the {@code city} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCity();

    /**
     * Get the {@code state} value.
     * Can be {@code null}.
     */
    @Nullable
    String getState();

    /**
     * Get the {@code pincode} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPincode();

    /**
     * Get the {@code email2} value.
     * Can be {@code null}.
     */
    @Nullable
    String getEmail2();

    /**
     * Get the {@code phone1} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPhone1();

    /**
     * Get the {@code phone2} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPhone2();

    /**
     * Get the {@code locality} value.
     * Can be {@code null}.
     */
    @Nullable
    String getLocality();
}
