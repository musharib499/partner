package com.gaadi.pratnerapps.provider.citytable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save city list
 */
public interface CitytableModel extends BaseModel {

    /**
     * Get the {@code city_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getCityId();

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCityName();

    /**
     * Get the {@code state_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getStateId();

    /**
     * Get the {@code state_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getStateName();

    /**
     * Get the {@code pincode} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPincode();

    /**
     * Get the {@code region} value.
     * Can be {@code null}.
     */
    @Nullable
    String getRegion();
}
