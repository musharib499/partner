package com.gaadi.pratnerapps.provider.objecttable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code objecttable} table.
 */
public class ObjecttableSelection extends AbstractSelection<ObjecttableSelection> {
    @Override
    protected Uri baseUri() {
        return ObjecttableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ObjecttableCursor} object, which is positioned before the first entry, or null.
     */
    public ObjecttableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ObjecttableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ObjecttableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ObjecttableCursor} object, which is positioned before the first entry, or null.
     */
    public ObjecttableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ObjecttableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ObjecttableCursor query(Context context) {
        return query(context, null);
    }


    public ObjecttableSelection id(long... value) {
        addEquals("objecttable." + ObjecttableColumns._ID, toObjectArray(value));
        return this;
    }

    public ObjecttableSelection idNot(long... value) {
        addNotEquals("objecttable." + ObjecttableColumns._ID, toObjectArray(value));
        return this;
    }

    public ObjecttableSelection orderById(boolean desc) {
        orderBy("objecttable." + ObjecttableColumns._ID, desc);
        return this;
    }

    public ObjecttableSelection orderById() {
        return orderById(false);
    }

    public ObjecttableSelection keyId(String... value) {
        addEquals(ObjecttableColumns.KEY_ID, value);
        return this;
    }

    public ObjecttableSelection keyIdNot(String... value) {
        addNotEquals(ObjecttableColumns.KEY_ID, value);
        return this;
    }

    public ObjecttableSelection keyIdLike(String... value) {
        addLike(ObjecttableColumns.KEY_ID, value);
        return this;
    }

    public ObjecttableSelection keyIdContains(String... value) {
        addContains(ObjecttableColumns.KEY_ID, value);
        return this;
    }

    public ObjecttableSelection keyIdStartsWith(String... value) {
        addStartsWith(ObjecttableColumns.KEY_ID, value);
        return this;
    }

    public ObjecttableSelection keyIdEndsWith(String... value) {
        addEndsWith(ObjecttableColumns.KEY_ID, value);
        return this;
    }

    public ObjecttableSelection orderByKeyId(boolean desc) {
        orderBy(ObjecttableColumns.KEY_ID, desc);
        return this;
    }

    public ObjecttableSelection orderByKeyId() {
        orderBy(ObjecttableColumns.KEY_ID, false);
        return this;
    }

    public ObjecttableSelection keyValue(String... value) {
        addEquals(ObjecttableColumns.KEY_VALUE, value);
        return this;
    }

    public ObjecttableSelection keyValueNot(String... value) {
        addNotEquals(ObjecttableColumns.KEY_VALUE, value);
        return this;
    }

    public ObjecttableSelection keyValueLike(String... value) {
        addLike(ObjecttableColumns.KEY_VALUE, value);
        return this;
    }

    public ObjecttableSelection keyValueContains(String... value) {
        addContains(ObjecttableColumns.KEY_VALUE, value);
        return this;
    }

    public ObjecttableSelection keyValueStartsWith(String... value) {
        addStartsWith(ObjecttableColumns.KEY_VALUE, value);
        return this;
    }

    public ObjecttableSelection keyValueEndsWith(String... value) {
        addEndsWith(ObjecttableColumns.KEY_VALUE, value);
        return this;
    }

    public ObjecttableSelection orderByKeyValue(boolean desc) {
        orderBy(ObjecttableColumns.KEY_VALUE, desc);
        return this;
    }

    public ObjecttableSelection orderByKeyValue() {
        orderBy(ObjecttableColumns.KEY_VALUE, false);
        return this;
    }

    public ObjecttableSelection keyVersion(Integer... value) {
        addEquals(ObjecttableColumns.KEY_VERSION, value);
        return this;
    }

    public ObjecttableSelection keyVersionNot(Integer... value) {
        addNotEquals(ObjecttableColumns.KEY_VERSION, value);
        return this;
    }

    public ObjecttableSelection keyVersionGt(int value) {
        addGreaterThan(ObjecttableColumns.KEY_VERSION, value);
        return this;
    }

    public ObjecttableSelection keyVersionGtEq(int value) {
        addGreaterThanOrEquals(ObjecttableColumns.KEY_VERSION, value);
        return this;
    }

    public ObjecttableSelection keyVersionLt(int value) {
        addLessThan(ObjecttableColumns.KEY_VERSION, value);
        return this;
    }

    public ObjecttableSelection keyVersionLtEq(int value) {
        addLessThanOrEquals(ObjecttableColumns.KEY_VERSION, value);
        return this;
    }

    public ObjecttableSelection orderByKeyVersion(boolean desc) {
        orderBy(ObjecttableColumns.KEY_VERSION, desc);
        return this;
    }

    public ObjecttableSelection orderByKeyVersion() {
        orderBy(ObjecttableColumns.KEY_VERSION, false);
        return this;
    }
}
