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

public class ProviderEightFragment extends Fragment {

    private EditText editTextBelief;
    private EditText editTextOther;

    public ProviderEightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_eight, container, false);

        // Initialize the EditText views
        editTextBelief = view.findViewById(R.id.editTextBelief);
        editTextOther = view.findViewById(R.id.editTextOther);

        return view;
    }

    public void saveData() {
        // Get the data from the fragment
        String belief = getBelief();
        String comment = getComment();

    }

    public String getBelief() {
        return editTextBelief.getText().toString();
    }

    public String getComment() {
        return editTextOther.getText().toString();
    }

}