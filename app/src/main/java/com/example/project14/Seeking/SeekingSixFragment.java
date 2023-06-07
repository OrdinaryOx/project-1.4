package com.example.project14.Seeking;

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

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;


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

        return view;
    }

    public void saveData() {
        boolean ehboSelected = isEhboSelected();
        boolean bhvSelected = isBhvSelected();
        boolean reanimationSelected = isReanimationSelected();
        String seekingWork = getSeekingWork();
        String work = getWork();
        String health = getHealth();
        String healthInfo = getHealthInfo();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), SeekingSevenFragment.class);
        intent.putExtra("ehboSelected", ehboSelected);
        intent.putExtra("bhvSelected", bhvSelected);
        intent.putExtra("reanimationSelected", reanimationSelected);
        intent.putExtra("seekingWork", seekingWork);
        intent.putExtra("work", work);
        intent.putExtra("health", health);
        intent.putExtra("healthInfo", healthInfo);

        // Pass the intent to the next fragment
        passDataToNextFragment(intent);

    }

    public void passDataToNextFragment(Intent intent) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(intent);
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