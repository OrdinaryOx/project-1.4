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

import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ProviderSevenFragment extends Fragment {

    private RadioGroup radioGroupProviderWork;
    private EditText editTextProviderWork;
    private EditText editTextKeyword;
    private EditText editTextHobby;
    private RadioGroup radioGroupSelfPets;
    private EditText editTextPets;
    private RadioButton workYes;
    private RadioButton workNo;
    private RadioButton petsYes;
    private RadioButton petsNo;


    public ProviderSevenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_seven, container, false);

        // Initialize the EditText views
        radioGroupProviderWork = view.findViewById(R.id.radioGroupProviderWork);
        editTextProviderWork = view.findViewById(R.id.editTextProviderWork);
        editTextKeyword = view.findViewById(R.id.editTextKeyword);
        editTextHobby = view.findViewById(R.id.editTextHobby);
        radioGroupSelfPets = view.findViewById(R.id.radioGroupSelfPets);
        editTextPets = view.findViewById(R.id.editTextPets);

        workYes = view.findViewById(R.id.radio_button_provider_work_yes);
        workNo = view.findViewById(R.id.radio_button_provider_work_no);
        petsYes = view.findViewById(R.id.radio_button_yes);
        petsNo = view.findViewById(R.id.radio_button_no);

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("ProviderWorkDetails")) {
                String workDetails = fragmentDataList.get("ProviderWorkDetails");
                editTextProviderWork.setText(workDetails);
            }
            if (fragmentDataList.containsKey("Keyword")) {
                String keyword = fragmentDataList.get("Keyword");
                editTextKeyword.setText(keyword);
            }
            if (fragmentDataList.containsKey("Hobby")) {
                String hobby = fragmentDataList.get("Hobby");
                editTextHobby.setText(hobby);
            }
            if (fragmentDataList.containsKey("Pets")) {
                String pets = fragmentDataList.get("Pets");
                editTextPets.setText(pets);
            }
            if (fragmentDataList.containsKey("ProviderWork")) {
                String work = fragmentDataList.get("ProviderWork");
                setRadioButtonSelection(radioGroupProviderWork, work);
            }
            if (fragmentDataList.containsKey("SelfPets")) {
                String pets = fragmentDataList.get("SelfPets");
                setRadioButtonSelection(radioGroupSelfPets, pets);
            }

        }

        return view;
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

    public void saveData() {

        // Create an intent and add the data as extras
        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("ProviderWork", getProviderWork());
            fragmentDataList.put("ProviderWorkDetails", getProviderWorkDetails());
            fragmentDataList.put("Keyword", getKeyword());
            fragmentDataList.put("Hobby", getHobby());
            fragmentDataList.put("SelfPets", getSelfPets());
            fragmentDataList.put("Pets", getPets());
        }


        // Pass the intent to the next fragment
        //  passDataToNextFragment(intent);
    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        if (getSelfPets().equals("Ja") && TextUtils.isEmpty(getPets())) {
            return false;
        }

        if (getProviderWork().equals("Ja") && TextUtils.isEmpty(getProviderWorkDetails())) {
            return false;
        }
        return !TextUtils.isEmpty(getProviderWork()) &&
                !TextUtils.isEmpty(getKeyword()) &&
                !TextUtils.isEmpty(getHobby()) &&
                !TextUtils.isEmpty(getSelfPets());
    }

    public String getProviderWork() {
        int checkedRadioButtonId = radioGroupProviderWork.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getProviderWorkDetails() {
        return editTextProviderWork.getText().toString();
    }

    public String getKeyword() {
        return editTextKeyword.getText().toString();
    }

    public String getHobby() {
        return editTextHobby.getText().toString();
    }

    public String getSelfPets() {
        int checkedRadioButtonId = radioGroupSelfPets.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {

            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public String getPets() {
        return editTextPets.getText().toString();
    }


    public void highlightUnfilledFields() {
        if (getProviderWork().equals("-1")) {
            workYes.setBackgroundResource(R.drawable.border_red);
            workNo.setBackgroundResource(R.drawable.border_red);
        } else {
            workYes.setBackgroundResource(R.drawable.border);
            workNo.setBackgroundResource(R.drawable.border);
        }
        if (getSelfPets().equals("-1")) {
            petsNo.setBackgroundResource(R.drawable.border_red);
            petsYes.setBackgroundResource(R.drawable.border_red);
        } else {
            petsYes.setBackgroundResource(R.drawable.border);
            petsNo.setBackgroundResource(R.drawable.border);

        }
        if (TextUtils.isEmpty(getKeyword())) {
            editTextKeyword.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextKeyword.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getHobby())) {
            editTextHobby.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextHobby.setBackgroundResource(R.drawable.border);
        }
        if (getSelfPets().equals("Ja") && TextUtils.isEmpty(getPets())) {
            editTextPets.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPets.setBackgroundResource(R.drawable.border);
        }
        if (getProviderWork().equals("Ja") && TextUtils.isEmpty(getProviderWorkDetails())) {
            editTextProviderWork.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextProviderWork.setBackgroundResource(R.drawable.border);
        }
    }

}