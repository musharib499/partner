package com.gaadi.pratnerapps.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.notificationtable.NotificationtableColumns;
import com.gaadi.pratnerapps.provider.objecttable.ObjecttableColumns;
import com.gaadi.pratnerapps.provider.showroomtable.ShowroomtableColumns;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableColumns;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableColumns;

public class DealerAppDb extends SQLiteOpenHelper {
    private static final String TAG = DealerAppDb.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "dealerapp.db";
    private static final int DATABASE_VERSION = 1;
    private static DealerAppDb sInstance;
    private final Context mContext;
    private final DealerAppDbCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_CITYTABLE = "CREATE TABLE IF NOT EXISTS "
            + CitytableColumns.TABLE_NAME + " ( "
            + CitytableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CitytableColumns.CITY_ID + " INTEGER, "
            + CitytableColumns.CITY_NAME + " TEXT, "
            + CitytableColumns.STATE_ID + " INTEGER, "
            + CitytableColumns.STATE_NAME + " TEXT, "
            + CitytableColumns.PINCODE + " TEXT, "
            + CitytableColumns.REGION + " TEXT "
            + ", CONSTRAINT unique_city UNIQUE (city_id,state_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_MAKETABLE = "CREATE TABLE IF NOT EXISTS "
            + MaketableColumns.TABLE_NAME + " ( "
            + MaketableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MaketableColumns.MAKE_NAME + " TEXT, "
            + MaketableColumns.MAKE_ID + " INTEGER, "
            + MaketableColumns.RANK + " INTEGER, "
            + MaketableColumns.IN_STOCK + " INTEGER "
            + ", CONSTRAINT unique_make UNIQUE (make_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_MODELTABLE = "CREATE TABLE IF NOT EXISTS "
            + ModeltableColumns.TABLE_NAME + " ( "
            + ModeltableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ModeltableColumns.MODEL_NAME + " TEXT, "
            + ModeltableColumns.MODEL_ID + " INTEGER, "
            + ModeltableColumns.MAKE_ID + " INTEGER, "
            + ModeltableColumns.RANK + " INTEGER, "
            + ModeltableColumns.IN_STOCK + " INTEGER "
            + ", CONSTRAINT unique_model UNIQUE (model_id,make_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_NOTIFICATIONTABLE = "CREATE TABLE IF NOT EXISTS "
            + NotificationtableColumns.TABLE_NAME + " ( "
            + NotificationtableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NotificationtableColumns.TITLE + " TEXT, "
            + NotificationtableColumns.DESCRIPTION + " TEXT, "
            + NotificationtableColumns.SECONDARY_TEXT + " TEXT, "
            + NotificationtableColumns.DATE + " INTEGER, "
            + NotificationtableColumns.IMAGE_URL + " TEXT, "
            + NotificationtableColumns.READ_STATUS + " INTEGER "
            + " );";

    public static final String SQL_CREATE_TABLE_OBJECTTABLE = "CREATE TABLE IF NOT EXISTS "
            + ObjecttableColumns.TABLE_NAME + " ( "
            + ObjecttableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ObjecttableColumns.KEY_ID + " TEXT, "
            + ObjecttableColumns.KEY_VALUE + " TEXT, "
            + ObjecttableColumns.KEY_VERSION + " INTEGER "
            + ", CONSTRAINT unique_object UNIQUE (key_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_SHOWROOMTABLE = "CREATE TABLE IF NOT EXISTS "
            + ShowroomtableColumns.TABLE_NAME + " ( "
            + ShowroomtableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShowroomtableColumns.SHOWROOM_ID + " INTEGER, "
            + ShowroomtableColumns.NAME + " TEXT, "
            + ShowroomtableColumns.LATITUDE + " TEXT, "
            + ShowroomtableColumns.LONGITUDE + " TEXT, "
            + ShowroomtableColumns.DEALER_TYPE + " TEXT, "
            + ShowroomtableColumns.ADDRESS + " TEXT, "
            + ShowroomtableColumns.MOBILE + " TEXT, "
            + ShowroomtableColumns.EMAIL1 + " TEXT, "
            + ShowroomtableColumns.IMAGES + " TEXT, "
            + ShowroomtableColumns.CITY + " TEXT, "
            + ShowroomtableColumns.STATE + " TEXT, "
            + ShowroomtableColumns.PINCODE + " TEXT, "
            + ShowroomtableColumns.EMAIL2 + " TEXT, "
            + ShowroomtableColumns.PHONE1 + " TEXT, "
            + ShowroomtableColumns.PHONE2 + " TEXT, "
            + ShowroomtableColumns.LOCALITY + " TEXT "
            + ", CONSTRAINT unique_showroom UNIQUE (showroom_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_TESTIMONIALTABLE = "CREATE TABLE IF NOT EXISTS "
            + TestimonialtableColumns.TABLE_NAME + " ( "
            + TestimonialtableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TestimonialtableColumns.TESTIMONIAL_ID + " INTEGER, "
            + TestimonialtableColumns.NAME + " TEXT, "
            + TestimonialtableColumns.ROLE + " TEXT, "
            + TestimonialtableColumns.TESTIMONIAL + " TEXT, "
            + TestimonialtableColumns.DATE + " INTEGER, "
            + TestimonialtableColumns.IMAGE_URL + " TEXT, "
            + TestimonialtableColumns.RATING + " INTEGER "
            + ", CONSTRAINT unique_testimonial UNIQUE (testimonial_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_VERSIONTABLE = "CREATE TABLE IF NOT EXISTS "
            + VersiontableColumns.TABLE_NAME + " ( "
            + VersiontableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + VersiontableColumns.VERSION_NAME + " TEXT, "
            + VersiontableColumns.VERSION_ID + " INTEGER, "
            + VersiontableColumns.MODEL_ID + " INTEGER, "
            + VersiontableColumns.FUEL_TYPE + " TEXT "
            + ", CONSTRAINT unique_version UNIQUE (version_id,model_id) ON CONFLICT REPLACE"
            + " );";

    // @formatter:on

    public static DealerAppDb getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static DealerAppDb newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static DealerAppDb newInstancePreHoneycomb(Context context) {
        return new DealerAppDb(context);
    }

    private DealerAppDb(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new DealerAppDbCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static DealerAppDb newInstancePostHoneycomb(Context context) {
        return new DealerAppDb(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private DealerAppDb(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new DealerAppDbCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CITYTABLE);
        db.execSQL(SQL_CREATE_TABLE_MAKETABLE);
        db.execSQL(SQL_CREATE_TABLE_MODELTABLE);
        db.execSQL(SQL_CREATE_TABLE_NOTIFICATIONTABLE);
        db.execSQL(SQL_CREATE_TABLE_OBJECTTABLE);
        db.execSQL(SQL_CREATE_TABLE_SHOWROOMTABLE);
        db.execSQL(SQL_CREATE_TABLE_TESTIMONIALTABLE);
        db.execSQL(SQL_CREATE_TABLE_VERSIONTABLE);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
