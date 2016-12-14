package com.gaadi.pratnerapps.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.ShowroomsPagerAdapter;
import com.gaadi.pratnerapps.models.ShowroomApiModel;
import com.gaadi.pratnerapps.utils.DatabaseUtil;

import java.util.ArrayList;

/**
 * Created by priyarawat on 2/6/16.
 */
public class ShowroomFragment extends PartnerBaseFragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentActivity mActivity;
    ArrayList<ShowroomApiModel.ShowroomModel> showroomModelArrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showrooms_root_fragment, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        showroomModelArrayList = DatabaseUtil.getShowrooms(mActivity);

        if(showroomModelArrayList != null && showroomModelArrayList.size() > 1)
            getFragmentAdapter().setTitleMessage("Showrooms("+showroomModelArrayList.size() +")",false);
        else
            getFragmentAdapter().setTitleMessage("Showroom", false);

       ShowroomsPagerAdapter sellCarsPagerAdapter = new ShowroomsPagerAdapter(getChildFragmentManager(), showroomModelArrayList.size(), showroomModelArrayList);
        viewPager.setAdapter(sellCarsPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

public static ShowroomFragment newInstance()
{
    return new ShowroomFragment();
}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }
}
