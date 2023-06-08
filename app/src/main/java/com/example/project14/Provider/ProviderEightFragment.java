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

import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;

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


        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Belief")) {
                String belief = fragmentDataList.get("Belief");
                editTextBelief.setText(belief);
            }
            if (fragmentDataList.containsKey("Comment")) {
                String comment = fragmentDataList.get("Comment");
                editTextOther.setText(comment);
            }

        }

        return view;
    }

    public void saveData() {

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Belief", getBelief());
            fragmentDataList.put("Comment", getComment());
        }


    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getBelief()) &&
                !TextUtils.isEmpty(getComment());
    }

    public String getBelief() {
        return editTextBelief.getText().toString();
    }

    public String getComment() {
        return editTextOther.getText().toString();
    }

}