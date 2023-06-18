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
import android.widget.TextView;

import com.example.project14.LanguageUtils;
import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;


public class SeekingThreeFragment extends Fragment {
    private Spinner spinnerCity;
    private RadioGroup radioGroupPreference;
    private EditText editTextBudget;
    private Spinner spinnerMonth;
    private RadioButton woman;
    private RadioButton man;
    private RadioButton couple;
    private RadioButton none;
    private TextView cityTextView;
    private TextView preferenceTextView;
    private TextView budgetTextView;
    private TextView preferenceTimeTextView;


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

        woman = view.findViewById(R.id.radio_button_women);
        man = view.findViewById(R.id.radio_button_men);
        couple = view.findViewById(R.id.radio_button_couples);
        none = view.findViewById(R.id.radio_button_geen);

        cityTextView = view.findViewById(R.id.textView7);
        preferenceTextView = view.findViewById(R.id.textView);
        budgetTextView = view.findViewById(R.id.textView16);
        preferenceTimeTextView = view.findViewById(R.id.textView18);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String budgetEdit = "";

        String womanRadio = "";
        String manRadio = "";
        String coupleRadio = "";
        String noneRadio = "";

        String cityText = "";
        String preferenceText = "";
        String budgetText = "";
        String preferenceTimeText = "";

        if (languageCode.equals("nl")) {
            budgetEdit = getResources().getString(R.string.seeking_budgetHint);

            womanRadio = getResources().getString(R.string.seeking_preferenceW);
            manRadio = getResources().getString(R.string.seeking_preferenceM);
            coupleRadio = getResources().getString(R.string.seeking_preferenceC);
            noneRadio = getResources().getString(R.string.seeking_preferenceO);

            cityText = getResources().getString(R.string.seeking_cityRoom);
            preferenceText = getResources().getString(R.string.seeking_preference);
            budgetText = getResources().getString(R.string.seeking_budget);
            preferenceTimeText = getResources().getString(R.string.seeking_livingTime);
        } else if (languageCode.equals("en")) {
            budgetEdit = getResources().getString(R.string.seeking_budgetHint_en);

            womanRadio = getResources().getString(R.string.seeking_preferenceW_en);
            manRadio = getResources().getString(R.string.seeking_preferenceM_en);
            coupleRadio = getResources().getString(R.string.seeking_preferenceC_en);
            noneRadio = getResources().getString(R.string.seeking_preferenceO_en);

            cityText = getResources().getString(R.string.seeking_cityRoom_en);
            preferenceText = getResources().getString(R.string.seeking_preference_en);
            budgetText = getResources().getString(R.string.seeking_budget_en);
            preferenceTimeText = getResources().getString(R.string.seeking_livingTime_en);
        }

        editTextBudget.setHint(budgetEdit);

        woman.setText(womanRadio);
        man.setText(manRadio);
        couple.setText(coupleRadio);
        none.setText(noneRadio);

        cityTextView.setText(cityText);
        preferenceTextView.setText(preferenceText);
        budgetTextView.setText(budgetText);
        preferenceTimeTextView.setText(preferenceTimeText);

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
        if (getCity().equals("Maak een keuze") || getMonth().equals("Maak een keuze")) {
            return false;
        }
        if (getPreference().equals("-1")) {
            return false;
        }

        return !TextUtils.isEmpty(getCity()) &&
                !TextUtils.isEmpty(getBudget());

    }

    public String getCity() {
        return spinnerCity.getSelectedItem().toString();
    }

    public String getPreference() {
        int checkedRadioButtonId = radioGroupPreference.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
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

    public void highlightUnfilledFields() {
        if (getCity().equals("Maak een keuze")) {
            spinnerCity.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerCity.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
        if (getPreference().equals("-1")) {
            woman.setBackgroundResource(R.drawable.border_red);
            man.setBackgroundResource(R.drawable.border_red);
            couple.setBackgroundResource(R.drawable.border_red);
            none.setBackgroundResource(R.drawable.border_red);
        } else {
            woman.setBackgroundResource(R.drawable.border);
            man.setBackgroundResource(R.drawable.border);
            couple.setBackgroundResource(R.drawable.border);
            none.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getBudget())) {
            editTextBudget.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextBudget.setBackgroundResource(R.drawable.border);
        }
        if (getMonth().equals("Maak een keuze")) {
            spinnerMonth.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerMonth.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
    }

//    private boolean isRadioButtonSelected(RadioGroup radioGroup) {
//        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
//        return selectedRadioButtonId != -1;
//    }

}