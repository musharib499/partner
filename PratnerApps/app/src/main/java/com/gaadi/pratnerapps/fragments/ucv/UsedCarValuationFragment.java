package com.gaadi.pratnerapps.fragments.ucv;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.BasicListItemAdapter;
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
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.BasicListItemModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.models.UCVResponseModel;
import com.gaadi.pratnerapps.models.UsedCarValuactionModel;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableColumns;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.CustomMaterialAutoCompleteTxtVw;
import com.gaadi.pratnerapps.utils.DatabaseUtil;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.MakeModelType;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*author musharib ali*/
public class UsedCarValuationFragment extends PartnerBaseFragment implements AdapterView.OnItemClickListener ,View.OnClickListener,Validator.ValidationListener {

    private static final String PARAM_CITY = "city";
    private static final String PARAM_API_KERY = "apikey";
    private static final String PARAM_OUTPUT = "output";
    private static final String PARAM_API_SOURCE = "api_source";
    private static final String PARAM_API_SUB_SOURCE = "api_sub_source";
    private static final String PARAM_YEAR = "year";
    private static final String PARAM_METHOD = "method";
    private static final String PARAM_COLOR = "colour";
    private static final String PARAM_MAKE = "make";
    private static final String PARAM_MODEL = "model";
    private static final String PARAM_VERSION = "variant";
    private static final String PARAM_KMS = "kms";
    private static final String PARAM_CAR_ID = "car_id";
    private static final String PARAM_OWNER = "owner";
    private static final String PARAM_SEllER_TYPE = "seller_type";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Required(order = 1, messageResId = R.string.error_make_is_required)
    @TextRule(order = 2, minLength = 1, messageResId = R.string.error_make_is_required)
    CustomMaterialAutoCompleteTxtVw tv_make;
    @Required(order = 3, messageResId = R.string.error_model_required)
    @TextRule(order = 4, minLength = 1, messageResId = R.string.error_model_required)
    CustomMaterialAutoCompleteTxtVw tv_model;
    @Required(order = 5, messageResId = R.string.error_model_version_required)
    @TextRule(order = 6, minLength = 1, messageResId = R.string.error_model_version_required)
    EditText tv_variant;
    @Required(order = 7, messageResId = R.string.error_registration_place_is_required)
    @TextRule(order = 8, minLength = 1, messageResId = R.string.error_registration_place_is_required)
    AutoCompleteTextView tv_regPlace;
    @Required(order = 9, messageResId = R.string.error_year_required)
    @TextRule(order = 10, minLength = 1, messageResId = R.string.error_year_required)
    EditText tv_makeYear;
    @Required(order = 11, messageResId = R.string.error_kms_driven_is_required)
    @TextRule(order = 12, minLength = 1, messageResId = R.string.error_kms_driven_is_required)
    EditText et_kmsDriven;
    @Required(order = 13, messageResId = R.string.error_num_owners_required)
    @TextRule(order = 14, minLength = 1, messageResId = R.string.error_num_owners_required)
    private EditText numOwners;

    private  Context mContext;
    private  ModelCursorAdapter modelCursorAdapter;
    private  String makeId = "", modelId = "", versionId = "", selectedMonth = "", selectedFuelType = "";
    private  String name = "", email = "", location = "", contactNum = "",city_name="";
    private Cursor versionCursor;
    private Validator mValidator;
    private Button btn_Submit;
    private ArrayList<BasicListItemModel> numOwnersList = new ArrayList<BasicListItemModel>();
    private BasicListItemModel selectedNumOwners;
    private BasicListItemAdapter numOwnerAdapter;
    private RadioGroup isSellerOrBuyerRgrp;
    private RadioButton buyer;
    private boolean checkRadiobutton = false;
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


    public UsedCarValuationFragment() {
        // Required empty public constructor
    }

    public static UsedCarValuationFragment newInstance(String param1, String param2) {
        UsedCarValuationFragment fragment = new UsedCarValuationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PartnerApplication.getEventBus().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.usedcar_valuation,null);
        setHasOptionsMenu(true);
        initializationView(view);
        return view;
    }
    
    
    public void initializationView(View view)
    {
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
        getFragmentAdapter().setTitleMessage(getString(R.string.used_car),false);
        tv_make = (CustomMaterialAutoCompleteTxtVw) view.findViewById(R.id.tv_make);
        tv_model = (CustomMaterialAutoCompleteTxtVw) view.findViewById(R.id.tv_model);
        tv_variant = (EditText) view.findViewById(R.id.tv_version);
        tv_regPlace = (AutoCompleteTextView) view.findViewById(R.id.tv_regPlace);
        tv_makeYear = (EditText) view.findViewById(R.id.tv_makeYear);
        et_kmsDriven = (EditText) view.findViewById(R.id.kmsDriven);

        numOwners = (EditText) view.findViewById(R.id.numOwners);
        numOwners.setOnClickListener(this);
        btn_Submit = (Button) view.findViewById(R.id.btn_submit);
        btn_Submit.setOnClickListener(this);
        getFragmentAdapter().setTitleMessage(getString(R.string.used_car), false);

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
        modelCursorAdapter.setFilterQueryProvider(modelQueryProvider);
        tv_model.setOnItemClickListener(this);

        tv_variant.setOnClickListener(this);
        tv_makeYear.setOnClickListener(this);
        tv_regPlace.setOnItemClickListener(this);

        cityCursorAdapter = new CityCursorAdapter(mContext, null);
        tv_regPlace.setAdapter(cityCursorAdapter);
        cityCursorAdapter.setFilterQueryProvider(citiesQueryProvider);
        tv_regPlace.setThreshold(1);

        formLists();

        numOwners.setText(numOwnerAdapter.getItem(0).getValue());
        selectedNumOwners = new BasicListItemModel(numOwnerAdapter.getItem(0).getId(), numOwnerAdapter.getItem(0).getValue());

        isSellerOrBuyerRgrp = (RadioGroup) view.findViewById(R.id.isSellerOrBuyerRgrp);
        isSellerOrBuyerRgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.buyer:
                        checkRadiobutton = false;
                        break;
                    case R.id.seller:
                        checkRadiobutton = true;
                        break;

                }
            }
        });

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

        makeMonthsList = ObjectTableUtil.getMonths(mContext);
        fuelTypeList = ObjectTableUtil.getFuel(mContext);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof MakeCursorAdapter) {
            tv_model.setText("");
            tv_variant.setText("");
            Cursor cursor = (Cursor) tv_make.getAdapter().getItem(position);
          //  Logger.d("DataMake", DatabaseUtils.dumpCursorToString(cursor));
            makeId = cursor.getString(cursor.getColumnIndex(MaketableColumns.MAKE_ID));
            tv_make.setText(cursor.getString(cursor.getColumnIndex(MaketableColumns.MAKE_NAME)));
            tv_model.setEnabled(true);
           // tv_model.setFocusable(true);
        } else if (parent.getAdapter() instanceof ModelCursorAdapter) {
            tv_variant.setText("");
            Cursor cursor = (Cursor) tv_model.getAdapter().getItem(position);
           // Logger.d("DataModel", DatabaseUtils.dumpCursorToString(cursor));
            modelId = cursor.getString(cursor.getColumnIndex(ModeltableColumns.MODEL_ID));
            tv_model.setText(cursor.getString(cursor.getColumnIndex(ModeltableColumns.MODEL_NAME)));
            tv_variant.setEnabled(true);

            versionCursor = mContext.getContentResolver().query(VersiontableColumns.CONTENT_URI, null, VersiontableColumns.MODEL_ID + " =?", new String[]{modelId}, null);

        }
        else if (parent.getAdapter() instanceof CityCursorAdapter) {
            Cursor cursor = (Cursor) tv_regPlace.getAdapter().getItem(position);
            city_name= cursor.getString(cursor.getColumnIndex(CitytableColumns.CITY_NAME));
            tv_regPlace.setText(cursor.getString(cursor.getColumnIndex(CitytableColumns.CITY_NAME)));
        }
    }

    private void formLists() {

        for (Map.Entry<String, String> entry : numOwnersMapUsedCarValuation.entrySet()) {
            BasicListItemModel listItem = new BasicListItemModel(entry.getKey(), entry.getValue());
            numOwnersList.add(listItem);
        }

        numOwnersList.trimToSize();

        numOwnerAdapter = new BasicListItemAdapter(getActivity(), numOwnersList);
    }
    public static LinkedHashMap<String, String> numOwnersMapUsedCarValuation = new LinkedHashMap<String, String>() {
        {

            put("1", "First");
            put("2", "Second");
            put("3", "Third");
            put("4", "Fourth");
            put("5", "Four +");
        }
    };
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
                ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>( mContext, android.R.layout.simple_list_item_1, Utils.getMakeYearList() );
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                yearPopUpWindow.setAdapter(dataAdapter);
                yearPopUpWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tv_makeYear.setText(parent.getAdapter().getItem(position) + "");
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

            case R.id.numOwners:
                final android.widget.ListPopupWindow numOwnersPopupWindow = new android.widget.ListPopupWindow(getActivity());
                numOwnersPopupWindow.setAdapter(numOwnerAdapter);
                numOwnersPopupWindow.setModal(true);
                numOwnersPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.abc_popup_background_mtrl_mult));
                numOwnersPopupWindow.setAnchorView(numOwners);
                numOwnersPopupWindow.setWidth(android.widget.ListPopupWindow.WRAP_CONTENT);
                numOwnersPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedNumOwners = (BasicListItemModel) parent.getAdapter().getItem(position);
                        numOwners.setText(selectedNumOwners.getValue());
                        numOwnersPopupWindow.dismiss();
                    }
                });
                numOwners.post(new Runnable() {
                    @Override
                    public void run() {
                        numOwnersPopupWindow.show();
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
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put(PARAM_CITY, city_name);
        paramsMap.put(PARAM_API_KERY, "U3WfwtzOprXi3reyqceG");
        paramsMap.put(PARAM_OUTPUT, "json");
        paramsMap.put(PARAM_METHOD, "truPriceData");
        paramsMap.put(PARAM_API_SOURCE, "gaadi");
        paramsMap.put(PARAM_API_SUB_SOURCE, "website");
        paramsMap.put(PARAM_OWNER, "1");
        paramsMap.put(PARAM_CAR_ID, "1");
        paramsMap.put(PARAM_COLOR, "1");
        paramsMap.put(PARAM_SEllER_TYPE, isSellerOrBuyerRgrp.getCheckedRadioButtonId() == R.id.buyer ? "buyer" : "seller");

        paramsMap.put(PARAM_MAKE, makeId);
        paramsMap.put(PARAM_MODEL, modelId);
        paramsMap.put(PARAM_VERSION, versionId);
        paramsMap.put(PARAM_YEAR, tv_makeYear.getText().toString());
        paramsMap.put(PARAM_KMS, et_kmsDriven.getText().toString().replace(",",""));
        showProgress(getString(R.string.progress_dialog_msg));
        RestApiCalls.UCVCall.postUcv(paramsMap).enqueue(new Callback<UCVResponseModel>() {
            @Override
            public void onResponse(Call<UCVResponseModel> call, Response<UCVResponseModel> response) {
                hideProgress();
                if (response.isSuccessful() && null != response.body() && !response.body().getStatus().equals("F")) {
                        Log.d("UCV", "" + response.body());
                        UsedCarValuactionModel value = new UsedCarValuactionModel();
                        value.setMake(tv_make.getText().toString());
                        value.setVersion(tv_variant.getText().toString());
                        value.setRegyear(tv_makeYear.getText().toString());

                        value.setCarkm(et_kmsDriven.getText().toString());
                        value.setOwner(numOwners.getText().toString());
                        value.setBuyer(isSellerOrBuyerRgrp.getCheckedRadioButtonId() == R.id.buyer ? "Buyer" : "Seller");
                        value.setCity(tv_regPlace.getText().toString());
                    getFragmentAdapter().addToBackStack(UsedCarValuationFeedBackFragment.newInstance(value,response.body().getUsedCarData()).setFragmentAdapter(getFragmentAdapter()),true);

                } else if(response.body()!=null){
                    showToast(response.body().getMessage());
                }else{
                    showToast("Something went wrong, Please try later.");
                }
                Log.e("Response", response.raw().toString());
            }

            @Override
            public void onFailure(Call<UCVResponseModel> call, Throwable t) {
                hideProgress();
                showToast(t.getMessage());
                Log.e("Response on failure", t.getMessage());
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
    public void getPersonalDetailtels(SetPersonalDetailsEvent event)
    {
        name = event.getName();
        email = event.getEmail();
        location = event.getLocation();
        contactNum = event.getContactNum();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        TextView textView = CommonUtils.OverFlowMenuText(getActivity(), "RESET", 18, menu);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // buyer.setChecked(true);
                 tv_make.setText("");

                 et_kmsDriven.setText("");
                 tv_model.setText("");
                 tv_variant.setText("");
                 tv_regPlace.setText("");
                 tv_makeYear.setText("");
                 numOwners.setText("");
                 tv_model.setEnabled(false);
                 tv_variant.setEnabled(false);
                 //tv_makeYear.setFocusable(false);
                //et_kmsDriven.setEnabled(true);
                tv_make.setFocusable(true);
                et_kmsDriven.clearFocus();
                tv_make.setCursorVisible(true);
            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        PartnerApplication.getEventBus().unregister(this);
    }

    @Subscribe
    public void cancelMakeEvent(CancelMakeEvent event) {
        tv_model.setText("");
        tv_variant.setText("");
        tv_model.setEnabled(false);
        tv_variant.setEnabled(false);
    }
    @Subscribe
    public void cancelModelEvent(CancelModelEvent event) {
        tv_variant.setText("");
        tv_variant.setEnabled(false);
    }
}
