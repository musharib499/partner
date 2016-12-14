package com.gaadi.pratnerapps.provider.citytable;

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
public class CitytableColumns implements BaseColumns {
    public static final String TABLE_NAME = "citytable";
    public static final Uri CONTENT_URI = Uri.parse(DealerAppContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String CITY_ID = "city_id";

    public static final String CITY_NAME = "city_name";

    public static final String STATE_ID = "state_id";

    public static final String STATE_NAME = "state_name";

    public static final String PINCODE = "pincode";

    public static final String REGION = "region";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CITY_ID,
            CITY_NAME,
            STATE_ID,
            STATE_NAME,
            PINCODE,
            REGION
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CITY_ID) || c.contains("." + CITY_ID)) return true;
            if (c.equals(CITY_NAME) || c.contains("." + CITY_NAME)) return true;
            if (c.equals(STATE_ID) || c.contains("." + STATE_ID)) return true;
            if (c.equals(STATE_NAME) || c.contains("." + STATE_NAME)) return true;
            if (c.equals(PINCODE) || c.contains("." + PINCODE)) return true;
            if (c.equals(REGION) || c.contains("." + REGION)) return true;
        }
        return false;
    }

}
