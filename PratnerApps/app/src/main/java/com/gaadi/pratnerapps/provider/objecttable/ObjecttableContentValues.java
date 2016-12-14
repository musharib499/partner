package com.gaadi.pratnerapps.provider.objecttable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code objecttable} table.
 */
public class ObjecttableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ObjecttableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ObjecttableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ObjecttableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ObjecttableContentValues putKeyId(@Nullable String value) {
        mContentValues.put(ObjecttableColumns.KEY_ID, value);
        return this;
    }

    public ObjecttableContentValues putKeyIdNull() {
        mContentValues.putNull(ObjecttableColumns.KEY_ID);
        return this;
    }

    public ObjecttableContentValues putKeyValue(@Nullable String value) {
        mContentValues.put(ObjecttableColumns.KEY_VALUE, value);
        return this;
    }

    public ObjecttableContentValues putKeyValueNull() {
        mContentValues.putNull(ObjecttableColumns.KEY_VALUE);
        return this;
    }

    public ObjecttableContentValues putKeyVersion(@Nullable Integer value) {
        mContentValues.put(ObjecttableColumns.KEY_VERSION, value);
        return this;
    }

    public ObjecttableContentValues putKeyVersionNull() {
        mContentValues.putNull(ObjecttableColumns.KEY_VERSION);
        return this;
    }
}
