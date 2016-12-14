package com.gaadi.pratnerapps.provider.citytable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code citytable} table.
 */
public class CitytableSelection extends AbstractSelection<CitytableSelection> {
    @Override
    protected Uri baseUri() {
        return CitytableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CitytableCursor} object, which is positioned before the first entry, or null.
     */
    public CitytableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CitytableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CitytableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CitytableCursor} object, which is positioned before the first entry, or null.
     */
    public CitytableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CitytableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CitytableCursor query(Context context) {
        return query(context, null);
    }


    public CitytableSelection id(long... value) {
        addEquals("citytable." + CitytableColumns._ID, toObjectArray(value));
        return this;
    }

    public CitytableSelection idNot(long... value) {
        addNotEquals("citytable." + CitytableColumns._ID, toObjectArray(value));
        return this;
    }

    public CitytableSelection orderById(boolean desc) {
        orderBy("citytable." + CitytableColumns._ID, desc);
        return this;
    }

    public CitytableSelection orderById() {
        return orderById(false);
    }

    public CitytableSelection cityId(Integer... value) {
        addEquals(CitytableColumns.CITY_ID, value);
        return this;
    }

    public CitytableSelection cityIdNot(Integer... value) {
        addNotEquals(CitytableColumns.CITY_ID, value);
        return this;
    }

    public CitytableSelection cityIdGt(int value) {
        addGreaterThan(CitytableColumns.CITY_ID, value);
        return this;
    }

    public CitytableSelection cityIdGtEq(int value) {
        addGreaterThanOrEquals(CitytableColumns.CITY_ID, value);
        return this;
    }

    public CitytableSelection cityIdLt(int value) {
        addLessThan(CitytableColumns.CITY_ID, value);
        return this;
    }

    public CitytableSelection cityIdLtEq(int value) {
        addLessThanOrEquals(CitytableColumns.CITY_ID, value);
        return this;
    }

    public CitytableSelection orderByCityId(boolean desc) {
        orderBy(CitytableColumns.CITY_ID, desc);
        return this;
    }

    public CitytableSelection orderByCityId() {
        orderBy(CitytableColumns.CITY_ID, false);
        return this;
    }

    public CitytableSelection cityName(String... value) {
        addEquals(CitytableColumns.CITY_NAME, value);
        return this;
    }

    public CitytableSelection cityNameNot(String... value) {
        addNotEquals(CitytableColumns.CITY_NAME, value);
        return this;
    }

    public CitytableSelection cityNameLike(String... value) {
        addLike(CitytableColumns.CITY_NAME, value);
        return this;
    }

    public CitytableSelection cityNameContains(String... value) {
        addContains(CitytableColumns.CITY_NAME, value);
        return this;
    }

    public CitytableSelection cityNameStartsWith(String... value) {
        addStartsWith(CitytableColumns.CITY_NAME, value);
        return this;
    }

    public CitytableSelection cityNameEndsWith(String... value) {
        addEndsWith(CitytableColumns.CITY_NAME, value);
        return this;
    }

    public CitytableSelection orderByCityName(boolean desc) {
        orderBy(CitytableColumns.CITY_NAME, desc);
        return this;
    }

    public CitytableSelection orderByCityName() {
        orderBy(CitytableColumns.CITY_NAME, false);
        return this;
    }

    public CitytableSelection stateId(Integer... value) {
        addEquals(CitytableColumns.STATE_ID, value);
        return this;
    }

    public CitytableSelection stateIdNot(Integer... value) {
        addNotEquals(CitytableColumns.STATE_ID, value);
        return this;
    }

    public CitytableSelection stateIdGt(int value) {
        addGreaterThan(CitytableColumns.STATE_ID, value);
        return this;
    }

    public CitytableSelection stateIdGtEq(int value) {
        addGreaterThanOrEquals(CitytableColumns.STATE_ID, value);
        return this;
    }

    public CitytableSelection stateIdLt(int value) {
        addLessThan(CitytableColumns.STATE_ID, value);
        return this;
    }

    public CitytableSelection stateIdLtEq(int value) {
        addLessThanOrEquals(CitytableColumns.STATE_ID, value);
        return this;
    }

    public CitytableSelection orderByStateId(boolean desc) {
        orderBy(CitytableColumns.STATE_ID, desc);
        return this;
    }

    public CitytableSelection orderByStateId() {
        orderBy(CitytableColumns.STATE_ID, false);
        return this;
    }

    public CitytableSelection stateName(String... value) {
        addEquals(CitytableColumns.STATE_NAME, value);
        return this;
    }

    public CitytableSelection stateNameNot(String... value) {
        addNotEquals(CitytableColumns.STATE_NAME, value);
        return this;
    }

    public CitytableSelection stateNameLike(String... value) {
        addLike(CitytableColumns.STATE_NAME, value);
        return this;
    }

    public CitytableSelection stateNameContains(String... value) {
        addContains(CitytableColumns.STATE_NAME, value);
        return this;
    }

    public CitytableSelection stateNameStartsWith(String... value) {
        addStartsWith(CitytableColumns.STATE_NAME, value);
        return this;
    }

    public CitytableSelection stateNameEndsWith(String... value) {
        addEndsWith(CitytableColumns.STATE_NAME, value);
        return this;
    }

    public CitytableSelection orderByStateName(boolean desc) {
        orderBy(CitytableColumns.STATE_NAME, desc);
        return this;
    }

    public CitytableSelection orderByStateName() {
        orderBy(CitytableColumns.STATE_NAME, false);
        return this;
    }

    public CitytableSelection pincode(String... value) {
        addEquals(CitytableColumns.PINCODE, value);
        return this;
    }

    public CitytableSelection pincodeNot(String... value) {
        addNotEquals(CitytableColumns.PINCODE, value);
        return this;
    }

    public CitytableSelection pincodeLike(String... value) {
        addLike(CitytableColumns.PINCODE, value);
        return this;
    }

    public CitytableSelection pincodeContains(String... value) {
        addContains(CitytableColumns.PINCODE, value);
        return this;
    }

    public CitytableSelection pincodeStartsWith(String... value) {
        addStartsWith(CitytableColumns.PINCODE, value);
        return this;
    }

    public CitytableSelection pincodeEndsWith(String... value) {
        addEndsWith(CitytableColumns.PINCODE, value);
        return this;
    }

    public CitytableSelection orderByPincode(boolean desc) {
        orderBy(CitytableColumns.PINCODE, desc);
        return this;
    }

    public CitytableSelection orderByPincode() {
        orderBy(CitytableColumns.PINCODE, false);
        return this;
    }

    public CitytableSelection region(String... value) {
        addEquals(CitytableColumns.REGION, value);
        return this;
    }

    public CitytableSelection regionNot(String... value) {
        addNotEquals(CitytableColumns.REGION, value);
        return this;
    }

    public CitytableSelection regionLike(String... value) {
        addLike(CitytableColumns.REGION, value);
        return this;
    }

    public CitytableSelection regionContains(String... value) {
        addContains(CitytableColumns.REGION, value);
        return this;
    }

    public CitytableSelection regionStartsWith(String... value) {
        addStartsWith(CitytableColumns.REGION, value);
        return this;
    }

    public CitytableSelection regionEndsWith(String... value) {
        addEndsWith(CitytableColumns.REGION, value);
        return this;
    }

    public CitytableSelection orderByRegion(boolean desc) {
        orderBy(CitytableColumns.REGION, desc);
        return this;
    }

    public CitytableSelection orderByRegion() {
        orderBy(CitytableColumns.REGION, false);
        return this;
    }
}
