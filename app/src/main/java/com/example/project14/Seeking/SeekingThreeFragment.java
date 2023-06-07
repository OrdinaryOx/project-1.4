package com.example.project14.Seeking;

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

import com.example.project14.Provider.User_Provider_Form;
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
        String city = getCity();
        String preference = getPreference();
        String budget = getBudget();
        String month = getMonth();;

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), SeekingFourFragment.class);
        intent.putExtra("city", city);
        intent.putExtra("preference", preference);
        intent.putExtra("budget", budget);
        intent.putExtra("month", month);

        // Pass the intent to the next fragment
        passDataToNextFragment(intent);
    }

    public void passDataToNextFragment(Intent intent) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(intent);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getCity()) &&
                !TextUtils.isEmpty(getCity()) &&
                !TextUtils.isEmpty(getPreference()) &&
                !TextUtils.isEmpty(getBudget()) &&
                !TextUtils.isEmpty(getMonth());
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