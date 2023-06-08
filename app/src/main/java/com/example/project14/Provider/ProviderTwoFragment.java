package com.example.project14.Provider;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project14.R;
import com.example.project14.Seeking.User_Seeking_Form;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProviderTwoFragment extends Fragment {
    private EditText editTextAddress;
    private EditText editTextCity;
    private EditText editTextPostalCode;
    private EditText editTextCountry;
    private EditText editTextPhoneNumber;
    private EditText editTextBirthDate;
    private EditText editTextHouseNumber;
    private Calendar calendar;

    public ProviderTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_two, container, false);

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

        calendar = Calendar.getInstance();
        editTextBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        User_Provider_Form activity = (User_Provider_Form) getActivity();
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

    public boolean verifyPhone(String phoneNumber) {
        String normalizedPhoneNumber = phoneNumber.replaceAll("\\s+", "");
        return normalizedPhoneNumber.startsWith("06") && normalizedPhoneNumber.length() == 10;
    }

    public void saveData() {
        String address = getAddress();
        String city = getCity();
        String postalCode = getPostalCode();
        String country = getCountry();
        String phoneNumber = getPhoneNumber();
        String birthDate = getBirthDate();

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Address", getAddress());
            fragmentDataList.put("CityPersonal", getCity());
            fragmentDataList.put("PostalCode", getPostalCode());
            fragmentDataList.put("Country", getCountry());
            fragmentDataList.put("PhoneNumber", getPhoneNumber());
            fragmentDataList.put("BirthDate", getBirthDate());

        }

        // Pass the intent to the next fragment
        //   passDataToNextFragment(intent);
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