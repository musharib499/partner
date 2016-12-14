package com.gaadi.pratnerapps.provider.testimonialtable;

import android.net.Uri;
import android.provider.BaseColumns;

import com.gaadi.pratnerapps.provider.DealerAppContentProvider;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.notificationtable.NotificationtableColumns;
import com.gaadi.pratnerapps.provider.objecttable.ObjecttableColumns;
import com.gaadi.pratnerapps.provider.showroomtable.ShowroomtableColumns;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableColumns;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableColumns;

/**
 * to save notifications list
 */
public class TestimonialtableColumns implements BaseColumns {
    public static final String TABLE_NAME = "testimonialtable";
    public static final Uri CONTENT_URI = Uri.parse(DealerAppContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TESTIMONIAL_ID = "testimonial_id";

    public static final String NAME = "name";

    public static final String ROLE = "role";

    public static final String TESTIMONIAL = "testimonial";

    public static final String DATE = "date";

    public static final String IMAGE_URL = "image_url";

    public static final String RATING = "rating";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TESTIMONIAL_ID,
            NAME,
            ROLE,
            TESTIMONIAL,
            DATE,
            IMAGE_URL,
            RATING
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TESTIMONIAL_ID) || c.contains("." + TESTIMONIAL_ID)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(ROLE) || c.contains("." + ROLE)) return true;
            if (c.equals(TESTIMONIAL) || c.contains("." + TESTIMONIAL)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(IMAGE_URL) || c.contains("." + IMAGE_URL)) return true;
            if (c.equals(RATING) || c.contains("." + RATING)) return true;
        }
        return false;
    }

}
