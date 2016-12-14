package com.gaadi.pratnerapps.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gaadi.pratnerapps.fragments.CarDetailsFragment;
import com.gaadi.pratnerapps.fragments.PersonalDetailsFragment;
import com.gaadi.pratnerapps.utils.FragmentAdapter;

/**
 * Created by priyarawat on 30/5/16.
 */
public class SellCarsPagerAdapter extends FragmentStatePagerAdapter {
    String [] tabTitle = {"Your Details", "Car Details"};
 int tabCount;
    FragmentAdapter fragmentAdapter;

    public SellCarsPagerAdapter(FragmentManager childFragmentManager, int tabCount, FragmentAdapter fragmentAdapter) {
        super(childFragmentManager);
        this.tabCount = tabCount;
        this.fragmentAdapter = fragmentAdapter;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
               return new PersonalDetailsFragment().setFragmentAdapter(fragmentAdapter);


            case 1:
               return new CarDetailsFragment().setFragmentAdapter(fragmentAdapter);


             default:
                 return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];}
}
