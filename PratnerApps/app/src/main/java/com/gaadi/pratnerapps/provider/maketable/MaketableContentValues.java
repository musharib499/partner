package com.gaadi.pratnerapps.provider.maketable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code maketable} table.
 */
public class MaketableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MaketableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MaketableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MaketableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MaketableContentValues putMakeName(@Nullable String value) {
        mContentValues.put(MaketableColumns.MAKE_NAME, value);
        return this;
    }

    public MaketableContentValues putMakeNameNull() {
        mContentValues.putNull(MaketableColumns.MAKE_NAME);
        return this;
    }

    public MaketableContentValues putMakeId(@Nullable Integer value) {
        mContentValues.put(MaketableColumns.MAKE_ID, value);
        return this;
    }

    public MaketableContentValues putMakeIdNull() {
        mContentValues.putNull(MaketableColumns.MAKE_ID);
        return this;
    }

    public MaketableContentValues putRank(@Nullable Integer value) {
        mContentValues.put(MaketableColumns.RANK, value);
        return this;
    }

    public MaketableContentValues putRankNull() {
        mContentValues.putNull(MaketableColumns.RANK);
        return this;
    }

    public MaketableContentValues putInStock(@Nullable Integer value) {
        mContentValues.put(MaketableColumns.IN_STOCK, value);
        return this;
    }

    public MaketableContentValues putInStockNull() {
        mContentValues.putNull(MaketableColumns.IN_STOCK);
        return this;
    }
}
