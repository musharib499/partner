package com.gaadi.pratnerapps.fragments.insurance_finance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.EMICalculatorFragment;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.FinanceApiModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyarawat on 3/8/16.
 */
public class FinanceFragment extends InsuranceFinanceBaseFragment implements FragmentCallback{
    public static final int REQUEST_CAR_DETAIL = 100;
    public static final int REQUEST_PERSON_DETAIL = 200;
    private HashMap<String, String> paramsHashmap = new HashMap<>();
    private FinanceApiModel financeApiModel;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);

        getFragmentAdapter().setTitleMessage(getString(R.string.finance),true);


        Bundle bundle = new Bundle();
        bundle.putInt(FinanceCarDetailsFragment.EXTRA_REQUEST_CODE,REQUEST_CAR_DETAIL);

        Fragment fragment = new FinanceCarDetailsFragment().setFragmentCallback(this).setFragmentAdapter(getFragmentAdapter());
        fragment.setArguments(bundle);

        replaceContentFragment(fragment);

        financeApiModel = ObjectTableUtil.getFinanceModel(getContext());

        if(financeApiModel==null){
            loadFromApi();
        }else{
            initView();
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        getFragmentAdapter().setFabMenu(getActivity()).setVisibility(View.VISIBLE);
     */ /*  floatingActionButton.setVisibility(View.VISIBLE);
        Utils.setTint(floatingActionButton,getActivity(),"title_color_icon_color");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(new EMICalculatorFragment().setFragmentAdapter(getFragmentAdapter()), false);
            }
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();
        floatingActionButton= (FloatingActionButton) getFragmentAdapter().setFabMenu(getActivity());
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setImageResource(R.drawable.calculator_new);
        Utils.setTint(floatingActionButton,getActivity(),"title_color_icon_color");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(new EMICalculatorFragment().setFragmentAdapter(getFragmentAdapter()), false);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        getFragmentAdapter().setFabMenu(getActivity()).setVisibility(View.GONE);
    }

    private void initView(){
        setHeroAndWidget();
    }
    private void loadFromApi() {
        requestProgress("","Please wait...");
        RestApiCalls.getFinance(getContext()).enqueue(new Callback<FinanceApiModel>() {
            @Override
            public void onResponse(Call<FinanceApiModel> call, Response<FinanceApiModel> response) {
                hideProgress();
                if(response.isSuccessful() && null != response.body()) {
                    financeApiModel = response.body();
                    ObjectTableUtil.setFinanceModel(getContext(),financeApiModel);
                    initView();
                }
                else
                {
                    showToast(getString(R.string.server_error_msg));
                }
            }

            @Override
            public void onFailure(Call<FinanceApiModel> call, Throwable t) {
                hideProgress();
            }
        });
    }

    @Override
    public String getHeroImage() {
        return financeApiModel.getHeroImage();
    }

    @Override
    public ArrayList<String> getPartners() {
        ArrayList<FinanceApiModel.PartnersModel> partnersModelArrayList = financeApiModel.getPartners();
        ArrayList<String> partnersImageList = new ArrayList<>();
        if(partnersModelArrayList!=null  && partnersModelArrayList.size() >0)
        {
            for (FinanceApiModel.PartnersModel obj : partnersModelArrayList) {
                partnersImageList.add(obj.getImage());
            }
        }
        return partnersImageList;
    }

    @Override
    public void onContinue(int requestCode,HashMap<String,String> params) {
        if(requestCode==REQUEST_CAR_DETAIL) {
            paramsHashmap.putAll(params);
            Bundle bundle = new Bundle();
            bundle.putInt(FinanceCarDetailsFragment.EXTRA_REQUEST_CODE,REQUEST_PERSON_DETAIL);
            Fragment fragment = new PersonalDetailsBaseFragment().setFragmentCallback(this).setFragmentAdapter(getFragmentAdapter());
            fragment.setArguments(bundle);
            replaceContentFragmentWithBackStack(fragment);
        }
        else if(requestCode==REQUEST_PERSON_DETAIL){
            paramsHashmap.putAll(params);
            requestProgress("","Please wait...");
            RestApiCalls.postFinance(getContext(),paramsHashmap).enqueue(new Callback<BaseResponseModel>() {
                @Override
                public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                    hideProgress();
                    if(response.isSuccessful() && null != response.body() && response.body().isSuccess())
                    {
                       /* showToast("Successfully post");

                        getFragmentAdapter().popFragment(false);*/
                        showSubmitDialog(getResources().getString(R.string.finance_title_msg),
                                getResources().getString(R.string.finance_msg));
                        getFragmentAdapter().replaceFragment(new FinanceFragment(),true);
                    }
                    else if(null != response.body())
                    {
                        showToast(response.raw().message());
                    }
                    else
                    {
                        showToast(getString(R.string.server_error_msg));
                    }
                }

                @Override
                public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                    hideProgress();
                    showToast(t.getMessage());
                    Logger.e("Failure message in Finance Fragment", null != t ? t.getMessage() : "error");
                }
            });

        }

    }
}
