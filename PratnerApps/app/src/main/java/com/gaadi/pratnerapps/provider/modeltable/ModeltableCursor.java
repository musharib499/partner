package com.gaadi.pratnerapps.provider.modeltable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code modeltable} table.
 */
public class ModeltableCursor extends AbstractCursor implements ModeltableModel {
    public ModeltableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ModeltableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code model_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getModelName() {
        String res = getStringOrNull(ModeltableColumns.MODEL_NAME);
        return res;
    }

    /**
     * Get the {@code model_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getModelId() {
        Integer res = getIntegerOrNull(ModeltableColumns.MODEL_ID);
        return res;
    }

    /**
     * Get the {@code make_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getMakeId() {
        Integer res = getIntegerOrNull(ModeltableColumns.MAKE_ID);
        return res;
    }

    /**
     * Get the {@code rank} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getRank() {
        Integer res = getIntegerOrNull(ModeltableColumns.RANK);
        return res;
    }

    /**
     * Get the {@code in_stock} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getInStock() {
        Integer res = getIntegerOrNull(ModeltableColumns.IN_STOCK);
        return res;
    }
}
