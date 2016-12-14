package com.gaadi.pratnerapps.fragments.buy_car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.activities.RequestOtpActivity;
import com.gaadi.pratnerapps.adapter.BuyCarListAdapter;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.models.BuyCarIInfo;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.MenuTintUtils;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface FilterListener {
    void onFilterApply(BuyCarFilterModel buyCarFilterModel);
    void onFilterCancel();
    void onFilterReset();
}

public class BuyCarListFragment extends PartnerBaseFragment
        implements BuyCarListAdapter.CarClickListener ,FilterListener {
    public static final String EXTRA_BODY_TYPE="extra_body_type";
    public static final String EXTRA_BRAND_TYPE = "extra_brand_type";

    private static final String TAG = "BuyCarListFragment";
    BuyCarListAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvEmptyView;
    private BuyCarIInfo buyCarIInfo = new BuyCarIInfo();
    private FloatingActionButton floatingActionButton;
    private boolean hasMorePages = true;
    private View view;



    private BuyCarFilterModel buyCarFilterModel = new BuyCarFilterModel();

    public static BuyCarListFragment newInstance() {
        return newInstance(null);
    }
    public static BuyCarListFragment newInstance(Bundle bundle) {
        BuyCarListFragment fragment=new BuyCarListFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            if(getArguments().containsKey(EXTRA_BODY_TYPE)){
                ArrayList<String> bodyTypes = new ArrayList<>();
                bodyTypes.add(getArguments().getString(EXTRA_BODY_TYPE));
                buyCarFilterModel.setBodyTypes(bodyTypes);
            }else if(getArguments().containsKey(EXTRA_BRAND_TYPE)){
                HashMap<Integer, ArrayList<Integer>> models = new HashMap<>();
                models.put(getArguments().getInt(EXTRA_BRAND_TYPE),new ArrayList<Integer>());
                buyCarFilterModel.setModels(models);
                buyCarFilterModel.getModels().get(getArguments().getInt(EXTRA_BRAND_TYPE)).add(0);
            }

//            setArguments(null);
        }

        if (Utils.checkInternetConnection(getActivity())) {
            onLoadData();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view!=null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_recycler_view_buy_car, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentAdapter().setTitleMessage(getString(R.string.available_car),false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        recyclerView.setNestedScrollingEnabled(false);
        tvEmptyView = (TextView) view.findViewById(R.id.tvEmptyview);
       // floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);


        if (buyCarIInfo != null)
            onSetData(buyCarIInfo);

        if (!Utils.checkInternetConnection(getActivity())){
            Utils.showNoConnectionDialog(getActivity());
            if (buyCarIInfo.getItems().isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                tvEmptyView.setVisibility(View.VISIBLE);

            } else {
                recyclerView.setVisibility(View.VISIBLE);
                tvEmptyView.setVisibility(View.GONE);
            }

            tvEmptyView.setText(R.string.enable_internet);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
       // setActionBarTitle(getString(R.string.available_car));

    }


    @Override
    public void onResume() {
        super.onResume();
        floatingActionButton=(FloatingActionButton) getFragmentAdapter().setFabMenu(getActivity());
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setImageResource(R.drawable.ic_filter_fill);
        Utils.setTint(floatingActionButton,getActivity(),"title_color_icon_color");
        tvEmptyView.setText("Loading.....");
        tvEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadData();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(BuyCarFilterFragment.newInstance(buyCarFilterModel).setFilterListener(BuyCarListFragment.this).setFragmentAdapter(getFragmentAdapter()), false);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        getFragmentAdapter().setFabMenu(getActivity()).setVisibility(View.GONE);
    }

    public void onLoadData() {
        if (Utils.checkInternetConnection(getActivity())) {

            if (!hasMorePages) {
                Logger.e(TAG, "No records, returning...");
                showToast("No more records!");
                return;
            }

            requestProgress("Car List Loading..", "Please wait !");
            Logger.d("Filter Data", buyCarFilterModel.getFieldMap().toString());
            RestApiCalls.getSearchResults(getContext(),buyCarFilterModel.getFieldMap()).enqueue(new Callback<BuyCarIInfo>() {
                @Override
                public void onResponse(Call<BuyCarIInfo> call, Response<BuyCarIInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        hideProgress();
                        BuyCarIInfo searchCar = response.body();
                        if (searchCar.getItems().size() == 0) {
                            tvEmptyView.setVisibility(View.VISIBLE);
                            tvEmptyView.setText("No records!");
                            return;
                        }


                        if (buyCarIInfo.getItems().size() == 0) {
                            buyCarIInfo = searchCar;
                            if (getView() != null)
                                onSetData(buyCarIInfo);
                        } else {
                            buyCarIInfo.getItems().addAll(searchCar.getItems());
                            if (buyCarIInfo.getItems().size() != 0)

                                adapter.notifyDataSetChanged();


                        }

                        int totalPages = searchCar.getMeta().getTotalRecords() / searchCar.getMeta().getRecordsPerPage()
                                + (searchCar.getMeta().getTotalRecords() % searchCar.getMeta().getRecordsPerPage() > 0 ? 1 : 0);

                        hasMorePages = totalPages > searchCar.getMeta().getCurrentPage();
                        buyCarFilterModel.setPage("" + (searchCar.getMeta().getCurrentPage() + 1));
                        Logger.e(TAG, "" + response.toString());



                    }
                }

                @Override
                public void onFailure(Call<BuyCarIInfo> call, Throwable t) {
                    tvEmptyView.setText("Unable to load, Kindly make sure you have connectivity.");
                    hideProgress();
                }
            });
        }else {
            Utils.showNoConnectionDialog(getActivity());
            if (buyCarIInfo.getItems().isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                tvEmptyView.setVisibility(View.VISIBLE);

            } else {
                recyclerView.setVisibility(View.VISIBLE);
                tvEmptyView.setVisibility(View.GONE);
            }

            tvEmptyView.setText(R.string.enable_internet);
        }
    }

    public void onSetData(BuyCarIInfo buyCarIInfo) {
        adapter = new BuyCarListAdapter(getActivity(),buyCarIInfo.getItems());
        adapter.setCarClickListener(this);
        CommonUtils.recyclerView(recyclerView, getActivity(), true).setAdapter(adapter);


        if (buyCarIInfo.getItems().size() == 0) {
            recyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
            floatingActionButton.setVisibility(View.VISIBLE);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentAdapter().addToBackStack(BuyCarFilterFragment.newInstance(buyCarFilterModel).setFilterListener(BuyCarListFragment.this).setFragmentAdapter(getFragmentAdapter()), false);
                }
            });
        }
    }

   /* @Override
    public void onBind(final BuyCarListViewHolder holder, final int position) {
        final BuyCarDetails carsDetails = buyCarIInfo.getItems().get(position);
        if (carsDetails.getImages().size() != 0) {
            Utils.loadImage(getActivity(), carsDetails.getImages().get(0), holder.imgCar);
        }else {
           holder.imgCar.setImageResource(R.drawable.placeholder_img);
        }
        if (!TextUtils.isEmpty(carsDetails.getPrice())){
            price = carsDetails.getPrice().trim();
            holder.tvPrice.setText(Constants.RUPES + " " + Utils.convertCommaIntoNumber(price,"##,##,###"));

        }
        holder.tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!carsDetails.getMobile().isEmpty())
                    Utils.callPhone(getActivity(), carsDetails.getMobile());
            }
        });
        holder.tvRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(BuyCarRequestCallFragment.newInstance(carsDetails).setFragmentAdapter(getFragmentAdapter()), false);
            }
        });
        StringBuilder stringBuilderMake = new StringBuilder();

        if (!TextUtils.isEmpty(carsDetails.getPrice()))
            stringBuilderMake.append(carsDetails.getMake() + " ");

        if (!TextUtils.isEmpty(carsDetails.getPrice()))
            stringBuilderMake.append(carsDetails.getModel());

        holder.tvMake.setText(stringBuilderMake);

        if (!TextUtils.isEmpty(carsDetails.getCity()))
        holder.tvAddress.setText(carsDetails.getCity());

        StringBuilder stringBuilderYearKill = new StringBuilder();
        if (!TextUtils.isEmpty(carsDetails.getPrice())) {
            stringBuilderYearKill.append(carsDetails.getYear() + " " + Constants.BULLET);
           *//* stringBuilderMake.append(" " +  + " ");*//*
        }
        if (!TextUtils.isEmpty(carsDetails.getPrice())) {
            year = carsDetails.getKilometerDriven().trim();
            stringBuilderYearKill.append(Utils.convertCommaIntoNumber(year,"##,##,###") + " kms " + Constants.BULLET);

        }

        if (!TextUtils.isEmpty(carsDetails.getFuelType()))
            stringBuilderYearKill.append(carsDetails.getFuelType());

        holder.tvYearKilFuel.setText(stringBuilderYearKill);
        holder.imgCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(BuyCarDetailsFragment.newInstance(carsDetails).setFragmentAdapter(getFragmentAdapter()), true);
            }
        });
       *//* holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buyCarIInfo.getItems().remove(position);
            }
        });*//*

            loadIfLastItem(position + 1);

    }
*/
    public void loadIfLastItem(int position) {
        if (buyCarIInfo.getItems().size() == position && hasMorePages) {
            Logger.e(TAG, "Last item displayed, loading  more...");
            onLoadData();
        }
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_car_shorting, menu);

    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_car_shorting, menu);
        MenuTintUtils.tintAllIcons(((PartnerApplication) getContext().getApplicationContext()), menu);


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSortBy:
                sortFilter(0);
                Toast.makeText(getActivity(), R.string.sort_by, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemPLtH:
                sortFilter(1);
                Toast.makeText(getActivity(), R.string.price_low_to_high, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemPHTL:
                sortFilter(2);
                Toast.makeText(getActivity(), R.string.price_high_to_low, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemKLTH:
                sortFilter(3);
                Toast.makeText(getActivity(), R.string.km_low_to_high, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemKHTL:
                sortFilter(4);
                Toast.makeText(getActivity(), R.string.km_high_to_low, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemOld:
                sortFilter(5);
                Toast.makeText(getActivity(), R.string.oldest, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemNewest:
                sortFilter(6);

                Toast.makeText(getActivity(), R.string.newest, Toast.LENGTH_SHORT).show();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }


    }

    private void sortFilter(int p) {
        buyCarIInfo.getItems().clear();
        buyCarFilterModel.setSort("" + p);
        buyCarFilterModel.setPage(""+ 1);
        hasMorePages = true;

            onLoadData();

    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);

    }

    @Override
    public void onFilterApply(BuyCarFilterModel buyCarFilterModel) {
        this.buyCarFilterModel = buyCarFilterModel;
        sortFilter(0);
    }

    @Override
    public void onFilterCancel() {
    }

    @Override
    public void onFilterReset() {
        Toast.makeText(getActivity(), "Filter Reset", Toast.LENGTH_SHORT).show();
        buyCarFilterModel=new BuyCarFilterModel();
        getFragmentAdapter().addToBackStack(BuyCarFilterFragment.newInstance(buyCarFilterModel).setFilterListener(BuyCarListFragment.this).setFragmentAdapter(getFragmentAdapter()), false);

    }
    @Override
    public void onCarDetail(BuyCarDetails buyCarDetails) {
        getFragmentAdapter().addToBackStack(BuyCarDetailsFragment.newInstance(buyCarDetails).setFragmentAdapter(getFragmentAdapter()), true);

    }

    @Override
    public void onCallRequest(BuyCarDetails buyCarDetails) {
        if(AppPreferences.getBooleanSharedPreference(getContext(),AppPreferences.DEALER_VERIFIED,false)) {
            getFragmentAdapter().addToBackStack(BuyCarRequestCallFragment.newInstance(buyCarDetails).setFragmentAdapter(getFragmentAdapter()), false);
        }else {
            showToast("Kindly sign-in to request call.");
            startActivity(new Intent(getActivity(), RequestOtpActivity.class));
        }
    }

    @Override
    public void onLoadItem(int position) {
        loadIfLastItem(position+1);
    }


}




