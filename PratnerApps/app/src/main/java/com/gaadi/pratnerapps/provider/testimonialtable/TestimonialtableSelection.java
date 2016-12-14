package com.gaadi.pratnerapps.provider.testimonialtable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.gaadi.pratnerapps.provider.base.AbstractSelection;

/**
 * Selection for the {@code testimonialtable} table.
 */
public class TestimonialtableSelection extends AbstractSelection<TestimonialtableSelection> {
    @Override
    protected Uri baseUri() {
        return TestimonialtableColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TestimonialtableCursor} object, which is positioned before the first entry, or null.
     */
    public TestimonialtableCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TestimonialtableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TestimonialtableCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TestimonialtableCursor} object, which is positioned before the first entry, or null.
     */
    public TestimonialtableCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TestimonialtableCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TestimonialtableCursor query(Context context) {
        return query(context, null);
    }


    public TestimonialtableSelection id(long... value) {
        addEquals("testimonialtable." + TestimonialtableColumns._ID, toObjectArray(value));
        return this;
    }

    public TestimonialtableSelection idNot(long... value) {
        addNotEquals("testimonialtable." + TestimonialtableColumns._ID, toObjectArray(value));
        return this;
    }

    public TestimonialtableSelection orderById(boolean desc) {
        orderBy("testimonialtable." + TestimonialtableColumns._ID, desc);
        return this;
    }

    public TestimonialtableSelection orderById() {
        return orderById(false);
    }

    public TestimonialtableSelection testimonialId(Integer... value) {
        addEquals(TestimonialtableColumns.TESTIMONIAL_ID, value);
        return this;
    }

    public TestimonialtableSelection testimonialIdNot(Integer... value) {
        addNotEquals(TestimonialtableColumns.TESTIMONIAL_ID, value);
        return this;
    }

    public TestimonialtableSelection testimonialIdGt(int value) {
        addGreaterThan(TestimonialtableColumns.TESTIMONIAL_ID, value);
        return this;
    }

    public TestimonialtableSelection testimonialIdGtEq(int value) {
        addGreaterThanOrEquals(TestimonialtableColumns.TESTIMONIAL_ID, value);
        return this;
    }

    public TestimonialtableSelection testimonialIdLt(int value) {
        addLessThan(TestimonialtableColumns.TESTIMONIAL_ID, value);
        return this;
    }

    public TestimonialtableSelection testimonialIdLtEq(int value) {
        addLessThanOrEquals(TestimonialtableColumns.TESTIMONIAL_ID, value);
        return this;
    }

    public TestimonialtableSelection orderByTestimonialId(boolean desc) {
        orderBy(TestimonialtableColumns.TESTIMONIAL_ID, desc);
        return this;
    }

    public TestimonialtableSelection orderByTestimonialId() {
        orderBy(TestimonialtableColumns.TESTIMONIAL_ID, false);
        return this;
    }

    public TestimonialtableSelection name(String... value) {
        addEquals(TestimonialtableColumns.NAME, value);
        return this;
    }

    public TestimonialtableSelection nameNot(String... value) {
        addNotEquals(TestimonialtableColumns.NAME, value);
        return this;
    }

    public TestimonialtableSelection nameLike(String... value) {
        addLike(TestimonialtableColumns.NAME, value);
        return this;
    }

    public TestimonialtableSelection nameContains(String... value) {
        addContains(TestimonialtableColumns.NAME, value);
        return this;
    }

    public TestimonialtableSelection nameStartsWith(String... value) {
        addStartsWith(TestimonialtableColumns.NAME, value);
        return this;
    }

    public TestimonialtableSelection nameEndsWith(String... value) {
        addEndsWith(TestimonialtableColumns.NAME, value);
        return this;
    }

    public TestimonialtableSelection orderByName(boolean desc) {
        orderBy(TestimonialtableColumns.NAME, desc);
        return this;
    }

    public TestimonialtableSelection orderByName() {
        orderBy(TestimonialtableColumns.NAME, false);
        return this;
    }

    public TestimonialtableSelection role(String... value) {
        addEquals(TestimonialtableColumns.ROLE, value);
        return this;
    }

    public TestimonialtableSelection roleNot(String... value) {
        addNotEquals(TestimonialtableColumns.ROLE, value);
        return this;
    }

    public TestimonialtableSelection roleLike(String... value) {
        addLike(TestimonialtableColumns.ROLE, value);
        return this;
    }

    public TestimonialtableSelection roleContains(String... value) {
        addContains(TestimonialtableColumns.ROLE, value);
        return this;
    }

    public TestimonialtableSelection roleStartsWith(String... value) {
        addStartsWith(TestimonialtableColumns.ROLE, value);
        return this;
    }

    public TestimonialtableSelection roleEndsWith(String... value) {
        addEndsWith(TestimonialtableColumns.ROLE, value);
        return this;
    }

    public TestimonialtableSelection orderByRole(boolean desc) {
        orderBy(TestimonialtableColumns.ROLE, desc);
        return this;
    }

    public TestimonialtableSelection orderByRole() {
        orderBy(TestimonialtableColumns.ROLE, false);
        return this;
    }

    public TestimonialtableSelection testimonial(String... value) {
        addEquals(TestimonialtableColumns.TESTIMONIAL, value);
        return this;
    }

    public TestimonialtableSelection testimonialNot(String... value) {
        addNotEquals(TestimonialtableColumns.TESTIMONIAL, value);
        return this;
    }

    public TestimonialtableSelection testimonialLike(String... value) {
        addLike(TestimonialtableColumns.TESTIMONIAL, value);
        return this;
    }

    public TestimonialtableSelection testimonialContains(String... value) {
        addContains(TestimonialtableColumns.TESTIMONIAL, value);
        return this;
    }

    public TestimonialtableSelection testimonialStartsWith(String... value) {
        addStartsWith(TestimonialtableColumns.TESTIMONIAL, value);
        return this;
    }

    public TestimonialtableSelection testimonialEndsWith(String... value) {
        addEndsWith(TestimonialtableColumns.TESTIMONIAL, value);
        return this;
    }

    public TestimonialtableSelection orderByTestimonial(boolean desc) {
        orderBy(TestimonialtableColumns.TESTIMONIAL, desc);
        return this;
    }

    public TestimonialtableSelection orderByTestimonial() {
        orderBy(TestimonialtableColumns.TESTIMONIAL, false);
        return this;
    }

    public TestimonialtableSelection date(Long... value) {
        addEquals(TestimonialtableColumns.DATE, value);
        return this;
    }

    public TestimonialtableSelection dateNot(Long... value) {
        addNotEquals(TestimonialtableColumns.DATE, value);
        return this;
    }

    public TestimonialtableSelection dateGt(long value) {
        addGreaterThan(TestimonialtableColumns.DATE, value);
        return this;
    }

    public TestimonialtableSelection dateGtEq(long value) {
        addGreaterThanOrEquals(TestimonialtableColumns.DATE, value);
        return this;
    }

    public TestimonialtableSelection dateLt(long value) {
        addLessThan(TestimonialtableColumns.DATE, value);
        return this;
    }

    public TestimonialtableSelection dateLtEq(long value) {
        addLessThanOrEquals(TestimonialtableColumns.DATE, value);
        return this;
    }

    public TestimonialtableSelection orderByDate(boolean desc) {
        orderBy(TestimonialtableColumns.DATE, desc);
        return this;
    }

    public TestimonialtableSelection orderByDate() {
        orderBy(TestimonialtableColumns.DATE, false);
        return this;
    }

    public TestimonialtableSelection imageUrl(String... value) {
        addEquals(TestimonialtableColumns.IMAGE_URL, value);
        return this;
    }

    public TestimonialtableSelection imageUrlNot(String... value) {
        addNotEquals(TestimonialtableColumns.IMAGE_URL, value);
        return this;
    }

    public TestimonialtableSelection imageUrlLike(String... value) {
        addLike(TestimonialtableColumns.IMAGE_URL, value);
        return this;
    }

    public TestimonialtableSelection imageUrlContains(String... value) {
        addContains(TestimonialtableColumns.IMAGE_URL, value);
        return this;
    }

    public TestimonialtableSelection imageUrlStartsWith(String... value) {
        addStartsWith(TestimonialtableColumns.IMAGE_URL, value);
        return this;
    }

    public TestimonialtableSelection imageUrlEndsWith(String... value) {
        addEndsWith(TestimonialtableColumns.IMAGE_URL, value);
        return this;
    }

    public TestimonialtableSelection orderByImageUrl(boolean desc) {
        orderBy(TestimonialtableColumns.IMAGE_URL, desc);
        return this;
    }

    public TestimonialtableSelection orderByImageUrl() {
        orderBy(TestimonialtableColumns.IMAGE_URL, false);
        return this;
    }

    public TestimonialtableSelection rating(Integer... value) {
        addEquals(TestimonialtableColumns.RATING, value);
        return this;
    }

    public TestimonialtableSelection ratingNot(Integer... value) {
        addNotEquals(TestimonialtableColumns.RATING, value);
        return this;
    }

    public TestimonialtableSelection ratingGt(int value) {
        addGreaterThan(TestimonialtableColumns.RATING, value);
        return this;
    }

    public TestimonialtableSelection ratingGtEq(int value) {
        addGreaterThanOrEquals(TestimonialtableColumns.RATING, value);
        return this;
    }

    public TestimonialtableSelection ratingLt(int value) {
        addLessThan(TestimonialtableColumns.RATING, value);
        return this;
    }

    public TestimonialtableSelection ratingLtEq(int value) {
        addLessThanOrEquals(TestimonialtableColumns.RATING, value);
        return this;
    }

    public TestimonialtableSelection orderByRating(boolean desc) {
        orderBy(TestimonialtableColumns.RATING, desc);
        return this;
    }

    public TestimonialtableSelection orderByRating() {
        orderBy(TestimonialtableColumns.RATING, false);
        return this;
    }
}
