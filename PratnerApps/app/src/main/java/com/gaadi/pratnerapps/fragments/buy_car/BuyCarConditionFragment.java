package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.BuyCarConditions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarConditionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarConditionFragment extends PartnerBaseFragment {

    private static final String ARG_PARAM = "carConditions";

    private BuyCarConditions mParam;
    private TextView tvCondition;
    private TextView tvExteriour;
    private TextView tvInteriour;
    private TextView tvBodyFrame;
    private TextView tvEngine;
    private TextView tvSuspensive;


    public BuyCarConditionFragment() {
        // Required empty public constructor
    }

    public static BuyCarConditionFragment newInstance(BuyCarConditions carConditions) {
        BuyCarConditionFragment fragment = new BuyCarConditionFragment();
        Bundle args = new Bundle();

        args.putSerializable(ARG_PARAM, carConditions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = (BuyCarConditions) getArguments().getSerializable(ARG_PARAM);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buyercar_condition, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCondition = (TextView) view.findViewById(R.id.tvCondition);
        tvExteriour = (TextView) view.findViewById(R.id.tvExteriour);
        tvInteriour = (TextView) view.findViewById(R.id.tvInteriour);
        tvBodyFrame = (TextView) view.findViewById(R.id.tvBodyFrame);
        tvEngine = (TextView) view.findViewById(R.id.tvEngine);
        tvSuspensive = (TextView) view.findViewById(R.id.tvSuspensive);
        setData();
    }

    public void setData() {
        tvCondition.setText(mParam.getOverall_condition());
        tvExteriour.setText(mParam.getExterior());
        tvInteriour.setText(mParam.getInterior());
        tvBodyFrame.setText(mParam.getBodyframe());
        tvEngine.setText(mParam.getEngine());
        tvSuspensive.setText(mParam.getEngine());

    }

}
