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

public class ProviderSixFragment extends Fragment {
    private EditText editTextOffer;
    private EditText editTextImportantNote;
    private RadioGroup radioGroupVolunteer;
    private EditText editTextCommentVolunteer;

    public ProviderSixFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_six, container, false);

        // Initialize the EditText views
        editTextOffer = view.findViewById(R.id.editTextOffer);
        editTextImportantNote = view.findViewById(R.id.editTextImportantNote);
        radioGroupVolunteer = view.findViewById(R.id.radioGroupVolunteer);
        editTextCommentVolunteer = view.findViewById(R.id.editTextCommentVolunteer);

        return view;
    }

    public void saveData() {
        String offer = getOffer();
        String importantNote = getImportantNote();
        String volunteer = getVolunteer();
        String commentVolunteer = getCommentVolunteer();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), ProviderTwoFragment.class);
        intent.putExtra("offer", offer);
        intent.putExtra("importantNote", importantNote);
        intent.putExtra("volunteer", volunteer);
        intent.putExtra("commentVolunteer", commentVolunteer);

        // Pass the intent to the next fragment
        passDataToNextFragment(intent);
    }


    public void passDataToNextFragment(Intent intent) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(intent);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getOffer()) &&
                !TextUtils.isEmpty(getImportantNote()) &&
                !TextUtils.isEmpty(getVolunteer()) &&
                !TextUtils.isEmpty(getCommentVolunteer());
    }

    public String getOffer() {
        return editTextOffer.getText().toString();
    }

    public String getImportantNote() {
        return editTextImportantNote.getText().toString();
    }

    public String getVolunteer() {
        int checkedRadioButtonId = radioGroupVolunteer.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getCommentVolunteer() {
        return editTextCommentVolunteer.getText().toString();
    }

}