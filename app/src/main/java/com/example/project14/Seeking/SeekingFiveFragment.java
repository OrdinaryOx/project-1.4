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
import android.widget.TextView;

import com.example.project14.LanguageUtils;
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
    private TextView startDateTextView;
    private TextView startTextView;
    private TextView endDateTextView;
    private TextView endTextView;
    private TextView reasonTextView;
    private TextView schoolTextView;
    private TextView schoolFutureTextView;

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

        startDateTextView = view.findViewById(R.id.textView17);
        startTextView = view.findViewById(R.id.textView20);
        endDateTextView = view.findViewById(R.id.textView21);
        endTextView = view.findViewById(R.id.textView22);
        reasonTextView = view.findViewById(R.id.textView23);
        schoolTextView = view.findViewById(R.id.textView15);
        schoolFutureTextView = view.findViewById(R.id.textView25);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String startDateEdit = "";
        String endDateEdit = "";
        String gradeEdit = "";
        String courseEdit = "";
        String startDateText = "";
        String startText = "";
        String endDateText = "";
        String endText = "";
        String reasonText = "";
        String schoolText = "";
        String schoolFutureText = "";

        if (languageCode.equals("nl")) {
            startDateEdit = getResources().getString(R.string.seeking_startDateHint);
            endDateEdit = getResources().getString(R.string.seeking_endDateHint);
            gradeEdit = getResources().getString(R.string.seeking_schoolHint);
            courseEdit = getResources().getString(R.string.seeking_schoolFutureHint);

            startDateText = getResources().getString(R.string.seeking_startDate);
            startText = getResources().getString(R.string.seeking_start);
            endDateText = getResources().getString(R.string.seeking_endDate);
            endText = getResources().getString(R.string.seeking_end);
            reasonText = getResources().getString(R.string.seeking_reason);
            schoolText = getResources().getString(R.string.seeking_school);
            schoolFutureText = getResources().getString(R.string.seeking_schoolFuture);
        } else if (languageCode.equals("en")) {
            startDateEdit = getResources().getString(R.string.seeking_startDateHint_en);
            endDateEdit = getResources().getString(R.string.seeking_endDateHint_en);
            gradeEdit = getResources().getString(R.string.seeking_schoolHint_en);
            courseEdit = getResources().getString(R.string.seeking_schoolFutureHint_en);

            startDateText = getResources().getString(R.string.seeking_startDate_en);
            startText = getResources().getString(R.string.seeking_start_en);
            endDateText = getResources().getString(R.string.seeking_endDate_en);
            endText = getResources().getString(R.string.seeking_end_en);
            reasonText = getResources().getString(R.string.seeking_reason_en);
            schoolText = getResources().getString(R.string.seeking_school_en);
            schoolFutureText = getResources().getString(R.string.seeking_schoolFuture_en);
        }

        editTextStartDate.setHint(startDateEdit);
        editTextEndDate.setHint(endDateEdit);
        editTextGrade.setHint(gradeEdit);
        editTextCourse.setHint(courseEdit);

        startDateTextView.setText(startDateText);
        startTextView.setText(startText);
        endDateTextView.setText(endDateText);
        endTextView.setText(endText);
        reasonTextView.setText(reasonText);
        schoolTextView.setText(schoolText);
        schoolFutureTextView.setText(schoolFutureText);

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
        if (getReason().equals("Maak een keuze") ||
                TextUtils.isEmpty(getStartDate()) ||
                TextUtils.isEmpty(getEndDate())) {
            return false;
        }
return true;


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


    public void highlightUnfilledFields() {

        if (TextUtils.isEmpty(getStartDate())) {
            editTextStartDate.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextStartDate.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getEndDate())) {
            editTextEndDate.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextEndDate.setBackgroundResource(R.drawable.border);
        }
        if (getReason().equals("Maak een keuze")) {
            spinnerReason.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerReason.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }


    }

}