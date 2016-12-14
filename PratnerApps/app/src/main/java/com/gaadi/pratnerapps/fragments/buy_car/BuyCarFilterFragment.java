package com.gaadi.pratnerapps.fragments.buy_car;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.utils.CommonUtils;


public class BuyCarFilterFragment extends PartnerBaseFragment implements View.OnClickListener, BuyCarFilterLeftItemFragment.OnListFragmentInteractionListener {

    private static final String TAG = "BuyCarFilterFragment";
    public static final String EXTRA_FILTER_MODEL = "extra_filter_model";

    private BuyCarFilterLeftItemFragment filterLeftItemFragment;
    private BuyCarFilterModel buyCarFilterModel;
    private FilterListener filterListener;



    public static BuyCarFilterFragment newInstance(BuyCarFilterModel buyCarFilterModel) {
        BuyCarFilterFragment fragment = new BuyCarFilterFragment();
        fragment.buyCarFilterModel = new BuyCarFilterModel(buyCarFilterModel);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_filter, null, false);

        filterLeftItemFragment = (BuyCarFilterLeftItemFragment) getChildFragmentManager().findFragmentById(R.id.content_left);
        filterLeftItemFragment.setOnListFragmentInteractionListener(this);

        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        view.findViewById(R.id.btn_apply).setOnClickListener(this);
        setHasOptionsMenu(true);

        //onListFragmentInteraction(0);
        filterLeftItemFragment.clickTvPrice();

        getFragmentAdapter().setTitleMessage(getString(R.string.filter),false);

        return view;
    }

    @Override
    public void onListFragmentInteraction(int position) {
        switch (position) {
            case 0:
                switchFragment(BuyCarFilterPriceRangeFragment.newInstance(buyCarFilterModel));
                break;
            case 1:
                switchFragment(BuyCarFilterKmRangeFragment.newInstance(buyCarFilterModel));
                break;
            case 2:
                switchFragment(BuyCarMakeFragment.newInstance(buyCarFilterModel));
                break;
            case 3:
                switchFragment(BuyCarAdvanceFilterFragment.newInstance(buyCarFilterModel));
                break;
            case 4:
                break;
        }
    }

    public void switchFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_right, fragment).commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        TextView textView = CommonUtils.OverFlowMenuText(getActivity(), "RESET", 18, menu);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                buyCarFilterModel.getColors().clear();
//                buyCarFilterModel.getModels().clear();
//                buyCarFilterModel.getBodyTypes().clear();
//                buyCarFilterModel.getPriceRange().clear();
//                buyCarFilterModel = new BuyCarFilterModel();
//               // onListFragmentInteraction(0);
                 getFragmentAdapter().popFragment(false);
                 filterListener.onFilterReset();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                //getFragmentAdapter().popFragment(false);
                filterListener.onFilterCancel();
                getActivity().onBackPressed();
                break;
            case R.id.btn_apply:
                getFragmentAdapter().popFragment(false);
                filterListener.onFilterApply(buyCarFilterModel);
                break;
        }
    }

    public BuyCarFilterFragment setFilterListener(FilterListener filterListener) {
        this.filterListener = filterListener;
        return this;
    }


}
