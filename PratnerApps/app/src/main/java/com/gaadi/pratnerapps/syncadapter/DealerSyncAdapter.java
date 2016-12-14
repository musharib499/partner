package com.gaadi.pratnerapps.syncadapter;

import android.accounts.Account;
import android.app.AlarmManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.gaadi.pratnerapps.utils.Logger;


/**
 * Created by vinodtakhar on 2016/05/30.
 */
public class DealerSyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String TAG = "DealerSyncAdapter";
    private Context context;

    public DealerSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize,false);
        this.context = context;
    }

    public DealerSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        this.context = context;
    }

    @Override
    public void onPerformSync(Account account,
                              Bundle extras,
                              String authority,
                              ContentProviderClient provider,
                              SyncResult syncResult) {
        try {
            DealerSyncManager.startSynchronized(context,extras);
        } catch (Exception e) {
            if (e != null) {
                Logger.e(TAG, "exception: " + e.getMessage());
                Crashlytics.logException(e.getCause());
            }
        }

//        syncResult.delayUntil = AlarmManager.INTERVAL_DAY;
//        syncResult.tooManyRetries = false;
    }
}
