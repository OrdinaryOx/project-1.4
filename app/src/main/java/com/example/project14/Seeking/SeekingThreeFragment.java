package com.example.project14.Seeking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.R;


public class SeekingThreeFragment extends Fragment {
    private Spinner spinnerCity;
    private RadioGroup radioGroupPreference;
    private EditText editTextBudget;
    private Spinner spinnerMonth;

    public SeekingThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_three, container, false);

        // Initialize the EditText views

        spinnerCity = view.findViewById(R.id.spinnerCity);
        radioGroupPreference = view.findViewById(R.id.radioGroupPreference);
        editTextBudget = view.findViewById(R.id.editTextBudget);
        spinnerMonth = view.findViewById(R.id.spinnerMonth);

        return view;
    }
    public void saveData() {
        // Get the data from the fragment
        String city = getCity();
        String preference = getPreference();
        String budget = getBudget();
        String month = getMonth();

    }

    public String getCity() {
        return spinnerCity.getSelectedItem().toString();
    }

    public String getPreference() {
        int checkedRadioButtonId = radioGroupPreference.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getBudget() {
        return editTextBudget.getText().toString();
    }

    public String getMonth() {
        return spinnerMonth.getSelectedItem().toString();
    }

}