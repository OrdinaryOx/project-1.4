package com.example.project14.Provider;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.R;


public class ProviderThreeFragment extends Fragment {
    private Spinner spinnerSituation;
    private RadioGroup radioGroupHouse;
    private EditText editTextFound;
    private EditText editTextProviderMotivation;


    public ProviderThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_three, container, false);

        // Initialize the EditText views
        spinnerSituation = view.findViewById(R.id.spinnerSituation);
        radioGroupHouse = view.findViewById(R.id.radioGroupHouse);
        editTextFound = view.findViewById(R.id.editTextFound);
        editTextProviderMotivation = view.findViewById(R.id.editTextProviderMotivation);

        return view;
    }

    public void saveData() {
        String situation = getSituation();
        String house = getHouse();
        String found = getFound();
        String providerMotivation = getProviderMotivation();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), ProviderFourFragment.class);
        intent.putExtra("situation", situation);
        intent.putExtra("house", house);
        intent.putExtra("found", found);
        intent.putExtra("providerMotivation", providerMotivation);

        // Pass the intent to the next fragment
     //   passDataToNextFragment(intent);
    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getSituation()) &&
                !TextUtils.isEmpty(getHouse()) &&
                !TextUtils.isEmpty(getFound()) &&
                !TextUtils.isEmpty(getProviderMotivation());
    }

    public String getSituation() {
        return spinnerSituation.getSelectedItem().toString();
    }

    public String getHouse() {
        int checkedRadioButtonId = radioGroupHouse.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getFound() {
        return editTextFound.getText().toString();
    }

    public String getProviderMotivation() {
        return editTextProviderMotivation.getText().toString();
    }


}