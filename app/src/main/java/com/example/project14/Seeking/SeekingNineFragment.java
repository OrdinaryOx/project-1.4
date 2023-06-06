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

public class SeekingNineFragment extends Fragment {
 private EditText editTextBelief;
 private EditText editTextOther;

    public SeekingNineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_nine, container, false);

        // Initialize the EditText views
        editTextBelief = view.findViewById(R.id.editTextBelief);
        editTextOther = view.findViewById(R.id.editTextOther);

        return view;
    }
    public void saveData() {
        // Get the data from the fragment
        String belief = getBelief();
        String other = getOther();

    }

    public String getBelief() {
        return editTextBelief.getText().toString();
    }

    public String getOther() {
        return editTextOther.getText().toString();
    }

}