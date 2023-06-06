package com.example.project14.Seeking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.R;

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

        return view;
    }

    public void saveData() {
        // Get the data from the fragment
        String day = getDay();
        String pets = getPets();
        String selfPets = getSelfPets();
        String petsComment = getPetsComment();

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