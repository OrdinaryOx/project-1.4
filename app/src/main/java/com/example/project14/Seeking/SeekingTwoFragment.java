package com.example.project14.Seeking;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.project14.LanguageUtils;
import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class SeekingTwoFragment extends Fragment {
    //TODO phone verification +31 6
    private EditText editTextAddress;
    private EditText editTextCity;
    private EditText editTextPostalCode;
    private EditText editTextCountry;
    private EditText editTextPhoneNumber;
    private EditText editTextBirthDate;
    private EditText editTextStreet;
    private EditText editTextHouseNumber;
    private TextView postCodeTextView;
    private TextView houseNumberTextView;
    private TextView streetTextView;
    private TextView cityTextView;
    private TextView countryTextView;
    private TextView phoneNumberTextView;
    private TextView birthDateTextView;
    private Calendar calendar;

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
        editTextHouseNumber = view.findViewById(R.id.editTextHouseNumber);
        editTextPostalCode.addTextChangedListener(textWatcher);
        editTextHouseNumber.addTextChangedListener(textWatcher);

        postCodeTextView = view.findViewById(R.id.textView6);
        houseNumberTextView = view.findViewById(R.id.textViewHuisNummer);
        streetTextView = view.findViewById(R.id.textView7);
        cityTextView = view.findViewById(R.id.textView2);
        countryTextView = view.findViewById(R.id.textView11);
        phoneNumberTextView = view.findViewById(R.id.textView14);
        birthDateTextView = view.findViewById(R.id.textView26);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String addressEdit = "";
        String cityEdit = "";
        String postalCodeEdit = "";
        String countryEdit = "";
        String phoneNumberEdit = "";
        String birthDateEdit = "";
        String houseNumberEdit = "";

        String postCodeText = "";
        String houseNumberText = "";
        String streetText = "";
        String cityText = "";
        String countryText = "";
        String phoneNumberText = "";
        String birthDateText = "";

        if (languageCode.equals("nl")) {
            addressEdit = getResources().getString(R.string.provider_streetHint);
            cityEdit = getResources().getString(R.string.provider_cityHint);
            postalCodeEdit = getResources().getString(R.string.provider_codeHint);
            countryEdit = getResources().getString(R.string.provider_countryHint);
            phoneNumberEdit = getResources().getString(R.string.provider_phoneNumberHint);
            birthDateEdit = getResources().getString(R.string.provider_birthHint);
            houseNumberEdit = getResources().getString(R.string.provider_houseNumberHint);

            postCodeText = getResources().getString(R.string.provider_code);
            houseNumberText = getResources().getString(R.string.provider_houseNumber);
            streetText = getResources().getString(R.string.provider_street);
            cityText = getResources().getString(R.string.provider_city);
            countryText = getResources().getString(R.string.provider_country);
            phoneNumberText = getResources().getString(R.string.provider_phoneNumber);
            birthDateText = getResources().getString(R.string.provider_birth);
        } else if (languageCode.equals("en")) {
            addressEdit = getResources().getString(R.string.provider_streetHint_en);
            cityEdit = getResources().getString(R.string.provider_cityHint_en);
            postalCodeEdit = getResources().getString(R.string.provider_codeHint_en);
            countryEdit = getResources().getString(R.string.provider_countryHint_en);
            phoneNumberEdit = getResources().getString(R.string.provider_phoneNumberHint_en);
            birthDateEdit = getResources().getString(R.string.provider_birthHint_en);
            houseNumberEdit = getResources().getString(R.string.provider_houseNumberHint_en);

            postCodeText = getResources().getString(R.string.provider_code_en);
            houseNumberText = getResources().getString(R.string.provider_houseNumber_en);
            streetText = getResources().getString(R.string.provider_street_en);
            cityText = getResources().getString(R.string.provider_city_en);
            countryText = getResources().getString(R.string.provider_country_en);
            phoneNumberText = getResources().getString(R.string.provider_phoneNumber_en);
            birthDateText = getResources().getString(R.string.provider_birth_en);
        }

        editTextAddress.setHint(addressEdit);
        editTextCity.setHint(cityEdit);
        editTextPostalCode.setHint(postalCodeEdit);
        editTextCountry.setHint(countryEdit);
        editTextPhoneNumber.setHint(phoneNumberEdit);
        editTextBirthDate.setHint(birthDateEdit);
        editTextHouseNumber.setHint(houseNumberEdit);

        postCodeTextView.setText(postCodeText);
        houseNumberTextView.setText(houseNumberText);
        streetTextView.setText(streetText);
        cityTextView.setText(cityText);
        countryTextView.setText(countryText);
        phoneNumberTextView.setText(phoneNumberText);
        birthDateTextView.setText(birthDateText);

        calendar = Calendar.getInstance();
        editTextBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


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

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                R.style.DatePickerDialogStyle,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        updateBirthDate();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // Set the date picker dialog to display the year spinner
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        datePickerDialog.show();
    }

    private void updateBirthDate() {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        editTextBirthDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Street", getAddress());
            fragmentDataList.put("HouseNumber", getHouseNumber());
            fragmentDataList.put("CityPersonal", getCity());
            fragmentDataList.put("PostalCode", getPostalCode());
            fragmentDataList.put("Country", getCountry());
            fragmentDataList.put("PhoneNumber", getPhoneNumber());
            fragmentDataList.put("BirthDate", getBirthDate());

        }
    }


    public boolean isDataValid() {
        return !TextUtils.isEmpty(getAddress()) &&
                !TextUtils.isEmpty(getCity()) &&
                !TextUtils.isEmpty(getHouseNumber()) &&
                !TextUtils.isEmpty(getPostalCode()) &&
                !TextUtils.isEmpty(getCountry()) &&
                !TextUtils.isEmpty(getPhoneNumber()) &&
                !TextUtils.isEmpty(getBirthDate()) &&
                verifyPhone(getPhoneNumber());

    }

    public boolean verifyPhone(String phoneNumber) {
        String normalizedPhoneNumber = phoneNumber.replaceAll("\\s+", "");
        return normalizedPhoneNumber.startsWith("06") && normalizedPhoneNumber.length() == 10;
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
        } else {
            editTextAddress.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getCity())) {
            editTextCity.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextCity.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getPostalCode())) {
            editTextPostalCode.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPostalCode.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getCountry())) {
            editTextCountry.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextCountry.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getPhoneNumber())) {
            editTextPhoneNumber.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPhoneNumber.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getBirthDate())) {
            editTextBirthDate.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextBirthDate.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getHouseNumber())) {
            editTextHouseNumber.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextHouseNumber.setBackgroundResource(R.drawable.border);
        }
        if (!isPhoneValid()) {
            editTextPhoneNumber.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPhoneNumber.setBackgroundResource(R.drawable.border);
        }
    }

    public boolean isPhoneValid() {
        if (!verifyPhone(getPhoneNumber())) {
            Toast.makeText(getContext(), "Telefoonnummer ongeldig", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}