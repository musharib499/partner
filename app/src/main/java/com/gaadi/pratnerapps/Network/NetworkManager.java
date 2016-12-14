package com.gaadi.pratnerapps.Network;

import android.content.Context;
import android.util.Log;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.Network.Retrofit.RetrofitAdapter;
import com.gaadi.pratnerapps.Network.Retrofit.RetrofitInterface;
import com.gaadi.pratnerapps.Network.models.SearchResponse;
import com.gaadi.pratnerapps.Network.models.VDPResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lakshaygirdhar on 2/4/16.
 */
public class NetworkManager<T> {

    private static final String TAG = "NetworkManager";

    public static final String REST_HOST = BuildConfig.REST_HOST;
    public static final long CONNECT_TIMEOUT_SECS = 50;
    public static final long WRITE_TIMEOUT_SECS = 50;
    public static final long READ_TIMEOUT_SECS = 50;
    public static final int CONNECTION_POOL_SIZE = 4;
    public static final int CONNECTION_MAX_IDLE_TIME_MS = 3000;
    public static final String ENVIRONMENT = BuildConfig.ENVIRONMENT;
    private Context mContext;
    private NetworkCallback mCallback;
    public Class<T> mClass;
    private int requestCode;

    private RetrofitInterface retrofitInterface = RetrofitAdapter.getRestInterface();

    public interface NetworkCallback<T> {
        public void onNetworkResponse(T object, int requestCode);
        public void onNetworkError(Throwable t, int requestCode);
    }

    public static <T> NetworkManager with(NetworkCallback callback,int requestCode) {
        NetworkManager manager = new NetworkManager();
        manager.mCallback = callback;
        manager.requestCode = requestCode;
        return manager;
    }

    public static <T> NetworkManager with(NetworkCallback callback,int requestCode,Class<T> object) {
        NetworkManager manager = new NetworkManager();
        manager.mCallback = callback;
        manager.requestCode = requestCode;
        manager.mClass = object;
        return manager;
    }

    public void getSearchResults(HashMap<String, String> params) {
        Call<SearchResponse> call = retrofitInterface.getSearchResults(params);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, retrofit2.Response<SearchResponse> response) {
                Log.d(TAG, "onResponse: ");
                mCallback.onNetworkResponse(response.body(),requestCode);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                mCallback.onNetworkError(t,requestCode);
            }
        });
    }

    public void getVDPDetails(HashMap<String, String> params) {
        Call<VDPResponse> call = retrofitInterface.getVDPDetails(params);
        call.enqueue(new Callback<VDPResponse>() {
            @Override
            public void onResponse(Call<VDPResponse> call, retrofit2.Response<VDPResponse> response) {
                Log.d(TAG, "onResponse: ");
                mCallback.onNetworkResponse(response.body(),requestCode);
            }

            @Override
            public void onFailure(Call<VDPResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                mCallback.onNetworkError(t,requestCode);
            }
        });
    }

}
