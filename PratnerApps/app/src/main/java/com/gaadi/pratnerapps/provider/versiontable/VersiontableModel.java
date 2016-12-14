package com.gaadi.pratnerapps.provider.versiontable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save version list
 */
public interface VersiontableModel extends BaseModel {

    /**
     * Get the {@code version_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getVersionName();

    /**
     * Get the {@code version_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getVersionId();

    /**
     * Get the {@code model_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getModelId();

    /**
     * Get the {@code fuel_type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getFuelType();
}
