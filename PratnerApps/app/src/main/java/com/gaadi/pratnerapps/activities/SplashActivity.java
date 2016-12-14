package com.gaadi.pratnerapps.activities;

import android.app.AlarmManager;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.SplashApiModel;
import com.gaadi.pratnerapps.syncadapter.DealerSyncManager;
import com.gaadi.pratnerapps.utils.ActivityTransitionAnimation;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.squareup.otto.Subscribe;

public class SplashActivity extends PartnerBaseActivity {

    private LinearLayout rootLayout;
    private ImageView ivLogo,ivSplash;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        PartnerApplication.getEventBus().register(this);
        rootLayout = (LinearLayout)findViewById(R.id.rlParentSplash);
        textView = (TextView)findViewById(R.id.textView);
        ivLogo = (ImageView)findViewById(R.id.iVLogo);
        ivSplash = (ImageView)findViewById(R.id.ivSplash);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setDealerSplash();

        if (true||(AppPreferences.getLongSharedPreference(this,AppPreferences.KEY_LAST_STARTUP_SYNC_TIME) + (AlarmManager.INTERVAL_FIFTEEN_MINUTES/3))<=System.currentTimeMillis()) {
            syncResources();
        }else{
            textView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadHome();
                }
            }, 3000);
        }
    }

    private void setDealerSplash() {
        SplashApiModel.SplashConfig splashConfig = ObjectTableUtil.getSplashConfig(this);
       if (splashConfig!=null) {
           textView.setText(splashConfig.getMessage());
           if (!TextUtils.isEmpty(splashConfig.getLogo_url()))
               Utils.loadImage(this, splashConfig.getLogo_url(), ivLogo);

           if (!TextUtils.isEmpty(splashConfig.getImage_url()))
               Utils.loadImage(this, splashConfig.getImage_url(), ivSplash, R.drawable.splash_transparent);
       }

    }

    private void syncResources(){
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                try {
                    DealerSyncManager.startSynchronized(SplashActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    return e.toString();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                showProgress("Please wait...");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(isRunning()) {
//                    hideProgress();

                    if (s != null) {
                        Utils.showNoConnectionDialog(SplashActivity.this, "Sync Failed", s, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (AppPreferences.getLongSharedPreference(SplashActivity.this, AppPreferences.KEY_LAST_STARTUP_SYNC_TIME) == 0l)
                                    SplashActivity.this.finish();
                                else {
                                    loadHome();
                                }
                            }
                        });
                    } else {
                        AppPreferences.setLongSharedPreference(SplashActivity.this, AppPreferences.KEY_LAST_STARTUP_SYNC_TIME, System.currentTimeMillis());

                        moveToNextActivity();
                    }
                }
            }
        }.execute();
    }

    private void moveToNextActivity() {
        if (AppPreferences.getBooleanSharedPreference(this,AppPreferences.DEALER_VERIFIED,false) ||
                AppPreferences.getBooleanSharedPreference(this,AppPreferences.DEALER_SKIPPED_VERIFICATION,false)){
            loadHome();
        }else{
            loadOtpVerification();
        }
    }

    private void loadHome() {
        ActivityTransitionAnimation.translateAnimationBottomUp(this, /*ivLogo, getString(R.string.app_name), */HomeActivity.class/*, null*/);
        this.finish();
    }
    private void loadOtpVerification(){
        ActivityTransitionAnimation.translateAnimationBottomUp(this,/* ivLogo, getString(R.string.app_name), */RequestOtpActivity.class/*, null*/);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PartnerApplication.getEventBus().unregister(this);
    }

    @Subscribe
    public void syncNewSplash(String syncSplashEvent)
    {
        if(syncSplashEvent.equals("syncSplash")){
            setDealerSplash();
        }
    }
}
