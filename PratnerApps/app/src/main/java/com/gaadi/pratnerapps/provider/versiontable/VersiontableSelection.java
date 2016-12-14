package com.gaadi.pratnerapps.provider.versiontable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code versiontable} table.
 */
public class VersiontableSelection extends AbstractSelection<VersiontableSelection> {
    @Override
    protected Uri baseUri() {
        return VersiontableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code VersiontableCursor} object, which is positioned before the first entry, or null.
     */
    public VersiontableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new VersiontableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public VersiontableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code VersiontableCursor} object, which is positioned before the first entry, or null.
     */
    public VersiontableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new VersiontableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public VersiontableCursor query(Context context) {
        return query(context, null);
    }


    public VersiontableSelection id(long... value) {
        addEquals("versiontable." + VersiontableColumns._ID, toObjectArray(value));
        return this;
    }

    public VersiontableSelection idNot(long... value) {
        addNotEquals("versiontable." + VersiontableColumns._ID, toObjectArray(value));
        return this;
    }

    public VersiontableSelection orderById(boolean desc) {
        orderBy("versiontable." + VersiontableColumns._ID, desc);
        return this;
    }

    public VersiontableSelection orderById() {
        return orderById(false);
    }

    public VersiontableSelection versionName(String... value) {
        addEquals(VersiontableColumns.VERSION_NAME, value);
        return this;
    }

    public VersiontableSelection versionNameNot(String... value) {
        addNotEquals(VersiontableColumns.VERSION_NAME, value);
        return this;
    }

    public VersiontableSelection versionNameLike(String... value) {
        addLike(VersiontableColumns.VERSION_NAME, value);
        return this;
    }

    public VersiontableSelection versionNameContains(String... value) {
        addContains(VersiontableColumns.VERSION_NAME, value);
        return this;
    }

    public VersiontableSelection versionNameStartsWith(String... value) {
        addStartsWith(VersiontableColumns.VERSION_NAME, value);
        return this;
    }

    public VersiontableSelection versionNameEndsWith(String... value) {
        addEndsWith(VersiontableColumns.VERSION_NAME, value);
        return this;
    }

    public VersiontableSelection orderByVersionName(boolean desc) {
        orderBy(VersiontableColumns.VERSION_NAME, desc);
        return this;
    }

    public VersiontableSelection orderByVersionName() {
        orderBy(VersiontableColumns.VERSION_NAME, false);
        return this;
    }

    public VersiontableSelection versionId(Integer... value) {
        addEquals(VersiontableColumns.VERSION_ID, value);
        return this;
    }

    public VersiontableSelection versionIdNot(Integer... value) {
        addNotEquals(VersiontableColumns.VERSION_ID, value);
        return this;
    }

    public VersiontableSelection versionIdGt(int value) {
        addGreaterThan(VersiontableColumns.VERSION_ID, value);
        return this;
    }

    public VersiontableSelection versionIdGtEq(int value) {
        addGreaterThanOrEquals(VersiontableColumns.VERSION_ID, value);
        return this;
    }

    public VersiontableSelection versionIdLt(int value) {
        addLessThan(VersiontableColumns.VERSION_ID, value);
        return this;
    }

    public VersiontableSelection versionIdLtEq(int value) {
        addLessThanOrEquals(VersiontableColumns.VERSION_ID, value);
        return this;
    }

    public VersiontableSelection orderByVersionId(boolean desc) {
        orderBy(VersiontableColumns.VERSION_ID, desc);
        return this;
    }

    public VersiontableSelection orderByVersionId() {
        orderBy(VersiontableColumns.VERSION_ID, false);
        return this;
    }

    public VersiontableSelection modelId(Integer... value) {
        addEquals(VersiontableColumns.MODEL_ID, value);
        return this;
    }

    public VersiontableSelection modelIdNot(Integer... value) {
        addNotEquals(VersiontableColumns.MODEL_ID, value);
        return this;
    }

    public VersiontableSelection modelIdGt(int value) {
        addGreaterThan(VersiontableColumns.MODEL_ID, value);
        return this;
    }

    public VersiontableSelection modelIdGtEq(int value) {
        addGreaterThanOrEquals(VersiontableColumns.MODEL_ID, value);
        return this;
    }

    public VersiontableSelection modelIdLt(int value) {
        addLessThan(VersiontableColumns.MODEL_ID, value);
        return this;
    }

    public VersiontableSelection modelIdLtEq(int value) {
        addLessThanOrEquals(VersiontableColumns.MODEL_ID, value);
        return this;
    }

    public VersiontableSelection orderByModelId(boolean desc) {
        orderBy(VersiontableColumns.MODEL_ID, desc);
        return this;
    }

    public VersiontableSelection orderByModelId() {
        orderBy(VersiontableColumns.MODEL_ID, false);
        return this;
    }

    public VersiontableSelection fuelType(String... value) {
        addEquals(VersiontableColumns.FUEL_TYPE, value);
        return this;
    }

    public VersiontableSelection fuelTypeNot(String... value) {
        addNotEquals(VersiontableColumns.FUEL_TYPE, value);
        return this;
    }

    public VersiontableSelection fuelTypeLike(String... value) {
        addLike(VersiontableColumns.FUEL_TYPE, value);
        return this;
    }

    public VersiontableSelection fuelTypeContains(String... value) {
        addContains(VersiontableColumns.FUEL_TYPE, value);
        return this;
    }

    public VersiontableSelection fuelTypeStartsWith(String... value) {
        addStartsWith(VersiontableColumns.FUEL_TYPE, value);
        return this;
    }

    public VersiontableSelection fuelTypeEndsWith(String... value) {
        addEndsWith(VersiontableColumns.FUEL_TYPE, value);
        return this;
    }

    public VersiontableSelection orderByFuelType(boolean desc) {
        orderBy(VersiontableColumns.FUEL_TYPE, desc);
        return this;
    }

    public VersiontableSelection orderByFuelType() {
        orderBy(VersiontableColumns.FUEL_TYPE, false);
        return this;
    }
}
