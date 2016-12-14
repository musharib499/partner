package com.gaadi.pratnerapps.provider.objecttable;

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
 * to save city list
 */
public class ObjecttableColumns implements BaseColumns {
    public static final String TABLE_NAME = "objecttable";
    public static final Uri CONTENT_URI = Uri.parse(DealerAppContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String KEY_ID = "key_id";

    public static final String KEY_VALUE = "key_value";

    public static final String KEY_VERSION = "key_version";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            KEY_ID,
            KEY_VALUE,
            KEY_VERSION
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(KEY_ID) || c.contains("." + KEY_ID)) return true;
            if (c.equals(KEY_VALUE) || c.contains("." + KEY_VALUE)) return true;
            if (c.equals(KEY_VERSION) || c.contains("." + KEY_VERSION)) return true;
        }
        return false;
    }

}
