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

public class SeekingTwoFragment extends Fragment {
    private EditText editTextAddress;
    private EditText editTextCity;
    private EditText editTextPostalCode;
    private EditText editTextCountry;
    private EditText editTextPhoneNumber;
    private EditText editTextBirthDate;

    public SeekingTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_two, container, false);

        // Initialize the EditText views

        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextCity = view.findViewById(R.id.editTextCity);
        editTextPostalCode = view.findViewById(R.id.editTextPostalCode);
        editTextCountry = view.findViewById(R.id.editTextCountry);
        editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
        editTextBirthDate = view.findViewById(R.id.editTextBirthDate);


        Bundle arguments = getArguments();
        if (arguments != null) {
            String salutation = arguments.getString("salutation");
            String firstName = arguments.getString("firstName");
            String infix = arguments.getString("infix");
            String lastName = arguments.getString("lastName");
            String password = arguments.getString("password");
            String passwordAgain = arguments.getString("passwordAgain");
        }

        return view;
    }

    public void saveData() {
        String address = getAddress();
        String city = getCity();
        String postalCode = getPostalCode();
        String country = getCountry();
        String phoneNumber = getPhoneNumber();
        String birthDate = getBirthDate();

        // Create an intent and add the data as extras

        Intent intent = new Intent(getContext(), SeekingThreeFragment.class);
        intent.putExtra("address", address);
        intent.putExtra("city", city);
        intent.putExtra("postalCode", postalCode);
        intent.putExtra("country", country);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("birthDate", birthDate);
        // Pass the intent to the next fragment
        //passDataToNextFragment(intent);

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