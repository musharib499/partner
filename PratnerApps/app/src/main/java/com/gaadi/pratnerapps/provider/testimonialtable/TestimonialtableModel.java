package com.gaadi.pratnerapps.provider.testimonialtable;

import com.gaadi.pratnerapps.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * to save notifications list
 */
public interface TestimonialtableModel extends BaseModel {

    /**
     * Get the {@code testimonial_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getTestimonialId();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code role} value.
     * Can be {@code null}.
     */
    @Nullable
    String getRole();

    /**
     * Get the {@code testimonial} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTestimonial();

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getDate();

    /**
     * Get the {@code image_url} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImageUrl();

    /**
     * Get the {@code rating} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getRating();
}
