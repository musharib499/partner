package com.gaadi.pratnerapps.provider.versiontable;

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
 * to save version list
 */
public class VersiontableColumns implements BaseColumns {
    public static final String TABLE_NAME = "versiontable";
    public static final Uri CONTENT_URI = Uri.parse(DealerAppContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String VERSION_NAME = "version_name";

    public static final String VERSION_ID = "version_id";

    public static final String MODEL_ID = "model_id";

    public static final String FUEL_TYPE = "fuel_type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            VERSION_NAME,
            VERSION_ID,
            MODEL_ID,
            FUEL_TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(VERSION_NAME) || c.contains("." + VERSION_NAME)) return true;
            if (c.equals(VERSION_ID) || c.contains("." + VERSION_ID)) return true;
            if (c.equals(MODEL_ID) || c.contains("." + MODEL_ID)) return true;
            if (c.equals(FUEL_TYPE) || c.contains("." + FUEL_TYPE)) return true;
        }
        return false;
    }

}
