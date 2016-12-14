package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.BaseRecyclerAdapter;
import com.gaadi.pratnerapps.holder.BuyCarFilterPriceRangeViewHolder;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarFilterPriceRangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarFilterPriceRangeFragment extends Fragment implements BaseRecyclerAdapter.BindAdapterListener<BuyCarFilterPriceRangeViewHolder> {
    private RecyclerView recyclerView;
    private BuyCarFilterModel buyCarFilterModel;
    private ArrayList<ParameterApiModel.KeyValueModel> pricelist = new ArrayList<>();

    public static BuyCarFilterPriceRangeFragment newInstance(BuyCarFilterModel buyCarFilterModel) {
        BuyCarFilterPriceRangeFragment fragment = new BuyCarFilterPriceRangeFragment();
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
        View view = inflater.inflate(R.layout.layout_recycler_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        pricelist = ObjectTableUtil.getPriceRange(getContext());
        BaseRecyclerAdapter<ParameterApiModel.KeyValueModel, BuyCarFilterPriceRangeViewHolder> serchNavigation = new BaseRecyclerAdapter<>(getActivity(), pricelist, this, BuyCarFilterPriceRangeViewHolder.class, R.layout.item_price_range);
        CommonUtils.recyclerView(recyclerView, getActivity(), true).setAdapter(serchNavigation);
        return view;
    }


    @Override
    public void onBind(final BuyCarFilterPriceRangeViewHolder holder, final int position) {
        holder.checkBox.setText(pricelist.get(position).getValue());

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(buyCarFilterModel.getPriceRange().contains(pricelist.get(position).getKey()));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !buyCarFilterModel.getPriceRange().contains(pricelist.get(position).getKey()))
                    buyCarFilterModel.getPriceRange().add(pricelist.get(position).getKey());
                else
                    buyCarFilterModel.getPriceRange().remove(pricelist.get(position).getKey());
            }
        });



    }
}
