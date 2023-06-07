package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import java.util.ArrayList;

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


        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
        Log.d("ARRAYLIST FRAGMENT 4", " " + fragmentDataList);



        return view;
    }


        public void saveData() {
            User_Seeking_Form activity = (User_Seeking_Form) getActivity();
            if (activity != null) {
                ArrayList<String> fragmentDataList = activity.getFragmentDataList();
                fragmentDataList.add(getDay());
                fragmentDataList.add(getPets());
                fragmentDataList.add(getSelfPets());
                fragmentDataList.add(getPetsComment());
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
                !TextUtils.isEmpty(getSelfPets()) &&
                !TextUtils.isEmpty(getPetsComment());
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

}