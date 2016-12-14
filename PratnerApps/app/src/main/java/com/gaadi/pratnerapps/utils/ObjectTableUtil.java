package com.gaadi.pratnerapps.utils;

import android.content.Context;

import com.gaadi.pratnerapps.models.BuyCarIInfo;
import com.gaadi.pratnerapps.models.ColorApiModel;
import com.gaadi.pratnerapps.models.ContactUsApiModel;
import com.gaadi.pratnerapps.models.FinanceApiModel;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.models.InsuranceApiModel;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.models.ServicesApiModel;
import com.gaadi.pratnerapps.models.SplashApiModel;
import com.gaadi.pratnerapps.models.StartupApiModel;
import com.gaadi.pratnerapps.models.UserModel;
import com.gaadi.pratnerapps.provider.objecttable.ObjecttableContentValues;
import com.gaadi.pratnerapps.provider.objecttable.ObjecttableCursor;
import com.gaadi.pratnerapps.provider.objecttable.ObjecttableSelection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vinodtakhar on 2/6/16.
 */
public class ObjectTableUtil {
    private static final String TAG = "ObjectTableUtil.java";

    public static final String START_UP_VERSION_MODEL = "start_up_model";
    public static final String PAGE_CONTACT_US = "page_contact_us";
    public static final String PAGE_PARAMETERS_SORTING = "page_parameters_sorting";
    public static final String PAGE_PARAMETERS_PRICE = "page_parameters_price";
    public static final String PAGE_PARAMETERS_PRICE_RANGE = "page_parameters_price_range";
    public static final String PAGE_PARAMETERS_MONTHS = "page_parameters_months";
    public static final String PAGE_PARAMETERS_FUEL = "page_parameters_fuel";
    public static final String PAGE_PARAMETERS_CAR_COLOR = "page_parameters_car_color";
    public static final String PAGE_PARAMETERS_CAR_BODY = "page_parameters_car_body";
    public static final String PAGE_PARAMETERS_PLANNING_TO_BUY = "page_parameters_planning_to_buy";
    private static final String PAGE_RECENT_LISTING = "page_recent_listing";
    private static final String PAGE_INSURANCE = "page_insurance";
    private static final String PAGE_FINANCE = "page_finance";

    private static final String CONFIG_USER_COLORS = "config_user_colors";
    private static final String CONFIG_SPLASH = "config_splash";
    private static final String CONFIG_HOME = "config_home";
    private static final String CONFIG_NAVIGATION = "config_navigation";
    private static final String USER_MODEL = "user_model";
    private static final String PAGE_SERVICE = "page_services";


    private static void addUpdateObjectModel(Context context,String key,String json){
        ObjecttableContentValues objecttableContentValues = new ObjecttableContentValues();
        objecttableContentValues.putKeyValue(json);
        objecttableContentValues.putKeyId(key);
        objecttableContentValues.insert(context);
    }

    private static String getObjectJson(Context context,String key){
        String json  = null;

        ObjecttableCursor objecttableCursor = new ObjecttableSelection().keyId(key).query(context);

        if (objecttableCursor != null && objecttableCursor.getCount() > 0 && objecttableCursor.moveToFirst()) {
            json= objecttableCursor.getKeyValue();
            objecttableCursor.close();
        }

        Logger.e(TAG,"key:"+key+"->"+"JSON:"+json);

        return json;
    }

    public static StartupApiModel.VersionModel getVersionModel(Context context) {

        String json = getObjectJson(context,START_UP_VERSION_MODEL);

        if (json==null) {
            return new StartupApiModel.VersionModel();
        } else {
            return new Gson().fromJson(json, StartupApiModel.VersionModel.class);
        }
    }
    public static void setVersionModel(Context context, StartupApiModel.VersionModel oldVersionModel) {
        addUpdateObjectModel(context,START_UP_VERSION_MODEL,new Gson().toJson(oldVersionModel));
    }

    private static ArrayList<ParameterApiModel.KeyValueModel> getKeyValueModelList(Context context,String key) {

        Type listType = new TypeToken<List<ParameterApiModel.KeyValueModel>>() {}.getType();

        String json = getObjectJson(context, key);

        if (json==null) {
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(json, listType);
        }
    }

    private static HashMap<String,String> getHashType(Context context,String key) {

        Type hashType = new TypeToken<HashMap<String, String>>(){}.getType();

        String json = getObjectJson(context, key);

        Logger.e(TAG,"Key:"+key+"JSON:"+json);

        if (json==null) {
            return new HashMap<String,String>();
        } else {
            return new Gson().fromJson(json, hashType);
        }
    }

    public static void setSorting(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context,PAGE_PARAMETERS_SORTING,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getSorting(Context context){
        return getKeyValueModelList(context,PAGE_PARAMETERS_SORTING);
    }

    public static void setPrice(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context,PAGE_PARAMETERS_PRICE,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getPrice(Context context){
        return getKeyValueModelList(context,PAGE_PARAMETERS_PRICE);
    }

    public static void setPriceRange(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context,PAGE_PARAMETERS_PRICE_RANGE,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getPriceRange(Context context){
        return getKeyValueModelList(context,PAGE_PARAMETERS_PRICE_RANGE);
    }

    public static void setMonths(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context, PAGE_PARAMETERS_MONTHS,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getMonths(Context context){
        return getKeyValueModelList(context, PAGE_PARAMETERS_MONTHS);
    }

    public static void setFuel(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context,PAGE_PARAMETERS_FUEL,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getFuel(Context context){
        return getKeyValueModelList(context,PAGE_PARAMETERS_FUEL);
    }

    public static void setPlanningToBuy(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context,PAGE_PARAMETERS_PLANNING_TO_BUY,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getPlanningToBuy(Context context){
        return getKeyValueModelList(context,PAGE_PARAMETERS_PLANNING_TO_BUY);
    }

    public static void setCarColors(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context,PAGE_PARAMETERS_CAR_COLOR,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getCarColors(Context context){
        return getKeyValueModelList(context,PAGE_PARAMETERS_CAR_COLOR);
    }

    public static void setCarBodyTypes(Context context, ArrayList<ParameterApiModel.KeyValueModel> list) {
        addUpdateObjectModel(context,PAGE_PARAMETERS_CAR_BODY,new Gson().toJson(list));
    }

    public static ArrayList<ParameterApiModel.KeyValueModel> getCarBodyTypes(Context context){
        return getKeyValueModelList(context,PAGE_PARAMETERS_CAR_BODY);
    }

    public static ContactUsApiModel.ContactUsModel getContactUsModel(Context context){
        String json = getObjectJson(context,PAGE_CONTACT_US);

        if (json==null) {
            return new ContactUsApiModel.ContactUsModel();
        } else {
            return new Gson().fromJson(json, ContactUsApiModel.ContactUsModel.class);
        }
    }
    public static void setContactUsModel(Context context, ContactUsApiModel.ContactUsModel contactUsModel) {
        addUpdateObjectModel(context,PAGE_CONTACT_US,new Gson().toJson(contactUsModel));
    }

    public static void setRecentListingModel(Context context, BuyCarIInfo buyCarIInfo) {
        addUpdateObjectModel(context, PAGE_RECENT_LISTING, new Gson().toJson(buyCarIInfo));
    }

    public static BuyCarIInfo getRecentListingModel(Context context) {
        String json = getObjectJson(context,PAGE_RECENT_LISTING);

        if (json==null) {
            return new BuyCarIInfo();
        } else {
            return new Gson().fromJson(json, BuyCarIInfo.class);
        }
    }

    public static void setUserColors(Context context,ColorApiModel userColors) {
        addUpdateObjectModel(context,CONFIG_USER_COLORS,new Gson().toJson(userColors));
    }

    public static ColorApiModel getUserColors(Context context){
        String json = getObjectJson(context,CONFIG_USER_COLORS);

        if (json==null) {
            return new ColorApiModel();
        } else {
            return new Gson().fromJson(json, ColorApiModel.class);
        }
    }

    public static void setSplashConfig(Context context, SplashApiModel.SplashConfig splashConfig) {
        addUpdateObjectModel(context,CONFIG_SPLASH,new Gson().toJson(splashConfig));
    }

    public static SplashApiModel.SplashConfig getSplashConfig(Context context){
        String json = getObjectJson(context,CONFIG_SPLASH);

        if (json==null) {
            return new SplashApiModel.SplashConfig();
        } else {
            return new Gson().fromJson(json, SplashApiModel.SplashConfig.class);
        }
    }

    public static void setHomeConfig(Context context, HomeApiModel homeApiModel) {
        addUpdateObjectModel(context,CONFIG_HOME,new Gson().toJson(homeApiModel));
    }

    public static HomeApiModel getHomeConfig(Context context){
        String json = getObjectJson(context,CONFIG_HOME);

        if (json==null) {
            return new HomeApiModel();
        } else {
            return new Gson().fromJson(json, HomeApiModel.class);
        }
    }

    public static void setNavigation(Context context, MenuApiModel navigationApiModel) {
        //for temp
//        MenuApiModel.MenuModel menuModel = new MenuApiModel.MenuModel();
        /*menuModel.setKey("insurance");
        menuModel.setValue("Insurance");
        navigationApiModel.getItems().add(menuModel);*/

        /*menuModel = new MenuApiModel.MenuModel();
        menuModel.setKey("finance");
        menuModel.setValue("Finance");
        navigationApiModel.getItems().add(menuModel);*/

//        menuModel = new MenuApiModel.MenuModel();
//        menuModel.setKey("ucv");
//        menuModel.setValue("Car Valuation");
//        navigationApiModel.getItems().add(menuModel);

        /*menuModel = new MenuApiModel.MenuModel();
        menuModel.setKey("service");
        menuModel.setValue("Service");
        navigationApiModel.getItems().add(menuModel);*/

        addUpdateObjectModel(context,CONFIG_NAVIGATION,new Gson().toJson(navigationApiModel));
    }

    public static MenuApiModel getNavigation(Context context){
        String json = getObjectJson(context,CONFIG_NAVIGATION);

        if (json==null) {
            return new MenuApiModel();
        } else {
            return new Gson().fromJson(json, MenuApiModel.class);
        }
    }

    public static void setUserModel(Context context, UserModel userModel) {
        addUpdateObjectModel(context,USER_MODEL,new Gson().toJson(userModel));
    }

    public static UserModel getUserModel(Context context){
        String json = getObjectJson(context,USER_MODEL);

        if (json==null) {
            return new UserModel();
        } else {
            return new Gson().fromJson(json, UserModel.class);
        }
    }

    public static void setServiceModel(Context context, ServicesApiModel financeApiModel) {
        addUpdateObjectModel(context,PAGE_SERVICE,new Gson().toJson(financeApiModel));
    }

    public static ServicesApiModel getServiceModel(Context context){
        String json = getObjectJson(context,PAGE_SERVICE);

        if (json==null) {
            return null;
        } else {
            return new Gson().fromJson(json, ServicesApiModel.class);
        }
    }

    public static void setFinanceModel(Context context, FinanceApiModel financeApiModel) {
        addUpdateObjectModel(context,PAGE_FINANCE,new Gson().toJson(financeApiModel));
    }

    public static FinanceApiModel getFinanceModel(Context context){
        String json = getObjectJson(context,PAGE_FINANCE);

        if (json==null) {
            return null;
        } else {
            return new Gson().fromJson(json, FinanceApiModel.class);
        }
    }

    public static void setInsuranceModel(Context context, InsuranceApiModel insuranceApiModel) {
        addUpdateObjectModel(context,PAGE_INSURANCE,new Gson().toJson(insuranceApiModel));
    }

    public static InsuranceApiModel getInsuranceModel(Context context){
        String json = getObjectJson(context,PAGE_INSURANCE);

        if (json==null) {
            return null;
        } else {
            return new Gson().fromJson(json, InsuranceApiModel.class);
        }
    }
}
