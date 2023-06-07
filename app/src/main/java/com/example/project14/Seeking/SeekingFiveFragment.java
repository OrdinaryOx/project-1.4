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

        return view;
    }

    public void saveData() {
        String startDate = getStartDate();
        String endDate = getEndDate();
        String reason = getReason();
        String grade = getGrade();
        String course = getCourse();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), SeekingSixFragment.class);
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        intent.putExtra("reason", reason);
        intent.putExtra("grade", grade);
        intent.putExtra("course", course);

        // Pass the intent to the next fragment
        passDataToNextFragment(intent);
    }

    public void passDataToNextFragment(Intent intent) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(intent);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getStartDate()) &&
                !TextUtils.isEmpty(getEndDate()) &&
                !TextUtils.isEmpty(getReason()) &&
                !TextUtils.isEmpty(getGrade()) &&
                !TextUtils.isEmpty(getCourse());
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

}