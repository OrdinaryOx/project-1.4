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
import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SeekingFourFragment extends Fragment {
    private Spinner spinnerDay;
    private RadioGroup radioGroupPets;
    private RadioGroup radioGroupSelfPets;
    private EditText editTextPets;

    private RadioButton preferenceYes;
    private RadioButton preferenceNo;
    private RadioButton selfYes;
    private RadioButton selfNo;
    private TextView daysTextView;
    private TextView providerPetTextView;
    private TextView selfPetTextView;
    private TextView selfPetMoreTextView;

    public SeekingFourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_four, container, false);

        // Initialize the EditText views
        spinnerDay = view.findViewById(R.id.spinnerDay);
        radioGroupPets = view.findViewById(R.id.radioGroupPets);
        radioGroupSelfPets = view.findViewById(R.id.radioGroupSelfPets);

        editTextPets = view.findViewById(R.id.editTextPets);

        preferenceYes = view.findViewById(R.id.radio_button_preference_yes);
        preferenceNo = view.findViewById(R.id.radio_button_preference_no);
        selfYes = view.findViewById(R.id.radio_button_yes);
        selfNo = view.findViewById(R.id.radio_button_no);

        daysTextView = view.findViewById(R.id.textView17);
        providerPetTextView = view.findViewById(R.id.textView19);
        selfPetTextView = view.findViewById(R.id.textView12);
        selfPetMoreTextView = view.findViewById(R.id.textView15);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String petsEdit = "";

        String preferenceRadioYes = "";
        String preferenceRadioNo = "";
        String selfRadioYes = "";
        String selfRadioNo = "";

        String daysText = "";
        String providerPetText = "";
        String selfPetText = "";
        String selfPetMoreText = "";

        if (languageCode.equals("nl")) {
            petsEdit = getResources().getString(R.string.seeking_petsSelfCommentHint);

            preferenceRadioYes = getResources().getString(R.string.seeking_petsYes);
            preferenceRadioNo = getResources().getString(R.string.seeking_petsNo);
            selfRadioYes = getResources().getString(R.string.seeking_selfPetsYes);
            selfRadioNo = getResources().getString(R.string.seeking_selfPetsNo);

            daysText = getResources().getString(R.string.seeking_days);
            providerPetText = getResources().getString(R.string.seeking_otherPet);
            selfPetText = getResources().getString(R.string.seeking_pets);
            selfPetMoreText = getResources().getString(R.string.seeking_petsComment);
        } else if (languageCode.equals("en")) {
            petsEdit = getResources().getString(R.string.seeking_petsSelfCommentHint_en);

            preferenceRadioYes = getResources().getString(R.string.seeking_petsYes_en);
            preferenceRadioNo = getResources().getString(R.string.seeking_petsNo_en);
            selfRadioYes = getResources().getString(R.string.seeking_selfPetsYes_en);
            selfRadioNo = getResources().getString(R.string.seeking_selfPetsNo_en);

            daysText = getResources().getString(R.string.seeking_days_en);
            providerPetText = getResources().getString(R.string.seeking_otherPet_en);
            selfPetText = getResources().getString(R.string.seeking_pets_en);
            selfPetMoreText = getResources().getString(R.string.seeking_petsComment_en);
        }

        editTextPets.setHint(petsEdit);

        preferenceYes.setText(preferenceRadioYes);
        preferenceNo.setText(preferenceRadioNo);
        selfYes.setText(selfRadioYes);
        selfNo.setText(selfRadioNo);

        daysTextView.setText(daysText);
        providerPetTextView.setText(providerPetText);
        selfPetTextView.setText(selfPetText);
        selfPetMoreTextView.setText(selfPetMoreText);
//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 4", " " + fragmentDataList);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Day")) {
                String day = fragmentDataList.get("Day");
                setSpinnerSelection(spinnerDay, day);
            }
            if (fragmentDataList.containsKey("Pets")) {
                String pets = fragmentDataList.get("Pets");
                setRadioGroupSelection(radioGroupPets, pets);
            }
            if (fragmentDataList.containsKey("SelfPets")) {
                String selfPets = fragmentDataList.get("SelfPets");
                setRadioGroupSelection(radioGroupSelfPets, selfPets);
            }
            if (fragmentDataList.containsKey("PetsComment")) {
                String petsComment = fragmentDataList.get("PetsComment");
                editTextPets.setText(petsComment);
            }
        }


        return view;
    }


    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Day", getDay());
            fragmentDataList.put("Pets", getPets());
            fragmentDataList.put("SelfPets", getSelfPets());
            fragmentDataList.put("PetsComment", getPetsComment());
        }
    }

    public boolean isDataValid() {
        if (getDay().equals("Maak een keuze") ||
                getPets().equals("-1") ||
               getSelfPets().equals("-1")) {
            return false;
        }

        if (getSelfPets().equals("Ja") && TextUtils.isEmpty(getPetsComment())) {
            return false;
        }

        return true;

    }

    public String getDay() {
        return spinnerDay.getSelectedItem().toString();
    }

    public String getPets() {
        int checkedRadioButtonId = radioGroupPets.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();

        }
        return "-1";
    }

    public String getSelfPets() {

        int checkedRadioButtonId = radioGroupSelfPets.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {

            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            Log.d("TAG", radioButton.getText().toString());
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public String getPetsComment() {
        return editTextPets.getText().toString();
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

    private void setRadioGroupSelection(RadioGroup radioGroup, String value) {
        int radioButtonCount = radioGroup.getChildCount();
        for (int i = 0; i < radioButtonCount; i++) {
            View radioButton = radioGroup.getChildAt(i);
            if (radioButton instanceof RadioButton) {
                RadioButton radioBtn = (RadioButton) radioButton;
                if (radioBtn.getText().toString().equals(value)) {
                    radioBtn.setChecked(true);
                    break;
                }
            }
        }
    }

    public void highlightUnfilledFields() {

        if (getDay().equals("Maak een keuze")) {
            spinnerDay.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerDay.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
        if (getPets().equals("-1")) {
            preferenceYes.setBackgroundResource(R.drawable.border_red);
            preferenceNo.setBackgroundResource(R.drawable.border_red);
        } else {
            preferenceYes.setBackgroundResource(R.drawable.border);
            preferenceNo.setBackgroundResource(R.drawable.border);
        }
        if (getSelfPets().equals("-1")) {
            selfYes.setBackgroundResource(R.drawable.border_red);
            selfNo.setBackgroundResource(R.drawable.border_red);
        } else {
            selfYes.setBackgroundResource(R.drawable.border);
            selfNo.setBackgroundResource(R.drawable.border);
        }

        if (getSelfPets().equals("Ja") && TextUtils.isEmpty(getPetsComment())) {
            editTextPets.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPets.setBackgroundResource(R.drawable.border);
        }

    }

}