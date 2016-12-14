package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.provider.notificationtable.NotificationtableCursor;
import com.gaadi.pratnerapps.utils.Utils;

import org.w3c.dom.Text;

/**
 * Created by vinodtakhar on 26/5/16.
 */
public class NotificationAdapter extends RecyclerViewAdapter<NotificationAdapter.ViewHolder>{
    private Context context;

    public NotificationAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        NotificationtableCursor notificationtableCursor = new NotificationtableCursor(cursor);

        if(!TextUtils.isEmpty(notificationtableCursor.getTitle())) {
            viewHolder.tvTitle.setVisibility(View.VISIBLE);
            viewHolder.tvTitle.setText(notificationtableCursor.getTitle());
        }else{
            viewHolder.tvTitle.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(notificationtableCursor.getDescription())) {
            viewHolder.tvDescription.setVisibility(View.VISIBLE);
            viewHolder.tvDescription.setText(notificationtableCursor.getDescription());
        }else{
            viewHolder.tvDescription.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(notificationtableCursor.getSecondaryText())) {
            viewHolder.tvSecondaryText.setVisibility(View.VISIBLE);
            viewHolder.tvSecondaryText.setText(notificationtableCursor.getSecondaryText());
        }else{
            viewHolder.tvSecondaryText.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(notificationtableCursor.getImageUrl())) {
            viewHolder.ivImage.setVisibility(View.VISIBLE);
            Utils.loadImage(context,notificationtableCursor.getImageUrl(),viewHolder.ivImage);
        }else{
            viewHolder.ivImage.setVisibility(View.GONE);
        }

        viewHolder.tvDate.setText(Utils.getRelativeDate(notificationtableCursor.getDate()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle,tvDescription,tvDate,tvSecondaryText;
        private ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
            tvSecondaryText = (TextView)itemView.findViewById(R.id.tvSecondaryText);

            ivImage = (ImageView)itemView.findViewById(R.id.ivImage);
        }
    }
}
