package com.gaadi.pratnerapps.retrofit;

import android.content.Context;
import android.text.TextUtils;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.BuyCarIInfo;
import com.gaadi.pratnerapps.models.CityApiModel;
import com.gaadi.pratnerapps.models.ColorApiModel;
import com.gaadi.pratnerapps.models.ContactUsApiModel;
import com.gaadi.pratnerapps.models.FinanceApiModel;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.models.InsuranceApiModel;
import com.gaadi.pratnerapps.models.MakeApiModel;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.models.ServicesApiModel;
import com.gaadi.pratnerapps.models.ShowroomApiModel;
import com.gaadi.pratnerapps.models.SplashApiModel;
import com.gaadi.pratnerapps.models.StartupApiModel;
import com.gaadi.pratnerapps.models.TestimonialApiModel;
import com.gaadi.pratnerapps.models.UCVResponseModel;
import com.gaadi.pratnerapps.models.UserModel;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vinodtakhar on 25/5/16.
 */
public class RestApiCalls {
    //public static final String REST_HOST = BuildConfig.REST_HOST+"v2/";
    public static final String REST_HOST = BuildConfig.REST_HOST+"v2/";

    private static volatile RetrofitInterface retrofitInterface;

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static RetrofitInterface getRetrofitApi(final Context context) {

        if(retrofitInterface == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMyyyy");

                    String apikey = md5("98aa6f0d54a3fe0a381559443e5aaa3e"+simpleDateFormat.format(System.currentTimeMillis()));

                    Logger.e("API KEY",""+apikey);

                    UserModel userModel = ObjectTableUtil.getUserModel(context);

                    Request request = original.newBuilder()
                            .header("VersionCode", String.valueOf(BuildConfig.VERSION_CODE))
                            .header("apikey", apikey)
                            .method(original.method(), original.body())
                            .build();

                    if(userModel!=null && !TextUtils.isEmpty(userModel.getLogin_token())) {
                        request.headers().newBuilder().add("login_token", userModel.getLogin_token());
                    }

                    return chain.proceed(request);
                }
            };

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .readTimeout(3, TimeUnit.MINUTES)
//                    .connectTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .addInterceptor(headerInterceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(REST_HOST)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()   )
                    .build();

            retrofitInterface =  retrofit.create(RetrofitInterface.class);
        }

        return retrofitInterface;
    }

    public static Call<BuyCarIInfo> getSearchResults(Context context) {
        return getRetrofitApi(context).getSearchResults(BuildConfig.DEALER_ID,new HashMap<String, String>());
    }

    public static Call<BuyCarIInfo> getSearchResults(Context context,Map<String, String> params) {
        return getRetrofitApi(context).getSearchResults(BuildConfig.DEALER_ID,params);
    }

    public static Call<StartupApiModel> getStartUp(Context context) {
        return getRetrofitApi(context).getStartup(BuildConfig.DEALER_ID);
    }

    public static Call<ShowroomApiModel> getShowrooms(Context context) {
        return getRetrofitApi(context).getShowrooms(BuildConfig.DEALER_ID);
    }

    public static Call<TestimonialApiModel> getTestimonials(Context context) {
        return getRetrofitApi(context).getTestimonials(BuildConfig.DEALER_ID);
    }

    public static Call<ContactUsApiModel> getContactUs(Context context) {
        return getRetrofitApi(context).getContactUs(BuildConfig.DEALER_ID);
    }

    public static Call<BaseResponseModel> submitCarInfo(Context context,Map<String,String> params) {
        return getRetrofitApi(context).submitSellCarInfo(BuildConfig.DEALER_ID,params);
    }

    public static Call<BaseResponseModel> requestCall(Context context,Map<String,String> params) {
        return getRetrofitApi(context).requestCall(BuildConfig.DEALER_ID,params);
    }

    public static Call<BaseResponseModel> referAFriend(Context context,Map<String,String> params) {
        return getRetrofitApi(context).referAFriend(BuildConfig.DEALER_ID,params);
    }

    public static Call<BaseResponseModel> requestOtp(Context context,Map<String,String> params) {
        return getRetrofitApi(context).requestOtp(BuildConfig.DEALER_ID,params);
    }

    public static Call<UserModel> verifyOtp(Context context,Map<String,String> params) {
        return getRetrofitApi(context).verifyOtp(BuildConfig.DEALER_ID,params);
    }

    public static Call<BaseResponseModel> saveGcm(Context context,Map<String,String> params) {
        return getRetrofitApi(context).saveGcm(BuildConfig.DEALER_ID,params);
    }

    public static Call<BaseResponseModel> saveProfile(Context context,Map<String,String> params) {
        return getRetrofitApi(context).saveProfile(BuildConfig.DEALER_ID,params);
    }

    public static Call<BaseResponseModel> postInsurance(Context context,Map<String,String> params,String filePath) {
        if(filePath!=null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), new File(filePath));

            HashMap<String,RequestBody> map = new HashMap<>();

            for(String key:params.keySet()){
                map.put(key,RequestBody.create(MediaType.parse("text/plain"),params.get(key)));
            }

            return getRetrofitApi(context).postInsurance(BuildConfig.DEALER_ID, requestFile,map);
        }else {
            return getRetrofitApi(context).postInsurance(BuildConfig.DEALER_ID, params);
        }
    }

    public static Call<BaseResponseModel> postFinance(Context context,Map<String,String> params) {
        return getRetrofitApi(context).postFinance(BuildConfig.DEALER_ID,params);
    }

    public static Call<MakeApiModel> getMakeModels(Context context) {
        return getRetrofitApi(context).getMakeModels(BuildConfig.DEALER_ID);
    }

    public static Call<SplashApiModel> getSplashConfig(Context context) {
        return getRetrofitApi(context).getSplashConfig(BuildConfig.DEALER_ID);
    }

    public static Call<ColorApiModel> getColors(Context context) {
        return getRetrofitApi(context).getColors(BuildConfig.DEALER_ID);
    }

    public static Call<MenuApiModel> getNavigation(Context context) {
        return getRetrofitApi(context).getNavigation(BuildConfig.DEALER_ID);
    }

    public static Call<HomeApiModel> getHomeConfig(Context context) {
        return getRetrofitApi(context).getHomeConfig(BuildConfig.DEALER_ID);
    }

    public static Call<BaseResponseModel> logout(Context context) {
        return getRetrofitApi(context).logout(BuildConfig.DEALER_ID);
    }

    public static Call<CityApiModel> getCityList(Context context) {
        return getRetrofitApi(context).getCityList();
    }

    public static Call<ParameterApiModel> getParameters(Context context) {
        return getRetrofitApi(context).getParameters();
    }

    public static Call<FinanceApiModel> getFinance(Context context) {
        return getRetrofitApi(context).getFinance(BuildConfig.DEALER_ID);
    }

    public static Call<InsuranceApiModel> getInsurance(Context context) {
        return getRetrofitApi(context).getInsurance(BuildConfig.DEALER_ID);
    }

    public static Call<ServicesApiModel> getServices(Context context) {
        return getRetrofitApi(context).getServices(BuildConfig.DEALER_ID);
    }

    public static Call<BaseResponseModel> postServices(Context context,HashMap<String,String> params) {
        return getRetrofitApi(context).postServices(BuildConfig.DEALER_ID,params);
    }

    public static class UCVCall{

        public static final String UCV_HOST_URL ="http://beta.usedcarsin.in/truprice/serviceNew.php/";
        //public static final String UCV_HOST_URL = REST_HOST + "truprice/serviceNew.php/";

        private static RetrofitInterface getRetrofitApi() {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UCV_HOST_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create()   )
                        .build();

            return retrofit.create(RetrofitInterface.class);
        }

        public static Call<UCVResponseModel> postUcv(Map<String,String> params) {
            return getRetrofitApi().postUcv(BuildConfig.DEALER_ID,params);
        }
    }
    public static Call<BaseResponseModel> postTestimonial(Context context, Map<String,String> params, String filePath) {
        if(filePath != null) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), new File(filePath));

            HashMap<String,RequestBody> map = new HashMap<>();

            for(String key : params.keySet()) {
                map.put(key, RequestBody.create(MediaType.parse("text/plain"), params.get(key)));
            }

            return getRetrofitApi(context).postTestimonial(BuildConfig.DEALER_ID, requestFile, map);
        }else {
            return getRetrofitApi(context).postTestimonial(BuildConfig.DEALER_ID,params);
        }

    }
}
