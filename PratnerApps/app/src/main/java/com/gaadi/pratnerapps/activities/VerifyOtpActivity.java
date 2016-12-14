package com.gaadi.pratnerapps.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.UserModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.onesignal.OneSignal;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashishkumar on 11/7/16.
 */
public class VerifyOtpActivity extends PartnerBaseActivity {

    public static final String EXTRA_MOBILE = "extra_mobile";
    public static final String EXTRA_NAME = "extra_name";

    private MaterialEditText etOpt;
    private TextView tvTimer, tvMobile, tvResendOtp;
    private RelativeLayout layoutAutoVerify;

    private String mobile = "", name = "", otp = "";

    int remainingSeconds = 120;
    private Runnable runnable;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            remainingSeconds--;

            tvTimer.setText("" + getDuration(remainingSeconds));

            if (remainingSeconds > 0)
                handler.postDelayed(runnable, 1000);
            else {
                layoutAutoVerify.setVisibility(View.GONE);
                tvResendOtp.setVisibility(View.VISIBLE);
            }
        }
    };

    private String getDuration(int seconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;

        return (minutes > 9 ? "" + minutes : "0" + minutes) + ":" + (seconds > 9 ? "" + seconds : "0" + seconds);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    try {
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        SmsMessage message;
                        for (int i = 0; i < pdus.length; i++) {
                            message = SmsMessage.createFromPdu((byte[]) pdus[i]);
                            String msgBody = message.getMessageBody();

                            if (msgBody.contains("Gaadi Verification")) {

                                otp = msgBody.substring(msgBody.length() - 6);
                                etOpt.setText("" + otp);

                                layoutAutoVerify.setVisibility(View.GONE);
                                tvResendOtp.setVisibility(View.GONE);

                                unregisterCallbacks();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        etOpt = (MaterialEditText) findViewById(R.id.etOTP);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvMobile = (TextView) findViewById(R.id.tvMobile);
        tvResendOtp = (TextView) findViewById(R.id.tvResendOtp);
        Utils.setTint((FloatingActionButton) findViewById(R.id.btn_next), this, "title_color_icon_color");

        tvResendOtp.setVisibility(View.GONE);

        layoutAutoVerify = (RelativeLayout) findViewById(R.id.layoutAutoVerify);

        if (getIntent() != null) {
            if (getIntent().hasExtra(EXTRA_MOBILE))
                mobile = getIntent().getStringExtra(EXTRA_MOBILE);

            if (getIntent().hasExtra(EXTRA_NAME))
                name = getIntent().getStringExtra(EXTRA_NAME);
        }

        tvMobile.setText(mobile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitle("Sign In");

        startTimer();
    }

    private void startTimer(){
        remainingSeconds = 120;

        layoutAutoVerify.setVisibility(View.VISIBLE);

        tvTimer.setText("");

        if(runnable!=null){
            handler.removeCallbacks(runnable);
        }

        runnable = new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };

        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        registerReceiver(broadcastReceiver, intentFilter);
    }

    public void onSkip(View view) {
        AppPreferences.setBooleanSharedPreference(this, AppPreferences.DEALER_SKIPPED_VERIFICATION, true);
        startActivity(new Intent(VerifyOtpActivity.this, HomeActivity.class));
        this.finish();
    }

    public void onVerify(View view) {
        verifyOtp();
    }

    public void onResend(View view) {
        reSensOtp();
    }



    private boolean validate() {
        String etOtpd = etOpt.getText().toString().trim();

        if (etOtpd.isEmpty()) {
            etOpt.setError("Enter OTP");
            return false;
        } else if (etOtpd.length() != 6) {
            etOpt.setError("OTP should have 6 digits");
            return false;
        } /*else if (!etOtpd.equals(otp)) {
            etOpt.setError("OTP does not match Enter correct OTP");
            return false;
        }*/

        return true;
    }

    private void verifyOtp() {
        if (!validate()) return;

        String otp = etOpt.getText().toString().trim();

        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.MOBILE, mobile);
        params.put(Constants.OTP, otp);
        // params.put(Constants.PARAM_DEVICE_ID, Utils.getDeviceId(this));

        showProgress("Please wait..");

        RestApiCalls.verifyOtp(VerifyOtpActivity.this,params).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                hideProgress();

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        otpVerified(response.body());
                    } else {
                        showToast(response.body().getMessage());
                    }
                } else {
                    showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                hideProgress();
                showToast(t.getMessage());
            }
        });
    }

    private void otpVerified(UserModel userModel) {
        userModel.setName(name);
        userModel.setMobile(mobile);
        ObjectTableUtil.setUserModel(this, userModel);

        OneSignal.sendTag("mobile", mobile);

        if (!TextUtils.isEmpty(userModel.getEmail())) {
            OneSignal.sendTag("email", userModel.getEmail());
        }

        AppPreferences.setBooleanSharedPreference(VerifyOtpActivity.this, AppPreferences.DEALER_VERIFIED, true);
        Intent intent = new Intent(VerifyOtpActivity.this, SplashActivity.class);
        startActivity(intent);
        VerifyOtpActivity.this.finish();
    }

    private void reSensOtp() {
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.MOBILE, mobile);
        params.put(Constants.NAME, name);
        params.put(Constants.PARAM_DEVICE_ID, Utils.getDeviceId(this));
        showProgress("please wait..");


        RestApiCalls.requestOtp(VerifyOtpActivity.this,params).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {

                hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        startTimer();
                    } else {
                        showToast(response.body().getMessage());
                    }
                } else {
                    showToast(response.message());
                }

            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                hideProgress();
                showToast(t.getMessage());
            }
        });
    }


    @Override
    protected void onPause() {
        unregisterCallbacks();
        super.onPause();
    }

    private void unregisterCallbacks() {
        try {
            handler.removeCallbacks(runnable);
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
