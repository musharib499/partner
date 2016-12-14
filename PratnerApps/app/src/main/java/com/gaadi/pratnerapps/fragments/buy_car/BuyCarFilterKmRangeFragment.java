package com.gaadi.pratnerapps.fragments.buy_car;

import android.content.Context;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vinodtakhar
 * @version 1.0
 * @since 29/11/16
 */

public class BuyCarFilterKmRangeFragment extends Fragment implements BaseRecyclerAdapter.BindAdapterListener<BuyCarFilterPriceRangeViewHolder> {
    private RecyclerView recyclerView;
    private BuyCarFilterModel buyCarFilterModel;

    private String kms = "[\n" +
            "        {\n" +
            "            \"key\": \"0-20000\",\n" +
            "            \"value\": \"0 - 20,000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"key\": \"20000-40000\",\n" +
            "            \"value\": \"20,000 - 40,000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"key\": \"40000-60000\",\n" +
            "            \"value\": \"40,000 - 60, 000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"key\": \"60000-80000\",\n" +
            "            \"value\": \"60,000 - 80,000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"key\": \"80000-100000\",\n" +
            "            \"value\": \"80,000 - 1,00,000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"key\": \"100000-150000\",\n" +
            "            \"value\": \"1,00,000 - 1,50,000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"key\": \"150000-9999999999999999999999999\",\n" +
            "            \"value\": \"Above 1,50,000\"\n" +
            "        }]";

    private ArrayList<ParameterApiModel.KeyValueModel> kmlist = new ArrayList<>();

    public static BuyCarFilterKmRangeFragment newInstance(BuyCarFilterModel buyCarFilterModel) {
        BuyCarFilterKmRangeFragment fragment = new BuyCarFilterKmRangeFragment();
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
        kmlist = getKeyValueModelList(kms);
        BaseRecyclerAdapter<ParameterApiModel.KeyValueModel, BuyCarFilterPriceRangeViewHolder> serchNavigation = new BaseRecyclerAdapter<>(getActivity(), kmlist, this, BuyCarFilterPriceRangeViewHolder.class, R.layout.item_price_range);
        CommonUtils.recyclerView(recyclerView, getActivity(), true).setAdapter(serchNavigation);
        return view;
    }


    @Override
    public void onBind(final BuyCarFilterPriceRangeViewHolder holder, final int position) {
        holder.checkBox.setText(kmlist.get(position).getValue());

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(buyCarFilterModel.getKmRange().contains(kmlist.get(position).getKey()));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !buyCarFilterModel.getKmRange().contains(kmlist.get(position).getKey()))
                    buyCarFilterModel.getKmRange().add(kmlist.get(position).getKey());
                else
                    buyCarFilterModel.getKmRange().remove(kmlist.get(position).getKey());
            }
        });

    }

    private static ArrayList<ParameterApiModel.KeyValueModel> getKeyValueModelList(String json) {

        Type listType = new TypeToken<List<ParameterApiModel.KeyValueModel>>() {}.getType();

        if (json==null) {
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(json, listType);
        }
    }
}

