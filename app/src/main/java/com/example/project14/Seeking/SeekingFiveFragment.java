package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;


public class SeekingFiveFragment extends Fragment {
 private EditText editTextStartDate;
 private EditText editTextEndDate;
 private Spinner spinnerReason;
 private EditText editTextGrade;
 private EditText editTextCourse;

    public SeekingFiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_five, container, false);

        // Initialize the EditText views
        editTextStartDate = view.findViewById(R.id.editTextStartDate);
        editTextEndDate = view.findViewById(R.id.editTextEndDate);
        spinnerReason = view.findViewById(R.id.spinnerReason);
        editTextGrade = view.findViewById(R.id.editTextGrade);
        editTextCourse = view.findViewById(R.id.editTextCourse);

//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 5", " " + fragmentDataList);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("StartDate")) {
                String startDate = fragmentDataList.get("StartDate");
                editTextStartDate.setText(startDate);
            }
            if (fragmentDataList.containsKey("EndDate")) {
                String endDate = fragmentDataList.get("EndDate");
                editTextEndDate.setText(endDate);
            }
            if (fragmentDataList.containsKey("Reason")) {
                String reason = fragmentDataList.get("Reason");
                setSpinnerSelection(spinnerReason, reason);
            }
            if (fragmentDataList.containsKey("Grade")) {
                String grade = fragmentDataList.get("Grade");
                editTextGrade.setText(grade);
            }
            if (fragmentDataList.containsKey("Course")) {
                String course = fragmentDataList.get("Course");
                editTextCourse.setText(course);
            }
        }

        return view;
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("StartDate", getStartDate());
            fragmentDataList.put("EndDate", getEndDate());
            fragmentDataList.put("Reason", getReason());
            fragmentDataList.put("Grade", getGrade());
            fragmentDataList.put("Course", getCourse());
        }
       }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getStartDate()) &&
                !TextUtils.isEmpty(getEndDate()) &&
                !TextUtils.isEmpty(getReason());
    }

    public String getStartDate() {
        return editTextStartDate.getText().toString();
    }

    public String getEndDate() {
        return editTextEndDate.getText().toString();
    }

    public String getReason() {
        return spinnerReason.getSelectedItem().toString();
    }

    public String getGrade() {
        return editTextGrade.getText().toString();
    }

    public String getCourse() {
        return editTextCourse.getText().toString();
    }

    private void setSpinnerSelection(Spinner spinner, String value) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinner.getAdapter();
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if (adapter.getItem(i).toString().equals(value)) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

}