package com.gaadi.pratnerapps.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashishkumar on 11/7/16.
 */
public class RequestOtpActivity extends PartnerBaseActivity implements View.OnClickListener  {

    private MaterialEditText etName, etMobile;
    private TextView skipText;
    private FloatingActionButton btnNext;
    public static final String TAG ="RequestOtpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_otp);

        etName = (MaterialEditText) findViewById(R.id.et_name);
        etMobile = (MaterialEditText) findViewById(R.id.et_mobile);

        btnNext = (FloatingActionButton) findViewById(R.id.btn_next);
        Utils.setTint(btnNext,this,"title_color_icon_color");
        skipText = (TextView)findViewById(R.id.skip_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitle("Sign In");

        btnNext.setOnClickListener(this);
        skipText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.skip_text :
                AppPreferences.setBooleanSharedPreference(this,AppPreferences.DEALER_SKIPPED_VERIFICATION,true);
                startActivity(new Intent(RequestOtpActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                this.finish();
                break;
            case R.id.btn_next:
                if(Utils.checkInternetConnection(RequestOtpActivity.this)){
                    requestOtp();
                }
                else {
                    showToast(getString(R.string.no_connection));
                }
                break;

        }
    }
    private boolean validate() {
        String name = etName.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Enter Name");
            return false;
        } /*else if (!name.matches("[a-zA-Z]+")) {
            etName.setError("Enter a valid Name");
            etName.requestFocus();
            return false;
        } */else if (name.length() < 3) {
            etName.setError("Name should contain at least 3 characters");
            etName.requestFocus();
            return false;
        }else if (mobile.isEmpty()) {
            etMobile.setError("Enter mobile number");
            return false;
        }
        else if (mobile.length() < 10){
            etMobile.setError("Mobile should have at least 10 digits");
            return false;
        }
        else if (!Patterns.PHONE.matcher(mobile).matches()) {
            etMobile.setError("Enter valid mobile number");
            return false;
        }

        return true;
    }

    @Override
    public void onPermissionGranted(int requstCode, String grantedPermission) {
        startActivity(new Intent(RequestOtpActivity.this,VerifyOtpActivity.class)
                .putExtra(VerifyOtpActivity.EXTRA_MOBILE,etMobile.getText().toString())
                .putExtra(VerifyOtpActivity.EXTRA_NAME,etName.getText().toString()));

        RequestOtpActivity.this.finish();
    }

    @Override
    public void onPermissionDenied(int requestCode, String deniedPermission) {
        requestPermission(requestCode, deniedPermission);
    }

    private void requestOtp(){
        if(!validate())return;

        String name = etName.getText().toString();
        final String mobile = etMobile.getText().toString();

        HashMap<String,String> params =new HashMap<>();
        params.put(Constants.NAME,name);
        params.put(Constants.MOBILE,mobile);
        params.put(Constants.PARAM_DEVICE_ID, Utils.getDeviceId(this));

        showProgress("Please wait..");
        RestApiCalls.requestOtp(RequestOtpActivity.this,params).enqueue(new Callback<BaseResponseModel>() {


            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();

                if(response.isSuccessful() && response.body()!=null){
                    Logger.d(TAG,response.toString());
                    if(response.body().isSuccess()){
                        requestPermission(140, Manifest.permission.RECEIVE_SMS);
                    }else{
                        showToast(response.body().getMessage());
                    }
                }else{
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
}
