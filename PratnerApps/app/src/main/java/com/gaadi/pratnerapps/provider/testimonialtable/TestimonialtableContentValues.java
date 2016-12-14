package com.gaadi.pratnerapps.provider.testimonialtable;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code testimonialtable} table.
 */
public class TestimonialtableContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TestimonialtableColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TestimonialtableSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable TestimonialtableSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TestimonialtableContentValues putTestimonialId(@Nullable Integer value) {
        mContentValues.put(TestimonialtableColumns.TESTIMONIAL_ID, value);
        return this;
    }

    public TestimonialtableContentValues putTestimonialIdNull() {
        mContentValues.putNull(TestimonialtableColumns.TESTIMONIAL_ID);
        return this;
    }

    public TestimonialtableContentValues putName(@Nullable String value) {
        mContentValues.put(TestimonialtableColumns.NAME, value);
        return this;
    }

    public TestimonialtableContentValues putNameNull() {
        mContentValues.putNull(TestimonialtableColumns.NAME);
        return this;
    }

    public TestimonialtableContentValues putRole(@Nullable String value) {
        mContentValues.put(TestimonialtableColumns.ROLE, value);
        return this;
    }

    public TestimonialtableContentValues putRoleNull() {
        mContentValues.putNull(TestimonialtableColumns.ROLE);
        return this;
    }

    public TestimonialtableContentValues putTestimonial(@Nullable String value) {
        mContentValues.put(TestimonialtableColumns.TESTIMONIAL, value);
        return this;
    }

    public TestimonialtableContentValues putTestimonialNull() {
        mContentValues.putNull(TestimonialtableColumns.TESTIMONIAL);
        return this;
    }

    public TestimonialtableContentValues putDate(@Nullable Long value) {
        mContentValues.put(TestimonialtableColumns.DATE, value);
        return this;
    }

    public TestimonialtableContentValues putDateNull() {
        mContentValues.putNull(TestimonialtableColumns.DATE);
        return this;
    }

    public TestimonialtableContentValues putImageUrl(@Nullable String value) {
        mContentValues.put(TestimonialtableColumns.IMAGE_URL, value);
        return this;
    }

    public TestimonialtableContentValues putImageUrlNull() {
        mContentValues.putNull(TestimonialtableColumns.IMAGE_URL);
        return this;
    }

    public TestimonialtableContentValues putRating(@Nullable Integer value) {
        mContentValues.put(TestimonialtableColumns.RATING, value);
        return this;
    }

    public TestimonialtableContentValues putRatingNull() {
        mContentValues.putNull(TestimonialtableColumns.RATING);
        return this;
    }
}
