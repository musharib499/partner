package com.gaadi.pratnerapps.provider.modeltable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code modeltable} table.
 */
public class ModeltableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ModeltableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ModeltableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ModeltableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ModeltableContentValues putModelName(@Nullable String value) {
        mContentValues.put(ModeltableColumns.MODEL_NAME, value);
        return this;
    }

    public ModeltableContentValues putModelNameNull() {
        mContentValues.putNull(ModeltableColumns.MODEL_NAME);
        return this;
    }

    public ModeltableContentValues putModelId(@Nullable Integer value) {
        mContentValues.put(ModeltableColumns.MODEL_ID, value);
        return this;
    }

    public ModeltableContentValues putModelIdNull() {
        mContentValues.putNull(ModeltableColumns.MODEL_ID);
        return this;
    }

    public ModeltableContentValues putMakeId(@Nullable Integer value) {
        mContentValues.put(ModeltableColumns.MAKE_ID, value);
        return this;
    }

    public ModeltableContentValues putMakeIdNull() {
        mContentValues.putNull(ModeltableColumns.MAKE_ID);
        return this;
    }

    public ModeltableContentValues putRank(@Nullable Integer value) {
        mContentValues.put(ModeltableColumns.RANK, value);
        return this;
    }

    public ModeltableContentValues putRankNull() {
        mContentValues.putNull(ModeltableColumns.RANK);
        return this;
    }

    public ModeltableContentValues putInStock(@Nullable Integer value) {
        mContentValues.put(ModeltableColumns.IN_STOCK, value);
        return this;
    }

    public ModeltableContentValues putInStockNull() {
        mContentValues.putNull(ModeltableColumns.IN_STOCK);
        return this;
    }
}
