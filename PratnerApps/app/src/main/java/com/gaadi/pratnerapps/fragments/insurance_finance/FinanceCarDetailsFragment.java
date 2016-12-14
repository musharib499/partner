package com.gaadi.pratnerapps.fragments.insurance_finance;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.Toast;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.MakeCursorAdapter;
import com.gaadi.pratnerapps.adapter.ModelCursorAdapter;
import com.gaadi.pratnerapps.adapter.VersionCursorAdapter;
import com.gaadi.pratnerapps.annotations.Required;
import com.gaadi.pratnerapps.annotations.TextRule;
import com.gaadi.pratnerapps.annotations.rules.Rule;
import com.gaadi.pratnerapps.annotations.rules.Validator;
import com.gaadi.pratnerapps.events.CancelMakeEvent;
import com.gaadi.pratnerapps.events.CancelModelEvent;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableColumns;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.CustomMaterialAutoCompleteTxtVw;
import com.gaadi.pratnerapps.utils.MakeModelType;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by priyarawat on 3/8/16.
 */
public class FinanceCarDetailsFragment extends PartnerBaseFragment implements View.OnClickListener, Validator.ValidationListener, AdapterView.OnItemClickListener{
    public static final String EXTRA_REQUEST_CODE  = "extra_request_code";
    protected static final String PARAM_MONTH = "month";
    protected static final String PARAM_YEAR = "year";
    private static final String PARAM_REGPLACE = "regplace";
    protected static final String PARAM_MAKE = "make";
    private static final String PARAM_MAKEID = "makeId";
    protected static final String PARAM_MODEL = "model";
    private static final String PARAM_MODELID = "modelId";
    protected static final String PARAM_VERSION = "version";
    private static final String PARAM_VERSIONID = "versionId";
    private static final String PARAM_FUEL = "fuel";
    private static final String PARAM_KMS = "km";
    private static final String PARAM_PRICEFROM = "pricefrom";
    private Button btnContinue;
    private FragmentCallback fragmentCallback;
    private int requestCode;
    Context mContext;
    ModelCursorAdapter modelCursorAdapter;
    String makeId = "", modelId = "", versionId = "", selectedMonth = "";
    Cursor versionCursor;
    ArrayList<ParameterApiModel.KeyValueModel> makeMonthsList;
    Validator mValidator;

    @Required(order = 1, messageResId = R.string.error_make_is_required)
    @TextRule(order = 2, minLength = 1, messageResId = R.string.error_make_is_required)
    CustomMaterialAutoCompleteTxtVw tv_make;
    @Required(order = 3, messageResId = R.string.error_model_required)
    @TextRule(order = 4, minLength = 1, messageResId = R.string.error_model_required)
    CustomMaterialAutoCompleteTxtVw tv_model;
    @Required(order = 5, messageResId = R.string.error_model_version_required)
    @TextRule(order = 6, minLength = 1, messageResId = R.string.error_model_version_required)
    EditText tv_variant;
    @Required(order = 7, messageResId = R.string.error_year_is_required)
    EditText tv_makeYear;
    @Required(order = 8, messageResId = R.string.error_month_is_required)
    EditText tv_makeMonth;
    FilterQueryProvider makeQueryProvider = new FilterQueryProvider() {
        @Override
        public Cursor runQuery(CharSequence constraint) {
            return mContext.getContentResolver().query(MaketableColumns.CONTENT_URI, null, MaketableColumns.MAKE_NAME + " like ?", new String[]{constraint.toString() + "%"}, null);
        }
    };
    FilterQueryProvider modelQueryProvider = new FilterQueryProvider() {
        @Override
        public Cursor runQuery(CharSequence constraint) {
            return mContext.getContentResolver().query(ModeltableColumns.CONTENT_URI, null, ModeltableColumns.MAKE_ID + "=? and " + ModeltableColumns.MODEL_NAME + " like ?", new String[]{makeId, constraint.toString() + "%"}, null);
        }
    };
    private MakeCursorAdapter makeCursorAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        requestCode = args.getInt(EXTRA_REQUEST_CODE);
    }

    public int getRequestCode() {
        return requestCode;
    }

    public FinanceCarDetailsFragment setFragmentCallback(FragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
        return this;
    }

    public FragmentCallback getFragmentCallback() {
        return fragmentCallback;
    }

    protected  int getLayoutId(){
        return R.layout.finance_car_details;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
        tv_make = (CustomMaterialAutoCompleteTxtVw) view.findViewById(R.id.tv_make);
        tv_model = (CustomMaterialAutoCompleteTxtVw) view.findViewById(R.id.tv_model);
        tv_variant = (EditText) view.findViewById(R.id.tv_version);
        tv_makeYear = (EditText) view.findViewById(R.id.tv_makeYear);
        tv_makeMonth = (EditText) view.findViewById(R.id.tv_makeMonth);
        btnContinue = (Button) view.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValidator.validate();
            }
        });

        makeCursorAdapter = new MakeCursorAdapter(mContext,null);

        tv_make.setAdapter(makeCursorAdapter);
        tv_make.setType(MakeModelType.MAKE);
        tv_make.setThreshold(1);

        makeCursorAdapter.setFilterQueryProvider(makeQueryProvider);
        tv_make.setOnItemClickListener(this);
        modelCursorAdapter = new ModelCursorAdapter(mContext, null);
        tv_model.setAdapter(modelCursorAdapter);
        tv_model.setThreshold(1);
        tv_model.setType(MakeModelType.MODEL);
        tv_model.setOnItemClickListener(this);
        modelCursorAdapter.setFilterQueryProvider(modelQueryProvider);
        tv_variant.setOnClickListener(this);
        tv_makeYear.setOnClickListener(this);
        tv_makeMonth.setOnClickListener(this);
        makeMonthsList = ObjectTableUtil.getMonths(mContext);

        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof MakeCursorAdapter) {
            tv_model.setText("");
            tv_variant.setText("");
            Cursor cursor = (Cursor) tv_make.getAdapter().getItem(position);
            makeId = cursor.getString(cursor.getColumnIndex(MaketableColumns.MAKE_ID));
            tv_make.setText(cursor.getString(cursor.getColumnIndex(MaketableColumns.MAKE_NAME)));
            tv_model.setEnabled(true);
        } else if (parent.getAdapter() instanceof ModelCursorAdapter) {
            tv_variant.setText("");
            Cursor cursor = (Cursor) tv_model.getAdapter().getItem(position);
            modelId = cursor.getString(cursor.getColumnIndex(ModeltableColumns.MODEL_ID));
            tv_model.setText(cursor.getString(cursor.getColumnIndex(ModeltableColumns.MODEL_NAME)));
            tv_variant.setEnabled(true);
            versionCursor = mContext.getContentResolver().query(VersiontableColumns.CONTENT_URI, null, VersiontableColumns.MODEL_ID + " =?", new String[]{modelId}, null);

        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_version:
                final ListPopupWindow popUpWindow = new ListPopupWindow(mContext);
                popUpWindow.setAnchorView(tv_variant);
                popUpWindow.setModal(true);
                popUpWindow.setAdapter(new VersionCursorAdapter(mContext, versionCursor));
                popUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cursor cursor1 = (Cursor) parent.getAdapter().getItem(position);
                        tv_variant.setText(cursor1.getString(cursor1.getColumnIndex(VersiontableColumns.VERSION_NAME)));
                        versionId = cursor1.getString(cursor1.getColumnIndex(VersiontableColumns.VERSION_ID));
                        tv_variant.setError(null);
                        popUpWindow.dismiss();
                    }
                });


                tv_variant.post(new Runnable() {
                    @Override
                    public void run() {
                        popUpWindow.show();
                    }
                });
                break;

            case R.id.tv_makeYear:
                final ListPopupWindow yearPopUpWindow = new ListPopupWindow(mContext);
                yearPopUpWindow.setAnchorView(tv_makeYear);
                yearPopUpWindow.setModal(true);
                ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(mContext, android.R.layout.simple_list_item_1, Utils.getMakeYearList());
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                yearPopUpWindow.setAdapter(dataAdapter);
                yearPopUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv_makeYear.setText((Integer) parent.getAdapter().getItem(position) + "");
                        tv_makeYear.setError(null);

                        yearPopUpWindow.dismiss();
                    }
                });


                tv_makeYear.post(new Runnable() {
                    @Override
                    public void run() {
                        yearPopUpWindow.show();
                    }
                });


                break;

            case R.id.tv_makeMonth:
                final ListPopupWindow monthPopUpWindow = new ListPopupWindow(mContext);
                monthPopUpWindow.setAnchorView(tv_makeMonth);
                monthPopUpWindow.setModal(true);

               int position=0;

                if (tv_makeYear.getText().toString().trim().equals(""+Utils.getMakeYearList()[0]))
                {
                    Calendar cal=Calendar.getInstance();
                    position=cal.get(Calendar.MONTH) + 1;

                }else
                {
                    position=makeMonthsList.size();
                }
                ArrayAdapter<ParameterApiModel.KeyValueModel> dataAdptr = new ArrayAdapter<ParameterApiModel.KeyValueModel>(mContext, android.R.layout.simple_list_item_1, makeMonthsList.subList(0,position));
                dataAdptr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                monthPopUpWindow.setAdapter(dataAdptr);
                monthPopUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv_makeMonth.setText(makeMonthsList.get(position).getValue());
                        selectedMonth = makeMonthsList.get(position).getKey();
                        tv_makeMonth.setError(null);
                        monthPopUpWindow.dismiss();

                    }
                });


                tv_makeMonth.post(new Runnable() {
                    @Override
                    public void run() {
                        monthPopUpWindow.show();
                    }
                });
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        getFragmentCallback().onContinue(requestCode,loadHashmap());
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {

        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(failedRule.getFailureMessage());
            CommonUtils.shakeView(mContext, failedView);
            return;
        }

        View v = (View) failedView.getParent();
        Toast.makeText(mContext, failedRule.getFailureMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewValidationSucceeded(View succeededView) {

    }
    protected HashMap<String,String> loadHashmap()
    {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put(PARAM_MAKE, tv_make.getText().toString());
        paramsMap.put(PARAM_MODEL, tv_model.getText().toString());
        paramsMap.put(PARAM_VERSION, tv_variant.getText().toString());
        paramsMap.put(PARAM_MONTH, selectedMonth);
        paramsMap.put(PARAM_YEAR, tv_makeYear.getText().toString());


        return  paramsMap;
    }
    @Subscribe
    public void cancelMakeEvent(CancelMakeEvent event)
    {
        tv_model.setText("");
        tv_variant.setText("");
        tv_model.setEnabled(false);
        tv_variant.setEnabled(false);
    }
    @Subscribe
    public void cancelModelEvent(CancelModelEvent event)
    {
        tv_variant.setText("");
        tv_variant.setEnabled(false);
    }

}
