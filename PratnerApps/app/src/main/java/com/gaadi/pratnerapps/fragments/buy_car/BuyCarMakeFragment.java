package com.gaadi.pratnerapps.fragments.buy_car;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.RecyclerViewAdapter;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.maketable.MaketableCursor;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableCursor;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableSelection;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.Logger;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarMakeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarMakeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = BuyCarMakeFragment.class.getName();
    private static final int LOADER_MAKE = 0;
    private static final int LOADER_MODEL = 1;
    private RecyclerView recyclerModel, recyclerMake;
    private EditText searchView;
    private ImageView ivBack;
    private LinearLayout layoutMake, layoutModel;
    private BuyCarFilterModel buyCarFilterModel;
    private MakeCursorAdapter makeCursorAdapter;
    private ModelCursorAdapter modelCursorAdapter;
    private String filterQuery = "";
    private int makeId;
    private int currentLoader = LOADER_MAKE;

    public BuyCarMakeFragment() {
    }


    public static BuyCarMakeFragment newInstance(BuyCarFilterModel buyCarFilterModel) {
        BuyCarMakeFragment fragment = new BuyCarMakeFragment();
        fragment.buyCarFilterModel = buyCarFilterModel;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_make_model, container, false);

        recyclerModel = (RecyclerView) view.findViewById(R.id.recyclerModel);
        recyclerMake = (RecyclerView) view.findViewById(R.id.recyclerMake);
        searchView = (EditText) view.findViewById(R.id.atv_search);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);

        layoutMake = (LinearLayout) view.findViewById(R.id.layoutMake);
        layoutModel = (LinearLayout) view.findViewById(R.id.layoutModel);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterQuery = s.toString();
                getLoaderManager().restartLoader(0, null, BuyCarMakeFragment.this);
            }
        });
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                   // Toast.makeText(getContext(),"Hiding keyboasrd",Toast.LENGTH_SHORT).show();
                    InputMethodManager imm=(InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToMakeView();
            }
        });

        makeCursorAdapter = new MakeCursorAdapter(getContext(), null);
        modelCursorAdapter = new ModelCursorAdapter(getContext(), null);

        CommonUtils.recyclerView(recyclerMake, getActivity(), true).setAdapter(makeCursorAdapter);
        CommonUtils.recyclerView(recyclerModel, getActivity(), true).setAdapter(modelCursorAdapter);

        getLoaderManager().initLoader(LOADER_MAKE, null, this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        currentLoader = id;

        switch (id) {
            case LOADER_MAKE:
                return new CursorLoader(getActivity(), MaketableColumns.CONTENT_URI, null,
                        MaketableColumns.MAKE_NAME + " like '" + filterQuery + "%' and "+MaketableColumns.IN_STOCK+"=1", null, null);
            default:
                return new CursorLoader(getContext(), ModeltableColumns.CONTENT_URI, null, ModeltableColumns.MAKE_ID + "=? and "+ModeltableColumns.IN_STOCK +"=1", new String[]{"" + makeId}, null);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        Logger.e(TAG, "count:" + data.getCount());

        if (currentLoader == LOADER_MAKE)
            makeCursorAdapter.changeCursor(data);
        else
            modelCursorAdapter.changeCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (currentLoader == LOADER_MAKE)
            makeCursorAdapter.changeCursor(null);
        else
            modelCursorAdapter.changeCursor(null);
    }

    private void switchToModelView(int make) {
        makeId = make;
        ArrayList<Integer> models = buyCarFilterModel.getModels().get(make);

        if (models == null) models = new ArrayList<>();

        buyCarFilterModel.getModels().put(make, models);

        layoutModel.setVisibility(View.VISIBLE);
        layoutMake.setVisibility(View.GONE);

        modelCursorAdapter.changeCursor(null);

//        animateModel();

        getLoaderManager().restartLoader(LOADER_MODEL, null, this);
    }

    private void switchToMakeView() {
        layoutModel.setVisibility(View.GONE);
        layoutMake.setVisibility(View.VISIBLE);
        getLoaderManager().restartLoader(LOADER_MAKE, null, this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

//    private void animateModel(){
//        ObjectAnimator anim = ObjectAnimator.ofFloat(layoutModel, "x", layoutModel.getX()*2, layoutModel.getX());
//        anim.setDuration((long)layoutModel.getX());
//        anim.start();
//    }

    public class MakeCursorAdapter extends RecyclerViewAdapter<MakeCursorAdapter.ViewHolder> {

        public MakeCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

            MaketableCursor maketableCursor = new MaketableCursor(cursor);

            Logger.e(TAG,"In Stock: "+maketableCursor.getInStock());

            final int make = maketableCursor.getMakeId();

            viewHolder.tvMake.setText(maketableCursor.getMakeName() + " (" + getModelCount(maketableCursor.getMakeId()) + ")");

            viewHolder.tvMake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchToModelView(make);
                }
            });
        }

        private int getModelCount(int makeId) {
            int count = 0;
            ModeltableCursor modeltableCursor = new ModeltableSelection().makeId(makeId).and().inStock(1).query(getContext());

            if (modeltableCursor != null) {
                count = modeltableCursor.getCount();
                modeltableCursor.close();
            }

            return count;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_filter_make_layout, null));
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvMake;
           // CheckBox ckSelection;

            public ViewHolder(View itemView) {
                super(itemView);

                tvMake = (TextView) itemView.findViewById(R.id.tv_make);
              //  ckSelection = (CheckBox) itemView.findViewById(R.id.ckSelecction);
            }
        }
    }

    public class ModelCursorAdapter extends RecyclerViewAdapter<ModelCursorAdapter.ViewHolder> {

        public ModelCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
            ModeltableCursor modeltableCursor = new ModeltableCursor(cursor);

            final int modelId = modeltableCursor.getModelId();
            viewHolder.ckSelection.setText(modeltableCursor.getModelName());

            viewHolder.ckSelection.setOnCheckedChangeListener(null);

            viewHolder.ckSelection.setChecked(buyCarFilterModel.getModels().get(makeId).contains(modelId));

            viewHolder.ckSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    searchView.clearFocus();
                    if (isChecked && !buyCarFilterModel.getModels().get(makeId).contains(modelId)) {
                        buyCarFilterModel.getModels().get(makeId).add(modelId);
                    }
                    else {
                        buyCarFilterModel.getModels().get(makeId).remove(Integer.valueOf(modelId));
                    }
                }
            });
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_price_range, null));
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            CheckBox ckSelection;
            TextView tvModel;

            public ViewHolder(View itemView) {
                super(itemView);


                ckSelection = (CheckBox) itemView.findViewById(R.id.cbItem);
                // tvModel = (TextView) itemView.findViewById(R.id.tvPrice);


                //ckSelection.setVisibility(View.VISIBLE);
            }
        }
    }

}
