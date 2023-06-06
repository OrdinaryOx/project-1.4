package com.example.project14.Seeking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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
        // Get the data from the fragment
        String startDate = getStartDate();
        String endDate = getEndDate();
        String reason = getReason();
        String grade = getGrade();
        String course = getCourse();

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