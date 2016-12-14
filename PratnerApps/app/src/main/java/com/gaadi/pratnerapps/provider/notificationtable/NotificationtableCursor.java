package com.gaadi.pratnerapps.provider.notificationtable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code notificationtable} table.
 */
public class NotificationtableCursor extends AbstractCursor implements NotificationtableModel {
    public NotificationtableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(NotificationtableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(NotificationtableColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code description} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDescription() {
        String res = getStringOrNull(NotificationtableColumns.DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code secondary_text} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSecondaryText() {
        String res = getStringOrNull(NotificationtableColumns.SECONDARY_TEXT);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getDate() {
        Long res = getLongOrNull(NotificationtableColumns.DATE);
        return res;
    }

    /**
     * Get the {@code image_url} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImageUrl() {
        String res = getStringOrNull(NotificationtableColumns.IMAGE_URL);
        return res;
    }

    /**
     * Get the {@code read_status} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getReadStatus() {
        Integer res = getIntegerOrNull(NotificationtableColumns.READ_STATUS);
        return res;
    }
}
