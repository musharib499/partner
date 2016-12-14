package com.gaadi.pratnerapps.Network.Retrofit;

import com.gaadi.pratnerapps.Network.NetworkManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * Created by lakshaygirdhar on 2/4/16.
 */
public class RetrofitAdapter {

    private static final String TAG = "RetrofitAdapter";

    public static RetrofitInterface getRestInterface() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkManager.REST_HOST)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create()   )
                .build();

        return retrofit.create(RetrofitInterface.class);
//        final Gson gson = new GsonBuilder().create();
//        final GsonConverter converter = new GsonConverter(gson);
//        final OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setConnectTimeout(NetworkManager.CONNECT_TIMEOUT_SECS, TimeUnit.SECONDS);
//        okHttpClient.setWriteTimeout(NetworkManager.WRITE_TIMEOUT_SECS, TimeUnit.SECONDS);
//        okHttpClient.setReadTimeout(NetworkManager.READ_TIMEOUT_SECS, TimeUnit.SECONDS);
//        okHttpClient.setConnectionPool(new ConnectionPool(NetworkManager.CONNECTION_POOL_SIZE, NetworkManager.CONNECTION_MAX_IDLE_TIME_MS));
//        okHttpClient.setRetryOnConnectionFailure(true);
//
//
//        return new RestAdapter.Builder()
//                .setEndpoint(NetworkManager.REST_HOST)
//                .setExecutors(Executors.newFixedThreadPool(NetworkManager.CONNECTION_POOL_SIZE), new MainThreadExecutor())
//                .setConverter(converter)
//                .setLogLevel(NetworkManager.ENVIRONMENT.equals("PROD") ? RestAdapter.LogLevel.NONE : RestAdapter.LogLevel.FULL)
//                .setClient(new OkClient(okHttpClient))
//                .build();
    }
}
