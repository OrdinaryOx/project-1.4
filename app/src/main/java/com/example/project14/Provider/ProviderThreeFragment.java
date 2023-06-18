package com.example.project14.Provider;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project14.LanguageUtils;
import com.example.project14.R;
import com.example.project14.Seeking.User_Seeking_Form;

import java.util.ArrayList;
import java.util.HashMap;


public class ProviderThreeFragment extends Fragment {
    private Spinner spinnerSituation;
    private RadioGroup radioGroupHouse;
    private EditText editTextFound;
    private EditText editTextProviderMotivation;
    private RadioButton houseYes;
    private RadioButton houseNo;

    private TextView situationTextView;
    private TextView boughtTextView;
    private TextView foundTextView;
    private TextView motivationTextView;



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
        houseYes = view.findViewById(R.id.radio_button_house_yes);
        houseNo = view.findViewById(R.id.radio_button_house_no);

        situationTextView = view.findViewById(R.id.textView7);
        boughtTextView = view.findViewById(R.id.textView);
        foundTextView = view.findViewById(R.id.textView16);
        motivationTextView = view.findViewById(R.id.textView18);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String yesButton = "";
        String noButton = "";

        String foundEdit = "";
        String providerMotivationEdit = "";

        String situationText = "";
        String boughtText = "";
        String foundText = "";
        String motivationText = "";

        if (languageCode.equals("nl")) {
            yesButton = getResources().getString(R.string.provider_boughtYes);
            noButton = getResources().getString(R.string.provider_boughtNo);

            foundEdit = getResources().getString(R.string.provider_foundHint);
            providerMotivationEdit = getResources().getString(R.string.provider_reasonHint);

            situationText = getResources().getString(R.string.provider_situation);
            boughtText = getResources().getString(R.string.provider_bought);
            foundText = getResources().getString(R.string.provider_found);
            motivationText = getResources().getString(R.string.provider_reason);
        } else if (languageCode.equals("en")) {
            yesButton = getResources().getString(R.string.provider_boughtYes_en);
            noButton = getResources().getString(R.string.provider_boughtNo_en);

            foundEdit = getResources().getString(R.string.provider_foundHint_en);
            providerMotivationEdit = getResources().getString(R.string.provider_reasonHint_en);

            situationText = getResources().getString(R.string.provider_situation_en);
            boughtText = getResources().getString(R.string.provider_bought_en);
            foundText = getResources().getString(R.string.provider_found_en);
            motivationText = getResources().getString(R.string.provider_reason_en);
        }

        houseYes.setText(yesButton);
        houseNo.setText(noButton);

        editTextFound.setHint(foundEdit);
        editTextProviderMotivation.setHint(providerMotivationEdit);

        situationTextView.setText(situationText);
        boughtTextView.setText(boughtText);
        foundTextView.setText(foundText);
        motivationTextView.setText(motivationText);

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Situation")) {
                String situation = fragmentDataList.get("Situation");
                setSpinnerSelection(spinnerSituation, situation);
            }
            if (fragmentDataList.containsKey("House")) {
                String house = fragmentDataList.get("House");
                setRadioButtonSelection(radioGroupHouse, house);
            }
            if (fragmentDataList.containsKey("Found")) {
                String postalCode = fragmentDataList.get("Found");
                editTextFound.setText(postalCode);
            }
            if (fragmentDataList.containsKey("ProviderMotivation")) {
                String providerMotivation = fragmentDataList.get("ProviderMotivation");
                editTextProviderMotivation.setText(providerMotivation);
            }

        }


        return view;
    }

    public void saveData() {
        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Situation", getSituation());
            fragmentDataList.put("House", getHouse());
            fragmentDataList.put("Found", getFound());
            fragmentDataList.put("ProviderMotivation", getProviderMotivation());
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
        if (checkedRadioButtonId != -1) {
            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public String getFound() {
        return editTextFound.getText().toString();
    }

    public String getProviderMotivation() {
        return editTextProviderMotivation.getText().toString();
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
        if (getSituation().equals("Maak een keuze")) {
            spinnerSituation.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerSituation.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
        if (getHouse().equals("-1")) {
            houseNo.setBackgroundResource(R.drawable.border_red);
            houseYes.setBackgroundResource(R.drawable.border_red);
        } else {
            houseYes.setBackgroundResource(R.drawable.border);
            houseNo.setBackgroundResource(R.drawable.border);

        }
        if (TextUtils.isEmpty(getFound())) {
            editTextFound.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextFound.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getProviderMotivation())) {
            editTextProviderMotivation.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextProviderMotivation.setBackgroundResource(R.drawable.border);
        }
    }

}