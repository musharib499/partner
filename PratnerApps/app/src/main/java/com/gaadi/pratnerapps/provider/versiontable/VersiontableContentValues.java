package com.gaadi.pratnerapps.provider.versiontable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code versiontable} table.
 */
public class VersiontableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return VersiontableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable VersiontableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable VersiontableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public VersiontableContentValues putVersionName(@Nullable String value) {
        mContentValues.put(VersiontableColumns.VERSION_NAME, value);
        return this;
    }

    public VersiontableContentValues putVersionNameNull() {
        mContentValues.putNull(VersiontableColumns.VERSION_NAME);
        return this;
    }

    public VersiontableContentValues putVersionId(@Nullable Integer value) {
        mContentValues.put(VersiontableColumns.VERSION_ID, value);
        return this;
    }

    public VersiontableContentValues putVersionIdNull() {
        mContentValues.putNull(VersiontableColumns.VERSION_ID);
        return this;
    }

    public VersiontableContentValues putModelId(@Nullable Integer value) {
        mContentValues.put(VersiontableColumns.MODEL_ID, value);
        return this;
    }

    public VersiontableContentValues putModelIdNull() {
        mContentValues.putNull(VersiontableColumns.MODEL_ID);
        return this;
    }

    public VersiontableContentValues putFuelType(@Nullable String value) {
        mContentValues.put(VersiontableColumns.FUEL_TYPE, value);
        return this;
    }

    public VersiontableContentValues putFuelTypeNull() {
        mContentValues.putNull(VersiontableColumns.FUEL_TYPE);
        return this;
    }
}
