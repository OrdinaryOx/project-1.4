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
import android.widget.Spinner;

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.util.ArrayList;


public class SeekingSixFragment extends Fragment {
   private RadioButton radioButtonEhbo;
   private RadioButton radioButtonBhv;
   private RadioButton radioButtonReanimation;
   private RadioGroup radioGroupSeekingWork;
   private EditText editTextWork;
   private RadioGroup radioGroupHealth;
   private EditText editTextHealth;

    public SeekingSixFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_six, container, false);

        // Initialize the EditText views
        radioButtonEhbo = view.findViewById(R.id.radioButtonEhbo);
        radioButtonBhv = view.findViewById(R.id.radioButtonBhv);
        radioButtonReanimation = view.findViewById(R.id.radioButtonReanimation);
        radioGroupSeekingWork = view.findViewById(R.id.radioGroupSeekingWork);
        editTextWork = view.findViewById(R.id.editTextWork);
        radioGroupHealth = view.findViewById(R.id.radioGroupHealth);
        editTextHealth = view.findViewById(R.id.editTextHealth);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
        Log.d("ARRAYLIST FRAGMENT 6", " " + fragmentDataList);

        return view;
    }

    public void saveData() {
        int EHBO = 0;
        int BHV = 0;
        int Reanimation = 0;

        if (isEhboSelected()) {
            EHBO = 1;
        }
        if (isBhvSelected()) {
            BHV = 1;
        }
        if (isReanimationSelected()) {
            Reanimation = 1;
        }

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            ArrayList<String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.add(" " + EHBO);
            fragmentDataList.add(" " + BHV);
            fragmentDataList.add(" " + Reanimation);
            fragmentDataList.add(getSeekingWork());
            fragmentDataList.add(getWork());
            fragmentDataList.add(getHealth());
            fragmentDataList.add(getHealthInfo());
        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return isEhboSelected() &&
                isBhvSelected() &&
                isReanimationSelected() &&
                !TextUtils.isEmpty(getSeekingWork()) &&
                !TextUtils.isEmpty(getWork()) &&
                !TextUtils.isEmpty(getHealth()) &&
                !TextUtils.isEmpty(getHealthInfo());
    }

    public boolean isEhboSelected() {
        return radioButtonEhbo.isChecked();
    }

    public boolean isBhvSelected() {
        return radioButtonBhv.isChecked();
    }

    public boolean isReanimationSelected() {
        return radioButtonReanimation.isChecked();
    }

    public String getSeekingWork() {
        int checkedRadioButtonId = radioGroupSeekingWork.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getWork() {
        return editTextWork.getText().toString();
    }

    public String getHealth() {
        int checkedRadioButtonId = radioGroupHealth.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getHealthInfo() {
        return editTextHealth.getText().toString();
    }

}