package com.gaadi.pratnerapps.Network.Retrofit;


import com.gaadi.pratnerapps.Network.models.SearchResponse;
import com.gaadi.pratnerapps.Network.models.VDPResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lakshaygirdhar on 2/4/16.
 */
public interface RetrofitInterface<T> {

    //    @FormUrlEncoded
//    @POST("used-cars/getTopUsedCarFilters")
//    <T> Call<T> getResults(@FieldMap HashMap<String,String> params);

    @FormUrlEncoded
    @POST("search-used-cars/searchUsedCar")
    Call<SearchResponse> getSearchResults(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("vdp/getUsedCarFullDetails")
    Call<VDPResponse> getVDPDetails(@FieldMap HashMap<String, String> params);

}
