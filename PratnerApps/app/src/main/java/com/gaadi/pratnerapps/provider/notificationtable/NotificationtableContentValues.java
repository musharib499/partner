package com.gaadi.pratnerapps.provider.notificationtable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code notificationtable} table.
 */
public class NotificationtableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return NotificationtableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable NotificationtableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable NotificationtableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public NotificationtableContentValues putTitle(@Nullable String value) {
        mContentValues.put(NotificationtableColumns.TITLE, value);
        return this;
    }

    public NotificationtableContentValues putTitleNull() {
        mContentValues.putNull(NotificationtableColumns.TITLE);
        return this;
    }

    public NotificationtableContentValues putDescription(@Nullable String value) {
        mContentValues.put(NotificationtableColumns.DESCRIPTION, value);
        return this;
    }

    public NotificationtableContentValues putDescriptionNull() {
        mContentValues.putNull(NotificationtableColumns.DESCRIPTION);
        return this;
    }

    public NotificationtableContentValues putSecondaryText(@Nullable String value) {
        mContentValues.put(NotificationtableColumns.SECONDARY_TEXT, value);
        return this;
    }

    public NotificationtableContentValues putSecondaryTextNull() {
        mContentValues.putNull(NotificationtableColumns.SECONDARY_TEXT);
        return this;
    }

    public NotificationtableContentValues putDate(@Nullable Long value) {
        mContentValues.put(NotificationtableColumns.DATE, value);
        return this;
    }

    public NotificationtableContentValues putDateNull() {
        mContentValues.putNull(NotificationtableColumns.DATE);
        return this;
    }

    public NotificationtableContentValues putImageUrl(@Nullable String value) {
        mContentValues.put(NotificationtableColumns.IMAGE_URL, value);
        return this;
    }

    public NotificationtableContentValues putImageUrlNull() {
        mContentValues.putNull(NotificationtableColumns.IMAGE_URL);
        return this;
    }

    public NotificationtableContentValues putReadStatus(@Nullable Integer value) {
        mContentValues.put(NotificationtableColumns.READ_STATUS, value);
        return this;
    }

    public NotificationtableContentValues putReadStatusNull() {
        mContentValues.putNull(NotificationtableColumns.READ_STATUS);
        return this;
    }
}
