package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.BodyAdapter;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.utils.BodyUtils;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarBodyTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarBodyTypeFragment extends Fragment {
    private ArrayList<ParameterApiModel.KeyValueModel> bodyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BuyCarFilterModel buyCarFilterModel;

    public static BuyCarBodyTypeFragment newInstance(BuyCarFilterModel buyCarFilterModel) {
        BuyCarBodyTypeFragment fragment = new BuyCarBodyTypeFragment();
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
        View view = inflater.inflate(R.layout.fragment_body_type_blank, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        recyclerView = CommonUtils.recyclerView(recyclerView, getActivity(), true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        bodyList = ObjectTableUtil.getCarBodyTypes(getContext());
        BodyAdapter bodyAdapter=new BodyAdapter(getActivity(),bodyList,buyCarFilterModel);
        recyclerView.setAdapter(bodyAdapter);
        return view;
    }
    public static int getIcon(String key) {
        switch (key)
        {
            case BodyUtils.COUPE:
                return R.drawable.coupe;
            case BodyUtils.CONVERTIBLES:
                 return R.drawable.convertible;
            case BodyUtils.HATCHBACK:
                return R.drawable.hatchback;
            case BodyUtils.MUV:
                return R.drawable.muv;
            case BodyUtils.MINIVAN:
                return R.drawable.minivans;
            case BodyUtils.SEDAN:
                return R.drawable.sedan;
            case BodyUtils.SUV:
                return R.drawable.suv;
            default:
                return R.drawable.hybrids;
        }
    }
  /*  @Override
    public void onBind(final BuyCarBodyTypeViewHolder holder, final int position) {
        holder.tv_view.setText(bodyList.get(position).getValue());

        holder.im_view.setImageResource(getIcon(bodyList.get(position).getValue()));
        holder.view_check.setImageResource(getIcon(bodyList.get(position).getValue()));
        Utils.setTint(holder.view_check,getActivity(),"primary_color");

        if (buyCarFilterModel.getBodyTypes().contains(bodyList.get(position).getKey())) {
            holder.view_check.setVisibility(View.VISIBLE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyCarFilterModel.getBodyTypes().contains(bodyList.get(position).getKey())) {
                    buyCarFilterModel.getBodyTypes().remove(bodyList.get(position).getKey());
                    holder.view_check.setVisibility(View.GONE);
                } else {
                    buyCarFilterModel.getBodyTypes().add(bodyList.get(position).getKey());
                    holder.view_check.setVisibility(View.VISIBLE);
                }
            }
        });

    }*/
}
