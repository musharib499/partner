package com.gaadi.pratnerapps.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.FeaturesPagerAdapter;
import com.gaadi.pratnerapps.custom.NestedScrollViewPager;


/**
 * Created by lakshaygirdhar on 1/4/16.
 */
public class FeaturesSpecificationFragment extends Fragment {

    private Context mContext;
    private FragmentActivity mActivity;

    private TabLayout tabLayout;
    private NestedScrollViewPager viewPager;

    public static FeaturesSpecificationFragment newInstance() {

        Bundle args = new Bundle();

        FeaturesSpecificationFragment fragment = new FeaturesSpecificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requirement_specifications, null);

        tabLayout = (TabLayout) view.findViewById(R.id.features_tablayout);
        viewPager = (NestedScrollViewPager) view.findViewById(R.id.features_viewpager);
        tabLayout.addTab(tabLayout.newTab().setText("Features"));
        tabLayout.addTab(tabLayout.newTab().setText("Specifications"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        FeaturesPagerAdapter mPagerAdapter = new FeaturesPagerAdapter(mActivity.getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                // tabLayout.setTabTextColors(ColorStateList.valueOf(Color.RED));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}
