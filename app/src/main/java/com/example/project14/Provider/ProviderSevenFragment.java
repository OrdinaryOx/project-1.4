package com.example.project14.Provider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.project14.R;


public class ProviderSevenFragment extends Fragment {

    private RadioGroup radioGroupProviderWork;
    private EditText editTextProviderWork;
    private EditText editTextKeyword;
    private EditText editTextHobby;
    private RadioGroup radioGroupSelfPets;
    private EditText editTextPets;


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

        return view;
    }

    public void saveData() {
        // Get the data from the fragment
        String providerWork = getProviderWork();
        String providerWorkDetails = getProviderWorkDetails();
        String keyword = getKeyword();
        String hobby = getHobby();
        String selfPets = getSelfPets();
        String pets = getPets();

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
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getPets() {
        return editTextPets.getText().toString();
    }

}