package com.gaadi.pratnerapps.utils;

import android.content.Context;

import com.gaadi.pratnerapps.models.ShowroomApiModel;
import com.gaadi.pratnerapps.models.TestimonialApiModel;
import com.gaadi.pratnerapps.provider.showroomtable.ShowroomtableCursor;
import com.gaadi.pratnerapps.provider.showroomtable.ShowroomtableSelection;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableCursor;
import com.gaadi.pratnerapps.provider.testimonialtable.TestimonialtableSelection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by priyarawat on 3/6/16.
 */
public class DatabaseUtil {
    public static ArrayList<ShowroomApiModel.ShowroomModel> getShowrooms(Context context){
        ArrayList<ShowroomApiModel.ShowroomModel> showroomModels = new ArrayList<>();
        ShowroomtableCursor showroomtableCursor = new ShowroomtableSelection().query(context);

        ShowroomApiModel.ShowroomModel showroomModel;
        Type listType = new TypeToken<List<String>>() {}.getType();

        if(showroomtableCursor!=null && showroomtableCursor.getCount()>0 && showroomtableCursor.moveToFirst()){
            while(!showroomtableCursor.isAfterLast()){
                showroomModel = new ShowroomApiModel.ShowroomModel();
                showroomModel.setAddress(showroomtableCursor.getAddress());
                ArrayList<String> imagesList = new Gson().fromJson(showroomtableCursor.getImages(), listType);
                showroomModel.setImages(imagesList);
                showroomModel.setAddress(showroomtableCursor.getAddress());
                showroomModel.setCity(showroomtableCursor.getCity());
                showroomModel.setId(showroomtableCursor.getShowroomId());
                showroomModel.setLat(showroomtableCursor.getLatitude());
                showroomModel.setLng(showroomtableCursor.getLongitude());
                showroomModel.setMobile(showroomtableCursor.getMobile());
                showroomModel.setName(showroomtableCursor.getName());
                showroomModel.setLocality(showroomtableCursor.getLocality());
                showroomModel.setPin_code(showroomtableCursor.getPincode());
                showroomModel.setState(showroomtableCursor.getState());
                ArrayList<String> emailList = new ArrayList<>();

                emailList.add(showroomtableCursor.getEmail1());

                if(!Utils.isBlank(showroomtableCursor.getEmail2())){
                    emailList.add(showroomtableCursor.getEmail2());
                }

                showroomModel.setEmail(emailList);

                ArrayList<String> phones = new ArrayList<>();

                phones.add(showroomtableCursor.getPhone1());

                if(!Utils.isBlank(showroomtableCursor.getPhone2())){
                    phones.add(showroomtableCursor.getPhone2());
                }

                showroomModel.setPhone(phones);

                showroomModels.add(showroomModel);
                showroomtableCursor.moveToNext();
            }
            showroomtableCursor.close();
        }

        return  showroomModels;
    }

    public static ArrayList<TestimonialApiModel.TestimonialModel> getTopTestimonial(Context context, int count) {
        if(count<=0){
            count = 5;
        }

        ArrayList<TestimonialApiModel.TestimonialModel> testimonialModels = new ArrayList<>();

        TestimonialApiModel.TestimonialModel testimonialModel;

        TestimonialtableCursor testimonialtableCursor = new TestimonialtableSelection().orderByTestimonialId(true).limit(count).query(context);

        if(testimonialtableCursor!=null && testimonialtableCursor.getCount()>0 && testimonialtableCursor.moveToFirst()){
            while(!testimonialtableCursor.isAfterLast()){
                testimonialModel = new TestimonialApiModel.TestimonialModel();

                testimonialModel.setDate(testimonialtableCursor.getDate());
                testimonialModel.setImage_name(testimonialtableCursor.getImageUrl());
                testimonialModel.setName(testimonialtableCursor.getName());
                testimonialModel.setRating(testimonialtableCursor.getRating());
                testimonialModel.setRole(testimonialtableCursor.getRole());
                testimonialModel.setTestimonial(testimonialtableCursor.getTestimonial());

                testimonialModels.add(testimonialModel);
                testimonialtableCursor.moveToNext();
            }
            testimonialtableCursor.close();
        }

        return testimonialModels;
    }
}
