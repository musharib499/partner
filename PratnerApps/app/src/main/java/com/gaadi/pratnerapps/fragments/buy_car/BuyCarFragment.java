package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.activities.HomeActivity;
import com.gaadi.pratnerapps.models.BuyCarIInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarFragment extends Fragment {

    private BuyCarIInfo buyCarIInfo = new BuyCarIInfo();

    private FragmentManager fragmentManager;

    public BuyCarFragment() {

    }


    public static BuyCarFragment newInstance() {
        BuyCarFragment fragment = new BuyCarFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_car, container, false);
        switchToFragment(getActivity(), BuyCarListFragment.newInstance(), false);
        return view;
    }

    public void switchToFragment(FragmentActivity context, Fragment fragment, boolean expandToolbar) {
        fragmentManager = context.getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.flcontainer, fragment, fragment.getClass().getName())
                .addToBackStack(null)
                .commit();


        ((HomeActivity) context).setExpandCollapse(expandToolbar);
    }

}
