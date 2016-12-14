package com.gaadi.pratnerapps.fragments.service;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.Logger;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceReviewFragment extends PartnerBaseFragment {
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    private String nameParam;
    private String emailParam;
    private String phoneParam;
    private String addressParam;
    private String dateParam;
    private String timeParam;
    private String startDateParam;
    private String endDateParam;
    private String serviceType;
    private boolean pickWantParam;
    private TextView tvPickUp;
    private TextView tvPickUpOn;
    private TextView tvPickUpFrom;
    private TextView tvName;
    private TextView tvServiceType;
    private TextView tvMobile;
    private TextView tvEmail;


    public ServiceReviewFragment() {
    }

    public static ServiceReviewFragment newInstance(Bundle bundle) {
        ServiceReviewFragment fragment = new ServiceReviewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameParam = getArguments().getString(ServiceDetailsFragment.NAME);
            emailParam = getArguments().getString(ServiceDetailsFragment.EMAIL);
            phoneParam = getArguments().getString(ServiceDetailsFragment.PHONE);
            endDateParam = getArguments().getString(ServiceDetailsFragment.END_DATE);
            startDateParam = getArguments().getString(ServiceDetailsFragment.START_DATE);
            timeParam = getArguments().getString(ServiceDetailsFragment.TIME);
            dateParam = getArguments().getString(ServiceDetailsFragment.DATE);
            addressParam = getArguments().getString(ServiceDetailsFragment.ADDRESS);
            serviceType = getArguments().getString(ServiceDetailsFragment.SERVICE_TYPE);
            pickWantParam = getArguments().getBoolean(ServiceDetailsFragment.PICK_UP_WANT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_service_review, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvPickUp = (TextView) view.findViewById(R.id.tvPickUp);
        tvPickUpOn = (TextView) view.findViewById(R.id.tvPickUpOn);
        tvPickUpFrom = (TextView) view.findViewById(R.id.tvPickUpFrom);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvMobile = (TextView) view.findViewById(R.id.tvMobile);
        tvEmail = (TextView) view.findViewById(R.id.tvEmai);
        tvServiceType = (TextView) view.findViewById(R.id.tvCarServices);
        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitData();
            }
        });
        setData();
    }

    public void setData() {

        if (pickWantParam) {
            tvPickUp.setText(getString(R.string.pick_up_details));
            if (!TextUtils.isEmpty(timeParam) && !TextUtils.isEmpty(dateParam))
                tvPickUpOn.setText(getString(R.string.pick_up_on) + ": " + dateParam + " " + timeParam);
            if (tvPickUpFrom.getVisibility() == View.GONE) {
                tvPickUpFrom.setVisibility(View.VISIBLE);
                tvPickUpFrom.setText(getString(R.string.pick_up_from, addressParam));
            }
        } else {
            tvPickUp.setText(getString(R.string.service_timing));
            if (!TextUtils.isEmpty(timeParam) && !TextUtils.isEmpty(dateParam))
                tvPickUpOn.setText(dateParam + " " + timeParam);
        }
        if (!TextUtils.isEmpty(serviceType))
        {
           tvServiceType.setText(serviceType);
        }

        if (!TextUtils.isEmpty(nameParam))
            tvName.setText(nameParam);

        if (!TextUtils.isEmpty(phoneParam))
            tvMobile.setText(phoneParam);

        if (!TextUtils.isEmpty(emailParam))
            tvEmail.setText(emailParam);
    }

    public void onSubmitData() {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put(ServiceDetailsFragment.NAME, nameParam);
        paramsMap.put(ServiceDetailsFragment.PHONE, phoneParam);
        paramsMap.put(ServiceDetailsFragment.EMAIL, emailParam);
        paramsMap.put(ServiceDetailsFragment.ADDRESS, addressParam);

        if(pickWantParam) {
            paramsMap.put(ServiceDetailsFragment.START_DATE, startDateParam);
        }
        else {
            paramsMap.put(ServiceDetailsFragment.END_DATE, endDateParam);
        }

        paramsMap.put(ServiceDetailsFragment.SERVICE_TYPE,serviceType);

        showProgress("Please wait...");

        Logger.d("ServiceReview ",paramsMap.toString());

        RestApiCalls.postServices(getContext(),paramsMap).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();
                if (response.isSuccessful() && null != response.body() && response.body().isSuccess()) {
                    showSubmitDialog(getResources().getString(R.string.service_title_msg),
                            getResources().getString(R.string.service_book_msg));

                }else if(response.body()!=null){
                    showToast("Failed: "+response.body().getMessage());
                }
                else {
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
