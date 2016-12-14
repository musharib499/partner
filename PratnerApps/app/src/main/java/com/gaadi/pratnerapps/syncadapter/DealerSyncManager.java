package com.gaadi.pratnerapps.syncadapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.gaadi.pratnerapps.PartnerApplication;
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
import com.gaadi.pratnerapps.provider.citytable.CitytableColumns;
import com.gaadi.pratnerapps.provider.citytable.CitytableContentValues;
import com.gaadi.pratnerapps.provider.maketable.MaketableColumns;
import com.gaadi.pratnerapps.provider.maketable.MaketableContentValues;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableColumns;
import com.gaadi.pratnerapps.provider.modeltable.ModeltableContentValues;
import com.gaadi.pratnerapps.provider.notificationtable.NotificationtableColumns;
import com.gaadi.pratnerapps.provider.objecttable.ObjecttableColumns;
import com.gaadi.pratnerapps.provider.showroomtable.ShowroomtableColumns;
import com.gaadi.pratnerapps.provider.showroomtable.ShowroomtableContentValues;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableColumns;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableContentValues;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableColumns;
import com.gaadi.pratnerapps.provider.versiontable.VersiontableContentValues;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.SyncUtils;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vinodtakhar on 30/5/16.
 */
public class DealerSyncManager {
    public static final int SYNC_SCOPE_ALL = 1;
    public static final String EXTRA_SYNC_SCOPE ="extra_sync_scope";
    public static final int SYNC_MODE_ASSETS=1;
    public static final int SYNC_MODE_LIVE=2;
    public static final String EXTRA_SYNC_MODE="extra_sync_mode";
    private static final String TAG = "DealerSyncManager";
    private boolean synchronizedCall=false;

    private Context context;
    private Bundle extras;
    private StartupApiModel.VersionModel oldVersionModel;
    private StartupApiModel.VersionModel newVersionModel;

    private DealerSyncManager(Context context, Bundle bundle, boolean synchronizedCall){
        this.context=context;
        this.extras=bundle;
        this.synchronizedCall = synchronizedCall;
    }

    public static void startAsynchronous(Context context, Bundle bundle) {
        if (!SyncUtils.createSyncAccountIfNotExistOrDisabled(context, bundle))
            SyncUtils.startSync(bundle);
    }

    public static void startAsynchronous(Context context) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SYNC_MODE, SYNC_MODE_LIVE);
        bundle.putInt(EXTRA_SYNC_SCOPE, SYNC_SCOPE_ALL);
        startAsynchronous(context, bundle);
    }

    public static void startSynchronized(Context context, Bundle bundle) throws JSONException, IOException {
        new DealerSyncManager(context, bundle, true).syncNow();
    }

    public static void startSynchronized(Context context) throws JSONException, IOException {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SYNC_MODE, SYNC_MODE_LIVE);
        bundle.putInt(EXTRA_SYNC_SCOPE, SYNC_SCOPE_ALL);
        startSynchronized(context, bundle);
    }

    public static void deleteAllData(Context context){
        context.getContentResolver().delete(MaketableColumns.CONTENT_URI,null,null);
        context.getContentResolver().delete(ModeltableColumns.CONTENT_URI, null, null);
        context.getContentResolver().delete(CitytableColumns.CONTENT_URI, null, null);
        context.getContentResolver().delete(TestimonialtableColumns.CONTENT_URI, null, null);
        context.getContentResolver().delete(NotificationtableColumns.CONTENT_URI, null, null);
        context.getContentResolver().delete(VersiontableColumns.CONTENT_URI, null, null);
        context.getContentResolver().delete(ObjecttableColumns.CONTENT_URI, null, null);
        context.getContentResolver().delete(ShowroomtableColumns.CONTENT_URI, null, null);
    }

    private void syncNow() throws JSONException, IOException {
        int syncMode=extras!=null?extras.getInt(EXTRA_SYNC_MODE,SYNC_MODE_LIVE):SYNC_MODE_LIVE;

        Logger.e(TAG, "Sync Mode:" + syncMode);

        if(syncMode==SYNC_MODE_ASSETS){
            syncFromAssets();
        }else if(syncMode==SYNC_MODE_LIVE) {
            syncFromServer();
        }
    }

    private void syncFromAssets(){

        int syncType=extras!=null?extras.getInt(EXTRA_SYNC_SCOPE, SYNC_SCOPE_ALL): SYNC_SCOPE_ALL;

        Logger.e("Sync databases from assets type: " + syncType);

        if ((syncType & SYNC_SCOPE_ALL) == SYNC_SCOPE_ALL) {
            String dealerJson = readAsset(context.getAssets(), "dealer_list.json");
        }
    }

    private String readAsset(AssetManager mgr, String path) {
        String contents = "";
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = mgr.open(path);
            reader = new BufferedReader(new InputStreamReader(is));
            contents = reader.readLine();
            String line = null;
            while ((line = reader.readLine()) != null) {
                contents += '\n' + line;
            }
        } catch (final Exception e) {
            if (e != null) {
                Crashlytics.logException(e.getCause());
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return contents;
    }

    private void syncFromServer() throws JSONException, IOException {

        int syncType=extras!=null?extras.getInt(EXTRA_SYNC_SCOPE, SYNC_SCOPE_ALL): SYNC_SCOPE_ALL;

        Logger.e("Sync databases from server type: "+syncType);

        if ((syncType & SYNC_SCOPE_ALL) == SYNC_SCOPE_ALL) {
            oldVersionModel = ObjectTableUtil.getVersionModel(context);
            syncStartUp();
        }
    }

    private void syncStartUp() throws IOException,JSONException {
        Call<StartupApiModel> call = RestApiCalls.getStartUp(context);

        if (synchronizedCall) {
            handleStartup(call.execute());
        } else {
            call.enqueue(new Callback<StartupApiModel>() {
                @Override
                public void onResponse(Call<StartupApiModel> call, Response<StartupApiModel> response) {
                    try {
                        handleStartup(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<StartupApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void handleStartup(Response<StartupApiModel> response) throws IOException {
        if (response.isSuccessful() && response.body() != null) {
            newVersionModel = response.body().getVersions();

            if(newVersionModel.getColor_theme()!=oldVersionModel.getColor_theme()){
                syncColors();
                ((PartnerApplication)context.getApplicationContext()).resetTheme();
            }
            if (newVersionModel.getSplash() != oldVersionModel.getSplash()) {
                syncSplash();
                PartnerApplication.getEventBus().post("syncSplash");
            }
            if(newVersionModel.getParameters()!=oldVersionModel.getParameters()){
                syncParameters();
            }
            if (newVersionModel.getHome() != oldVersionModel.getHome()) {
                syncHome();
            }
            if (newVersionModel.getMake() != oldVersionModel.getMake()) {
                syncMakeModels();
            }
            if (newVersionModel.getShowroom() != oldVersionModel.getShowroom()) {
                syncShowroom();
            }
            if (newVersionModel.getTestimonial() != oldVersionModel.getTestimonial()) {
                syncTestimonial();
            }
            if(newVersionModel.getCity()!=oldVersionModel.getCity()){
                syncCityList();
            }
            if(newVersionModel.getContact()!=oldVersionModel.getContact()){
                syncContactUs();
            }
            if(newVersionModel.getInsurance()!=oldVersionModel.getInsurance()){
                syncInsurance();
            }
            if(newVersionModel.getFinance()!=oldVersionModel.getFinance()){
                syncFinance();
            }
            if(newVersionModel.getServices()!=oldVersionModel.getServices()){
                syncServices();
            }
            if(newVersionModel.getNavigations()!=oldVersionModel.getNavigations()){
                syncNavigations();
            }
            if(newVersionModel.getBuy_car()!=oldVersionModel.getBuy_car() || true){
                syncBuyCarForRecentListing();
            }

//            if(newVersionModel.getSession()==0 && oldVersionModel.getSession()==1){
//                AppPreferences.setBooleanSharedPreference(context,AppPreferences.DEALER_VERIFIED,false);
//            }

            ObjectTableUtil.setVersionModel(context, oldVersionModel);
        }
    }

    private void syncInsurance() throws IOException {
        Call<InsuranceApiModel> call = RestApiCalls.getInsurance(context);

        if(synchronizedCall){
            ObjectTableUtil.setInsuranceModel(context,call.execute().body());
            oldVersionModel.setInsurance(newVersionModel.getInsurance());
        }else{
            call.enqueue(new Callback<InsuranceApiModel>() {
                @Override
                public void onResponse(Call<InsuranceApiModel> call, Response<InsuranceApiModel> response) {
                    ObjectTableUtil.setInsuranceModel(context,response.body());
                    oldVersionModel.setInsurance(newVersionModel.getInsurance());
                }
                @Override
                public void onFailure(Call<InsuranceApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void syncFinance() throws IOException {
        Call<FinanceApiModel> call = RestApiCalls.getFinance(context);

        if(synchronizedCall){
            ObjectTableUtil.setFinanceModel(context,call.execute().body());
            oldVersionModel.setFinance(newVersionModel.getFinance());
        }else{
            call.enqueue(new Callback<FinanceApiModel>() {
                @Override
                public void onResponse(Call<FinanceApiModel> call, Response<FinanceApiModel> response) {
                    ObjectTableUtil.setFinanceModel(context,response.body());
                    oldVersionModel.setFinance(newVersionModel.getFinance());
                }
                @Override
                public void onFailure(Call<FinanceApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void syncServices() throws IOException {
        Call<ServicesApiModel> call = RestApiCalls.getServices(context);

        if(synchronizedCall){
            ObjectTableUtil.setServiceModel(context,call.execute().body());
            oldVersionModel.setServices(newVersionModel.getServices());
        }else{
            call.enqueue(new Callback<ServicesApiModel>() {
                @Override
                public void onResponse(Call<ServicesApiModel> call, Response<ServicesApiModel> response) {
                    ObjectTableUtil.setServiceModel(context,response.body());
                    oldVersionModel.setServices(newVersionModel.getServices());
                }
                @Override
                public void onFailure(Call<ServicesApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void syncHome() throws IOException {
        Call<HomeApiModel> call = RestApiCalls.getHomeConfig(context);

        if(synchronizedCall){
            updateHomeConfig(call.execute());
        }else{
            call.enqueue(new Callback<HomeApiModel>() {
                @Override
                public void onResponse(Call<HomeApiModel> call, Response<HomeApiModel> response) {
                    updateHomeConfig(response);
                }
                @Override
                public void onFailure(Call<HomeApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateHomeConfig(Response<HomeApiModel> response) {
        if(response.isSuccessful() && response.body()!=null) {
            ObjectTableUtil.setHomeConfig(context,response.body());

            oldVersionModel.setHome(newVersionModel.getHome());
        }
    }

    private void syncSplash() throws IOException {
        Call<SplashApiModel> call = RestApiCalls.getSplashConfig(context);

        if(synchronizedCall){
            updateSplashConfig(call.execute());
        }else{
            call.enqueue(new Callback<SplashApiModel>() {
                @Override
                public void onResponse(Call<SplashApiModel> call, Response<SplashApiModel> response) {
                    updateSplashConfig(response);
                }
                @Override
                public void onFailure(Call<SplashApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateSplashConfig(Response<SplashApiModel> response) {
        if(response.isSuccessful() && response.body()!=null) {
            ObjectTableUtil.setSplashConfig(context,response.body().getItem());

            oldVersionModel.setSplash(newVersionModel.getSplash());
        }
    }

    private void syncBuyCarForRecentListing() throws IOException {
        HashMap<String,String> params = new HashMap<>();
        params.put("limit","5");

        Call<BuyCarIInfo> call = RestApiCalls.getSearchResults(context,params);

        if(synchronizedCall){
            updateRecentListing(call.execute());
        }else{
            call.enqueue(new Callback<BuyCarIInfo>() {
                @Override
                public void onResponse(Call<BuyCarIInfo> call, Response<BuyCarIInfo> response) {
                    updateRecentListing(response);
                }
                @Override
                public void onFailure(Call<BuyCarIInfo> call, Throwable t) {
                }
            });
        }
    }

    private void updateRecentListing(Response<BuyCarIInfo> response) {
        if(response.isSuccessful() && response.body()!=null) {
            ObjectTableUtil.setRecentListingModel(context,response.body());

            oldVersionModel.setBuy_car(newVersionModel.getBuy_car());
        }
    }


    private void syncContactUs() throws IOException {
        Call<ContactUsApiModel> call = RestApiCalls.getContactUs(context);

        if(synchronizedCall){
            updateContactUs(call.execute());
        }else{
            call.enqueue(new Callback<ContactUsApiModel>() {
                @Override
                public void onResponse(Call<ContactUsApiModel> call, Response<ContactUsApiModel> response) {
                    updateContactUs(response);
                }
                @Override
                public void onFailure(Call<ContactUsApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateContactUs(Response<ContactUsApiModel> response){
        if(response.isSuccessful() && response.body()!=null) {
            ObjectTableUtil.setContactUsModel(context,response.body().getItem().get(0));

            oldVersionModel.setContact(newVersionModel.getContact());
        }
    }

    private void syncParameters() throws IOException {
        Call<ParameterApiModel> call = RestApiCalls.getParameters(context);

        if(synchronizedCall){
            updateParameters(call.execute());
        }else{
            call.enqueue(new Callback<ParameterApiModel>() {
                @Override
                public void onResponse(Call<ParameterApiModel> call, Response<ParameterApiModel> response) {
                    updateParameters(response);
                }
                @Override
                public void onFailure(Call<ParameterApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateParameters(Response<ParameterApiModel> response){
        if(response.isSuccessful() && response.body()!=null) {
            ObjectTableUtil.setSorting(context,response.body().getSorting());
            ObjectTableUtil.setFuel(context,response.body().getFuel());
            ObjectTableUtil.setMonths(context,response.body().getMonths());
            ObjectTableUtil.setPlanningToBuy(context,response.body().getPlanning_to_buy());
            ObjectTableUtil.setPrice(context,response.body().getPrice());
            ObjectTableUtil.setPriceRange(context,response.body().getPrice_range());
            ObjectTableUtil.setCarColors(context,response.body().getColors());
            ObjectTableUtil.setCarBodyTypes(context,response.body().getBody_type());

            oldVersionModel.setParameters(newVersionModel.getParameters());
        }
    }

    private void syncColors() throws IOException {
        Call<ColorApiModel> call = RestApiCalls.getColors(context);

        if(synchronizedCall){
            ObjectTableUtil.setUserColors(context,call.execute().body());
            oldVersionModel.setColor_theme(newVersionModel.getColor_theme());
        }else{
            call.enqueue(new Callback<ColorApiModel>() {
                @Override
                public void onResponse(Call<ColorApiModel> call, Response<ColorApiModel> response) {
                    ObjectTableUtil.setUserColors(context,response.body());
                    oldVersionModel.setColor_theme(newVersionModel.getColor_theme());
                }
                @Override
                public void onFailure(Call<ColorApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void syncNavigations() throws IOException {
        Call<MenuApiModel> call = RestApiCalls.getNavigation(context);

        if(synchronizedCall){
            ObjectTableUtil.setNavigation(context,call.execute().body());
            oldVersionModel.setNavigations(newVersionModel.getNavigations());
        }else{
            call.enqueue(new Callback<MenuApiModel>() {
                @Override
                public void onResponse(Call<MenuApiModel> call, Response<MenuApiModel> response) {
                    ObjectTableUtil.setNavigation(context,response.body());
                    oldVersionModel.setNavigations(newVersionModel.getNavigations());
                }
                @Override
                public void onFailure(Call<MenuApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void syncCityList() throws IOException {
        Call<CityApiModel> call = RestApiCalls.getCityList(context);

        if(synchronizedCall){
            updateCityList(call.execute());
        }else{
            call.enqueue(new Callback<CityApiModel>() {
                @Override
                public void onResponse(Call<CityApiModel> call, Response<CityApiModel> response) {
                    updateCityList(response);
                }
                @Override
                public void onFailure(Call<CityApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateCityList(Response<CityApiModel> response) {
        if(response.isSuccessful() && response.body()!=null){
            CityApiModel cityApiModel = response.body();

            context.getContentResolver().delete(CitytableColumns.CONTENT_URI,null,null);

            ArrayList<ContentValues> values = new ArrayList<>();

            CitytableContentValues citytableContentValues;

            for(CityApiModel.StateModel stateModel: cityApiModel.getItems()){

                for(CityApiModel.CityModel cityModel: stateModel.getCities()) {
                    citytableContentValues = new CitytableContentValues();

                    citytableContentValues.putCityId(cityModel.getCityId());
                    citytableContentValues.putCityName(cityModel.getName());
                    citytableContentValues.putPincode(cityModel.getPin_code());
                    citytableContentValues.putStateName(stateModel.getState_name());
                    citytableContentValues.putStateId(stateModel.getState_id());

                    values.add(citytableContentValues.values());
                }
            }

            int count = context.getContentResolver().bulkInsert(CitytableColumns.CONTENT_URI,values.toArray(new ContentValues[0]));

            Logger.e(TAG,"City added:"+count);

            oldVersionModel.setCity(newVersionModel.getCity());
        }
    }

    private void syncMakeModels() throws IOException {
        Call<MakeApiModel> call = RestApiCalls.getMakeModels(context);

        if (synchronizedCall) {
            updateMakeModels(call.execute());
        } else {
            call.enqueue(new Callback<MakeApiModel>() {
                @Override
                public void onResponse(Call<MakeApiModel> call, Response<MakeApiModel> response) {
                    updateMakeModels(response);
                }

                @Override
                public void onFailure(Call<MakeApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateMakeModels(Response<MakeApiModel> response) {
        if (response.isSuccessful() && response.body() != null) {
            MakeApiModel makeApiModel = response.body();

            context.getContentResolver().delete(MaketableColumns.CONTENT_URI, null, null);
            context.getContentResolver().delete(ModeltableColumns.CONTENT_URI, null, null);
            context.getContentResolver().delete(VersiontableColumns.CONTENT_URI, null, null);

            ArrayList<ContentValues> valuesMake = new ArrayList<>();
            ArrayList<ContentValues> valuesModel = new ArrayList<>();
            ArrayList<ContentValues> valuesVersion = new ArrayList<>();

            MaketableContentValues maketableContentValues;
            ModeltableContentValues modeltableContentValues;
            VersiontableContentValues versiontableContentValues;

            if(makeApiModel.getItems()!=null)
            for (MakeApiModel.MakeModel makeModel : makeApiModel.getItems()) {
                Logger.e(TAG,"make:"+makeModel.toString());

                maketableContentValues = new MaketableContentValues();
                maketableContentValues.putMakeId(makeModel.getId());
                maketableContentValues.putMakeName(makeModel.getMake());
                maketableContentValues.putRank(makeModel.getRank());
                maketableContentValues.putInStock(makeModel.getIn_stock());
                valuesMake.add(maketableContentValues.values());

                if(makeModel.getModel()!=null)
                for (MakeApiModel.MakeModel.Model model : makeModel.getModel()) {
                    modeltableContentValues = new ModeltableContentValues();
                    modeltableContentValues.putMakeId(makeModel.getId());
                    modeltableContentValues.putRank(model.getRank());
                    modeltableContentValues.putModelId(model.getId());
                    modeltableContentValues.putModelName(model.getModel());
                    modeltableContentValues.putInStock(model.getIn_stock());
                    valuesModel.add(modeltableContentValues.values());

                    if(model.getVersion()!=null)
                    for (MakeApiModel.MakeModel.Model.Version version : model.getVersion()) {
                        versiontableContentValues = new VersiontableContentValues();
                        versiontableContentValues.putModelId(model.getId());
                        versiontableContentValues.putVersionId(version.getId());
                        versiontableContentValues.putFuelType(version.getFuel());
                        versiontableContentValues.putVersionName(version.getVersion());
                        valuesVersion.add(versiontableContentValues.values());
                    }
                }
            }

            int count = context.getContentResolver().bulkInsert(MaketableColumns.CONTENT_URI, valuesMake.toArray(new ContentValues[0]));

            Logger.e(TAG, "Make added:" + count);

            count = context.getContentResolver().bulkInsert(ModeltableColumns.CONTENT_URI, valuesModel.toArray(new ContentValues[0]));

            Logger.e(TAG, "Model added:" + count);

            count = context.getContentResolver().bulkInsert(VersiontableColumns.CONTENT_URI, valuesVersion.toArray(new ContentValues[0]));

            Logger.e(TAG, "Version added:" + count);

            oldVersionModel.setMake(newVersionModel.getMake());
        }
    }

    private void syncShowroom() throws IOException {
        Call<ShowroomApiModel> call = RestApiCalls.getShowrooms(context);

        if (synchronizedCall) {
            updateShowrooms(call.execute());
        } else {
            call.enqueue(new Callback<ShowroomApiModel>() {
                @Override
                public void onResponse(Call<ShowroomApiModel> call, Response<ShowroomApiModel> response) {
                    updateShowrooms(response);
                }

                @Override
                public void onFailure(Call<ShowroomApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateShowrooms(Response<ShowroomApiModel> response) {
        if (response.isSuccessful() && response.body() != null) {
            ShowroomApiModel showroomApiModel = response.body();

            context.getContentResolver().delete(ShowroomtableColumns.CONTENT_URI, null, null);

            ArrayList<ContentValues> values = new ArrayList<>();
            ShowroomtableContentValues showroomtableContentValues;

            for (ShowroomApiModel.ShowroomModel showroomModel : showroomApiModel.getItems()) {

                showroomtableContentValues = new ShowroomtableContentValues();
                showroomtableContentValues.putName(showroomModel.getName());
                showroomtableContentValues.putAddress(showroomModel.getAddress());
                showroomtableContentValues.putCity(showroomModel.getCity());
                showroomtableContentValues.putState(showroomModel.getState());
                showroomtableContentValues.putImages(new Gson().toJson(showroomModel.getImages()));
                showroomtableContentValues.putLatitude(showroomModel.getLat());
                showroomtableContentValues.putLongitude(showroomModel.getLng());
                showroomtableContentValues.putMobile(showroomModel.getMobile());
                showroomtableContentValues.putLocality(showroomModel.getLocality());
                showroomtableContentValues.putPincode(showroomModel.getPin_code());
                showroomtableContentValues.putShowroomId(showroomModel.getId());

                if (showroomModel.getPhone() != null) {
                    if (showroomModel.getPhone().size() > 0)
                        showroomtableContentValues.putPhone1(showroomModel.getPhone().get(0));
                    if (showroomModel.getPhone().size() > 1)
                        showroomtableContentValues.putPhone2(showroomModel.getPhone().get(1));
                }
                if (showroomModel.getEmail() != null) {
                    if (showroomModel.getEmail().size() > 0)
                        showroomtableContentValues.putEmail1(showroomModel.getEmail().get(0));
                    if (showroomModel.getEmail().size() > 1)
                        showroomtableContentValues.putEmail2(showroomModel.getEmail().get(1));
                }

                values.add(showroomtableContentValues.values());
            }

            int count = context.getContentResolver().bulkInsert(ShowroomtableColumns.CONTENT_URI, values.toArray(new ContentValues[0]));

            Logger.e(TAG, "Showroom added:" + count);

            oldVersionModel.setShowroom(newVersionModel.getShowroom());
        }
    }

    private void syncTestimonial() throws IOException {
        Call<TestimonialApiModel> call = RestApiCalls.getTestimonials(context);

        if (synchronizedCall) {
            updateTestimonial(call.execute());
        } else {
            call.enqueue(new Callback<TestimonialApiModel>() {
                @Override
                public void onResponse(Call<TestimonialApiModel> call, Response<TestimonialApiModel> response) {
                    updateTestimonial(response);
                }

                @Override
                public void onFailure(Call<TestimonialApiModel> call, Throwable t) {
                }
            });
        }
    }

    private void updateTestimonial(Response<TestimonialApiModel> response) {
        if (response.isSuccessful() && response.body() != null) {
            TestimonialApiModel testimonialApiModel = response.body();

            context.getContentResolver().delete(TestimonialtableColumns.CONTENT_URI, null, null);

            ArrayList<ContentValues> values = new ArrayList<>();
            TestimonialtableContentValues testimonialtableContentValues;

            for (TestimonialApiModel.TestimonialModel testimonialModel : testimonialApiModel.getItems()) {
                testimonialtableContentValues = new TestimonialtableContentValues();
                testimonialtableContentValues.putDate(testimonialModel.getDate());
                testimonialtableContentValues.putImageUrl(testimonialModel.getImage_name());
                testimonialtableContentValues.putName(testimonialModel.getName());
                testimonialtableContentValues.putRating(testimonialModel.getRating());
                testimonialtableContentValues.putRole(testimonialModel.getRole());
                testimonialtableContentValues.putTestimonial(testimonialModel.getTestimonial());

                values.add(testimonialtableContentValues.values());
            }

            int count = context.getContentResolver().bulkInsert(TestimonialtableColumns.CONTENT_URI, values.toArray(new ContentValues[0]));

            Logger.e(TAG, "Testimonial added:" + count);

            oldVersionModel.setTestimonial(newVersionModel.getTestimonial());
        }
    }
}
