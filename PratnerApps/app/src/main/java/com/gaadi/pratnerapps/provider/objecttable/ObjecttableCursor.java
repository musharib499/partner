package com.gaadi.pratnerapps.provider.objecttable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code objecttable} table.
 */
public class ObjecttableCursor extends AbstractCursor implements ObjecttableModel {
    public ObjecttableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ObjecttableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code key_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getKeyId() {
        String res = getStringOrNull(ObjecttableColumns.KEY_ID);
        return res;
    }

    /**
     * Get the {@code key_value} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getKeyValue() {
        String res = getStringOrNull(ObjecttableColumns.KEY_VALUE);
        return res;
    }

    /**
     * Get the {@code key_version} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getKeyVersion() {
        Integer res = getIntegerOrNull(ObjecttableColumns.KEY_VERSION);
        return res;
    }
}
