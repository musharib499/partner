package com.gaadi.pratnerapps.provider.showroomtable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code showroomtable} table.
 */
public class ShowroomtableSelection extends AbstractSelection<ShowroomtableSelection> {
    @Override
    protected Uri baseUri() {
        return ShowroomtableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ShowroomtableCursor} object, which is positioned before the first entry, or null.
     */
    public ShowroomtableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ShowroomtableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ShowroomtableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ShowroomtableCursor} object, which is positioned before the first entry, or null.
     */
    public ShowroomtableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ShowroomtableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ShowroomtableCursor query(Context context) {
        return query(context, null);
    }


    public ShowroomtableSelection id(long... value) {
        addEquals("showroomtable." + ShowroomtableColumns._ID, toObjectArray(value));
        return this;
    }

    public ShowroomtableSelection idNot(long... value) {
        addNotEquals("showroomtable." + ShowroomtableColumns._ID, toObjectArray(value));
        return this;
    }

    public ShowroomtableSelection orderById(boolean desc) {
        orderBy("showroomtable." + ShowroomtableColumns._ID, desc);
        return this;
    }

    public ShowroomtableSelection orderById() {
        return orderById(false);
    }

    public ShowroomtableSelection showroomId(Integer... value) {
        addEquals(ShowroomtableColumns.SHOWROOM_ID, value);
        return this;
    }

    public ShowroomtableSelection showroomIdNot(Integer... value) {
        addNotEquals(ShowroomtableColumns.SHOWROOM_ID, value);
        return this;
    }

    public ShowroomtableSelection showroomIdGt(int value) {
        addGreaterThan(ShowroomtableColumns.SHOWROOM_ID, value);
        return this;
    }

    public ShowroomtableSelection showroomIdGtEq(int value) {
        addGreaterThanOrEquals(ShowroomtableColumns.SHOWROOM_ID, value);
        return this;
    }

    public ShowroomtableSelection showroomIdLt(int value) {
        addLessThan(ShowroomtableColumns.SHOWROOM_ID, value);
        return this;
    }

    public ShowroomtableSelection showroomIdLtEq(int value) {
        addLessThanOrEquals(ShowroomtableColumns.SHOWROOM_ID, value);
        return this;
    }

    public ShowroomtableSelection orderByShowroomId(boolean desc) {
        orderBy(ShowroomtableColumns.SHOWROOM_ID, desc);
        return this;
    }

    public ShowroomtableSelection orderByShowroomId() {
        orderBy(ShowroomtableColumns.SHOWROOM_ID, false);
        return this;
    }

    public ShowroomtableSelection name(String... value) {
        addEquals(ShowroomtableColumns.NAME, value);
        return this;
    }

    public ShowroomtableSelection nameNot(String... value) {
        addNotEquals(ShowroomtableColumns.NAME, value);
        return this;
    }

    public ShowroomtableSelection nameLike(String... value) {
        addLike(ShowroomtableColumns.NAME, value);
        return this;
    }

    public ShowroomtableSelection nameContains(String... value) {
        addContains(ShowroomtableColumns.NAME, value);
        return this;
    }

    public ShowroomtableSelection nameStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.NAME, value);
        return this;
    }

    public ShowroomtableSelection nameEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.NAME, value);
        return this;
    }

    public ShowroomtableSelection orderByName(boolean desc) {
        orderBy(ShowroomtableColumns.NAME, desc);
        return this;
    }

    public ShowroomtableSelection orderByName() {
        orderBy(ShowroomtableColumns.NAME, false);
        return this;
    }

    public ShowroomtableSelection latitude(String... value) {
        addEquals(ShowroomtableColumns.LATITUDE, value);
        return this;
    }

    public ShowroomtableSelection latitudeNot(String... value) {
        addNotEquals(ShowroomtableColumns.LATITUDE, value);
        return this;
    }

    public ShowroomtableSelection latitudeLike(String... value) {
        addLike(ShowroomtableColumns.LATITUDE, value);
        return this;
    }

    public ShowroomtableSelection latitudeContains(String... value) {
        addContains(ShowroomtableColumns.LATITUDE, value);
        return this;
    }

    public ShowroomtableSelection latitudeStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.LATITUDE, value);
        return this;
    }

    public ShowroomtableSelection latitudeEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.LATITUDE, value);
        return this;
    }

    public ShowroomtableSelection orderByLatitude(boolean desc) {
        orderBy(ShowroomtableColumns.LATITUDE, desc);
        return this;
    }

    public ShowroomtableSelection orderByLatitude() {
        orderBy(ShowroomtableColumns.LATITUDE, false);
        return this;
    }

    public ShowroomtableSelection longitude(String... value) {
        addEquals(ShowroomtableColumns.LONGITUDE, value);
        return this;
    }

    public ShowroomtableSelection longitudeNot(String... value) {
        addNotEquals(ShowroomtableColumns.LONGITUDE, value);
        return this;
    }

    public ShowroomtableSelection longitudeLike(String... value) {
        addLike(ShowroomtableColumns.LONGITUDE, value);
        return this;
    }

    public ShowroomtableSelection longitudeContains(String... value) {
        addContains(ShowroomtableColumns.LONGITUDE, value);
        return this;
    }

    public ShowroomtableSelection longitudeStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.LONGITUDE, value);
        return this;
    }

    public ShowroomtableSelection longitudeEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.LONGITUDE, value);
        return this;
    }

    public ShowroomtableSelection orderByLongitude(boolean desc) {
        orderBy(ShowroomtableColumns.LONGITUDE, desc);
        return this;
    }

    public ShowroomtableSelection orderByLongitude() {
        orderBy(ShowroomtableColumns.LONGITUDE, false);
        return this;
    }

    public ShowroomtableSelection dealerType(String... value) {
        addEquals(ShowroomtableColumns.DEALER_TYPE, value);
        return this;
    }

    public ShowroomtableSelection dealerTypeNot(String... value) {
        addNotEquals(ShowroomtableColumns.DEALER_TYPE, value);
        return this;
    }

    public ShowroomtableSelection dealerTypeLike(String... value) {
        addLike(ShowroomtableColumns.DEALER_TYPE, value);
        return this;
    }

    public ShowroomtableSelection dealerTypeContains(String... value) {
        addContains(ShowroomtableColumns.DEALER_TYPE, value);
        return this;
    }

    public ShowroomtableSelection dealerTypeStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.DEALER_TYPE, value);
        return this;
    }

    public ShowroomtableSelection dealerTypeEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.DEALER_TYPE, value);
        return this;
    }

    public ShowroomtableSelection orderByDealerType(boolean desc) {
        orderBy(ShowroomtableColumns.DEALER_TYPE, desc);
        return this;
    }

    public ShowroomtableSelection orderByDealerType() {
        orderBy(ShowroomtableColumns.DEALER_TYPE, false);
        return this;
    }

    public ShowroomtableSelection address(String... value) {
        addEquals(ShowroomtableColumns.ADDRESS, value);
        return this;
    }

    public ShowroomtableSelection addressNot(String... value) {
        addNotEquals(ShowroomtableColumns.ADDRESS, value);
        return this;
    }

    public ShowroomtableSelection addressLike(String... value) {
        addLike(ShowroomtableColumns.ADDRESS, value);
        return this;
    }

    public ShowroomtableSelection addressContains(String... value) {
        addContains(ShowroomtableColumns.ADDRESS, value);
        return this;
    }

    public ShowroomtableSelection addressStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.ADDRESS, value);
        return this;
    }

    public ShowroomtableSelection addressEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.ADDRESS, value);
        return this;
    }

    public ShowroomtableSelection orderByAddress(boolean desc) {
        orderBy(ShowroomtableColumns.ADDRESS, desc);
        return this;
    }

    public ShowroomtableSelection orderByAddress() {
        orderBy(ShowroomtableColumns.ADDRESS, false);
        return this;
    }

    public ShowroomtableSelection mobile(String... value) {
        addEquals(ShowroomtableColumns.MOBILE, value);
        return this;
    }

    public ShowroomtableSelection mobileNot(String... value) {
        addNotEquals(ShowroomtableColumns.MOBILE, value);
        return this;
    }

    public ShowroomtableSelection mobileLike(String... value) {
        addLike(ShowroomtableColumns.MOBILE, value);
        return this;
    }

    public ShowroomtableSelection mobileContains(String... value) {
        addContains(ShowroomtableColumns.MOBILE, value);
        return this;
    }

    public ShowroomtableSelection mobileStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.MOBILE, value);
        return this;
    }

    public ShowroomtableSelection mobileEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.MOBILE, value);
        return this;
    }

    public ShowroomtableSelection orderByMobile(boolean desc) {
        orderBy(ShowroomtableColumns.MOBILE, desc);
        return this;
    }

    public ShowroomtableSelection orderByMobile() {
        orderBy(ShowroomtableColumns.MOBILE, false);
        return this;
    }

    public ShowroomtableSelection email1(String... value) {
        addEquals(ShowroomtableColumns.EMAIL1, value);
        return this;
    }

    public ShowroomtableSelection email1Not(String... value) {
        addNotEquals(ShowroomtableColumns.EMAIL1, value);
        return this;
    }

    public ShowroomtableSelection email1Like(String... value) {
        addLike(ShowroomtableColumns.EMAIL1, value);
        return this;
    }

    public ShowroomtableSelection email1Contains(String... value) {
        addContains(ShowroomtableColumns.EMAIL1, value);
        return this;
    }

    public ShowroomtableSelection email1StartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.EMAIL1, value);
        return this;
    }

    public ShowroomtableSelection email1EndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.EMAIL1, value);
        return this;
    }

    public ShowroomtableSelection orderByEmail1(boolean desc) {
        orderBy(ShowroomtableColumns.EMAIL1, desc);
        return this;
    }

    public ShowroomtableSelection orderByEmail1() {
        orderBy(ShowroomtableColumns.EMAIL1, false);
        return this;
    }

    public ShowroomtableSelection images(String... value) {
        addEquals(ShowroomtableColumns.IMAGES, value);
        return this;
    }

    public ShowroomtableSelection imagesNot(String... value) {
        addNotEquals(ShowroomtableColumns.IMAGES, value);
        return this;
    }

    public ShowroomtableSelection imagesLike(String... value) {
        addLike(ShowroomtableColumns.IMAGES, value);
        return this;
    }

    public ShowroomtableSelection imagesContains(String... value) {
        addContains(ShowroomtableColumns.IMAGES, value);
        return this;
    }

    public ShowroomtableSelection imagesStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.IMAGES, value);
        return this;
    }

    public ShowroomtableSelection imagesEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.IMAGES, value);
        return this;
    }

    public ShowroomtableSelection orderByImages(boolean desc) {
        orderBy(ShowroomtableColumns.IMAGES, desc);
        return this;
    }

    public ShowroomtableSelection orderByImages() {
        orderBy(ShowroomtableColumns.IMAGES, false);
        return this;
    }

    public ShowroomtableSelection city(String... value) {
        addEquals(ShowroomtableColumns.CITY, value);
        return this;
    }

    public ShowroomtableSelection cityNot(String... value) {
        addNotEquals(ShowroomtableColumns.CITY, value);
        return this;
    }

    public ShowroomtableSelection cityLike(String... value) {
        addLike(ShowroomtableColumns.CITY, value);
        return this;
    }

    public ShowroomtableSelection cityContains(String... value) {
        addContains(ShowroomtableColumns.CITY, value);
        return this;
    }

    public ShowroomtableSelection cityStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.CITY, value);
        return this;
    }

    public ShowroomtableSelection cityEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.CITY, value);
        return this;
    }

    public ShowroomtableSelection orderByCity(boolean desc) {
        orderBy(ShowroomtableColumns.CITY, desc);
        return this;
    }

    public ShowroomtableSelection orderByCity() {
        orderBy(ShowroomtableColumns.CITY, false);
        return this;
    }

    public ShowroomtableSelection state(String... value) {
        addEquals(ShowroomtableColumns.STATE, value);
        return this;
    }

    public ShowroomtableSelection stateNot(String... value) {
        addNotEquals(ShowroomtableColumns.STATE, value);
        return this;
    }

    public ShowroomtableSelection stateLike(String... value) {
        addLike(ShowroomtableColumns.STATE, value);
        return this;
    }

    public ShowroomtableSelection stateContains(String... value) {
        addContains(ShowroomtableColumns.STATE, value);
        return this;
    }

    public ShowroomtableSelection stateStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.STATE, value);
        return this;
    }

    public ShowroomtableSelection stateEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.STATE, value);
        return this;
    }

    public ShowroomtableSelection orderByState(boolean desc) {
        orderBy(ShowroomtableColumns.STATE, desc);
        return this;
    }

    public ShowroomtableSelection orderByState() {
        orderBy(ShowroomtableColumns.STATE, false);
        return this;
    }

    public ShowroomtableSelection pincode(String... value) {
        addEquals(ShowroomtableColumns.PINCODE, value);
        return this;
    }

    public ShowroomtableSelection pincodeNot(String... value) {
        addNotEquals(ShowroomtableColumns.PINCODE, value);
        return this;
    }

    public ShowroomtableSelection pincodeLike(String... value) {
        addLike(ShowroomtableColumns.PINCODE, value);
        return this;
    }

    public ShowroomtableSelection pincodeContains(String... value) {
        addContains(ShowroomtableColumns.PINCODE, value);
        return this;
    }

    public ShowroomtableSelection pincodeStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.PINCODE, value);
        return this;
    }

    public ShowroomtableSelection pincodeEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.PINCODE, value);
        return this;
    }

    public ShowroomtableSelection orderByPincode(boolean desc) {
        orderBy(ShowroomtableColumns.PINCODE, desc);
        return this;
    }

    public ShowroomtableSelection orderByPincode() {
        orderBy(ShowroomtableColumns.PINCODE, false);
        return this;
    }

    public ShowroomtableSelection email2(String... value) {
        addEquals(ShowroomtableColumns.EMAIL2, value);
        return this;
    }

    public ShowroomtableSelection email2Not(String... value) {
        addNotEquals(ShowroomtableColumns.EMAIL2, value);
        return this;
    }

    public ShowroomtableSelection email2Like(String... value) {
        addLike(ShowroomtableColumns.EMAIL2, value);
        return this;
    }

    public ShowroomtableSelection email2Contains(String... value) {
        addContains(ShowroomtableColumns.EMAIL2, value);
        return this;
    }

    public ShowroomtableSelection email2StartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.EMAIL2, value);
        return this;
    }

    public ShowroomtableSelection email2EndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.EMAIL2, value);
        return this;
    }

    public ShowroomtableSelection orderByEmail2(boolean desc) {
        orderBy(ShowroomtableColumns.EMAIL2, desc);
        return this;
    }

    public ShowroomtableSelection orderByEmail2() {
        orderBy(ShowroomtableColumns.EMAIL2, false);
        return this;
    }

    public ShowroomtableSelection phone1(String... value) {
        addEquals(ShowroomtableColumns.PHONE1, value);
        return this;
    }

    public ShowroomtableSelection phone1Not(String... value) {
        addNotEquals(ShowroomtableColumns.PHONE1, value);
        return this;
    }

    public ShowroomtableSelection phone1Like(String... value) {
        addLike(ShowroomtableColumns.PHONE1, value);
        return this;
    }

    public ShowroomtableSelection phone1Contains(String... value) {
        addContains(ShowroomtableColumns.PHONE1, value);
        return this;
    }

    public ShowroomtableSelection phone1StartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.PHONE1, value);
        return this;
    }

    public ShowroomtableSelection phone1EndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.PHONE1, value);
        return this;
    }

    public ShowroomtableSelection orderByPhone1(boolean desc) {
        orderBy(ShowroomtableColumns.PHONE1, desc);
        return this;
    }

    public ShowroomtableSelection orderByPhone1() {
        orderBy(ShowroomtableColumns.PHONE1, false);
        return this;
    }

    public ShowroomtableSelection phone2(String... value) {
        addEquals(ShowroomtableColumns.PHONE2, value);
        return this;
    }

    public ShowroomtableSelection phone2Not(String... value) {
        addNotEquals(ShowroomtableColumns.PHONE2, value);
        return this;
    }

    public ShowroomtableSelection phone2Like(String... value) {
        addLike(ShowroomtableColumns.PHONE2, value);
        return this;
    }

    public ShowroomtableSelection phone2Contains(String... value) {
        addContains(ShowroomtableColumns.PHONE2, value);
        return this;
    }

    public ShowroomtableSelection phone2StartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.PHONE2, value);
        return this;
    }

    public ShowroomtableSelection phone2EndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.PHONE2, value);
        return this;
    }

    public ShowroomtableSelection orderByPhone2(boolean desc) {
        orderBy(ShowroomtableColumns.PHONE2, desc);
        return this;
    }

    public ShowroomtableSelection orderByPhone2() {
        orderBy(ShowroomtableColumns.PHONE2, false);
        return this;
    }

    public ShowroomtableSelection locality(String... value) {
        addEquals(ShowroomtableColumns.LOCALITY, value);
        return this;
    }

    public ShowroomtableSelection localityNot(String... value) {
        addNotEquals(ShowroomtableColumns.LOCALITY, value);
        return this;
    }

    public ShowroomtableSelection localityLike(String... value) {
        addLike(ShowroomtableColumns.LOCALITY, value);
        return this;
    }

    public ShowroomtableSelection localityContains(String... value) {
        addContains(ShowroomtableColumns.LOCALITY, value);
        return this;
    }

    public ShowroomtableSelection localityStartsWith(String... value) {
        addStartsWith(ShowroomtableColumns.LOCALITY, value);
        return this;
    }

    public ShowroomtableSelection localityEndsWith(String... value) {
        addEndsWith(ShowroomtableColumns.LOCALITY, value);
        return this;
    }

    public ShowroomtableSelection orderByLocality(boolean desc) {
        orderBy(ShowroomtableColumns.LOCALITY, desc);
        return this;
    }

    public ShowroomtableSelection orderByLocality() {
        orderBy(ShowroomtableColumns.LOCALITY, false);
        return this;
    }
}
