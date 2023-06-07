package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;

public class SeekingTwoFragment extends Fragment {
    private EditText editTextAddress;
    private EditText editTextCity;
    private EditText editTextPostalCode;
    private EditText editTextCountry;
    private EditText editTextPhoneNumber;
    private EditText editTextBirthDate;

    private Bundle bundleFragmentOne;
    private String salutation;
    private String firstName;
    private String infix;
    private String lastName;
    private String password;
    private String passwordAgain;
    private int test = 0;

    public SeekingTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_two, container, false);


//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 2", " " + fragmentDataList);


        // Initialize the EditText views
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextCity = view.findViewById(R.id.editTextCity);
        editTextPostalCode = view.findViewById(R.id.editTextPostalCode);
        editTextCountry = view.findViewById(R.id.editTextCountry);
        editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
        editTextBirthDate = view.findViewById(R.id.editTextBirthDate);


        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Address")) {
                String address = fragmentDataList.get("Address");
                editTextAddress.setText(address);
            }
            if (fragmentDataList.containsKey("CityPersonal")) {
                String city = fragmentDataList.get("CityPersonal");
                editTextCity.setText(city);
            }
            if (fragmentDataList.containsKey("PostalCode")) {
                String postalCode = fragmentDataList.get("PostalCode");
                editTextPostalCode.setText(postalCode);
            }
            if (fragmentDataList.containsKey("Country")) {
                String country = fragmentDataList.get("Country");
                editTextCountry.setText(country);
            }
            if (fragmentDataList.containsKey("PhoneNumber")) {
                String phoneNumber = fragmentDataList.get("PhoneNumber");
                editTextPhoneNumber.setText(phoneNumber);
            }
            if (fragmentDataList.containsKey("BirthDate")) {
                String birthDate = fragmentDataList.get("BirthDate");
                editTextBirthDate.setText(birthDate);
            }
        }


        return view;
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Address", getAddress());
            fragmentDataList.put("CityPersonal", getCity());
            fragmentDataList.put("PostalCode", getPostalCode());
            fragmentDataList.put("Country", getCountry());
            fragmentDataList.put("PhoneNumber", getPhoneNumber());
            fragmentDataList.put("BirthDate", getBirthDate());

        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getAddress()) &&
                !TextUtils.isEmpty(getCity()) &&
                !TextUtils.isEmpty(getPostalCode()) &&
                !TextUtils.isEmpty(getCountry()) &&
                !TextUtils.isEmpty(getPhoneNumber()) &&
                !TextUtils.isEmpty(getBirthDate());
    }

    public String getAddress() {
        return editTextAddress.getText().toString();
    }

    public String getCity() {
        return editTextCity.getText().toString();
    }

    public String getPostalCode() {
        return editTextPostalCode.getText().toString();
    }

    public String getCountry() {
        return editTextCountry.getText().toString();
    }

    public String getPhoneNumber() {
        return editTextPhoneNumber.getText().toString();
    }

    public String getBirthDate() {
        return editTextBirthDate.getText().toString();
    }

}