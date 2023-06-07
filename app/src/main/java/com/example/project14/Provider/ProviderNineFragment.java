package com.example.project14.Provider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project14.ActivitiesScreen;
import com.example.project14.R;
import com.example.project14.Seeking.SeekingTwoFragment;

import java.util.ArrayList;


public class ProviderNineFragment extends Fragment {

    private CheckBox checkBoxTruth;
    private CheckBox checkBoxPermission;
    private CheckBox checkBoxTerms;
    private EditText editTextComment;

    public ProviderNineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_nine, container, false);

        // Initialize the EditText views
        checkBoxTruth = view.findViewById(R.id.checkBoxTruth);
        checkBoxPermission = view.findViewById(R.id.checkBoxPermission);
        checkBoxTerms = view.findViewById(R.id.checkBoxTerms);
        editTextComment = view.findViewById(R.id.editTextComment);

        return view;
    }

    public void saveData() {
        boolean truthChecked = isTruthChecked();
        boolean permissionChecked = isPermissionChecked();
        boolean termsChecked = isTermsChecked();
        String comment = getComment();


    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return isTruthChecked() &&
                isPermissionChecked() &&
                isTermsChecked() &&
                !TextUtils.isEmpty(getComment());
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