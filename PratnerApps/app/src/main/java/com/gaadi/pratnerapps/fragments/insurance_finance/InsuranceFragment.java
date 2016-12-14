package com.gaadi.pratnerapps.fragments.insurance_finance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.FinanceApiModel;
import com.gaadi.pratnerapps.models.InsuranceApiModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyarawat on 3/8/16.
 */
public class InsuranceFragment extends InsuranceFinanceBaseFragment implements FragmentCallback{
    private static final String TAG = "InsuranceFragment";

    public static final int REQUEST_INSURANCE_DETAIL = 100;
    public static final int REQUEST_PERSON_DETAIL = 200;
    private HashMap<String, String> paramsHashMap = new HashMap<>();
    private InsuranceApiModel insuranceApiModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = super.onCreateView(inflater, container, savedInstanceState);

        getFragmentAdapter().setTitleMessage(getString(R.string.insurance),false);

        insuranceApiModel = ObjectTableUtil.getInsuranceModel(getContext());

        if(insuranceApiModel == null){
            loadFromApi();
        }else{
            initInterface();
        }



        return view;
    }

    private void initInterface(){
        setHeroAndWidget();

        Bundle bundle = new Bundle();
        bundle.putInt(FinanceCarDetailsFragment.EXTRA_REQUEST_CODE, REQUEST_INSURANCE_DETAIL);
        bundle.putStringArrayList(InsuranceDetailsFragment.EXTRA_COMAPNY_LIST,insuranceApiModel.getCompanies());

        Fragment fragment = new InsuranceDetailsFragment().setFragmentCallback(this).setFragmentAdapter(getFragmentAdapter());
        fragment.setArguments(bundle);
        replaceContentFragment(fragment);
    }

    private void loadFromApi() {
        requestProgress("","Please wait...");
        RestApiCalls.getInsurance(getContext()).enqueue(new Callback<InsuranceApiModel>() {

            @Override
            public void onResponse(Call<InsuranceApiModel> call, Response<InsuranceApiModel> response) {
                hideProgress();
                if(response.isSuccessful() && null != response.body()){
                    insuranceApiModel = response.body();
                    Logger.e(TAG,insuranceApiModel.toString());
                    ObjectTableUtil.setInsuranceModel(getContext(), insuranceApiModel);
                    initInterface();
                }
                else {
                    showToast(getString(R.string.server_error_msg));
                }
            }

            @Override
            public void onFailure(Call<InsuranceApiModel> call, Throwable t) {
                hideProgress();
                showToast(getString(R.string.network_connection_error));
            }
        });
    }

    @Override
    public String getHeroImage() {
        return insuranceApiModel.getHeroImage();
    }

    @Override
    public ArrayList<String> getPartners() {
        ArrayList<FinanceApiModel.PartnersModel> partnersModelArrayList = insuranceApiModel.getPartners();
        ArrayList<String> partnersImageList = new ArrayList<>();
        if (partnersModelArrayList !=null && partnersModelArrayList.size()>0) {
            for (FinanceApiModel.PartnersModel obj : partnersModelArrayList) {
                partnersImageList.add(obj.getImage());
            }
        }
        return partnersImageList;
    }

    @Override
    public void onContinue(int requestCode, HashMap<String, String> params) {
        if(requestCode == REQUEST_INSURANCE_DETAIL)
        {
            paramsHashMap.putAll(params);
            Bundle bundle = new Bundle();
            bundle.putInt(FinanceCarDetailsFragment.EXTRA_REQUEST_CODE,REQUEST_PERSON_DETAIL);
            Fragment fragment = new PersonalDetailsBaseFragment().setFragmentCallback(this).setFragmentAdapter(getFragmentAdapter());
            fragment.setArguments(bundle);
            replaceContentFragmentWithBackStack(fragment);
        }
        else if(requestCode==REQUEST_PERSON_DETAIL){
            paramsHashMap.putAll(params);

            requestProgress("","Please wait...");

            RestApiCalls.postInsurance(getContext(),paramsHashMap, paramsHashMap.get("imagePath")).enqueue(new Callback<BaseResponseModel>() {
                @Override
                public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                    hideProgress();
                    if(response.isSuccessful() && null != response.body() && response.body().isSuccess()){
                       // showToast("Successfully post");
                       // getFragmentAdapter().popFragment(false);
                        showSubmitDialog(getResources().getString(R.string.insurance_title_msg),
                                getResources().getString(R.string.insurance_msg));
                       // getFragmentAdapter().replaceFragment(new InsuranceFragment(),true);
                    }
                    else if(null != response.body()){
                        showToast(response.raw().message());
                    }
                    else{
                        showToast(getString(R.string.server_error_msg));
                    }
                }

                @Override
                public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                    hideProgress();
                    showToast(t.getMessage());
                    Logger.e("Failure message in Insurance Fragment", t.getMessage());
                }
            });
        }
    }
}
