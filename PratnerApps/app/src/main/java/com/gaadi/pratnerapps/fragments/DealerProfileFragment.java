package com.gaadi.pratnerapps.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FilterQueryProvider;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.activities.SplashActivity;
import com.gaadi.pratnerapps.adapter.CityCursorAdapter;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.UserModel;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.onesignal.OneSignal;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashishkumar on 13/7/16.
 */
public class DealerProfileFragment extends PartnerBaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{

    private MaterialEditText etName,etMobile,etEmail;
    private MaterialAutoCompleteTextView tvLocation;

    private Button btnSave;
    private CityCursorAdapter cityCursorAdapter;
    private static String TAG="DealerProfileFragment";
    private UserModel userModel;

    FilterQueryProvider citiesProvider = new FilterQueryProvider() {
        @Override
        public Cursor runQuery(CharSequence constraint) {
            return getActivity().getContentResolver().query(CitytableColumns.CONTENT_URI,null,CitytableColumns.CITY_NAME + " like ?", new String[]{constraint.toString()+"%"},null);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        setHasOptionsMenu(true);
        etName = (MaterialEditText)view.findViewById(R.id.et_name);
        etMobile = (MaterialEditText)view.findViewById(R.id.et_ContactNum);
        etEmail = (MaterialEditText)view.findViewById(R.id.et_email);
        tvLocation = (MaterialAutoCompleteTextView)view.findViewById(R.id.et_location);
        tvLocation.setOnItemClickListener(this);
        cityCursorAdapter = new CityCursorAdapter(getActivity(), null);
        tvLocation.setAdapter(cityCursorAdapter);
        cityCursorAdapter.setFilterQueryProvider(citiesProvider);
        tvLocation.setThreshold(1);
        getFragmentAdapter().setTitleMessage("My Profile", false);
        btnSave = (Button)view.findViewById(R.id.btn_continue);
        btnSave.setOnClickListener(this);

        loadDate();
        return view;
    }

    public void loadDate()
    {

        userModel =  ObjectTableUtil.getUserModel(getActivity());

         Logger.e(TAG,userModel.getMobile()+"/"+userModel.getName());

        if (userModel!=null && AppPreferences.getBooleanSharedPreference(getContext(),AppPreferences.DEALER_VERIFIED,false)) {

                if (!TextUtils.isEmpty(userModel.getEmail())) {
                    etEmail.setText(userModel.getEmail());
                }

                if (!TextUtils.isEmpty(userModel.getName())) {
                    etName.setText(userModel.getName());
                }

                if (!TextUtils.isEmpty(userModel.getMobile())) {
                    etMobile.setText(userModel.getMobile());
                }

                if (!TextUtils.isEmpty(userModel.getCity())) {
                    tvLocation.setText(userModel.getCity());
                }
            }


    }
    @Override
    public void onClick(View v) {
       if (validate())   {
           saveDealerDetails();
       }
    }

    private boolean validate() {
        String name = etName.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String city = tvLocation.getText().toString().trim();

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
        } else if (mobile.isEmpty()) {
            etMobile.setError("Enter Mobile number");
            return false;
        } else if (mobile.length() < 10){
            etMobile.setError("Enter should have 10 digits");
            return false;
        } else if (!Pattern.compile("^[7-9]").matcher(mobile).find()) {
            etMobile.setError("Enter valid Mobile number");
            return false;
        } else if (email.isEmpty()){
            etEmail.setError("Enter Email");
            return false;
        } else  if (! Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter valid Email id");
            return false;
        } else if (city.isEmpty()){
            tvLocation.setError("Enter city name");
            return false;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dealer_profle_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoOut:
                logOut();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }


    }
    public  void logOut()
    {
        showProgress("Please wait...");

        RestApiCalls.logout(getContext()).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();

               /* if(response.isSuccessful() && response.body()!=null){
                    if(response.body().isSuccess()){
                        AppPreferences.DeleteSharedPreference(getActivity());
                        ObjectTableUtil.setUserModel(getActivity(), null);
                        startActivity(new Intent(getActivity(), SplashActivity.class));
                        getActivity().finish();
                    }else{
                        showToast(response.body().getMessage());
                    }
                }else{
                    showToast(response.message());
                }*/
                saveLogOut();
            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                hideProgress();
                saveLogOut();
                showToast(t.getMessage());
            }
        });

    }

    public void saveLogOut()
    {
        AppPreferences.DeleteSharedPreference(getActivity());
        ObjectTableUtil.setUserModel(getActivity(), null);
        startActivity(new Intent(getActivity(), SplashActivity.class));
        getActivity().finish();
    }


    private void saveDealerDetails(){

        String name = etName.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String city = tvLocation.getText().toString().trim();

        HashMap<String,String> params = new HashMap<>();
        params.put(Constants.NAME,name);
        params.put(Constants.MOBILE,mobile);
        params.put(Constants.EMAIL, email);
        params.put(Constants.CITY, city);

        userModel.setCity(city);
        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setMobile(mobile);

        if(!TextUtils.isEmpty(userModel.getEmail())) {
            OneSignal.sendTag("email", userModel.getEmail());
        }

        showProgress("Please wait..");

        RestApiCalls.saveProfile(getContext(),params).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        dealerDetailSaved();

                    } else {
                        Logger.e(TAG,response.body().getMessage());
                        showToast(response.body().getMessage());
                    }

                } else {
                    Logger.e(TAG,response.message());
                    showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                hideProgress();
                Logger.e(TAG,t.getMessage());
                showToast(t.getMessage());

            }
        });

    }
    private void dealerDetailSaved(){
        AppPreferences.setBooleanSharedPreference(getActivity(), AppPreferences.DEALER_VERIFIED, true);
        getFragmentAdapter().popFragment(true);
        ObjectTableUtil.setUserModel(getContext(),userModel);
       // getFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof CityCursorAdapter) {
            Cursor cursor = (Cursor) tvLocation.getAdapter().getItem(position);

            tvLocation.setText(cursor.getString(cursor.getColumnIndex(CitytableColumns.CITY_NAME)));
        }
    }

}
