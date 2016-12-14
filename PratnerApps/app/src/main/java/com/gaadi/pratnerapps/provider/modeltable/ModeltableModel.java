package com.gaadi.pratnerapps.provider.modeltable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save model list
 */
public interface ModeltableModel extends BaseModel {

    /**
     * Get the {@code model_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getModelName();

    /**
     * Get the {@code model_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getModelId();

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
