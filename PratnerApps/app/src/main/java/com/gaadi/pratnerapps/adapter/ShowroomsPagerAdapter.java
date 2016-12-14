package com.gaadi.pratnerapps.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gaadi.pratnerapps.fragments.ShowroomsDetailFragment;
import com.gaadi.pratnerapps.models.ShowroomApiModel;

import java.util.ArrayList;

/**
 * Created by priyarawat on 2/6/16.
 */
public class ShowroomsPagerAdapter extends FragmentStatePagerAdapter {
   // String [] tabTitle = {"Your Details", "Car Details"};
    int tabCount;
    ArrayList<ShowroomApiModel.ShowroomModel> showroomModelArrayList;
    public ShowroomsPagerAdapter(FragmentManager childFragmentManager, int tabCount, ArrayList<ShowroomApiModel.ShowroomModel> showroomModelArrayList) {
        super(childFragmentManager);
        this.tabCount = tabCount;
        this.showroomModelArrayList = showroomModelArrayList;
    }

    @Override
    public Fragment getItem(int position) {

                ShowroomsDetailFragment fragment = ShowroomsDetailFragment.newInstance(showroomModelArrayList.get(position));
                return fragment;



    }

    @Override
    public CharSequence getPageTitle(int position) {
        return showroomModelArrayList.get(position).getLocality();
    }

    @Override
    public int getCount() {
        return tabCount;
    }


}
