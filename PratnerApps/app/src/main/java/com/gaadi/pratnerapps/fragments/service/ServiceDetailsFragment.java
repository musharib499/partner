package com.gaadi.pratnerapps.fragments.service;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.annotations.Email;
import com.gaadi.pratnerapps.annotations.FirstCharValidation;
import com.gaadi.pratnerapps.annotations.MobileNumber;
import com.gaadi.pratnerapps.annotations.Required;
import com.gaadi.pratnerapps.annotations.TextRule;
import com.gaadi.pratnerapps.annotations.rules.Rule;
import com.gaadi.pratnerapps.annotations.rules.Validator;
import com.gaadi.pratnerapps.fragments.PartnerBaseLocationFragment;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceDetailsFragment extends PartnerBaseLocationFragment implements View.OnClickListener,Validator.ValidationListener {
    private static final String EXTRA_SELECTED_SERVICES = "extra_selected_services";
    private static final String EXTRA_SHOW_PICKUP = "extra_show_pickup";

    public static final String START_DATE = "startdate";
    public static final String END_DATE = "enddate";
    public static final String ADDRESS = "address";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL ="email" ;
    public static final String PICK_UP_WANT = "pickupwant";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String SERVICE_TYPE = "service_type";

    private String selectedServices;
    private String showPickUp;
    private int pYear;
    private int pMonth;
    private int pDay;
    private int hour;
    private int minute;
    @Required(order = 1, messageResId = R.string.error_date_required)
    private EditText etDate;
    @Required(order = 2, messageResId = R.string.error_time_required)
    private EditText etTime;
    @Required(order = 3, messageResId = R.string.error_address_required)
    private EditText etLocation;
    @Required(order = 4, messageResId = R.string.error_name_required)
    @TextRule(order = 5, minLength = 3, messageResId = R.string.error_name_min)
    private EditText etName;
    @Required(order = 6, messageResId = R.string.error_phone_required)
    @TextRule(order = 7, minLength = 10, message = "invalid phone number")
    @MobileNumber(order = 8, minLength = 10, maxLength = 10, message = "invalid phone number")
    @FirstCharValidation(order = 9, notAllowedFirstChars = "0,1,2,3,4,5,6", message = "invalid phone number")
    private EditText etPhoneNumber;
    @Required(order = 10, messageResId = R.string.error_email_required)
    @Email(order = 11, minLength = 8, message = "invalid email id")
    private EditText etEmail;

    private RadioGroup radioGroup;
    private TextView tvDetails;
    private Button btnSave;
    private LinearLayout llPickUp;
    private Validator mValidator;
    private Context mContext;


    public ServiceDetailsFragment() {
    }

    private TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int min) {
            minute = min;
            hour = hourOfDay;
            updateTime(hour, minute);
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            pYear = selectedYear;
            pMonth = selectedMonth;
            pDay = selectedDay;

            // set selected date into textview
            updateDisplay();


        }
    };

    public static ServiceDetailsFragment newInstance(ArrayList<String> selected,String showPickUp) {
        ServiceDetailsFragment fragment = new ServiceDetailsFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_SELECTED_SERVICES, Utils.getStringSeparatedBullet(selected.iterator()));
        args.putString(EXTRA_SHOW_PICKUP, showPickUp);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            selectedServices = getArguments().getString(EXTRA_SELECTED_SERVICES);
            showPickUp = getArguments().getString(EXTRA_SHOW_PICKUP);
        }

        setDatePicker();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_service_details, container, false);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
        llPickUp = (LinearLayout) view.findViewById(R.id.llPickUp);
        etDate = (EditText) view.findViewById(R.id.etDatePick);
        etTime = (EditText) view.findViewById(R.id.etTimePick);
        etLocation = (EditText) view.findViewById(R.id.etLocation);
        tvDetails = (TextView) view.findViewById(R.id.tvDetails);
        etName = (EditText) view.findViewById(R.id.etName);
        etPhoneNumber = (EditText) view.findViewById(R.id.etContact);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        btnSave = (Button) view.findViewById(R.id.btn_continue);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        etTime.setOnClickListener(this);
        etDate.setOnClickListener(this);
        etLocation.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        if (showPickUp != null && showPickUp.equalsIgnoreCase("Yes")){
            llPickUp.setVisibility(View.VISIBLE);
            etLocation.setVisibility(View.VISIBLE);
            tvDetails.setText(getString(R.string.pick_up_details));
        }else {
            tvDetails.setText(getString(R.string.service_timing));
        }

//        if (TextUtils.isEmpty(etName.getText().toString()))
//            etName.setError(null);
//
//        if (TextUtils.isEmpty(etPhoneNumber.getText().toString()))
//            etPhoneNumber.setError(null);
//
//        if (TextUtils.isEmpty(etEmail.getText().toString()))
//            etEmail.setError(null);
//
//        if (TextUtils.isEmpty(etLocation.getText().toString()))
//            etLocation.setError(null);


        etLocation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etLocation.getRight() - etLocation.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        showProgress("Loading...").setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                                    hideProgress();
                                    return true;
                                }
                                return false;
                            }
                        });
                        requestLocation();
                        return true;
                    }
                }
                return false;
            }
            });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioYes:
                        tvDetails.setText(getString(R.string.pick_up_details));
                        if (etLocation.getVisibility() == View.GONE)
                            etLocation.setVisibility(View.VISIBLE);

                        break;
                    case R.id.radioNo:
                        etLocation.setVisibility(View.GONE);
                        tvDetails.setText(getString(R.string.service_timing));
                        break;
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        getFragmentAdapter().setTitleMessage("Book Services", false);
    }

    public void setDatePicker() {
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);
    }


    private void updateDisplay() {
        pMonth = pMonth + 1; // Month is 0 based so add 1
        etDate.setText(
                new StringBuilder()
                        .append(pDay < 10 ? "0" + pDay : pDay).append("-")
                        .append(pMonth < 10 ? "0" + pMonth : pMonth).append("-")
                        .append(pYear));
        etDate.setError(null);
    }

    private void updateTime(int hours, int mins) {

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        String aTime = new StringBuilder().append(hours < 10 ? "0" + hours : hours).append(':')
                .append(minutes)/*.append(":").append("00")*/.toString().trim();

        etTime.setText(Utils.convertion24to12hrs(aTime));
        etTime.setError(null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etDatePick:
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, datePickerListener,
                        pYear, pMonth, pDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                return;
            case R.id.etTimePick:
                new TimePickerDialog(getActivity(), R.style.DialogTheme, onTimeSetListener, hour, minute, false).show();
                return;
            case R.id.btn_continue:
                mValidator.validate();
        }

    }


    @Override
    public void onAddressAvailable(String address) {
        if(!TextUtils.isEmpty(address))
            etLocation.setText(address);

        hideProgress();

    }

    @Override
    public void onFailedToFetchAddress(String reason) {
        Logger.d("Location service ",reason);
        hideProgress();
    }

    private String toTwoDigits(int val){
        return val>9?String.valueOf(val):"0"+val;
    }

    private String getDateTime(){
        StringBuilder builder = new StringBuilder();
        builder.append(pYear).append("-").append(toTwoDigits(pMonth)).append("-").append(toTwoDigits(pDay));
        builder.append(" ").append(toTwoDigits(hour)).append(":").append(toTwoDigits(minute)).append(":00");
        return builder.toString();
    }

    @Override
    public void onValidationSucceeded() {

        Bundle bundle=new Bundle();
        bundle.putString(END_DATE,getDateTime());
        bundle.putString(START_DATE,getDateTime());
        bundle.putString(TIME,etTime.getText().toString());
        bundle.putString(DATE,etDate.getText().toString());
        bundle.putBoolean(PICK_UP_WANT,radioGroup.getCheckedRadioButtonId()==R.id.radioYes? true : false);
        bundle.putString(ADDRESS,etLocation.getText().toString());
        bundle.putString(NAME,etName.getText().toString());
        bundle.putString(PHONE,etPhoneNumber.getText().toString());
        bundle.putString(EMAIL,etEmail.getText().toString());
        bundle.putString(SERVICE_TYPE, selectedServices);
        getFragmentAdapter().addToBackStack(ServiceReviewFragment.newInstance(bundle).setFragmentAdapter(getFragmentAdapter()), false);


    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(failedRule.getFailureMessage());
            CommonUtils.shakeView(mContext, failedView);
            return;
        }

        failedView.getParent();
        Toast.makeText(getActivity(), failedRule.getFailureMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewValidationSucceeded(View succeededView) {

    }
}
