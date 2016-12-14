package com.gaadi.pratnerapps.models;

import com.gaadi.pratnerapps.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.gaadi.pratnerapps.utils.Utils.getStringCommaSeparated;

/**
 * Created by Mushareb Ali on 8/6/16.
 */
public class BuyCarFilterModel implements Serializable{
    private ArrayList<String> priceRange = new ArrayList<>();
    private ArrayList<String> kmRange = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();
    private ArrayList<String> bodyTypes = new ArrayList<>();
    private HashMap<Integer, ArrayList<Integer>> models = new HashMap<>();
    private String sort = "";
    private String page = "1";

    public BuyCarFilterModel(){

    }

    public BuyCarFilterModel(BuyCarFilterModel buyCarFilterModel){
        priceRange = new ArrayList<>(buyCarFilterModel.priceRange);
        kmRange = new ArrayList<>(buyCarFilterModel.kmRange);
        colors = new ArrayList<>(buyCarFilterModel.colors);
        bodyTypes = new ArrayList<>(buyCarFilterModel.bodyTypes);
        models = new HashMap<>(buyCarFilterModel.models);
        sort = buyCarFilterModel.sort;
        page = buyCarFilterModel.page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public ArrayList<String> getKmRange() {
        return kmRange;
    }

    public void setKmRange(ArrayList<String> kmRange) {
        this.kmRange = kmRange;
    }

    public ArrayList<String> getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(ArrayList<String> priceRange) {
        this.priceRange = priceRange;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public ArrayList<String> getBodyTypes() {
        return bodyTypes;
    }

    public void setBodyTypes(ArrayList<String> bodyTypes) {
        this.bodyTypes = bodyTypes;
    }

    public HashMap<Integer, ArrayList<Integer>> getModels() {
        return models;
    }

    public void setModels(HashMap<Integer, ArrayList<Integer>> models) {
        this.models = models;
    }

    public Map<String, String> getFieldMap() {
        HashMap<String,String> params = new HashMap<>();

        String value = Utils.getStringCommaSeparated(priceRange.iterator());
        if(!value.isEmpty())
            params.put("priceRangefrom",value);

        if (!getSort().isEmpty())
            params.put("sort", getSort());

        if (!getPage().isEmpty())
            params.put("page", getPage());


        value = Utils.getStringCommaSeparated(colors.iterator());
        if(!value.isEmpty())
            params.put("color",value);

        value = Utils.getStringCommaSeparated(bodyTypes.iterator());
        if(!value.isEmpty())
            params.put("body",value);

        value = getStringCommaSeparatedOfIntegers(models.keySet().iterator(),models);
        if(!value.isEmpty())
            params.put("makeId",value);

        value = Utils.getStringCommaSeparated(kmRange.iterator());
        if(!value.isEmpty())
            params.put("kmRange",value);

        ArrayList<Integer> list = new ArrayList<>();

        Iterator<Integer> iterator = models.keySet().iterator();

        while(iterator.hasNext()){
            int val = iterator.next();

            if(models.get(val).size()>0){
                list.addAll(models.get(val));
            }
        }

        value = getStringCommaSeparatedOfIntegers(list.iterator(),null);
        if(!value.isEmpty())
            params.put("modelId",value);

        return params;
    }

    private String getStringCommaSeparatedOfIntegers(Iterator<Integer> list, HashMap<Integer, ArrayList<Integer>> models) {
        boolean firstRecord = true;

        StringBuilder stringBuilder = new StringBuilder();
        while(list.hasNext()){

            int val = list.next();

//            if(models!=null && models.get(val).size()<=0)continue;

            if(!firstRecord){
                stringBuilder.append(",");
            }else{
                firstRecord=false;
            }

            stringBuilder.append(val);
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "BuyCarFilterModel{" +
                "priceRange=" + priceRange +
                "kmRange=" + kmRange +
                ", colors=" + colors +
                ", bodyTypes=" + bodyTypes +
                ", models=" + models +
                ", sort='" + sort + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
