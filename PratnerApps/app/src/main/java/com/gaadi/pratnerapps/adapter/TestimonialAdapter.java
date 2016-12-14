package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableCursor;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.Utils;

/**
 * Created by vinodtakhar on 27/5/16.
 */
public class TestimonialAdapter extends RecyclerViewAdapter<TestimonialAdapter.ViewHolder>{
    private Context context;
    private String TAG = this.getClass().getName();

    public TestimonialAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        TestimonialtableCursor testimonialtableCursor = new TestimonialtableCursor(cursor);

        Logger.e(TAG,"Name:"+testimonialtableCursor.getName());
        Logger.e(TAG,"Testimonial:"+testimonialtableCursor.getTestimonial());

        if(testimonialtableCursor.getName()!=null && !testimonialtableCursor.getName().trim().equals("")) {
            viewHolder.tvName.setText(testimonialtableCursor.getName());
        }
        viewHolder.tvTestimonial.setText(testimonialtableCursor.getTestimonial());

        Utils.setCircularImageView(context,testimonialtableCursor.getImageUrl(),viewHolder.ivImageView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.testimonial_list_item, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvTestimonial;
        private ImageView ivImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvTestimonial = (TextView)itemView.findViewById(R.id.tvTestimonial);
            ivImageView = (ImageView) itemView.findViewById(R.id.ivIcon);
        }
    }
}

