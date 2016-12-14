package com.gaadi.pratnerapps.provider.notificationtable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save notifications list
 */
public interface NotificationtableModel extends BaseModel {

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Get the {@code description} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDescription();

    /**
     * Get the {@code secondary_text} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSecondaryText();

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getDate();

    /**
     * Get the {@code image_url} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImageUrl();

    /**
     * Get the {@code read_status} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getReadStatus();
}
