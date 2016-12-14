package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.buyCar.FragmentActionsListener;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyCarAdvanceFilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyCarAdvanceFilterFragment extends Fragment implements FragmentActionsListener {

    private static final String TAG = "BuyCarAdvanceFilterFragment";
    private BuyCarFilterModel buyCarFilterModel;

    public static BuyCarAdvanceFilterFragment newInstance(BuyCarFilterModel buyCarFilterModel) {
        BuyCarAdvanceFilterFragment fragment = new BuyCarAdvanceFilterFragment();
        fragment.buyCarFilterModel = buyCarFilterModel;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advance_filter, container, false);

       /* BaseCheckListModel fuelTypeModel = new BaseCheckListModel();
        fuelTypeModel.setTitle("Fuel Type");
        fuelTypeModel.setItem(getFuelTypes());

        BaseCheckListModel ownerTypeModel = new BaseCheckListModel();
        ownerTypeModel.setTitle("Owner Type");
        ownerTypeModel.setItem(getOwnerTypes());

        BaseCheckListModel transTypeModel = new BaseCheckListModel();
        transTypeModel.setTitle("Transmission Type");
        transTypeModel.setItem(getTransmissionType());

        BaseCheckListModel listingTypeModel = new BaseCheckListModel();
        listingTypeModel.setTitle("Listing Type");
        listingTypeModel.setItem(getListingType());*/

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutColor, BuyCarFilterColorFragment.newInstance(buyCarFilterModel)).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutBody, BuyCarBodyTypeFragment.newInstance(buyCarFilterModel)).commit();
       /* //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flFuelType, BaseCheckBoxFragment.newInstance(fuelTypeModel, this)).commit();
        //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flOwnerType, BaseCheckBoxFragment.newInstance(ownerTypeModel, this)).commit();
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flTransmissionType, BaseCheckBoxFragment.newInstance(transTypeModel, this)).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flListingType, BaseCheckBoxFragment.newInstance(listingTypeModel, this)).commit();*/
        return view;
    }

  /*  private ArrayList<CheckListItem> getOwnerTypes() {
        ArrayList<CheckListItem> fuelTypes = new ArrayList<>();
        fuelTypes.add(new CheckListItem("1st", "1st"));
        fuelTypes.add(new CheckListItem("2nd", "2nd"));
        fuelTypes.add(new CheckListItem("3rd+", "3rd+"));
        return fuelTypes;
    }

    private ArrayList<CheckListItem> getTransmissionType() {
        ArrayList<CheckListItem> fuelTypes = new ArrayList<>();
        fuelTypes.add(new CheckListItem("Automatic", "Automatic"));
        fuelTypes.add(new CheckListItem("Manual", "Manual"));
        return fuelTypes;
    }

    private ArrayList<CheckListItem> getFuelTypes() {
        ArrayList<CheckListItem> fuelTypes = new ArrayList<>();
        fuelTypes.add(new CheckListItem("Petrol", "Petrol"));
        fuelTypes.add(new CheckListItem("Diesel", "Diesel"));
        fuelTypes.add(new CheckListItem("CNG", "CNG"));
        fuelTypes.add(new CheckListItem("Electric", "Electric"));
        return fuelTypes;
    }

    private ArrayList<CheckListItem> getListingType() {
        ArrayList<CheckListItem> fuelTypes = new ArrayList<>();
        fuelTypes.add(new CheckListItem("Dealer", "Dealer"));
        fuelTypes.add(new CheckListItem("Individual", "Individual"));
        return fuelTypes;
    }*/

    @Override
    public void onActionDone(Object object, Fragment fragment) {
  /*      CheckChangedEvent event = (CheckChangedEvent) object;
        Log.d(TAG, "onActionDone: " + event.getPosition() + " checked : " + event.isChecked());
*/
    }

    @Override
    public void onActionCompleted(Object object, Fragment fragment) {
        Log.d(TAG, "onActionCompleted: ");
    }
}
