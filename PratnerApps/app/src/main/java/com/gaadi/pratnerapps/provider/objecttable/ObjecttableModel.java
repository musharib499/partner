package com.gaadi.pratnerapps.provider.objecttable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save city list
 */
public interface ObjecttableModel extends BaseModel {

    /**
     * Get the {@code key_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getKeyId();

    /**
     * Get the {@code key_value} value.
     * Can be {@code null}.
     */
    @Nullable
    String getKeyValue();

    /**
     * Get the {@code key_version} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getKeyVersion();
}
