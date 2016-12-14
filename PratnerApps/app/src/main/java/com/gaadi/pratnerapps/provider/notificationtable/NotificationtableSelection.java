package com.gaadi.pratnerapps.provider.notificationtable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code notificationtable} table.
 */
public class NotificationtableSelection extends AbstractSelection<NotificationtableSelection> {
    @Override
    protected Uri baseUri() {
        return NotificationtableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code NotificationtableCursor} object, which is positioned before the first entry, or null.
     */
    public NotificationtableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new NotificationtableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public NotificationtableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code NotificationtableCursor} object, which is positioned before the first entry, or null.
     */
    public NotificationtableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new NotificationtableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public NotificationtableCursor query(Context context) {
        return query(context, null);
    }


    public NotificationtableSelection id(long... value) {
        addEquals("notificationtable." + NotificationtableColumns._ID, toObjectArray(value));
        return this;
    }

    public NotificationtableSelection idNot(long... value) {
        addNotEquals("notificationtable." + NotificationtableColumns._ID, toObjectArray(value));
        return this;
    }

    public NotificationtableSelection orderById(boolean desc) {
        orderBy("notificationtable." + NotificationtableColumns._ID, desc);
        return this;
    }

    public NotificationtableSelection orderById() {
        return orderById(false);
    }

    public NotificationtableSelection title(String... value) {
        addEquals(NotificationtableColumns.TITLE, value);
        return this;
    }

    public NotificationtableSelection titleNot(String... value) {
        addNotEquals(NotificationtableColumns.TITLE, value);
        return this;
    }

    public NotificationtableSelection titleLike(String... value) {
        addLike(NotificationtableColumns.TITLE, value);
        return this;
    }

    public NotificationtableSelection titleContains(String... value) {
        addContains(NotificationtableColumns.TITLE, value);
        return this;
    }

    public NotificationtableSelection titleStartsWith(String... value) {
        addStartsWith(NotificationtableColumns.TITLE, value);
        return this;
    }

    public NotificationtableSelection titleEndsWith(String... value) {
        addEndsWith(NotificationtableColumns.TITLE, value);
        return this;
    }

    public NotificationtableSelection orderByTitle(boolean desc) {
        orderBy(NotificationtableColumns.TITLE, desc);
        return this;
    }

    public NotificationtableSelection orderByTitle() {
        orderBy(NotificationtableColumns.TITLE, false);
        return this;
    }

    public NotificationtableSelection description(String... value) {
        addEquals(NotificationtableColumns.DESCRIPTION, value);
        return this;
    }

    public NotificationtableSelection descriptionNot(String... value) {
        addNotEquals(NotificationtableColumns.DESCRIPTION, value);
        return this;
    }

    public NotificationtableSelection descriptionLike(String... value) {
        addLike(NotificationtableColumns.DESCRIPTION, value);
        return this;
    }

    public NotificationtableSelection descriptionContains(String... value) {
        addContains(NotificationtableColumns.DESCRIPTION, value);
        return this;
    }

    public NotificationtableSelection descriptionStartsWith(String... value) {
        addStartsWith(NotificationtableColumns.DESCRIPTION, value);
        return this;
    }

    public NotificationtableSelection descriptionEndsWith(String... value) {
        addEndsWith(NotificationtableColumns.DESCRIPTION, value);
        return this;
    }

    public NotificationtableSelection orderByDescription(boolean desc) {
        orderBy(NotificationtableColumns.DESCRIPTION, desc);
        return this;
    }

    public NotificationtableSelection orderByDescription() {
        orderBy(NotificationtableColumns.DESCRIPTION, false);
        return this;
    }

    public NotificationtableSelection secondaryText(String... value) {
        addEquals(NotificationtableColumns.SECONDARY_TEXT, value);
        return this;
    }

    public NotificationtableSelection secondaryTextNot(String... value) {
        addNotEquals(NotificationtableColumns.SECONDARY_TEXT, value);
        return this;
    }

    public NotificationtableSelection secondaryTextLike(String... value) {
        addLike(NotificationtableColumns.SECONDARY_TEXT, value);
        return this;
    }

    public NotificationtableSelection secondaryTextContains(String... value) {
        addContains(NotificationtableColumns.SECONDARY_TEXT, value);
        return this;
    }

    public NotificationtableSelection secondaryTextStartsWith(String... value) {
        addStartsWith(NotificationtableColumns.SECONDARY_TEXT, value);
        return this;
    }

    public NotificationtableSelection secondaryTextEndsWith(String... value) {
        addEndsWith(NotificationtableColumns.SECONDARY_TEXT, value);
        return this;
    }

    public NotificationtableSelection orderBySecondaryText(boolean desc) {
        orderBy(NotificationtableColumns.SECONDARY_TEXT, desc);
        return this;
    }

    public NotificationtableSelection orderBySecondaryText() {
        orderBy(NotificationtableColumns.SECONDARY_TEXT, false);
        return this;
    }

    public NotificationtableSelection date(Long... value) {
        addEquals(NotificationtableColumns.DATE, value);
        return this;
    }

    public NotificationtableSelection dateNot(Long... value) {
        addNotEquals(NotificationtableColumns.DATE, value);
        return this;
    }

    public NotificationtableSelection dateGt(long value) {
        addGreaterThan(NotificationtableColumns.DATE, value);
        return this;
    }

    public NotificationtableSelection dateGtEq(long value) {
        addGreaterThanOrEquals(NotificationtableColumns.DATE, value);
        return this;
    }

    public NotificationtableSelection dateLt(long value) {
        addLessThan(NotificationtableColumns.DATE, value);
        return this;
    }

    public NotificationtableSelection dateLtEq(long value) {
        addLessThanOrEquals(NotificationtableColumns.DATE, value);
        return this;
    }

    public NotificationtableSelection orderByDate(boolean desc) {
        orderBy(NotificationtableColumns.DATE, desc);
        return this;
    }

    public NotificationtableSelection orderByDate() {
        orderBy(NotificationtableColumns.DATE, false);
        return this;
    }

    public NotificationtableSelection imageUrl(String... value) {
        addEquals(NotificationtableColumns.IMAGE_URL, value);
        return this;
    }

    public NotificationtableSelection imageUrlNot(String... value) {
        addNotEquals(NotificationtableColumns.IMAGE_URL, value);
        return this;
    }

    public NotificationtableSelection imageUrlLike(String... value) {
        addLike(NotificationtableColumns.IMAGE_URL, value);
        return this;
    }

    public NotificationtableSelection imageUrlContains(String... value) {
        addContains(NotificationtableColumns.IMAGE_URL, value);
        return this;
    }

    public NotificationtableSelection imageUrlStartsWith(String... value) {
        addStartsWith(NotificationtableColumns.IMAGE_URL, value);
        return this;
    }

    public NotificationtableSelection imageUrlEndsWith(String... value) {
        addEndsWith(NotificationtableColumns.IMAGE_URL, value);
        return this;
    }

    public NotificationtableSelection orderByImageUrl(boolean desc) {
        orderBy(NotificationtableColumns.IMAGE_URL, desc);
        return this;
    }

    public NotificationtableSelection orderByImageUrl() {
        orderBy(NotificationtableColumns.IMAGE_URL, false);
        return this;
    }

    public NotificationtableSelection readStatus(Integer... value) {
        addEquals(NotificationtableColumns.READ_STATUS, value);
        return this;
    }

    public NotificationtableSelection readStatusNot(Integer... value) {
        addNotEquals(NotificationtableColumns.READ_STATUS, value);
        return this;
    }

    public NotificationtableSelection readStatusGt(int value) {
        addGreaterThan(NotificationtableColumns.READ_STATUS, value);
        return this;
    }

    public NotificationtableSelection readStatusGtEq(int value) {
        addGreaterThanOrEquals(NotificationtableColumns.READ_STATUS, value);
        return this;
    }

    public NotificationtableSelection readStatusLt(int value) {
        addLessThan(NotificationtableColumns.READ_STATUS, value);
        return this;
    }

    public NotificationtableSelection readStatusLtEq(int value) {
        addLessThanOrEquals(NotificationtableColumns.READ_STATUS, value);
        return this;
    }

    public NotificationtableSelection orderByReadStatus(boolean desc) {
        orderBy(NotificationtableColumns.READ_STATUS, desc);
        return this;
    }

    public NotificationtableSelection orderByReadStatus() {
        orderBy(NotificationtableColumns.READ_STATUS, false);
        return this;
    }
}
