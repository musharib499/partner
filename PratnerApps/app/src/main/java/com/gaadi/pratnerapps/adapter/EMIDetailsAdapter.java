package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.EmiModel;

import java.util.ArrayList;

/**
 * Created by ashishkumar on 3/8/16.
 */
public class EMIDetailsAdapter extends RecyclerView.Adapter<LoanDetailsViewholder> {

    private Context context;
    private ArrayList<EmiModel> list ;

    public EMIDetailsAdapter (Context context,ArrayList<EmiModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public LoanDetailsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emi_details_recycle,parent,false);
        return new LoanDetailsViewholder(view);
    }

    @Override
    public void onBindViewHolder(LoanDetailsViewholder holder, int position) {
        holder.tvMonth.setText(""+list.get(position).getMonth());
        holder.tvEMIRupees.setText(""+(int)list.get(position).getPrice()+".00");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
class LoanDetailsViewholder extends RecyclerView.ViewHolder{

    public TextView tvMonth,tvEMIRupees;

    public LoanDetailsViewholder(View itemView) {
        super(itemView);
        tvMonth = (TextView)itemView.findViewById(R.id.tvMonth);
        tvEMIRupees = (TextView)itemView.findViewById(R.id.tvEMI);
    }
}
