package com.gaadi.pratnerapps.provider.testimonialtable;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gaadi.pratnerapps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code testimonialtable} table.
 */
public class TestimonialtableCursor extends AbstractCursor implements TestimonialtableModel {
    public TestimonialtableCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TestimonialtableColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code testimonial_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getTestimonialId() {
        Integer res = getIntegerOrNull(TestimonialtableColumns.TESTIMONIAL_ID);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(TestimonialtableColumns.NAME);
        return res;
    }

    /**
     * Get the {@code role} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getRole() {
        String res = getStringOrNull(TestimonialtableColumns.ROLE);
        return res;
    }

    /**
     * Get the {@code testimonial} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTestimonial() {
        String res = getStringOrNull(TestimonialtableColumns.TESTIMONIAL);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getDate() {
        Long res = getLongOrNull(TestimonialtableColumns.DATE);
        return res;
    }

    /**
     * Get the {@code image_url} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImageUrl() {
        String res = getStringOrNull(TestimonialtableColumns.IMAGE_URL);
        return res;
    }

    /**
     * Get the {@code rating} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getRating() {
        Integer res = getIntegerOrNull(TestimonialtableColumns.RATING);
        return res;
    }
}
