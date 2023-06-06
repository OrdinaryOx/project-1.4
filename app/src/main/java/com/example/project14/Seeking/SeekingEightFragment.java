package com.example.project14.Seeking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.project14.R;

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

        return view;
    }
    public void saveData() {
        // Get the data from the fragment
        String otherOffer = getOtherOffer();
        String importantNote = getImportantNote();
        String volunteerSelection = getVolunteerSelection();
        String volunteer = getVolunteer();

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