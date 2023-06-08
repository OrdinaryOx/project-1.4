package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SeekingTwoFragment extends Fragment {
    private EditText editTextAddress;
    private EditText editTextCity;
    private EditText editTextPostalCode;
    private EditText editTextCountry;
    private EditText editTextPhoneNumber;
    private EditText editTextBirthDate;

    private EditText editTextHouseNumber;

    private boolean isFragmentAttached = false;


    public SeekingTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_two, container, false);
        isFragmentAttached = true;

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
        editTextHouseNumber = view.findViewById(R.id.editTextHouseNumber);
        editTextPostalCode.addTextChangedListener(textWatcher);
        editTextHouseNumber.addTextChangedListener(textWatcher);


        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Address")) {
                String address = fragmentDataList.get("Address");
                editTextAddress.setText(address);
            }
            if (fragmentDataList.containsKey("HouseNumber")) {
                String houseNumber = fragmentDataList.get("HouseNumber");
                editTextHouseNumber.setText(houseNumber);
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
            fragmentDataList.put("HouseNumber", getHouseNumber());
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
                !TextUtils.isEmpty(getHouseNumber()) &&
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

    public String getHouseNumber() {
        return editTextHouseNumber.getText().toString();
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String postalCode = editTextPostalCode.getText().toString().trim();
            String houseNumber = editTextHouseNumber.getText().toString().trim();

            if (!TextUtils.isEmpty(postalCode) && !TextUtils.isEmpty(houseNumber)) {
                // Both postal code and house number are filled, make the API call
                makeAPICall(postalCode, houseNumber);
            }
        }

    };





    private void makeAPICall(String postalCode, String houseNumber) {
        String url = "https://postcode.tech/api/v1/postcode/full?postcode=" + postalCode + "&number=" + houseNumber;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer 98ab4b12-f310-48bd-a652-1d74d2c92752")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // Handle the API response here
                String responseData = response.body().string();
                Log.d("API Response", responseData);

                try {
                    JSONObject jsonObject = new JSONObject(responseData);

                    String street = jsonObject.optString("street");
                    String city = jsonObject.optString("city");
                    String municipality = jsonObject.optString("municipality");
                    String province = jsonObject.optString("province");

                    // Update the UI on the main thread

                    requireActivity().runOnUiThread(() -> {
                        editTextAddress.setText(street);
                        editTextCity.setText(city);
                        // You can fill other EditText fields as needed
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // Handle API call failure here
                e.printStackTrace();
            }
        });
    }



    public void highlightUnfilledFields() {
        // Reset the border color of all EditText views

        // Check each field and change the border color if it's empty
        if (TextUtils.isEmpty(getAddress())) {
            editTextAddress.setBackgroundResource(R.drawable.border_red);
        }else {
            editTextAddress.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getCity())) {
            editTextCity.setBackgroundResource(R.drawable.border_red);
        }else {
            editTextCity.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getPostalCode())) {
            editTextPostalCode.setBackgroundResource(R.drawable.border_red);
        }else {
            editTextPostalCode.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getCountry())) {
            editTextCountry.setBackgroundResource(R.drawable.border_red);
        }else {
            editTextCountry.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getPhoneNumber())) {
            editTextPhoneNumber.setBackgroundResource(R.drawable.border_red);
        }else {
            editTextPhoneNumber.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getBirthDate())) {
            editTextBirthDate.setBackgroundResource(R.drawable.border_red);
        }else {
            editTextBirthDate.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getHouseNumber())) {
            editTextHouseNumber.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextHouseNumber.setBackgroundResource(R.drawable.border);
        }
    }
}