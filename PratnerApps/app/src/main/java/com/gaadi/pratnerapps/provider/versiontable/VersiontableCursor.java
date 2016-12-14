package com.gaadi.pratnerapps.provider.versiontable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code versiontable} table.
 */
public class VersiontableCursor extends AbstractCursor implements VersiontableModel {
    public VersiontableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(VersiontableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code version_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getVersionName() {
        String res = getStringOrNull(VersiontableColumns.VERSION_NAME);
        return res;
    }

    /**
     * Get the {@code version_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getVersionId() {
        Integer res = getIntegerOrNull(VersiontableColumns.VERSION_ID);
        return res;
    }

    /**
     * Get the {@code model_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getModelId() {
        Integer res = getIntegerOrNull(VersiontableColumns.MODEL_ID);
        return res;
    }

    /**
     * Get the {@code fuel_type} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getFuelType() {
        String res = getStringOrNull(VersiontableColumns.FUEL_TYPE);
        return res;
    }
}
