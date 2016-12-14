package com.gaadi.pratnerapps.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.Toast;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.CityCursorAdapter;
import com.gaadi.pratnerapps.adapter.MakeCursorAdapter;
import com.gaadi.pratnerapps.adapter.ModelCursorAdapter;
import com.gaadi.pratnerapps.adapter.VersionCursorAdapter;
import com.gaadi.pratnerapps.annotations.Required;
import com.gaadi.pratnerapps.annotations.TextRule;
import com.gaadi.pratnerapps.annotations.rules.Rule;
import com.gaadi.pratnerapps.annotations.rules.Validator;
import com.gaadi.pratnerapps.events.CancelMakeEvent;
import com.gaadi.pratnerapps.events.CancelModelEvent;
import com.gaadi.pratnerapps.events.SetPersonalDetailsEvent;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableColumns;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.CustomMaterialAutoCompleteTxtVw;
import com.gaadi.pratnerapps.utils.MakeModelType;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyarawat on 31/5/16.
 */
public class CarDetailsFragment extends PartnerBaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener, Validator.ValidationListener {
    private static final String PARAM_NAME = "name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_CITY = "city";
    private static final String PARAM_MONTH = "month";
    private static final String PARAM_YEAR = "year";
    private static final String PARAM_REGPLACE = "regplace";
    private static final String PARAM_MAKE = "make";
    private static final String PARAM_MAKEID = "makeId";
    private static final String PARAM_MODEL = "model";
    private static final String PARAM_MODELID = "modelId";
    private static final String PARAM_VERSION = "version";
    private static final String PARAM_VERSIONID = "versionId";
    private static final String PARAM_FUEL = "fuel";
    private static final String PARAM_KMS = "km";
    private static final String PARAM_PRICEFROM = "pricefrom";

    @Required(order = 1, messageResId = R.string.error_make_is_required)
    @TextRule(order = 2, minLength = 1, messageResId = R.string.error_make_is_required)
    CustomMaterialAutoCompleteTxtVw tv_make;
    @Required(order = 3, messageResId = R.string.error_model_required)
    @TextRule(order = 4, minLength = 1, messageResId = R.string.error_model_required)
    CustomMaterialAutoCompleteTxtVw tv_model;
    @Required(order = 5, messageResId = R.string.error_model_version_required)
    @TextRule(order = 6, minLength = 1, messageResId = R.string.error_model_version_required)
    EditText tv_variant;
    @Required(order = 7, messageResId = R.string.error_model_version_required)
    @TextRule(order = 8, minLength = 1, messageResId = R.string.error_model_version_required)
    EditText tv_fuelType;
    @Required(order = 9, messageResId = R.string.error_price_is_required)
    EditText et_price;
    @Required(order = 10, messageResId = R.string.error_registration_place_is_required)
    AutoCompleteTextView tv_regPlace;
    @Required(order = 11, messageResId = R.string.error_registration_year_is_required)
    EditText tv_makeYear;
    @Required(order = 12, messageResId = R.string.error_registration_year_is_required)
    EditText tv_makeMonth;
    @Required(order = 13, messageResId = R.string.error_kms_driven_is_required)
    EditText et_kmsDriven;
  /*  @Required(order = 11, messageResId = R.string.error_city_is_required)
    MaterialAutoCompleteTextView et_city;*/
    Context mContext;
    ModelCursorAdapter modelCursorAdapter;
    String makeId = "", modelId = "", versionId = "", selectedMonth = "", selectedFuelType = "";
    String name = "", email = "", location = "", contactNum = "";
    Cursor versionCursor;
    Validator mValidator;
    Button btn_Submit;
    ArrayList<ParameterApiModel.KeyValueModel> makeMonthsList;
    ArrayList<ParameterApiModel.KeyValueModel> fuelTypeList;
    CityCursorAdapter cityCursorAdapter;
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

    FilterQueryProvider citiesQueryProvider = new FilterQueryProvider() {
        @Override
        public Cursor runQuery(CharSequence constraint) {
            return mContext.getContentResolver().query(CitytableColumns.CONTENT_URI, null, CitytableColumns.CITY_NAME +" like ?" ,new String[]{constraint.toString()+"%"},null);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.car_details_fragment_layout, container, false);
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
        ((NestedScrollView)view.findViewById(R.id.nestedScrollVw)).setNestedScrollingEnabled(false);
        tv_make = (CustomMaterialAutoCompleteTxtVw) view.findViewById(R.id.tv_make);
        tv_model = (CustomMaterialAutoCompleteTxtVw) view.findViewById(R.id.tv_model);
        tv_variant = (EditText) view.findViewById(R.id.tv_version);
        et_price = (EditText) view.findViewById(R.id.et_price);
        tv_makeYear = (EditText) view.findViewById(R.id.tv_makeYear);
        et_kmsDriven = (EditText) view.findViewById(R.id.et_kmsDriven);
        tv_makeMonth = (EditText) view.findViewById(R.id.tv_makeMonth);
        tv_regPlace = (AutoCompleteTextView) view.findViewById(R.id.tv_regPlace);
        tv_fuelType = (EditText) view.findViewById(R.id.tv_fuelType);
        tv_fuelType.setOnClickListener(this);
        btn_Submit = (Button) view.findViewById(R.id.btn_submit);
        btn_Submit.setOnClickListener(this);
        getFragmentAdapter().setTitleMessage("Sell Car", false);
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
        tv_regPlace.setOnItemClickListener(this);
        cityCursorAdapter = new CityCursorAdapter(mContext, null);
        tv_regPlace.setAdapter(cityCursorAdapter);
        cityCursorAdapter.setFilterQueryProvider(citiesQueryProvider);
        tv_regPlace.setThreshold(1);
        et_kmsDriven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.insertCommaIntoNumber(et_kmsDriven, s, "##,##,###");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.insertCommaIntoNumber(et_price, s, "##,##,###");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        makeMonthsList = ObjectTableUtil.getMonths(mContext);
        fuelTypeList = ObjectTableUtil.getFuel(mContext);

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
       else if (parent.getAdapter() instanceof CityCursorAdapter) {
            Cursor cursor = (Cursor) tv_regPlace.getAdapter().getItem(position);

            tv_regPlace.setText(cursor.getString(cursor.getColumnIndex(CitytableColumns.CITY_NAME)));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
                ArrayAdapter <Integer> dataAdapter = new ArrayAdapter<Integer>( mContext, android.R.layout.simple_list_item_1, Utils.getMakeYearList() );
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                yearPopUpWindow.setAdapter(dataAdapter);
                yearPopUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv_makeYear.setText((Integer) parent.getAdapter().getItem(position) + "");

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
                ArrayAdapter <ParameterApiModel.KeyValueModel> dataAdptr = new ArrayAdapter<ParameterApiModel.KeyValueModel>( mContext, android.R.layout.simple_list_item_1, makeMonthsList.subList(0,position) );
                dataAdptr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                monthPopUpWindow.setAdapter(dataAdptr);
                monthPopUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv_makeMonth.setText(makeMonthsList.get(position).getValue());
                        selectedMonth = makeMonthsList.get(position).getKey();
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

            case R.id.tv_fuelType:
                final ListPopupWindow fuelTypePopUpWindow = new ListPopupWindow(mContext);
                fuelTypePopUpWindow.setAnchorView(tv_fuelType);
                fuelTypePopUpWindow.setModal(true);
                ArrayAdapter <ParameterApiModel.KeyValueModel> fuelTypedataAdptr = new ArrayAdapter<ParameterApiModel.KeyValueModel>( mContext, android.R.layout.simple_list_item_1, fuelTypeList );
                fuelTypedataAdptr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                fuelTypePopUpWindow.setAdapter(fuelTypedataAdptr);
                fuelTypePopUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv_fuelType.setText(fuelTypeList.get(position).getValue());
                        selectedFuelType = fuelTypeList.get(position).getKey();
                        fuelTypePopUpWindow.dismiss();

                    }
                });


                tv_fuelType.post(new Runnable() {
                    @Override
                    public void run() {
                        fuelTypePopUpWindow.show();
                    }
                });
                break;

            case R.id.btn_submit:
                mValidator.validate();
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
//        Toast.makeText(mContext, "Validation Successful", Toast.LENGTH_LONG).show();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put(PARAM_NAME, name);
        paramsMap.put(PARAM_EMAIL, email);
        paramsMap.put(PARAM_CITY, location);
        paramsMap.put(PARAM_PHONE, contactNum);
        paramsMap.put(PARAM_MAKE, tv_make.getText().toString());
        paramsMap.put(PARAM_MAKEID, makeId);
        paramsMap.put(PARAM_MODEL, tv_model.getText().toString());
        paramsMap.put(PARAM_MODELID, modelId);
        paramsMap.put(PARAM_VERSION, tv_variant.getText().toString());
        paramsMap.put(PARAM_VERSIONID, versionId);
        paramsMap.put(PARAM_MONTH, selectedMonth);
        paramsMap.put(PARAM_YEAR, tv_makeYear.getText().toString());
        paramsMap.put(PARAM_REGPLACE, tv_regPlace.getText().toString());
        paramsMap.put(PARAM_FUEL, selectedFuelType);
        paramsMap.put(PARAM_KMS, et_kmsDriven.getText().toString().replace(",",""));
        paramsMap.put(PARAM_PRICEFROM, et_price.getText().toString().replace(",",""));

        requestProgress("","Please wait...");

        RestApiCalls.submitCarInfo(getContext(),paramsMap).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();
//                showToast(response.raw().toString());

                if (response.isSuccessful() && null != response.body()) {
                    if(response.body().isSuccess()) {
                       // showToast("Thanks for submitting details!");
                        showSubmitDialog(getResources().getString(R.string.sell_car_title_msg),
                                getResources().getString(R.string.sell_car_msg));
                        PartnerApplication.getEventBus().post("Reset");
                    }else{
                        showToast(getString(R.string.server_error_msg));
                    }
                }
                 else{
                    showToast(response.message());
                }

                Log.e("Response", response.raw().toString());
            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                hideProgress();
                //showToast(t.getMessage());
               // Log.e("Response on failure", t.getMessage());
            }
        });
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

    @Subscribe
    public void getPersonalDetails(SetPersonalDetailsEvent event)
    {
        name = event.getName();
        email = event.getEmail();
        location = event.getLocation();
        contactNum = event.getContactNum();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PartnerApplication.getEventBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PartnerApplication.getEventBus().unregister(this);
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
