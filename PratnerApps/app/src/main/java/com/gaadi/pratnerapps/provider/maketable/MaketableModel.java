package com.gaadi.pratnerapps.provider.maketable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save make list
 */
public interface MaketableModel extends BaseModel {

    /**
     * Get the {@code make_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMakeName();

    /**
     * Get the {@code make_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getMakeId();

    /**
     * Get the {@code rank} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getRank();

    /**
     * Get the {@code in_stock} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getInStock();
}
