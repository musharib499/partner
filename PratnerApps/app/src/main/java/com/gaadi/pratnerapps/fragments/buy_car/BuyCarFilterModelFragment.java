package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.BaseRecyclerAdapter;
import com.gaadi.pratnerapps.holder.SearchNavigationHolder;
import com.gaadi.pratnerapps.utils.CommonUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarFilterModelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarFilterModelFragment extends Fragment implements BaseRecyclerAdapter.BindAdapterListener<SearchNavigationHolder> {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private RecyclerView recyclerView;


    private ArrayList<String> searchMakeModels = new ArrayList<>();

    public static BuyCarFilterModelFragment newInstance(String arg) {
        BuyCarFilterModelFragment fragment = new BuyCarFilterModelFragment();
        Bundle args = new Bundle();
        //  args.putString(ARG_PARAM1, param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_model, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);

        return view;
    }

    public void updateView(ArrayList<String> objects) {
        searchMakeModels = objects;
        BaseRecyclerAdapter<String, SearchNavigationHolder> serchNavigation = new BaseRecyclerAdapter<>(getActivity(), searchMakeModels, this, SearchNavigationHolder.class, R.layout.item_navigation_right);
        CommonUtils.recyclerView(recyclerView, getActivity(), true).setAdapter(serchNavigation);
    }


    @Override
    public void onBind(SearchNavigationHolder holder, int position) {
        holder.cb_item.setText(searchMakeModels.get(position).toString());
    }
}
