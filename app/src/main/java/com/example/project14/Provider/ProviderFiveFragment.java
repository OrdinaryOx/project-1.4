package com.example.project14.Provider;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.R;

public class ProviderFiveFragment extends Fragment {
private EditText editTextSquareMeter;
private RadioGroup radioGroupFurnish;
private EditText editTextFurnished;
private EditText editTextPrice;

    public ProviderFiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_five, container, false);

        // Initialize the EditText views
        editTextSquareMeter = view.findViewById(R.id.editTextSquareMeter);
        radioGroupFurnish = view.findViewById(R.id.radioGroupFurnish);
        editTextFurnished = view.findViewById(R.id.editTextFurnished);
        editTextPrice = view.findViewById(R.id.editTextPrice);

        return view;
    }

    public void saveData() {
        String squareMeter = getSquareMeter();
        String furnish = getFurnish();
        String furnished = getFurnished();
        String price = getPrice();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), ProviderSixFragment.class);
        intent.putExtra("squareMeter", squareMeter);
        intent.putExtra("furnish", furnish);
        intent.putExtra("furnished", furnished);
        intent.putExtra("price", price);

        // Pass the intent to the next fragment
   //     passDataToNextFragment(intent);
    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getSquareMeter()) &&
                !TextUtils.isEmpty(getFurnish()) &&
                !TextUtils.isEmpty(getFurnished()) &&
                !TextUtils.isEmpty(getPrice());
    }

    public String getSquareMeter() {
        return editTextSquareMeter.getText().toString();
    }

    public String getFurnish() {
        int checkedRadioButtonId = radioGroupFurnish.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getFurnished() {
        return editTextFurnished.getText().toString();
    }

    public String getPrice() {
        return editTextPrice.getText().toString();
    }

}