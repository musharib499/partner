package com.gaadi.pratnerapps.provider.notificationtable;

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
public class NotificationtableColumns implements BaseColumns {
    public static final String TABLE_NAME = "notificationtable";
    public static final Uri CONTENT_URI = Uri.parse(DealerAppContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TITLE = "title";

    public static final String DESCRIPTION = "description";

    public static final String SECONDARY_TEXT = "secondary_text";

    public static final String DATE = "date";

    public static final String IMAGE_URL = "image_url";

    public static final String READ_STATUS = "read_status";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TITLE,
            DESCRIPTION,
            SECONDARY_TEXT,
            DATE,
            IMAGE_URL,
            READ_STATUS
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(DESCRIPTION) || c.contains("." + DESCRIPTION)) return true;
            if (c.equals(SECONDARY_TEXT) || c.contains("." + SECONDARY_TEXT)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(IMAGE_URL) || c.contains("." + IMAGE_URL)) return true;
            if (c.equals(READ_STATUS) || c.contains("." + READ_STATUS)) return true;
        }
        return false;
    }

}
