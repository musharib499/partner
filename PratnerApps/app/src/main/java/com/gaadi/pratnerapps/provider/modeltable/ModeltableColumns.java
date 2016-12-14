package com.gaadi.pratnerapps.provider.modeltable;

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
 * to save model list
 */
public class ModeltableColumns implements BaseColumns {
    public static final String TABLE_NAME = "modeltable";
    public static final Uri CONTENT_URI = Uri.parse(DealerAppContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MODEL_NAME = "model_name";

    public static final String MODEL_ID = "model_id";

    public static final String MAKE_ID = "make_id";

    public static final String RANK = "rank";

    public static final String IN_STOCK = "in_stock";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MODEL_NAME,
            MODEL_ID,
            MAKE_ID,
            RANK,
            IN_STOCK
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MODEL_NAME) || c.contains("." + MODEL_NAME)) return true;
            if (c.equals(MODEL_ID) || c.contains("." + MODEL_ID)) return true;
            if (c.equals(MAKE_ID) || c.contains("." + MAKE_ID)) return true;
            if (c.equals(RANK) || c.contains("." + RANK)) return true;
            if (c.equals(IN_STOCK) || c.contains("." + IN_STOCK)) return true;
        }
        return false;
    }

}
