package com.gaadi.pratnerapps.syncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.gaadi.pratnerapps.utils.Logger;


/**
 * Created by vinodtakhar on 2016/05/30.
 */
public class DealerSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static final String TAG = "DealerSyncService";
    private static DealerSyncAdapter sSyncAdapter = null;

    /**
     * Thread-safe constructor, creates static {@link Service} instance.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e(TAG + "Service created");
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new DealerSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    /**
     * Logging-only destructor.
     */
    public void onDestroy() {
        super.onDestroy();
        Logger.e(TAG + "Service destroyed");
    }

    /**
     * Return Binder handle for IPC communication with {@link Intent}.
     * <p/>
     * <p>New sync requests will be sent directly to the SyncAdapter using this channel.
     *
     * @param intent Calling intent
     * @return Binder handle for {@link IBinder}
     */
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
