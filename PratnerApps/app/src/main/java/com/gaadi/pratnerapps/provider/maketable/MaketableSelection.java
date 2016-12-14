package com.gaadi.pratnerapps.provider.maketable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code maketable} table.
 */
public class MaketableSelection extends AbstractSelection<MaketableSelection> {
    @Override
    protected Uri baseUri() {
        return MaketableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MaketableCursor} object, which is positioned before the first entry, or null.
     */
    public MaketableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MaketableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MaketableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MaketableCursor} object, which is positioned before the first entry, or null.
     */
    public MaketableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MaketableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MaketableCursor query(Context context) {
        return query(context, null);
    }


    public MaketableSelection id(long... value) {
        addEquals("maketable." + MaketableColumns._ID, toObjectArray(value));
        return this;
    }

    public MaketableSelection idNot(long... value) {
        addNotEquals("maketable." + MaketableColumns._ID, toObjectArray(value));
        return this;
    }

    public MaketableSelection orderById(boolean desc) {
        orderBy("maketable." + MaketableColumns._ID, desc);
        return this;
    }

    public MaketableSelection orderById() {
        return orderById(false);
    }

    public MaketableSelection makeName(String... value) {
        addEquals(MaketableColumns.MAKE_NAME, value);
        return this;
    }

    public MaketableSelection makeNameNot(String... value) {
        addNotEquals(MaketableColumns.MAKE_NAME, value);
        return this;
    }

    public MaketableSelection makeNameLike(String... value) {
        addLike(MaketableColumns.MAKE_NAME, value);
        return this;
    }

    public MaketableSelection makeNameContains(String... value) {
        addContains(MaketableColumns.MAKE_NAME, value);
        return this;
    }

    public MaketableSelection makeNameStartsWith(String... value) {
        addStartsWith(MaketableColumns.MAKE_NAME, value);
        return this;
    }

    public MaketableSelection makeNameEndsWith(String... value) {
        addEndsWith(MaketableColumns.MAKE_NAME, value);
        return this;
    }

    public MaketableSelection orderByMakeName(boolean desc) {
        orderBy(MaketableColumns.MAKE_NAME, desc);
        return this;
    }

    public MaketableSelection orderByMakeName() {
        orderBy(MaketableColumns.MAKE_NAME, false);
        return this;
    }

    public MaketableSelection makeId(Integer... value) {
        addEquals(MaketableColumns.MAKE_ID, value);
        return this;
    }

    public MaketableSelection makeIdNot(Integer... value) {
        addNotEquals(MaketableColumns.MAKE_ID, value);
        return this;
    }

    public MaketableSelection makeIdGt(int value) {
        addGreaterThan(MaketableColumns.MAKE_ID, value);
        return this;
    }

    public MaketableSelection makeIdGtEq(int value) {
        addGreaterThanOrEquals(MaketableColumns.MAKE_ID, value);
        return this;
    }

    public MaketableSelection makeIdLt(int value) {
        addLessThan(MaketableColumns.MAKE_ID, value);
        return this;
    }

    public MaketableSelection makeIdLtEq(int value) {
        addLessThanOrEquals(MaketableColumns.MAKE_ID, value);
        return this;
    }

    public MaketableSelection orderByMakeId(boolean desc) {
        orderBy(MaketableColumns.MAKE_ID, desc);
        return this;
    }

    public MaketableSelection orderByMakeId() {
        orderBy(MaketableColumns.MAKE_ID, false);
        return this;
    }

    public MaketableSelection rank(Integer... value) {
        addEquals(MaketableColumns.RANK, value);
        return this;
    }

    public MaketableSelection rankNot(Integer... value) {
        addNotEquals(MaketableColumns.RANK, value);
        return this;
    }

    public MaketableSelection rankGt(int value) {
        addGreaterThan(MaketableColumns.RANK, value);
        return this;
    }

    public MaketableSelection rankGtEq(int value) {
        addGreaterThanOrEquals(MaketableColumns.RANK, value);
        return this;
    }

    public MaketableSelection rankLt(int value) {
        addLessThan(MaketableColumns.RANK, value);
        return this;
    }

    public MaketableSelection rankLtEq(int value) {
        addLessThanOrEquals(MaketableColumns.RANK, value);
        return this;
    }

    public MaketableSelection orderByRank(boolean desc) {
        orderBy(MaketableColumns.RANK, desc);
        return this;
    }

    public MaketableSelection orderByRank() {
        orderBy(MaketableColumns.RANK, false);
        return this;
    }

    public MaketableSelection inStock(Integer... value) {
        addEquals(MaketableColumns.IN_STOCK, value);
        return this;
    }

    public MaketableSelection inStockNot(Integer... value) {
        addNotEquals(MaketableColumns.IN_STOCK, value);
        return this;
    }

    public MaketableSelection inStockGt(int value) {
        addGreaterThan(MaketableColumns.IN_STOCK, value);
        return this;
    }

    public MaketableSelection inStockGtEq(int value) {
        addGreaterThanOrEquals(MaketableColumns.IN_STOCK, value);
        return this;
    }

    public MaketableSelection inStockLt(int value) {
        addLessThan(MaketableColumns.IN_STOCK, value);
        return this;
    }

    public MaketableSelection inStockLtEq(int value) {
        addLessThanOrEquals(MaketableColumns.IN_STOCK, value);
        return this;
    }

    public MaketableSelection orderByInStock(boolean desc) {
        orderBy(MaketableColumns.IN_STOCK, desc);
        return this;
    }

    public MaketableSelection orderByInStock() {
        orderBy(MaketableColumns.IN_STOCK, false);
        return this;
    }
}
