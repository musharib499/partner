package com.gaadi.pratnerapps.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.provider.base.BaseContentProvider;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.notificationtable.NotificationtableColumns;
import com.gaadi.pratnerapps.provider.objecttable.ObjecttableColumns;
import com.gaadi.pratnerapps.provider.showroomtable.ShowroomtableColumns;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableColumns;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableColumns;

public class DealerAppContentProvider extends BaseContentProvider {
    private static final String TAG = DealerAppContentProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_CITYTABLE = 0;
    private static final int URI_TYPE_CITYTABLE_ID = 1;

    private static final int URI_TYPE_MAKETABLE = 2;
    private static final int URI_TYPE_MAKETABLE_ID = 3;

    private static final int URI_TYPE_MODELTABLE = 4;
    private static final int URI_TYPE_MODELTABLE_ID = 5;

    private static final int URI_TYPE_NOTIFICATIONTABLE = 6;
    private static final int URI_TYPE_NOTIFICATIONTABLE_ID = 7;

    private static final int URI_TYPE_OBJECTTABLE = 8;
    private static final int URI_TYPE_OBJECTTABLE_ID = 9;

    private static final int URI_TYPE_SHOWROOMTABLE = 10;
    private static final int URI_TYPE_SHOWROOMTABLE_ID = 11;

    private static final int URI_TYPE_TESTIMONIALTABLE = 12;
    private static final int URI_TYPE_TESTIMONIALTABLE_ID = 13;

    private static final int URI_TYPE_VERSIONTABLE = 14;
    private static final int URI_TYPE_VERSIONTABLE_ID = 15;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, CitytableColumns.TABLE_NAME, URI_TYPE_CITYTABLE);
        URI_MATCHER.addURI(AUTHORITY, CitytableColumns.TABLE_NAME + "/#", URI_TYPE_CITYTABLE_ID);
        URI_MATCHER.addURI(AUTHORITY, MaketableColumns.TABLE_NAME, URI_TYPE_MAKETABLE);
        URI_MATCHER.addURI(AUTHORITY, MaketableColumns.TABLE_NAME + "/#", URI_TYPE_MAKETABLE_ID);
        URI_MATCHER.addURI(AUTHORITY, ModeltableColumns.TABLE_NAME, URI_TYPE_MODELTABLE);
        URI_MATCHER.addURI(AUTHORITY, ModeltableColumns.TABLE_NAME + "/#", URI_TYPE_MODELTABLE_ID);
        URI_MATCHER.addURI(AUTHORITY, NotificationtableColumns.TABLE_NAME, URI_TYPE_NOTIFICATIONTABLE);
        URI_MATCHER.addURI(AUTHORITY, NotificationtableColumns.TABLE_NAME + "/#", URI_TYPE_NOTIFICATIONTABLE_ID);
        URI_MATCHER.addURI(AUTHORITY, ObjecttableColumns.TABLE_NAME, URI_TYPE_OBJECTTABLE);
        URI_MATCHER.addURI(AUTHORITY, ObjecttableColumns.TABLE_NAME + "/#", URI_TYPE_OBJECTTABLE_ID);
        URI_MATCHER.addURI(AUTHORITY, ShowroomtableColumns.TABLE_NAME, URI_TYPE_SHOWROOMTABLE);
        URI_MATCHER.addURI(AUTHORITY, ShowroomtableColumns.TABLE_NAME + "/#", URI_TYPE_SHOWROOMTABLE_ID);
        URI_MATCHER.addURI(AUTHORITY, TestimonialtableColumns.TABLE_NAME, URI_TYPE_TESTIMONIALTABLE);
        URI_MATCHER.addURI(AUTHORITY, TestimonialtableColumns.TABLE_NAME + "/#", URI_TYPE_TESTIMONIALTABLE_ID);
        URI_MATCHER.addURI(AUTHORITY, VersiontableColumns.TABLE_NAME, URI_TYPE_VERSIONTABLE);
        URI_MATCHER.addURI(AUTHORITY, VersiontableColumns.TABLE_NAME + "/#", URI_TYPE_VERSIONTABLE_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return DealerAppDb.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_CITYTABLE:
                return TYPE_CURSOR_DIR + CitytableColumns.TABLE_NAME;
            case URI_TYPE_CITYTABLE_ID:
                return TYPE_CURSOR_ITEM + CitytableColumns.TABLE_NAME;

            case URI_TYPE_MAKETABLE:
                return TYPE_CURSOR_DIR + MaketableColumns.TABLE_NAME;
            case URI_TYPE_MAKETABLE_ID:
                return TYPE_CURSOR_ITEM + MaketableColumns.TABLE_NAME;

            case URI_TYPE_MODELTABLE:
                return TYPE_CURSOR_DIR + ModeltableColumns.TABLE_NAME;
            case URI_TYPE_MODELTABLE_ID:
                return TYPE_CURSOR_ITEM + ModeltableColumns.TABLE_NAME;

            case URI_TYPE_NOTIFICATIONTABLE:
                return TYPE_CURSOR_DIR + NotificationtableColumns.TABLE_NAME;
            case URI_TYPE_NOTIFICATIONTABLE_ID:
                return TYPE_CURSOR_ITEM + NotificationtableColumns.TABLE_NAME;

            case URI_TYPE_OBJECTTABLE:
                return TYPE_CURSOR_DIR + ObjecttableColumns.TABLE_NAME;
            case URI_TYPE_OBJECTTABLE_ID:
                return TYPE_CURSOR_ITEM + ObjecttableColumns.TABLE_NAME;

            case URI_TYPE_SHOWROOMTABLE:
                return TYPE_CURSOR_DIR + ShowroomtableColumns.TABLE_NAME;
            case URI_TYPE_SHOWROOMTABLE_ID:
                return TYPE_CURSOR_ITEM + ShowroomtableColumns.TABLE_NAME;

            case URI_TYPE_TESTIMONIALTABLE:
                return TYPE_CURSOR_DIR + TestimonialtableColumns.TABLE_NAME;
            case URI_TYPE_TESTIMONIALTABLE_ID:
                return TYPE_CURSOR_ITEM + TestimonialtableColumns.TABLE_NAME;

            case URI_TYPE_VERSIONTABLE:
                return TYPE_CURSOR_DIR + VersiontableColumns.TABLE_NAME;
            case URI_TYPE_VERSIONTABLE_ID:
                return TYPE_CURSOR_ITEM + VersiontableColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_CITYTABLE:
            case URI_TYPE_CITYTABLE_ID:
                res.table = CitytableColumns.TABLE_NAME;
                res.idColumn = CitytableColumns._ID;
                res.tablesWithJoins = CitytableColumns.TABLE_NAME;
                res.orderBy = CitytableColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MAKETABLE:
            case URI_TYPE_MAKETABLE_ID:
                res.table = MaketableColumns.TABLE_NAME;
                res.idColumn = MaketableColumns._ID;
                res.tablesWithJoins = MaketableColumns.TABLE_NAME;
                res.orderBy = MaketableColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MODELTABLE:
            case URI_TYPE_MODELTABLE_ID:
                res.table = ModeltableColumns.TABLE_NAME;
                res.idColumn = ModeltableColumns._ID;
                res.tablesWithJoins = ModeltableColumns.TABLE_NAME;
                res.orderBy = ModeltableColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_NOTIFICATIONTABLE:
            case URI_TYPE_NOTIFICATIONTABLE_ID:
                res.table = NotificationtableColumns.TABLE_NAME;
                res.idColumn = NotificationtableColumns._ID;
                res.tablesWithJoins = NotificationtableColumns.TABLE_NAME;
                res.orderBy = NotificationtableColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_OBJECTTABLE:
            case URI_TYPE_OBJECTTABLE_ID:
                res.table = ObjecttableColumns.TABLE_NAME;
                res.idColumn = ObjecttableColumns._ID;
                res.tablesWithJoins = ObjecttableColumns.TABLE_NAME;
                res.orderBy = ObjecttableColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SHOWROOMTABLE:
            case URI_TYPE_SHOWROOMTABLE_ID:
                res.table = ShowroomtableColumns.TABLE_NAME;
                res.idColumn = ShowroomtableColumns._ID;
                res.tablesWithJoins = ShowroomtableColumns.TABLE_NAME;
                res.orderBy = ShowroomtableColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_TESTIMONIALTABLE:
            case URI_TYPE_TESTIMONIALTABLE_ID:
                res.table = TestimonialtableColumns.TABLE_NAME;
                res.idColumn = TestimonialtableColumns._ID;
                res.tablesWithJoins = TestimonialtableColumns.TABLE_NAME;
                res.orderBy = TestimonialtableColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_VERSIONTABLE:
            case URI_TYPE_VERSIONTABLE_ID:
                res.table = VersiontableColumns.TABLE_NAME;
                res.idColumn = VersiontableColumns._ID;
                res.tablesWithJoins = VersiontableColumns.TABLE_NAME;
                res.orderBy = VersiontableColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_CITYTABLE_ID:
            case URI_TYPE_MAKETABLE_ID:
            case URI_TYPE_MODELTABLE_ID:
            case URI_TYPE_NOTIFICATIONTABLE_ID:
            case URI_TYPE_OBJECTTABLE_ID:
            case URI_TYPE_SHOWROOMTABLE_ID:
            case URI_TYPE_TESTIMONIALTABLE_ID:
            case URI_TYPE_VERSIONTABLE_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
