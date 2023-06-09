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

import java.util.ArrayList;
import java.util.HashMap;

public class ProviderFiveFragment extends Fragment {
private EditText editTextSquareMeter;
private RadioGroup radioGroupFurnish;
private EditText editTextFurnished;
private EditText editTextPrice;
private RadioButton furnishYes;
private RadioButton furnishNo;

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
        furnishYes = view.findViewById(R.id.radio_button_furnish_yes);
        furnishNo = view.findViewById(R.id.radio_button_furnish_no);

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("SquareMeter")) {
                String squareMeter = fragmentDataList.get("SquareMeter");
                editTextSquareMeter.setText(squareMeter);
            }
            if (fragmentDataList.containsKey("Furnish")) {
                String furnish = fragmentDataList.get("Furnish");
                setRadioButtonSelection(radioGroupFurnish, furnish);
            }
            if (fragmentDataList.containsKey("Furnished")) {
                String furnished = fragmentDataList.get("Furnished");
                editTextFurnished.setText(furnished);
            }
            if (fragmentDataList.containsKey("Price")) {
                String price = fragmentDataList.get("Price");
                editTextPrice.setText(price);
            }

        }


        return view;
    }

    public void saveData() {
        String squareMeter = getSquareMeter();
        String furnish = getFurnish();
        String furnished = getFurnished();
        String price = getPrice();

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("SquareMeter", getSquareMeter());
            fragmentDataList.put("Furnish", getFurnish());
            fragmentDataList.put("Furnished", getFurnished());
            fragmentDataList.put("Price", getPrice());
        }

        // Pass the intent to the next fragment
   //     passDataToNextFragment(intent);
    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        if (getFurnish().equals("Ja") && TextUtils.isEmpty(getFurnished())) {
            return false;
        }

        return !TextUtils.isEmpty(getSquareMeter()) &&
                !TextUtils.isEmpty(getFurnish()) &&
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
        if (TextUtils.isEmpty(getSquareMeter())) {
            editTextSquareMeter.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextSquareMeter.setBackgroundResource(R.drawable.border);
        }
        if (getFurnish().equals("-1")) {
            furnishNo.setBackgroundResource(R.drawable.border_red);
            furnishYes.setBackgroundResource(R.drawable.border_red);
        } else {
            furnishNo.setBackgroundResource(R.drawable.border);
            furnishYes.setBackgroundResource(R.drawable.border);

        }
        if (getFurnish().equals("Ja") && TextUtils.isEmpty(getFurnished())) {
            editTextFurnished.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextFurnished.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getPrice())) {
            editTextPrice.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPrice.setBackgroundResource(R.drawable.border);
        }
    }
}