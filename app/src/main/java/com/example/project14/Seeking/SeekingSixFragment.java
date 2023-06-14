package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


public class SeekingSixFragment extends Fragment {
    private CheckBox radioButtonEhbo;
    private CheckBox radioButtonBhv;
    private CheckBox radioButtonReanimation;
    private RadioGroup radioGroupSeekingWork;
    private EditText editTextWork;
    private RadioGroup radioGroupHealth;
    private EditText editTextHealth;

    private RadioButton seekingWorkYes;
    private RadioButton seekingWorkNo;
    private RadioButton healthIssuesYes;
    private RadioButton healthIssuesNo;

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

        seekingWorkYes = view.findViewById(R.id.radio_button_seeking_work_yes);
        seekingWorkNo = view.findViewById(R.id.radio_button_seeking_work_no);
        healthIssuesYes = view.findViewById(R.id.radio_button_health_yes);
        healthIssuesNo = view.findViewById(R.id.radio_button_health_no);

//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 6", " " + fragmentDataList);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("EHBO")) {
                int ehbo = Integer.parseInt(fragmentDataList.get("EHBO"));
                radioButtonEhbo.setChecked(ehbo == 1);
            }
            if (fragmentDataList.containsKey("BHV")) {
                int bhv = Integer.parseInt(fragmentDataList.get("BHV"));
                radioButtonBhv.setChecked(bhv == 1);
            }
            if (fragmentDataList.containsKey("Reanimation")) {
                int reanimation = Integer.parseInt(fragmentDataList.get("Reanimation"));
                radioButtonReanimation.setChecked(reanimation == 1);
            }
            if (fragmentDataList.containsKey("SeekingWork")) {
                String seekingWork = fragmentDataList.get("SeekingWork");
                setSeekingWork(seekingWork);
            }
            if (fragmentDataList.containsKey("Work")) {
                String work = fragmentDataList.get("Work");
                editTextWork.setText(work);
            }
            if (fragmentDataList.containsKey("Health")) {
                String health = fragmentDataList.get("Health");
                setHealth(health);
            }
            if (fragmentDataList.containsKey("HealthInfo")) {
                String healthInfo = fragmentDataList.get("HealthInfo");
                editTextHealth.setText(healthInfo);
            }
        }
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
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("EHBO", "" + EHBO);
            fragmentDataList.put("BHV", "" + BHV);
            fragmentDataList.put("Reanimation", "" + Reanimation);
            fragmentDataList.put("SeekingWork", getSeekingWork());
            fragmentDataList.put("Work", getWork());
            fragmentDataList.put("Health", getHealth());
            fragmentDataList.put("HealthInfo", getHealthInfo());
        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {

        if (getSeekingWork().equals("-1") || getSeekingWork().equals("Ja") && TextUtils.isEmpty(getWork())) {
            return false;
        }

        if (getHealth().equals("-1") || getHealth().equals("Ja") && TextUtils.isEmpty(getHealthInfo())) {
            return false;
        }
        return true;

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
        if (checkedRadioButtonId != -1) {
            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public void setSeekingWork(String seekingWork) {
        if (seekingWork.equalsIgnoreCase("Ja")) {
            radioGroupSeekingWork.check(R.id.radio_button_seeking_work_yes);
        } else if (seekingWork.equalsIgnoreCase("Nee")) {
            radioGroupSeekingWork.check(R.id.radio_button_seeking_work_no);

        }
    }


    public String getWork() {
        return editTextWork.getText().toString();
    }

    public String getHealth() {
        int checkedRadioButtonId = radioGroupHealth.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {

            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public void setHealth(String health) {
        if (health.equalsIgnoreCase("Ja")) {
            radioGroupHealth.check(R.id.radio_button_health_yes);
        } else if (health.equalsIgnoreCase("Nee")) {
            radioGroupHealth.check(R.id.radio_button_health_no);
        }
    }

    public String getHealthInfo() {
        return editTextHealth.getText().toString();
    }

    public void highlightUnfilledFields() {
        if (getSeekingWork().equals("-1")) {
            seekingWorkNo.setBackgroundResource(R.drawable.border_red);
            seekingWorkYes.setBackgroundResource(R.drawable.border_red);
        } else {
            seekingWorkNo.setBackgroundResource(R.drawable.border);
            seekingWorkYes.setBackgroundResource(R.drawable.border);
        }
        if (getSeekingWork().equals("Ja") && TextUtils.isEmpty(getWork())) {
            editTextWork.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextWork.setBackgroundResource(R.drawable.border);
        }
        if (getHealth().equals("-1")) {
            healthIssuesNo.setBackgroundResource(R.drawable.border_red);
            healthIssuesYes.setBackgroundResource(R.drawable.border_red);
        } else {
            healthIssuesNo.setBackgroundResource(R.drawable.border);
            healthIssuesYes.setBackgroundResource(R.drawable.border);
        }
        if (getHealth().equals("Ja") && TextUtils.isEmpty(getHealthInfo())) {
            editTextHealth.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextHealth.setBackgroundResource(R.drawable.border);
        }
    }


}