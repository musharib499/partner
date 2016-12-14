package com.gaadi.pratnerapps.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.gaadi.pratnerapps.fragments.buy_car.BuyCarFeaturesFragment;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarSpecificationFragment;


/**
 * Created by Musharib on 30/3/16.
 */
public class FeaturesPagerAdapter extends FragmentStatePagerAdapter {
    int mNumTabs;

    public FeaturesPagerAdapter(FragmentManager fm, int numtabs) {
        super(fm);
        this.mNumTabs = numtabs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("Kanish", "In get item");
        switch (position) {
            case 0:
                BuyCarFeaturesFragment tab1 = new BuyCarFeaturesFragment();
                return tab1;
            case 1:
                BuyCarSpecificationFragment tab2 = new BuyCarSpecificationFragment();
                return tab2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        Log.e("Kanish", "In getcount pageradapter");
        return mNumTabs;
    }
}
