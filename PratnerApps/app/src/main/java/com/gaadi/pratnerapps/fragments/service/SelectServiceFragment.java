package com.gaadi.pratnerapps.fragments.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.ServiceAdapter;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.ServicesApiModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashishkumar on 30/8/16.
 *
 */
public class SelectServiceFragment extends PartnerBaseFragment implements View.OnClickListener {
    private ServiceAdapter serviceAdapter;
    private ServicesApiModel servicesApiModel;
    private Button btnNext;
    private RecyclerView recyclerView;

    public SelectServiceFragment getInstance(){
        SelectServiceFragment fragment = new SelectServiceFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadServices();
    }

    private void loadServices(){
        showProgress("Please wait...");

        RestApiCalls.getServices(getContext()).enqueue(new Callback<ServicesApiModel>() {
            @Override
            public void onResponse(Call<ServicesApiModel> call, Response<ServicesApiModel> response) {
                hideProgress();

                if(response.isSuccessful()){
                    if(response.body()!=null){
                        servicesApiModel = response.body();

                        serviceAdapter = new ServiceAdapter(getActivity(),servicesApiModel.getServices());
                        recyclerView.setAdapter(serviceAdapter);
                    }else{
                        showToast("Something went wrong at server, Please try later.");
                    }
                }else{
                    showToast("Failed to load services info: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ServicesApiModel> call, Throwable t) {
                hideProgress();
                showToast("Failed to load services info: "+t.getMessage());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service,container,false);

        getFragmentAdapter().setTitleMessage(getString(R.string.services),false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        btnNext = (Button)view.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(this);

        recyclerView = CommonUtils.recyclerView(recyclerView,getActivity(),true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);

        //loadServices();

        if(servicesApiModel!=null){
            serviceAdapter = new ServiceAdapter(getActivity(),servicesApiModel.getServices());
            recyclerView.setAdapter(serviceAdapter);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if(serviceAdapter==null || serviceAdapter.getSelected().size()==0){
            showToast("Please select services to continue.");
        }else {
            getFragmentAdapter().addToBackStack(ServiceDetailsFragment.newInstance(serviceAdapter.getSelected(), servicesApiModel.getHome_pickup_available()), false);
        }
    }
}
