package com.gaadi.pratnerapps.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.ContactUsApiModel;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends PartnerBaseFragment implements OnMapReadyCallback , View.OnClickListener{
    private GoogleMap mMap;
    private TextView tvPremium;
    private TextView tvContact;
    private TextView tvGetDirection, tv_DealerEmail, tv_DealerAddress, tv_DealerPhoneNum;
    ImageView iv_whatsApp, iv_GooglePlus, iv_Facebook, iv_Twitter,ivInstagram;
    Context mContext;
    ContactUsApiModel.ContactUsModel contactUsModelObj;
    NestedScrollView mainScrollView;
    LinearLayout contactNumLayout, emailIdlayout;

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        initilizationView(view);
       if( contactUsModelObj.getLat() != null && contactUsModelObj.getLang() != null &&!"".equals(contactUsModelObj.getLat()) && !"".equals(contactUsModelObj.getLang())) {
            view.findViewById(R.id.mapLayout).setVisibility(View.VISIBLE);
            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else
        {
            view.findViewById(R.id.mapLayout).setVisibility(View.GONE);
        }
        mainScrollView = (NestedScrollView) view.findViewById(R.id.main_scrollview);
        mainScrollView.setNestedScrollingEnabled(false);
        ImageView transparentImageView = (ImageView) view.findViewById(R.id.transparent_image);

        sharedSocial();

        transparentImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });

        getFragmentAdapter().setTitleMessage(getString(R.string.contact_us),false);

        return view;
    }

    public void sharedSocial()
    {
        if (!TextUtils.isEmpty(contactUsModelObj.getFacebook_url()))
            iv_Facebook.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(contactUsModelObj.getGoogle_url()))
            iv_GooglePlus.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(contactUsModelObj.getInstagram_url()))
            ivInstagram.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(contactUsModelObj.getTwitter_url()))
            iv_Twitter.setVisibility(View.VISIBLE);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void initilizationView(View v) {
        tvPremium = (TextView) v.findViewById(R.id.tvPremium);
        tvContact = (TextView) v.findViewById(R.id.tvContactWithUs);
        tvGetDirection = (TextView) v.findViewById(R.id.tvGetDirection);
        tv_DealerAddress = (TextView) v.findViewById(R.id.tv_dealerAddress);
        tv_DealerEmail = (TextView) v.findViewById(R.id.tv_dealerEailId);
        tv_DealerPhoneNum = (TextView) v.findViewById(R.id.tv_dealerPhoneNum);
        iv_Facebook = (ImageView) v.findViewById(R.id.iv_Facebook);
        iv_GooglePlus = (ImageView) v.findViewById(R.id.iv_googlePlus);
        iv_Twitter = (ImageView) v.findViewById(R.id.iv_Twitter);
        iv_whatsApp = (ImageView) v.findViewById(R.id.iv_whatsApp);
        ivInstagram = (ImageView) v.findViewById(R.id.ivInstagram);
        contactNumLayout = (LinearLayout) v.findViewById(R.id.contactNumLayout);
        emailIdlayout = (LinearLayout) v.findViewById(R.id.emailIdLayout);
        emailIdlayout.setOnClickListener(this);
        contactNumLayout.setOnClickListener(this);
        iv_whatsApp.setOnClickListener(this);
        iv_Twitter.setOnClickListener(this);
        iv_GooglePlus.setOnClickListener(this);
        iv_Facebook.setOnClickListener(this);
        setDataInField();
    }

    public void setDataInField() {
        tvContact.setText(getResources().getString(R.string.connect));
        tvPremium.setText(getResources().getString(R.string.app_name));
        tvGetDirection.setText(getResources().getString(R.string.direction));
        contactUsModelObj = ObjectTableUtil.getContactUsModel(mContext);
        tv_DealerAddress.setText(contactUsModelObj.getAddress());
        tv_DealerPhoneNum.setText(Utils.phoneNumberFormate(contactUsModelObj.getMobile()));
        tv_DealerEmail.setText(contactUsModelObj.getEmail());


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLong = new LatLng(Double.parseDouble(contactUsModelObj.getLat()), Double.parseDouble(contactUsModelObj.getLang()));
        mMap.addMarker(new MarkerOptions().position(latLong));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLong, 14));
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                String uri = "http://maps.google.com/maps?f=d&hl=en&daddr="+contactUsModelObj.getLat()+","+contactUsModelObj.getLang();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(Intent.createChooser(intent, "Select an application"));
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_Facebook:
                if(!TextUtils.isEmpty(contactUsModelObj.getFacebook_url()))
                    openBrowser(contactUsModelObj.getFacebook_url());
                else
                showToast("No link found");
                break;

            case R.id.iv_googlePlus:
                if(!TextUtils.isEmpty(contactUsModelObj.getGoogle_url()))
                    openBrowser(contactUsModelObj.getGoogle_url());
                else
                    showToast("No link found");
                break;

            case R.id.iv_Twitter:
                if(!TextUtils.isEmpty(contactUsModelObj.getTwitter_url()))
                    openBrowser(contactUsModelObj.getTwitter_url());
                else
                    showToast("No link found");
                break;

            case R.id.iv_whatsApp:
                sendMessageInWhatsApp(getContext(),"Message from DealerApps", contactUsModelObj.getMobile());
                break;

            case R.id.contactNumLayout:
                if(contactUsModelObj.getMobile() != null && !"".equals(contactUsModelObj.getMobile()))
                {
                    requestPermission(100, Manifest.permission.CALL_PHONE);
                }
                break;

            case R.id.emailIdLayout:
                if(contactUsModelObj.getEmail() != null && !"".equals(contactUsModelObj.getEmail()))
                {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+contactUsModelObj.getEmail()));
                    mContext.startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onPermissionGranted(int requstCode, String grantedPermission) {
        if(grantedPermission.equals(Manifest.permission.CALL_PHONE)){
            Utils.callPhone(mContext, contactUsModelObj.getMobile());
        }
    }

    private void openBrowser(String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }catch(Exception e){
            showToast("Can not open browser.");
        }
    }

    public static void sendMessageInWhatsApp(Context context,String shareText, String number) {
        if (isWhatsappInstalled(context,"com.whatsapp")) {
            PackageManager pm = context.getPackageManager();
            try {
                Uri uri = Uri.parse("smsto:" + number);
                Intent mIntent = new Intent(Intent.ACTION_SEND, uri);

                mIntent.setPackage("com.whatsapp");
                mIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                mIntent.setType("text/plain");

                    context.startActivity(Intent.createChooser(mIntent, "Open with"));

            } catch (Exception e) {
                Toast.makeText(context,"While sending as WhatsApp message, some unknown error occurred.",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context,"WhatsApp not installed.",Toast.LENGTH_SHORT).show();

        }
    }

    private static boolean isWhatsappInstalled(Context context,String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            StackTraceElement[] trace = e.getStackTrace();
        }
        return false;
    }
}
