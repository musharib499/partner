package com.gaadi.pratnerapps.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.NotificationAdapter;
import com.gaadi.pratnerapps.adapter.TestimonialAdapter;
import com.gaadi.pratnerapps.provider.notificationtable.NotificationtableColumns;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableColumns;
import com.gaadi.pratnerapps.utils.Logger;

/**
 * Created by vinodtakhar on 27/5/16.
 */
public class NotificationFragment extends PartnerBaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = NotificationFragment.class.getName();

    private RecyclerView mRecycler;
    private TextView mEmptyText;
    private NotificationAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view,null);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecycler.setNestedScrollingEnabled(false);
        mEmptyText = (TextView) view.findViewById(R.id.tvEmptyview);

        mEmptyText.setText("Nothing to show");

        getFragmentAdapter().setTitleMessage(getString(R.string.notification),false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecycler.setLayoutManager(layoutManager);

        //setup loader
        mAdapter = new NotificationAdapter(getContext(),null);

        mRecycler.setAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), NotificationtableColumns.CONTENT_URI,null,
                null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        Logger.e(TAG,"count:"+data.getCount());

        mAdapter.changeCursor(data);

        if (data.getCount() == 0) {
            mEmptyText.setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.GONE);
        } else {
            mEmptyText.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }
}

