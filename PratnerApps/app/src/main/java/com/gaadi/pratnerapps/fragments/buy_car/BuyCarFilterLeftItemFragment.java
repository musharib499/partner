package com.gaadi.pratnerapps.fragments.buy_car;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.theme.DealerTextView;

import java.util.ArrayList;


/*musharib Ali*/
public class BuyCarFilterLeftItemFragment extends PartnerBaseFragment {
    private static final String ARG_FILTER_ITEM = "filter-item";
    private OnListFragmentInteractionListener mListener;
    private ArrayList<String> filter_item = new ArrayList<String>();
    private String selectBackground;
    private String background;
    private String textColorAccent;
    private String textColor;
    private DealerTextView tvPrice,tvKm,tvMake,tvAdvance;

    public BuyCarFilterLeftItemFragment() {
    }

    public static BuyCarFilterLeftItemFragment newInstance(ArrayList<String> columnCount) {
        BuyCarFilterLeftItemFragment fragment = new BuyCarFilterLeftItemFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_FILTER_ITEM, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            filter_item = getArguments().getStringArrayList(ARG_FILTER_ITEM);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_left_item_list, container, false);
        String[] st = getResources().getStringArray(R.array.filter_left_item);

        for (int i = 0; i < st.length; i++) {
            filter_item.add(st[i]);
        }

        tvPrice = (DealerTextView) view.findViewById(R.id.tvPrice);
        tvMake = (DealerTextView) view.findViewById(R.id.tvMake);
        tvKm = (DealerTextView) view.findViewById(R.id.tvKm);
        tvAdvance = (DealerTextView) view.findViewById(R.id.tvAdvance);
     /*   ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.fragment_filter_left_item,R.id.tv_type,st);
        rcvList.setAdapter(stringArrayAdapter);
        rcvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(position);
                }
            }
        });*/

        selectBackground  = ((PartnerApplication) getContext().getApplicationContext()).getThemeProperty("background_color");
        background = ((PartnerApplication) getContext().getApplicationContext()).getThemeProperty("grid_background_color");
        textColorAccent = ((PartnerApplication) getContext().getApplicationContext()).getThemeProperty("accent_color");
        textColor = ((PartnerApplication) getContext().getApplicationContext()).getThemeProperty("subtitle_color");

        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTvPrice();
            }
        });
        tvKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTvKm();
            }
        });
        tvMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(selectBackground)) {
                    tvMake.setBackgroundColor(Color.parseColor(selectBackground));

                    if (!TextUtils.isEmpty(textColorAccent))
                        tvMake.setTextColor(Color.parseColor(textColorAccent));
                }


                if (!TextUtils.isEmpty(background)) {
                    tvPrice.setBackgroundColor(Color.parseColor(background));
                     if (!TextUtils.isEmpty(textColor))
                        tvPrice.setTextColor(Color.parseColor(textColor));
                }

                if (!TextUtils.isEmpty(background)) {
                    tvAdvance.setBackgroundColor(Color.parseColor(background));
                     if (!TextUtils.isEmpty(textColor))
                        tvAdvance.setTextColor(Color.parseColor(textColor));
                }

                if (!TextUtils.isEmpty(background)) {
                    tvKm.setBackgroundColor(Color.parseColor(background));
                    if (!TextUtils.isEmpty(textColor))
                        tvKm.setTextColor(Color.parseColor(textColor));
                }

                if (null != mListener) {
                    mListener.onListFragmentInteraction(2);
                }
            }
        });
        tvAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(selectBackground)) {
                    tvAdvance.setBackgroundColor(Color.parseColor(selectBackground));
                    if (!TextUtils.isEmpty(textColorAccent))
                        tvAdvance.setTextColor(Color.parseColor(textColorAccent));
                }

                if (!TextUtils.isEmpty(background)) {
                    tvPrice.setBackgroundColor(Color.parseColor(background));
                     if (!TextUtils.isEmpty(textColor))
                        tvPrice.setTextColor(Color.parseColor(textColor));
                }

                if (!TextUtils.isEmpty(background)) {
                    tvMake.setBackgroundColor(Color.parseColor(background));
                     if (!TextUtils.isEmpty(textColor))
                        tvMake.setTextColor(Color.parseColor(textColor));
                }

                if (!TextUtils.isEmpty(background)) {
                    tvKm.setBackgroundColor(Color.parseColor(background));
                    if (!TextUtils.isEmpty(textColor))
                        tvKm.setTextColor(Color.parseColor(textColor));
                }

                if (null != mListener) {
                    mListener.onListFragmentInteraction(3);
                }
            }
        });

//        BaseRecyclerAdapter<String, BuyCarLeftViewHolder> serchNavigation = new BaseRecyclerAdapter<>(getActivity(), filter_item, this, BuyCarLeftViewHolder.class, R.layout.fragment_filter_left_item);
//        CommonUtils.recyclerView(rcvList, getActivity(), true).setAdapter(serchNavigation);

        return view;
    }
    public void clickTvPrice(){
        if (!TextUtils.isEmpty(selectBackground)) {
            tvPrice.setBackgroundColor(Color.parseColor(selectBackground));
            if (!TextUtils.isEmpty(textColorAccent))
                tvPrice.setTextColor(Color.parseColor(textColorAccent));
        }

        if (!TextUtils.isEmpty(background)) {
            tvMake.setBackgroundColor(Color.parseColor(background));
             if (!TextUtils.isEmpty(textColor))
                tvMake.setTextColor(Color.parseColor(textColor));
        }

        if (!TextUtils.isEmpty(background)) {
            tvAdvance.setBackgroundColor(Color.parseColor(background));
             if (!TextUtils.isEmpty(textColor))
                tvAdvance.setTextColor(Color.parseColor(textColor));
        }

        if (!TextUtils.isEmpty(background)) {
            tvKm.setBackgroundColor(Color.parseColor(background));
            if (!TextUtils.isEmpty(textColor))
                tvKm.setTextColor(Color.parseColor(textColor));
        }

        if (null != mListener) {
            mListener.onListFragmentInteraction(0);
        }
    }

    public void clickTvKm(){
        if (!TextUtils.isEmpty(selectBackground)) {
            tvKm.setBackgroundColor(Color.parseColor(selectBackground));
            if (!TextUtils.isEmpty(textColorAccent))
                tvKm.setTextColor(Color.parseColor(textColorAccent));
        }

        if (!TextUtils.isEmpty(background)) {
            tvMake.setBackgroundColor(Color.parseColor(background));
            if (!TextUtils.isEmpty(textColor))
                tvMake.setTextColor(Color.parseColor(textColor));
        }

        if (!TextUtils.isEmpty(background)) {
            tvAdvance.setBackgroundColor(Color.parseColor(background));
            if (!TextUtils.isEmpty(textColor))
                tvAdvance.setTextColor(Color.parseColor(textColor));
        }

        if (!TextUtils.isEmpty(background)) {
            tvPrice.setBackgroundColor(Color.parseColor(background));
            if (!TextUtils.isEmpty(textColor))
                tvPrice.setTextColor(Color.parseColor(textColor));
        }


        if (null != mListener) {
            mListener.onListFragmentInteraction(1);
        }
    }

    public OnListFragmentInteractionListener getOnListFragmentInteractionListener() {
        return mListener;
    }

    public void setOnListFragmentInteractionListener(OnListFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*  @Override
      public void onBind(final BuyCarLeftViewHolder holder, final int position) {
          holder.tv_type.setText(filter_item.get(position).toString());

          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @TargetApi(Build.VERSION_CODES.LOLLIPOP)
              @Override
              public void onClick(View v) {
                  if (null != mListener) {
                      mListener.onListFragmentInteraction(position);
                  }
              }
          });
      }
  */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(int position);
    }
}
