package com.gaadi.pratnerapps.retrofit;


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

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by vinodtakhar on 2/6/16.
 */
public interface RetrofitInterface {

    @GET("cars/{dealerId}")
    Call<BuyCarIInfo> getSearchResults(@Path("dealerId") String dealerId, @QueryMap Map<String, String> params);

    @GET("startup/{dealerId}")
    Call<StartupApiModel> getStartup(@Path("dealerId") String dealerId);

    @GET("showroom/{dealerId}")
    Call<ShowroomApiModel> getShowrooms(@Path("dealerId") String dealerId);

    @GET("testimonial/{dealerId}")
    Call<TestimonialApiModel> getTestimonials(@Path("dealerId") String dealerId);

    @GET("contact/{dealerId}")
    Call<ContactUsApiModel> getContactUs(@Path("dealerId") String dealerId);

    @GET("make/{dealerId}")
    Call<MakeApiModel> getMakeModels(@Path("dealerId") String dealerId);

    @GET("splash/{dealerId}")
    Call<SplashApiModel> getSplashConfig(@Path("dealerId") String dealerId);

    @GET("colors/{dealerId}")
    Call<ColorApiModel> getColors(@Path("dealerId") String dealerId);

    @GET("navigations/{dealerId}")
    Call<MenuApiModel> getNavigation(@Path("dealerId") String dealerId);

    @GET("home/{dealerId}")
    Call<HomeApiModel> getHomeConfig(@Path("dealerId") String dealerId);

    @GET("parameters")
    Call<ParameterApiModel> getParameters();

    @GET("citylist")
    Call<CityApiModel> getCityList();

    @GET("finance/{dealerId}")
    Call<FinanceApiModel> getFinance(@Path("dealerId") String dealerId);

    @GET("insurance/{dealerId}")
    Call<InsuranceApiModel> getInsurance(@Path("dealerId") String dealerId);

    @GET("services/{dealerId}")
    Call<ServicesApiModel> getServices(@Path("dealerId") String dealerId);

    @POST("sellcar/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> submitSellCarInfo(@Path("dealerId") String dealerId,@FieldMap Map<String,String> params);

    @POST("requestcall/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> requestCall(@Path("dealerId") String dealerId,@FieldMap Map<String,String> params);

    @POST("refer/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> referAFriend(@Path("dealerId") String dealerId,@FieldMap Map<String,String> params);

    @POST("otpRequest/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> requestOtp(@Path("dealerId") String dealerId,@FieldMap Map<String,String> params);

    @POST("otpVerify/{dealerId}")
    @FormUrlEncoded
    Call<UserModel> verifyOtp(@Path("dealerId") String dealerId, @FieldMap Map<String,String> params);

    @POST("registerDevice/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> saveGcm(@Path("dealerId") String dealerId,@FieldMap Map<String,String> params);

    @POST("userProfile/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> saveProfile(@Path("dealerId") String dealerId,@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("insurance/{dealerId}")
    Call<BaseResponseModel> postInsurance(@Path("dealerId") String dealerId,
                                          @FieldMap Map<String,String> params);

    @Multipart
    @POST("insurance/{dealerId}")
    Call<BaseResponseModel> postInsurance(@Path("dealerId") String dealerId,
                                          @Part("file\"; filename=\"imagefile.jpg\" ") RequestBody file,
                                          @PartMap Map<String, RequestBody> params);

    @POST("finance/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> postFinance(@Path("dealerId") String dealerId,@FieldMap Map<String,String> params);


    @POST("logout/{dealerId}")
    Call<BaseResponseModel> logout(@Path("dealerId") String dealerId);


    @POST("postUcv/{dealerId}")
    @FormUrlEncoded
    Call<UCVResponseModel> postUcv(@Path("dealerId") String dealerId, @FieldMap Map<String,String> params);

    @POST("services/{dealerId}")
    @FormUrlEncoded
    Call<BaseResponseModel> postServices(@Path("dealerId") String dealerId,
                                          @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("testimonial/{dealerId}")
    Call<BaseResponseModel> postTestimonial(@Path("dealerId") String dealerId,
                                          @FieldMap Map<String,String> params);
    @Multipart
    @POST("testimonial/{dealerId}")
    Call<BaseResponseModel> postTestimonial(@Path("dealerId") String dealerId,
                                            @Part("file\"; filename=\"imagefile.jpg\" ") RequestBody file,
                                            @PartMap Map<String,RequestBody> params);
}
