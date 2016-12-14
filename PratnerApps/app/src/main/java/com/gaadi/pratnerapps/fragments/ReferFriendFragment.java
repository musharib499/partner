package com.gaadi.pratnerapps.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashishkumar on 2/6/16.
 */
public class ReferFriendFragment extends PartnerBaseFragment implements View.OnClickListener {

    private MaterialEditText etName, etEmail, etContactNumber;
    private Button btn_continue;
    private ImageView ivWhatsApp,ivSms,ivEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.referfriend_layout, container, false);
        etName = (MaterialEditText) view.findViewById(R.id.et_name);
        etContactNumber = (MaterialEditText) view.findViewById(R.id.et_contactnum);
        etEmail = (MaterialEditText) view.findViewById(R.id.et_email);

        ivWhatsApp = (ImageView)view.findViewById(R.id.whatsapp);
        ivSms = (ImageView)view.findViewById(R.id.textsms);
        ivEmail = (ImageView)view.findViewById(R.id.mail);

        btn_continue = (Button) view.findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(this);
        ivSms.setOnClickListener(this);
        ivWhatsApp.setOnClickListener(this);
        ivEmail.setOnClickListener(this);

        getFragmentAdapter().setTitleMessage(getString(R.string.refer_friends),false);

        return view;
    }


    private boolean validate() {
        if (etName.getText().toString().trim().equals("")) {
            etName.setError("Enter name");
            return false;
        } /*else if (!etName.getText().toString().trim().matches("[a-zA-Z]+")) {
            etName.setError("Enter a valid name");
            etName.requestFocus();
            return false;
        }*/ else if (etName.getText().toString().trim().length() < 3) {
            etName.setError("Name should contain at least 3 characters");
            etName.requestFocus();
            return false;
        }

        if (etContactNumber.getText().toString().trim().equals("")) {
            etContactNumber.setError("Enter mobile number");
            return false;
        }
        else if (etContactNumber.getText().toString().trim().length() < 10)
        {
            etContactNumber.setError("Enter valid mobile number");
            return false;
        }
        else if (!Pattern.compile("^[7-9]").matcher(etContactNumber.getText().toString().trim()).find()) {
            etContactNumber.setError("Enter valid mobile number");
            return false;
        }

        if (etEmail.getText().toString().trim().equals("")) {
            etEmail.setError("Enter Email ID");
            return false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            etEmail.setError("Enter valid email ID");
            return false;
        }

      /*  if(tv_Location.getText().toString().trim().equals(""))
        {
            tv_Location.setError("Enter location");
            return false;
        }*/
        return true;
    }

    private void validateAndSubmit(){
        String name = etName.getText().toString();
        String phone = etContactNumber.getText().toString();
        String email = etEmail.getText().toString();


        HashMap<String,String> params = new HashMap<>();
        params.put(Constants.NAME,name);
        params.put(Constants.EMAIL,email);
        params.put(Constants.MOBILE,phone);
        params.put("message", "");
        params.put("location","");

        requestProgress("","Please wait...");

        RestApiCalls.referAFriend(getContext(),params).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();
                Logger.e(response.toString());
                if(response.body()!=null && response.isSuccessful()){

                    if (response.body().isSuccess()) {
                        /*showToast("Thanks for submitting the details.");
                        getFragmentAdapter().popFragment(false);
                        getFragmentAdapter().showHome();*/
                        showSubmitDialog(getResources().getString(R.string.refer_friend_title_msg),
                                getResources().getString(R.string.refer_friend_msg));
                    }else {
                        showToast("Please try again !");
                    }
                } else if(response.body()!=null){
                    showToast(response.body().getMessage());
                }else{
                    showToast("Something went wrong, Please try later.");
                }
            }
            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                hideProgress();
                showToast(t.getMessage());
            }
        });
    }

    private String getPlayStoreUrl(){
        return "https://play.google.com/store/apps/details?id="+ BuildConfig.APPLICATION_ID;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_continue:
                if (Utils.checkInternetConnection(getActivity())) {
                    if (validate())
                        validateAndSubmit();
                }else {
                    showToast(getString(R.string.no_connection));
                }

                break;
            case R.id.whatsapp:
                ContactFragment.sendMessageInWhatsApp(getContext(),getPlayStoreUrl(),null);
                break;
            case R.id.textsms:
                shareBySms(etContactNumber.getText().toString(),getPlayStoreUrl());
                break;
            case R.id.mail:
                shareByEmail(etEmail.getText().toString(),getPlayStoreUrl());
                break;
        }
    }

    private void shareByEmail(String email,String body){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+email));
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(emailIntent, "Share Now"));
    }

    private void shareBySms(String phone,String body){
        try {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", "" + phone);
            smsIntent.putExtra("sms_body", body);
            startActivity(smsIntent);
        }catch(Exception e){
            showToast("Messaging application not found!");
        }
    }
}
