package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.BuyCarColorAdapter;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarFilterColorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarFilterColorFragment extends Fragment  {

    private RecyclerView recyclerView;
    private BuyCarFilterModel buyCarFilterModel;

    private ArrayList<ParameterApiModel.KeyValueModel> colorList = new ArrayList<>();

    public static BuyCarFilterColorFragment newInstance(BuyCarFilterModel buyCarFilterModel) {
        BuyCarFilterColorFragment fragment = new BuyCarFilterColorFragment();
        fragment.buyCarFilterModel = buyCarFilterModel;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_color, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        recyclerView = CommonUtils.recyclerView(recyclerView, getActivity(), true);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(4, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        colorList = ObjectTableUtil.getCarColors(getContext());

        BuyCarColorAdapter buyCarColorAdapter=new  BuyCarColorAdapter(getActivity(), colorList, buyCarFilterModel);

        recyclerView.setAdapter(buyCarColorAdapter);

        return view;
    }

   /* @Override
    public void onBind(final BuyCarFilterColorViewHolder holder, final int position) {

        holder.view_color.setBackgroundColor(Color.parseColor("#"+colorList.get(position).getValue()));
        if (buyCarFilterModel.getColors().contains(colorList.get(position).getKey()))
            holder.view_check.setVisibility(View.VISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyCarFilterModel.getColors().contains(colorList.get(position).getKey())) {
                    buyCarFilterModel.getColors().remove(colorList.get(position).getKey());
                    holder.view_check.setVisibility(View.GONE);
                } else {
                    buyCarFilterModel.getColors().add(colorList.get(position).getKey());
                    holder.view_check.setVisibility(View.VISIBLE);
                }
            }
        });
    }*/
}
