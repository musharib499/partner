package com.gaadi.pratnerapps.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.SellCarsPagerAdapter;
import com.gaadi.pratnerapps.events.PerformClickEvent;
import com.gaadi.pratnerapps.events.SwitchFragment;
import com.squareup.otto.Subscribe;

/**
 * Created by priyarawat on 30/5/16.
 */
public class SellCarsFragment extends PartnerBaseFragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentActivity mActivity;
    private boolean isValidated = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_cars_fragment, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("Your Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Car Details"));
        viewPager.setAdapter(new SellCarsPagerAdapter(mActivity.getSupportFragmentManager(), tabLayout.getTabCount(), getFragmentAdapter()));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1 && !isValidated)
                    PartnerApplication.getEventBus().post(new PerformClickEvent());
                else{
                    isValidated = false;
                    viewPager.setCurrentItem(tab.getPosition());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setHasOptionsMenu(true);

        getFragmentAdapter().setTitleMessage(getString(R.string.sell_car),false);

        return view;
    }
public static SellCarsFragment newInstance()
{
    return new SellCarsFragment();
}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) getActivity();
    }

    @Override
    public void onPause() {
        super.onPause();
        PartnerApplication.getEventBus().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        PartnerApplication.getEventBus().register(this);
    }

    @Subscribe
    public void switchTabAndFragment(SwitchFragment event)
    {
        if(event.isValidate()){
            isValidated = true;
            viewPager.setCurrentItem(1);
        }
        else if(!event.isValidate())
        {
            new Handler().postDelayed(
                    new Runnable(){
                        @Override
                        public void run() {
                            tabLayout.getTabAt(0).select();
                        }
                    }, 100);
        }

    }
    @Subscribe
    public void resetFormData(String event) {
        switch (event) {
            case "Reset":
            viewPager.setAdapter(new SellCarsPagerAdapter(mActivity.getSupportFragmentManager(), tabLayout.getTabCount(), getFragmentAdapter()));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return false;
    }
}
