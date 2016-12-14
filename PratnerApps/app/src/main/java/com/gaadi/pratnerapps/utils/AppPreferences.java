package com.gaadi.pratnerapps.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vinodtakhar on 25/5/16.
 */
public class AppPreferences {

    private static final String APP_SHARED_PREFERENCE = "PartnerAppPreferences";
    public static final String KEY_LAST_STARTUP_SYNC_TIME = "key_last_startup_sync_time";
    public static final String DEALER_VERIFIED = "dealer_verified";
    public static final String DEALER_SKIPPED_VERIFICATION = "dealer_skipped_verification";
    public static final String GCM_KEYS_SERVER_SYNC_STATUS = "key_gcm_server_sync_status";

    private static Context ctx;

    public static void setSharedPreference(Context ctx, String Key, String Value) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Key, Value);
        editor.apply();
    }


    public static void setBooleanSharedPreference(Context ctx, String key, boolean value) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setIntSharedPreference(Context ctx, String key, int value) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    public static String getSharedPreference(Context ctx, String Key,String defaultValue) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getString(Key,defaultValue);
    }

    public static void DeleteSharedPreference(Context ctx, String Key) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(Key)) {
            editor.remove(Key);
            editor.commit();
        }
    }

    public static void DeleteSharedPreference(Context ctx) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        pref.edit().clear().commit();
    }


    public static boolean getBooleanSharedPreference(Context ctx, String Key, Boolean defaultValue) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        Logger.i("key", ":" + Key);
        if (pref.contains(Key)) {
            Logger.i("Print id  s", ":" + pref.getBoolean(Key, defaultValue));
            return pref.getBoolean(Key, defaultValue);
        } else
            return defaultValue;
    }

    public static int getIntSharedPreference(Context ctx, String Key, int defaultValue) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        Logger.i("key", ":" + Key);
        if (pref.contains(Key)) {
            Logger.i("Print id  s", ":" + pref.getInt(Key, defaultValue));
            return pref.getInt(Key, defaultValue);
        } else
            return defaultValue;
    }


    public static void setLongSharedPreference(Context ctx, String key, long value) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLongSharedPreference(Context ctx, String Key) {
        SharedPreferences pref = ctx.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getLong(Key, 0l);
    }
}
