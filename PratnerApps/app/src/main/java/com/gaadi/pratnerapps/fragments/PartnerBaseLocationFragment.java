package com.gaadi.pratnerapps.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.List;
import java.util.Locale;

/**
 * @author vinodtakhar
 * @version 1.0
 * @since 5/9/16
 */
public abstract class PartnerBaseLocationFragment extends PartnerBaseFragment implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "PartnerBaseLocation";

    private static final int REQUEST_ENABLE_GPS = 1003;
    private static final int DEFAULT_INTERVAL = (int) AlarmManager.INTERVAL_FIFTEEN_MINUTES / 5;
    private static final int DEFAULT_ACCURACY = LocationRequest.PRIORITY_HIGH_ACCURACY;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private AsyncTask<Void, Void, String> addressGetter;

    protected void requestLocation() {
        requestPermission(100, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void onPermissionGranted(int requestCode, String grantedPermission) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission(100, Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            connectToGoogleClient();
        }
    }

    @Override
    public void onPermissionDenied(int requestCode, String deniedPermission) {
        requestPermission(100, deniedPermission);
    }

    private LocationRequest getLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        int updateIntervalInMillis = DEFAULT_INTERVAL;
        mLocationRequest.setInterval(updateIntervalInMillis);
        mLocationRequest.setFastestInterval(updateIntervalInMillis / 2);
        mLocationRequest.setPriority(DEFAULT_ACCURACY);
        return mLocationRequest;
    }

    private void connectToGoogleClient() {
        mLocationRequest = getLocationRequest();

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestToEnableGps();
    }

    public void requestToEnableGps() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        Log.e(TAG, "Start resolution request");

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();

                Log.e(TAG, "Status:" + status.getStatus());

                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        proceedGpsIsOn();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(getActivity(), REQUEST_ENABLE_GPS);
                        } catch (IntentSender.SendIntentException e) {
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }

    private void proceedGpsIsOn() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        PendingResult<Status> result = LocationServices.FusedLocationApi
                .requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        fetchAddress(location);
    }

    private void fetchAddress(final Location location) {
        if (addressGetter != null) {
            return;
        }

        addressGetter = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String address) {
                super.onPostExecute(address);

                onAddressAvailable(address);

                addressGetter = null;
            }

            @Override
            protected String doInBackground(Void... params) {
                return getAddress(location);
            }
        };
        addressGetter.execute();
    }

    public abstract void onAddressAvailable(String address);
    public abstract void onFailedToFetchAddress(String reason);

    protected String getAddress(Location location) {
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            String cityName = addresses.get(0).getAddressLine(0);
            String stateName = addresses.get(0).getAddressLine(1);
            String countryName = addresses.get(0).getAddressLine(2);

            String LINE_SEPARATOR = "\n";
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(cityName))
                stringBuilder.append(cityName).append(LINE_SEPARATOR);
            if (!TextUtils.isEmpty(stateName))
                stringBuilder.append(stateName).append(LINE_SEPARATOR);
            if (!TextUtils.isEmpty(countryName))
                stringBuilder.append(countryName).append(LINE_SEPARATOR);

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Address not available!";
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        onFailedToFetchAddress("Failed to load Location...");
    }

    @Override
    public void onDestroy() {
        try {
            LocationServices.FusedLocationApi
                    .removeLocationUpdates(mGoogleApiClient,
                            this);

            mGoogleApiClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_ENABLE_GPS){
            if(resultCode == Activity.RESULT_OK){
                proceedGpsIsOn();
            }else{
                onFailedToFetchAddress("GPS is not enabled, can't fetch location");
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
