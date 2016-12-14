package com.gaadi.pratnerapps.fragments;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.ShowroomApiModel;
import com.gaadi.pratnerapps.utils.Utils;
import com.gaadi.pratnerapps.widgets.ShowroomWidget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by priyarawat on 2/6/16.
 */
public class
ShowroomsDetailFragment extends PartnerBaseFragment implements OnMapReadyCallback, View.OnClickListener{
    LinearLayout showroom_imagesLayout;
    private GoogleMap mMap;
    ShowroomApiModel.ShowroomModel showroomModelObj;
    View fragmentView;
    NestedScrollView mainScrollView;
    TextView tv_dealerAddress, tv_PhoneNum, tv_DealerEmailId, tv_PremiumDealerTxt;
    private LinearLayout emailIdLayout, contactNumLayout;
    private FrameLayout parentFrameLayout;
    private ImageView transparentImageView;

    public static ShowroomsDetailFragment newInstance(ShowroomApiModel.ShowroomModel showroomModelObj)
    {
        ShowroomsDetailFragment showroomsDetailFragment = new ShowroomsDetailFragment();
        showroomsDetailFragment.showroomModelObj = showroomModelObj;
        return showroomsDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.showroom_details_fragment, container, false);
        mainScrollView = (NestedScrollView) fragmentView.findViewById(R.id.main_scrollview);
        mainScrollView.setNestedScrollingEnabled(false);
        parentFrameLayout = (FrameLayout)fragmentView.findViewById(R.id.mapParentFrame);

        transparentImageView = new ImageView(getContext());

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        transparentImageView.setLayoutParams(layoutParams);

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
        return fragmentView;
    }
    private void addWidgets(){
        ShowroomWidget bannerWidget = new ShowroomWidget(getContext(),true,showroomModelObj.getImages());
        showroom_imagesLayout.addView(bannerWidget.getView());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(Double.parseDouble(showroomModelObj.getLat()), Double.parseDouble(showroomModelObj.getLng()));
        mMap.addMarker(new MarkerOptions().position(latLng).title(""));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        mMap.getUiSettings().setMapToolbarEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                String uri = "http://maps.google.com/maps?f=d&hl=en&daddr="+showroomModelObj.getLat()+","+showroomModelObj.getLng();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(Intent.createChooser(intent, "Select an application"));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showroom_imagesLayout = (LinearLayout) fragmentView.findViewById(R.id.showroom_imagesLayout);
        TextView tv_getDirections = (TextView) fragmentView.findViewById(R.id.tvGetDirection);
        tv_dealerAddress = (TextView) fragmentView.findViewById(R.id.tv_dealerAddress);
        tv_DealerEmailId = (TextView) fragmentView.findViewById(R.id.tv_dealerEailId);
        tv_PhoneNum = (TextView) fragmentView.findViewById(R.id.tv_dealerPhoneNum);
        tv_PremiumDealerTxt = (TextView) fragmentView.findViewById(R.id.tv_PremiumDealers);
        emailIdLayout = (LinearLayout) fragmentView.findViewById(R.id.emailIdLayout);
        contactNumLayout = (LinearLayout) fragmentView.findViewById(R.id.contactNumLayout);
        emailIdLayout.setOnClickListener(this);
        contactNumLayout.setOnClickListener(this);

        if (!TextUtils.isEmpty(showroomModelObj.getName()))
        tv_PremiumDealerTxt.setText(showroomModelObj.getName());
        else
        tv_PremiumDealerTxt.setText(getResources().getString(R.string.app_name));

        tv_getDirections.setText(getResources().getString(R.string.direction));
        if(null != showroomModelObj.getImages() && showroomModelObj.getImages().size() > 0) {
            showroom_imagesLayout.setVisibility(View.VISIBLE);
            addWidgets();
        }
        else
        {
            showroom_imagesLayout.setVisibility(View.GONE);
        }
        tv_dealerAddress.setText(showroomModelObj.getAddress());
        tv_DealerEmailId.setText(showroomModelObj.getEmail() != null ? showroomModelObj.getEmail().get(0) : "");
        tv_PhoneNum.setText(Utils.phoneNumberFormate(showroomModelObj.getPhone() != null ? showroomModelObj.getPhone().get(0) : ""));
        if(showroomModelObj.getLat() != null && showroomModelObj.getLng() != null && !showroomModelObj.getLat().isEmpty() && !showroomModelObj.getLng().isEmpty()) {
            fragmentView.findViewById(R.id.framelayout).setVisibility(View.VISIBLE);
            SupportMapFragment supportMapFragment = new SupportMapFragment();
            // supportMapFragment.init(this, new );
            getChildFragmentManager().beginTransaction().add(R.id.framelayout, supportMapFragment, "MAP_TAG").commit();
//        SupportMapFragment supportMapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
            supportMapFragment.getMapAsync(this);

            parentFrameLayout.addView(transparentImageView);
        }
        else
            fragmentView.findViewById(R.id.framelayout).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.contactNumLayout:
                if(showroomModelObj.getPhone() != null)
                    requestPermission(100, Manifest.permission.CALL_PHONE);
                break;

            case R.id.emailIdLayout:
                if(showroomModelObj.getEmail() != null) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + showroomModelObj.getEmail().get(0)));
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onPermissionGranted(int requstCode, String grantedPermission) {
        if(grantedPermission.equals(Manifest.permission.CALL_PHONE))
        {
            Utils.callPhone(getActivity(), showroomModelObj.getPhone().get(0));
        }
    }
}
