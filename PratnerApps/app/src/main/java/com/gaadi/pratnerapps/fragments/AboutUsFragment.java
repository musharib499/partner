package com.gaadi.pratnerapps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;

public class AboutUsFragment extends Fragment {

    TextView tvAboutus, tvDescription;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    public  static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.about_us, container, false);
        tvAboutus = (TextView) view.findViewById(R.id.tvAboutus);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        return view;
    }

}
