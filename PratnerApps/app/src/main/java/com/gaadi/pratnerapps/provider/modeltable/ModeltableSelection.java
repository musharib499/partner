package com.gaadi.pratnerapps.provider.modeltable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code modeltable} table.
 */
public class ModeltableSelection extends AbstractSelection<ModeltableSelection> {
    @Override
    protected Uri baseUri() {
        return ModeltableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ModeltableCursor} object, which is positioned before the first entry, or null.
     */
    public ModeltableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ModeltableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ModeltableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ModeltableCursor} object, which is positioned before the first entry, or null.
     */
    public ModeltableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ModeltableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ModeltableCursor query(Context context) {
        return query(context, null);
    }


    public ModeltableSelection id(long... value) {
        addEquals("modeltable." + ModeltableColumns._ID, toObjectArray(value));
        return this;
    }

    public ModeltableSelection idNot(long... value) {
        addNotEquals("modeltable." + ModeltableColumns._ID, toObjectArray(value));
        return this;
    }

    public ModeltableSelection orderById(boolean desc) {
        orderBy("modeltable." + ModeltableColumns._ID, desc);
        return this;
    }

    public ModeltableSelection orderById() {
        return orderById(false);
    }

    public ModeltableSelection modelName(String... value) {
        addEquals(ModeltableColumns.MODEL_NAME, value);
        return this;
    }

    public ModeltableSelection modelNameNot(String... value) {
        addNotEquals(ModeltableColumns.MODEL_NAME, value);
        return this;
    }

    public ModeltableSelection modelNameLike(String... value) {
        addLike(ModeltableColumns.MODEL_NAME, value);
        return this;
    }

    public ModeltableSelection modelNameContains(String... value) {
        addContains(ModeltableColumns.MODEL_NAME, value);
        return this;
    }

    public ModeltableSelection modelNameStartsWith(String... value) {
        addStartsWith(ModeltableColumns.MODEL_NAME, value);
        return this;
    }

    public ModeltableSelection modelNameEndsWith(String... value) {
        addEndsWith(ModeltableColumns.MODEL_NAME, value);
        return this;
    }

    public ModeltableSelection orderByModelName(boolean desc) {
        orderBy(ModeltableColumns.MODEL_NAME, desc);
        return this;
    }

    public ModeltableSelection orderByModelName() {
        orderBy(ModeltableColumns.MODEL_NAME, false);
        return this;
    }

    public ModeltableSelection modelId(Integer... value) {
        addEquals(ModeltableColumns.MODEL_ID, value);
        return this;
    }

    public ModeltableSelection modelIdNot(Integer... value) {
        addNotEquals(ModeltableColumns.MODEL_ID, value);
        return this;
    }

    public ModeltableSelection modelIdGt(int value) {
        addGreaterThan(ModeltableColumns.MODEL_ID, value);
        return this;
    }

    public ModeltableSelection modelIdGtEq(int value) {
        addGreaterThanOrEquals(ModeltableColumns.MODEL_ID, value);
        return this;
    }

    public ModeltableSelection modelIdLt(int value) {
        addLessThan(ModeltableColumns.MODEL_ID, value);
        return this;
    }

    public ModeltableSelection modelIdLtEq(int value) {
        addLessThanOrEquals(ModeltableColumns.MODEL_ID, value);
        return this;
    }

    public ModeltableSelection orderByModelId(boolean desc) {
        orderBy(ModeltableColumns.MODEL_ID, desc);
        return this;
    }

    public ModeltableSelection orderByModelId() {
        orderBy(ModeltableColumns.MODEL_ID, false);
        return this;
    }

    public ModeltableSelection makeId(Integer... value) {
        addEquals(ModeltableColumns.MAKE_ID, value);
        return this;
    }

    public ModeltableSelection makeIdNot(Integer... value) {
        addNotEquals(ModeltableColumns.MAKE_ID, value);
        return this;
    }

    public ModeltableSelection makeIdGt(int value) {
        addGreaterThan(ModeltableColumns.MAKE_ID, value);
        return this;
    }

    public ModeltableSelection makeIdGtEq(int value) {
        addGreaterThanOrEquals(ModeltableColumns.MAKE_ID, value);
        return this;
    }

    public ModeltableSelection makeIdLt(int value) {
        addLessThan(ModeltableColumns.MAKE_ID, value);
        return this;
    }

    public ModeltableSelection makeIdLtEq(int value) {
        addLessThanOrEquals(ModeltableColumns.MAKE_ID, value);
        return this;
    }

    public ModeltableSelection orderByMakeId(boolean desc) {
        orderBy(ModeltableColumns.MAKE_ID, desc);
        return this;
    }

    public ModeltableSelection orderByMakeId() {
        orderBy(ModeltableColumns.MAKE_ID, false);
        return this;
    }

    public ModeltableSelection rank(Integer... value) {
        addEquals(ModeltableColumns.RANK, value);
        return this;
    }

    public ModeltableSelection rankNot(Integer... value) {
        addNotEquals(ModeltableColumns.RANK, value);
        return this;
    }

    public ModeltableSelection rankGt(int value) {
        addGreaterThan(ModeltableColumns.RANK, value);
        return this;
    }

    public ModeltableSelection rankGtEq(int value) {
        addGreaterThanOrEquals(ModeltableColumns.RANK, value);
        return this;
    }

    public ModeltableSelection rankLt(int value) {
        addLessThan(ModeltableColumns.RANK, value);
        return this;
    }

    public ModeltableSelection rankLtEq(int value) {
        addLessThanOrEquals(ModeltableColumns.RANK, value);
        return this;
    }

    public ModeltableSelection orderByRank(boolean desc) {
        orderBy(ModeltableColumns.RANK, desc);
        return this;
    }

    public ModeltableSelection orderByRank() {
        orderBy(ModeltableColumns.RANK, false);
        return this;
    }

    public ModeltableSelection inStock(Integer... value) {
        addEquals(ModeltableColumns.IN_STOCK, value);
        return this;
    }

    public ModeltableSelection inStockNot(Integer... value) {
        addNotEquals(ModeltableColumns.IN_STOCK, value);
        return this;
    }

    public ModeltableSelection inStockGt(int value) {
        addGreaterThan(ModeltableColumns.IN_STOCK, value);
        return this;
    }

    public ModeltableSelection inStockGtEq(int value) {
        addGreaterThanOrEquals(ModeltableColumns.IN_STOCK, value);
        return this;
    }

    public ModeltableSelection inStockLt(int value) {
        addLessThan(ModeltableColumns.IN_STOCK, value);
        return this;
    }

    public ModeltableSelection inStockLtEq(int value) {
        addLessThanOrEquals(ModeltableColumns.IN_STOCK, value);
        return this;
    }

    public ModeltableSelection orderByInStock(boolean desc) {
        orderBy(ModeltableColumns.IN_STOCK, desc);
        return this;
    }

    public ModeltableSelection orderByInStock() {
        orderBy(ModeltableColumns.IN_STOCK, false);
        return this;
    }
}
