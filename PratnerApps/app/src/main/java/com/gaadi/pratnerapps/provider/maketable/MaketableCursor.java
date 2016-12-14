package com.gaadi.pratnerapps.provider.maketable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code maketable} table.
 */
public class MaketableCursor extends AbstractCursor implements MaketableModel {
    public MaketableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MaketableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code make_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMakeName() {
        String res = getStringOrNull(MaketableColumns.MAKE_NAME);
        return res;
    }

    /**
     * Get the {@code make_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getMakeId() {
        Integer res = getIntegerOrNull(MaketableColumns.MAKE_ID);
        return res;
    }

    /**
     * Get the {@code rank} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getRank() {
        Integer res = getIntegerOrNull(MaketableColumns.RANK);
        return res;
    }

    /**
     * Get the {@code in_stock} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getInStock() {
        Integer res = getIntegerOrNull(MaketableColumns.IN_STOCK);
        return res;
    }
}
