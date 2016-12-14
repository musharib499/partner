package com.gaadi.pratnerapps.fragments.insurance_finance;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FilterQueryProvider;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.CityCursorAdapter;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.UserModel;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by priyarawat on 3/8/16.
 */
public class PersonalDetailsBaseFragment extends PartnerBaseFragment implements AdapterView.OnItemClickListener{
    private static final String PARAM_NAME = "name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_CITY = "city";

    MaterialEditText et_Name, et_Email, et_ContactNum;
    MaterialAutoCompleteTextView tv_Location;
    Activity mActivity;
    int requestCode;
    public static final String EXTRA_REQUEST_CODE  = "extra_request_code";
    FilterQueryProvider citiesQueryProvider = new FilterQueryProvider() {
        @Override
        public Cursor runQuery(CharSequence constraint) {
            return mActivity.getContentResolver().query(CitytableColumns.CONTENT_URI, null, CitytableColumns.CITY_NAME +" like ?" ,new String[]{constraint.toString()+"%"},null);
        }
    };
    private CityCursorAdapter cityCursorAdapter;
    FragmentCallback fragmentCallback;

    public PersonalDetailsBaseFragment setFragmentCallback(FragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
        return this;
    }
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        requestCode = args.getInt(EXTRA_REQUEST_CODE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finance_personal_details_fragment_layout, container, false);
        Button btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    HashMap<String, String> paramsMap = new HashMap<String, String>();
                    paramsMap.put(PARAM_NAME, et_Name.getText().toString());
                    paramsMap.put(PARAM_EMAIL, et_Email.getText().toString());
                    paramsMap.put(PARAM_CITY, tv_Location.getText().toString());
                    paramsMap.put(PARAM_PHONE, et_ContactNum.getText().toString());
                    fragmentCallback.onContinue(requestCode, paramsMap);
                }
            }
        });
        et_ContactNum = (MaterialEditText) view.findViewById(R.id.et_ContactNum);
        et_Email = (MaterialEditText) view.findViewById(R.id.et_email);
        tv_Location = (MaterialAutoCompleteTextView) view.findViewById(R.id.et_location);
        tv_Location.setOnItemClickListener(this);
        cityCursorAdapter = new CityCursorAdapter(mActivity, null);
        tv_Location.setAdapter(cityCursorAdapter);
        cityCursorAdapter.setFilterQueryProvider(citiesQueryProvider);
        tv_Location.setThreshold(1);
        et_Name = (MaterialEditText) view.findViewById(R.id.et_name);
        loadData();

        return view;
    }
    public void loadData()
    {

       UserModel userModel =  ObjectTableUtil.getUserModel(getActivity());

       // Logger.e(TAG,userModel.getMobile()+"/"+userModel.getName());

        if (userModel!=null && AppPreferences.getBooleanSharedPreference(getContext(),AppPreferences.DEALER_VERIFIED,false)) {

            if (!TextUtils.isEmpty(userModel.getEmail())) {
                et_Email.setText(userModel.getEmail());
            }

            if (!TextUtils.isEmpty(userModel.getName())) {
                et_Name.setText(userModel.getName());
            }

            if (!TextUtils.isEmpty(userModel.getMobile())) {
                et_ContactNum.setText(userModel.getMobile());
            }

            if (!TextUtils.isEmpty(userModel.getCity())) {
                tv_Location.setText(userModel.getCity());
            }
        }


    }
    private boolean validate() {
        if (et_Name.getText().toString().equals("")) {
            et_Name.setError("Enter name");
            return false;
        } else if (!et_Name.getText().toString().trim().matches("[a-z A-Z]+")) {
            et_Name.setError("Enter a valid name");
            et_Name.requestFocus();
            return false;
        } else if (et_Name.getText().toString().trim().length() < 3) {
            et_Name.setError("Name should contain atleast 3 characters");
            et_Name.requestFocus();
            return false;
        }

        if (et_ContactNum.getText().toString().trim().equals("")) {
            et_ContactNum.setError("Enter mobile number");
            return false;
        }
        else if (et_ContactNum.getText().toString().trim().length() < 10)
        {
            et_ContactNum.setError("Enter valid mobile number");
            return false;
        }
        else if (!Pattern.compile("^[7-9]").matcher(et_ContactNum.getText().toString().trim()).find()) {
            et_ContactNum.setError("Enter valid mobile number");
            return false;
        }

        if (et_Email.getText().toString().trim().equals("")) {
            et_Email.setError("Enter Email ID");
            return false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_Email.getText().toString().trim()).matches()) {
            et_Email.setError("Enter valid email ID");
            return false;
        }

        if(tv_Location.getText().toString().trim().equals(""))
        {
            tv_Location.setError("Enter location");
            return false;
        }

        return true;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mActivity = getActivity();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof CityCursorAdapter) {
            Cursor cursor = (Cursor) tv_Location.getAdapter().getItem(position);
            tv_Location.setText(cursor.getString(cursor.getColumnIndex(CitytableColumns.CITY_NAME)));
        }
    }


}
