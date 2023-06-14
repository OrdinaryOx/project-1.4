package com.example.project14.Provider;

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

import com.example.project14.ActivitiesScreen;
import com.example.project14.R;
import com.example.project14.Seeking.SeekingTwoFragment;
import com.example.project14.Seeking.User_Seeking_Form;

import java.util.ArrayList;
import java.util.HashMap;


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

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
        Log.d("ARRAYLIST FRAGMENT 10", " " + fragmentDataList);

        return view;
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Comment", getComment());
        }
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

    public void highlightUnfilledFields() {
        if (isTruthChecked()) {
            checkBoxTruth.setBackgroundResource(R.drawable.border);
        } else {
            checkBoxTruth.setBackgroundResource(R.drawable.border_red);
        }
        if (isPermissionChecked()) {
            checkBoxPermission.setBackgroundResource(R.drawable.border);
        } else {
            checkBoxPermission.setBackgroundResource(R.drawable.border_red);
        }
        if (isTermsChecked()) {
            checkBoxTerms.setBackgroundResource(R.drawable.border);
        } else {
            checkBoxTerms.setBackgroundResource(R.drawable.border_red);
        }
    }
}