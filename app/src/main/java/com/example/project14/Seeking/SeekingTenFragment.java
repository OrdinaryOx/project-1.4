package com.example.project14.Seeking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.util.ArrayList;

public class SeekingTenFragment extends Fragment {

    private CheckBox checkBoxTruth;
    private CheckBox checkBoxPermission;
    private CheckBox checkBoxTerms;
    private EditText editTextComment;
    private String test;

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

//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 10", " " + fragmentDataList);


        return view;
    }

    public void saveData() {
        boolean truthChecked = isTruthChecked();
        boolean permissionChecked = isPermissionChecked();
        boolean termsChecked = isTermsChecked();
        String comment = getComment();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), SeekingTenFragment.class);
        intent.putExtra("truthChecked", truthChecked);
        intent.putExtra("permissionChecked", permissionChecked);
        intent.putExtra("termsChecked", termsChecked);
        intent.putExtra("comment", comment);
        // Pass the intent to the next fragment
      //  passDataToNextFragment(intent);
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
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