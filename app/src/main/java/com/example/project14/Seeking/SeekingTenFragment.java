package com.example.project14.Seeking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project14.R;

public class SeekingTenFragment extends Fragment {

    private CheckBox checkBoxTruth;
    private CheckBox checkBoxPermission;
    private CheckBox checkBoxTerms;
    private EditText editTextComment;

    public SeekingTenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_ten, container, false);

        // Initialize the EditText views
        checkBoxTruth = view.findViewById(R.id.checkBoxTruth);
        checkBoxPermission = view.findViewById(R.id.checkBoxPermission);
        checkBoxTerms = view.findViewById(R.id.checkBoxTerms);
        editTextComment = view.findViewById(R.id.editTextComment);

        return view;
    }

    public void saveData() {
        // Get the data from the fragment
        boolean truthChecked = isTruthChecked();
        boolean permissionChecked = isPermissionChecked();
        boolean termsChecked = isTermsChecked();
        String comment = getComment();

    }

    public boolean isTruthChecked() {
        return checkBoxTruth.isChecked();
    }

    public boolean isPermissionChecked() {
        return checkBoxPermission.isChecked();
    }

    public boolean isTermsChecked() {
        return checkBoxTerms.isChecked();
    }

    public String getComment() {
        return editTextComment.getText().toString();
    }

}