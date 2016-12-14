package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.provider.citytable.CitytableCursor;

/**
 * Created by priyarawat on 9/6/16.
 */
public class CityCursorAdapter extends CursorAdapter{


    Context context;
    Cursor mCursor;
    LayoutInflater inflater;
    MakeViewHolder mViewHolder;

    public CityCursorAdapter(Context context, Cursor mCursor) {
        super(context, mCursor, false);
        this.context = context;
        this.mCursor = mCursor;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cursor_list_item, parent, false);
        mViewHolder = new MakeViewHolder();
        mViewHolder.tv_Name = (TextView) view.findViewById(R.id.tv_Name);

        view.setTag(mViewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CitytableCursor modeltableCursor = new CitytableCursor(cursor);
        mViewHolder = (MakeViewHolder) view.getTag();
        mViewHolder.tv_Name.setText(modeltableCursor.getCityName());
    }
}
