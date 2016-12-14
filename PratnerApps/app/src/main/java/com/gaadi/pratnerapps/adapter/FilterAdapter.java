package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

import java.util.ArrayList;

/**
 * Created by root on 21/6/16.
 */
public class FilterAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<String> list;
    private LayoutInflater inflater;
    public static boolean checkSelected=false;
    public FilterAdapter(Context context,ArrayList<String> arrayList) {
        this.mContext=context;
        this.list=arrayList;
        inflater= LayoutInflater.from(this.mContext);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MyViewHolder mViewHolder;
        String selectBackground=null;
        String background=null;
        if (convertView == null) {
              selectBackground = ((PartnerApplication) mContext.getApplicationContext()).getThemeProperty("background_color");
                         convertView = inflater.inflate(R.layout.fragment_filter_left_item, viewGroup, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }


        mViewHolder.tv_type.setText(list.get(i).toString());

        if (i==0)
        {
            convertView.setActivated(true);
           /* if (selectBackground !=null)
            convertView.setBackgroundColor(Color.parseColor(selectBackground));

            checkSelected = true;
*/
        }


        return convertView;
    }

    private class MyViewHolder {
        TextView tv_type;


        public MyViewHolder(View item) {
            tv_type = (TextView) item.findViewById(R.id.tv_type);

        }
    }
}
