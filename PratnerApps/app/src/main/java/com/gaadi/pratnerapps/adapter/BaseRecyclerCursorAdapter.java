package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vinodtakhar on 1/6/16.
 */
public class BaseRecyclerCursorAdapter<T extends RecyclerView.ViewHolder> extends RecyclerViewAdapter<T> {

    private static final String TAG = "BaseRecyclerCursorAdapter";
    private Context mContext;
    private Cursor cursor;
    private BindAdapterCursorListener mListener;
    private T mHolder;
    private Class<T> mHolderClass;
    private int layoutId;

    public BaseRecyclerCursorAdapter(Context context, Cursor cursor, BindAdapterCursorListener listener, Class<T> holderClass, int layoutId) {
        super(context,cursor);
        mContext = context;
        this.cursor = cursor;
        mHolderClass = holderClass;
        mListener = listener;
        this.layoutId = layoutId;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            return mHolderClass.getConstructor(View.class).newInstance(LayoutInflater.from(mContext).inflate(layoutId, parent, false));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, Cursor cursor) {
        mListener.onBind(holder, cursor);
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public interface BindAdapterCursorListener<T> {
        void onBind(T holder, Cursor cursor);
    }
}
