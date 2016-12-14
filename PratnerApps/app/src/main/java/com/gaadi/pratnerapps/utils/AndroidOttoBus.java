package com.gaadi.pratnerapps.utils;

import android.os.Handler;
import android.os.Looper;

import com.crashlytics.android.Crashlytics;
import com.gaadi.pratnerapps.BuildConfig;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Priya on 08/06/15.
 */
public class AndroidOttoBus extends Bus {

    private final Handler mainThread = new Handler(Looper.getMainLooper());

    public AndroidOttoBus() {
        super(ThreadEnforcer.ANY);
    }

    @Override
    public void post(final Object event) {
        if (BuildConfig.DEBUG)
            Logger.e("BUS: SYNC current thread=" + Thread.currentThread().getName() + ", post=" + event + " bus=" + this);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }

    @Override
    public void register(Object object) {
        try {
            super.register(object);
            if (BuildConfig.DEBUG)
                Logger.e("BUS: SYNC current thread=" + Thread.currentThread().getName() + ", register=" + object + " bus=" + this);
        } catch (Exception e) {
            if (e != null) {
                Crashlytics.logException(e.getCause());
            }
        }
    }

    @Override
    public void unregister(Object object) {
        try {
            super.unregister(object);
            if (BuildConfig.DEBUG)
                Logger.e("BUS: SYNC current thread=" + Thread.currentThread().getName() + ", unregister=" + object + " bus=" + this);
        } catch (Exception e) {
            if (e != null) {
                Crashlytics.logException(e.getCause());
            }
        }
    }

}
