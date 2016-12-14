package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.service.ServiceViewHolder;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by ashishkumar on 30/8/16.
 *
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {

    private ArrayList<ParameterApiModel.KeyValueModel> serviceList;
    private Context context;
    private ArrayList<String> selected = new ArrayList<>();
    private ArrayList<String> selectedValue = new ArrayList<>();

    public ServiceAdapter(Context context,ArrayList<ParameterApiModel.KeyValueModel> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_check, parent, false);

        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ServiceViewHolder holder, int position) {
        holder.key = serviceList.get(position).getKey();
        holder.value = serviceList.get(position).getValue();
        holder.tvService.setText(serviceList.get(position).getValue());

        Utils.loadImage(context,serviceList.get(position).getImage_url(),holder.ivService);

//        Utils.setTint(holder.ivService,context,"accent_color");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected.contains(holder.key)) {
                    holder.ivCheck.setVisibility(View.VISIBLE);
                    selected.add(holder.key);
                    selectedValue.add(holder.value);
                } else {
                    holder.ivCheck.setVisibility(View.GONE);
                    selected.remove(holder.key);
                    selectedValue.remove(holder.value);
                }
            }
        });
    }

    public ArrayList<String> getSelected() {
        return selectedValue;
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }
}
