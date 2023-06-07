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

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import java.util.ArrayList;

public class SeekingEightFragment extends Fragment {
    private EditText editTextOtherOffer;
    private EditText editTextImportantNote;
    private RadioGroup radioGroupVolunteer;
    private EditText editTextVolunteer;

    public SeekingEightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_eight, container, false);

        // Initialize the EditText views
        editTextOtherOffer = view.findViewById(R.id.editTextOtherOffer);
        editTextImportantNote = view.findViewById(R.id.editTextImportantNote);
        radioGroupVolunteer = view.findViewById(R.id.radioGroupVolunteer);
        editTextVolunteer = view.findViewById(R.id.editTextVolunteer);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
        Log.d("ARRAYLIST FRAGMENT 8", " " + fragmentDataList);
        return view;
    }

    public void saveData() {
        String otherOffer = getOtherOffer();
        String importantNote = getImportantNote();
        String volunteerSelection = getVolunteerSelection();
        String volunteer = getVolunteer();

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            ArrayList<String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.add(getOtherOffer());
            fragmentDataList.add(getImportantNote());
            fragmentDataList.add(getVolunteerSelection());
            fragmentDataList.add(getVolunteer());
        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }
    public boolean isDataValid() {
        return !TextUtils.isEmpty(getOtherOffer()) &&
                !TextUtils.isEmpty(getImportantNote()) &&
                !TextUtils.isEmpty(getVolunteerSelection()) &&
                !TextUtils.isEmpty(getVolunteer());
    }


    public String getOtherOffer() {
        return editTextOtherOffer.getText().toString();
    }

    public String getImportantNote() {
        return editTextImportantNote.getText().toString();
    }

    public String getVolunteerSelection() {
        int checkedRadioButtonId = radioGroupVolunteer.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getVolunteer() {
        return editTextVolunteer.getText().toString();
    }

}