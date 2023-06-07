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

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SeekingFourFragment extends Fragment {
    private Spinner spinnerDay;
    private RadioGroup radioGroupPets;
    private RadioGroup radioGroupSelfPets;
    private EditText editTextPets;

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
    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getDay()) &&
                !TextUtils.isEmpty(getPets()) &&
                !TextUtils.isEmpty(getSelfPets());
    }

    public String getDay() {
        return spinnerDay.getSelectedItem().toString();
    }

    public String getPets() {
        int checkedRadioButtonId = radioGroupPets.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getSelfPets() {
        int checkedRadioButtonId = radioGroupSelfPets.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
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

}