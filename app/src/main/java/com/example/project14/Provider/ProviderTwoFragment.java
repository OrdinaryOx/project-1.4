package com.example.project14.Provider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project14.R;

public class ProviderTwoFragment extends Fragment {
    private EditText editTextAddress;
    private EditText editTextCity;
    private EditText editTextPostalCode;
    private EditText editTextCountry;
    private EditText editTextPhoneNumber;
    private EditText editTextBirthDate;

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

        return view;
    }

    public void saveData() {
        // Get the data from the fragment
        String address = getAddress();
        String city = getCity();
        String postalCode = getPostalCode();
        String country = getCountry();
        String phoneNumber = getPhoneNumber();
        String birthDate = getBirthDate();

    }

    public String getAddress() { return editTextAddress.getText().toString();}

    public String getCity() { return editTextCity.getText().toString();}

    public String getPostalCode() { return editTextPostalCode.getText().toString();}

    public String getCountry() { return editTextCountry.getText().toString();}

    public String getPhoneNumber() {return editTextPhoneNumber.getText().toString();}

    public String getBirthDate() {return editTextBirthDate.getText().toString();}

}