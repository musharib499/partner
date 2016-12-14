package com.gaadi.pratnerapps.provider.showroomtable;

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
 * to save showroom list
 */
public class ShowroomtableColumns implements BaseColumns {
    public static final String TABLE_NAME = "showroomtable";
    public static final Uri CONTENT_URI = Uri.parse(DealerAppContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String SHOWROOM_ID = "showroom_id";

    public static final String NAME = "name";

    public static final String LATITUDE = "latitude";

    public static final String LONGITUDE = "longitude";

    public static final String DEALER_TYPE = "dealer_type";

    public static final String ADDRESS = "address";

    public static final String MOBILE = "mobile";

    public static final String EMAIL1 = "email1";

    public static final String IMAGES = "images";

    public static final String CITY = "city";

    public static final String STATE = "state";

    public static final String PINCODE = "pincode";

    public static final String EMAIL2 = "email2";

    public static final String PHONE1 = "phone1";

    public static final String PHONE2 = "phone2";

    public static final String LOCALITY = "locality";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SHOWROOM_ID,
            NAME,
            LATITUDE,
            LONGITUDE,
            DEALER_TYPE,
            ADDRESS,
            MOBILE,
            EMAIL1,
            IMAGES,
            CITY,
            STATE,
            PINCODE,
            EMAIL2,
            PHONE1,
            PHONE2,
            LOCALITY
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SHOWROOM_ID) || c.contains("." + SHOWROOM_ID)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(LATITUDE) || c.contains("." + LATITUDE)) return true;
            if (c.equals(LONGITUDE) || c.contains("." + LONGITUDE)) return true;
            if (c.equals(DEALER_TYPE) || c.contains("." + DEALER_TYPE)) return true;
            if (c.equals(ADDRESS) || c.contains("." + ADDRESS)) return true;
            if (c.equals(MOBILE) || c.contains("." + MOBILE)) return true;
            if (c.equals(EMAIL1) || c.contains("." + EMAIL1)) return true;
            if (c.equals(IMAGES) || c.contains("." + IMAGES)) return true;
            if (c.equals(CITY) || c.contains("." + CITY)) return true;
            if (c.equals(STATE) || c.contains("." + STATE)) return true;
            if (c.equals(PINCODE) || c.contains("." + PINCODE)) return true;
            if (c.equals(EMAIL2) || c.contains("." + EMAIL2)) return true;
            if (c.equals(PHONE1) || c.contains("." + PHONE1)) return true;
            if (c.equals(PHONE2) || c.contains("." + PHONE2)) return true;
            if (c.equals(LOCALITY) || c.contains("." + LOCALITY)) return true;
        }
        return false;
    }

}
