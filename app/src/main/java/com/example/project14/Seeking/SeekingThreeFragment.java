package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;


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

//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 3", " " + fragmentDataList);
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("City")) {
                String city = fragmentDataList.get("City");
                setSpinnerSelection(spinnerCity, city);
            }
            if (fragmentDataList.containsKey("Preference")) {
                String preference = fragmentDataList.get("Preference");
                setRadioButtonSelection(radioGroupPreference, preference);
            }
            if (fragmentDataList.containsKey("Budget")) {
                String budget = fragmentDataList.get("Budget");
                editTextBudget.setText(budget);
            }
            if (fragmentDataList.containsKey("Month")) {
                String month = fragmentDataList.get("Month");
                setSpinnerSelection(spinnerMonth, month);
            }
        }

//        Bundle arguments = getArguments();
//        Log.d("TAG", arguments.toString());

        return view;
    }

    public void saveData() {
//        String city = getCity();
//        String preference = getPreference();
//        String budget = getBudget();
//        String month = getMonth();;

//        // Create an intent and add the data as extras
//        Intent intent = new Intent(getContext(), SeekingFourFragment.class);
//        intent.putExtra("city", city);
//        intent.putExtra("preference", preference);
//        intent.putExtra("budget", budget);
//        intent.putExtra("month", month);

        // Pass the intent to the next fragment
     //   passDataToNextFragment(intent);
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("City", getCity());
            fragmentDataList.put("Preference", getPreference());
            fragmentDataList.put("Budget", getBudget());
            fragmentDataList.put("Month", getMonth());
        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
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

    private void setSpinnerSelection(Spinner spinner, String value) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinner.getAdapter();
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if (adapter.getItem(i).toString().equals(value)) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    private void setRadioButtonSelection(RadioGroup radioGroup, String value) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;
                if (radioButton.getText().toString().equals(value)) {
                    radioButton.setChecked(true);
                    break;
                }
            }
        }
    }
}