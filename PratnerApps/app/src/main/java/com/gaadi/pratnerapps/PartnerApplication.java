package com.gaadi.pratnerapps;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.gaadi.pratnerapps.activities.HomeActivity;
import com.gaadi.pratnerapps.model.DealerTheme;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.AndroidOttoBus;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.squareup.otto.Bus;

import java.util.HashMap;

import io.fabric.sdk.android.BuildConfig;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vinodtakhar on 27/5/16.
 */
public class PartnerApplication extends Application {
    private static final String TAG = PartnerApplication.class.getName();
    private static Bus mEventBus;
    private DealerTheme dealerTheme;
    private static Tracker mTracker;

    public static Bus getEventBus() {
        return mEventBus;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        Fabric.with(this, new Crashlytics());
        OneSignal.startInit(this).setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
            @Override
            public void notificationOpened(OSNotificationOpenResult result) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class).putExtra(HomeActivity.EXTRA_NOTIFICATION,true);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).init();

        mEventBus = new AndroidOttoBus();

        if(!AppPreferences.getBooleanSharedPreference(this,AppPreferences.GCM_KEYS_SERVER_SYNC_STATUS,false)) {
            OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                @Override
                public void idsAvailable(String userId, String registrationId) {
                    Logger.e(TAG,"IDS Available");

                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put(Constants.PARAM_GCM_ID,registrationId);
                    params.put(Constants.PARAM_DEVICE_ID, Utils.getDeviceId(PartnerApplication.this));
                    params.put(Constants.PARAM_ONE_SIGNAL_ID,userId);

                    OneSignal.sendTag("package", com.gaadi.pratnerapps.BuildConfig.APPLICATION_ID);

                    Logger.e(TAG,"is  Debuggable:"+com.gaadi.pratnerapps.BuildConfig.DEBUG);

                    if(com.gaadi.pratnerapps.BuildConfig.DEBUG){
                        Logger.e(TAG,"setting debug tag");
                        OneSignal.sendTag("debug","1");
                    }

                    Logger.e(TAG,"Params:"+params.toString());
                    RestApiCalls.saveGcm(PartnerApplication.this,params).enqueue(new Callback<BaseResponseModel>() {
                        @Override
                        public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                            if(response.isSuccessful() && response.body().isSuccess()){
                                AppPreferences.setBooleanSharedPreference(PartnerApplication.this,AppPreferences.GCM_KEYS_SERVER_SYNC_STATUS,true);
                            }
                        }
                        @Override
                        public void onFailure(Call<BaseResponseModel> call, Throwable t) {}
                    });
                }
            });
        }
    }

/*TODO Google Analytics intregration*/

    private static final String PROPERTY_ID = "UA-81311476-1";

    /**
     * Enum used to identify the tracker that needs to be used for tracking.
     *
     * A single tracker is usually enough for most purposes. In case you do need multiple trackers,
     * storing them all in Application object helps ensure that they are created only once per
     * application instance.
     */
    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public PartnerApplication() {
        super();
    }
    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
                    : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker)
                    : analytics.newTracker(R.xml.ecommerce_tracker);
            t.enableAutoActivityTracking(true);
            t.enableAdvertisingIdCollection(true);
            t.enableExceptionReporting(true);
            t.setAppVersion(BuildConfig.VERSION_NAME);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public String getThemeProperty(String property){
        if(dealerTheme==null){
            dealerTheme = new DealerTheme();

            HashMap<String,String> userColors = ObjectTableUtil.getUserColors(this).getItem();

            if(userColors!=null)
                dealerTheme.setThemeValues(userColors);
            else
                dealerTheme.setThemeValues(new HashMap<String, String>());
        }

        String value = dealerTheme.getProperty(property);

        Logger.e(TAG,property+"="+value);

        return value;
    }

    public void resetTheme() {
        dealerTheme = null;
    }
}
