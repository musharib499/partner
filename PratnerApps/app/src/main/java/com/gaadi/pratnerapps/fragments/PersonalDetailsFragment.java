package com.gaadi.pratnerapps.fragments;

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

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.CityCursorAdapter;
import com.gaadi.pratnerapps.events.PerformClickEvent;
import com.gaadi.pratnerapps.events.SetPersonalDetailsEvent;
import com.gaadi.pratnerapps.events.SwitchFragment;
import com.gaadi.pratnerapps.models.UserModel;
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.otto.Subscribe;

import java.util.regex.Pattern;

/**
 * Created by priyarawat on 31/5/16.
 */
public class PersonalDetailsFragment extends PartnerBaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button btnContinue;
    MaterialEditText et_Name, et_Email, et_ContactNum;
    MaterialAutoCompleteTextView tv_Location;
    Activity mActivity;
    FilterQueryProvider citiesQueryProvider = new FilterQueryProvider() {
        @Override
        public Cursor runQuery(CharSequence constraint) {
            return mActivity.getContentResolver().query(CitytableColumns.CONTENT_URI, null, CitytableColumns.CITY_NAME +" like ?" ,new String[]{constraint.toString()+"%"},null);
        }
    };
    private CityCursorAdapter cityCursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_details_fragment_layout, container, false);
        btnContinue = (Button) view.findViewById(R.id.btn_continue);
        et_ContactNum = (MaterialEditText) view.findViewById(R.id.et_ContactNum);
        et_Email = (MaterialEditText) view.findViewById(R.id.et_email);
        tv_Location = (MaterialAutoCompleteTextView) view.findViewById(R.id.et_location);
        tv_Location.setOnItemClickListener(this);
        getFragmentAdapter().setTitleMessage("Sell Car", false);
        cityCursorAdapter = new CityCursorAdapter(mActivity, null);
        tv_Location.setAdapter(cityCursorAdapter);
        cityCursorAdapter.setFilterQueryProvider(citiesQueryProvider);
        tv_Location.setThreshold(1);
        et_Name = (MaterialEditText) view.findViewById(R.id.et_name);
        btnContinue.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_continue:
                if(validate())
                {
                    //Toast.makeText(mActivity, "Form Submission Successful", Toast.LENGTH_LONG).show();
                    PartnerApplication.getEventBus().post(new SetPersonalDetailsEvent(et_Name.getText().toString(), et_Email.getText().toString(), et_ContactNum.getText().toString(), tv_Location.getText().toString()));
                    PartnerApplication.getEventBus().post(new SwitchFragment(true));

                }
                else
                {
                    PartnerApplication.getEventBus().post(new SwitchFragment(false));
                }

                break;
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
    public void performClickEvent(PerformClickEvent event)
    {
        btnContinue.performClick();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if (parent.getAdapter() instanceof CityCursorAdapter) {
            Cursor cursor = (Cursor) tv_Location.getAdapter().getItem(position);
           tv_Location.setText(cursor.getString(cursor.getColumnIndex(CitytableColumns.CITY_NAME)));
        }
    }
}
