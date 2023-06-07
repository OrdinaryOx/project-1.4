package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import java.util.ArrayList;

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

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
        Log.d("ARRAYLIST FRAGMENT 9", " " + fragmentDataList);
        return view;
    }

    public void saveData() {


        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            ArrayList<String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.add(getBelief());
            fragmentDataList.add(getOther());
        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getBelief()) &&
                !TextUtils.isEmpty(getOther());
    }

    public String getBelief() {
        return editTextBelief.getText().toString();
    }

    public String getOther() {
        return editTextOther.getText().toString();
    }

}