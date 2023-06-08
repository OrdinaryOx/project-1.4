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

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SeekingSevenFragment extends Fragment {
    private EditText editTextYourself;
    private EditText editTextKeyword;
    private EditText editTextRoom;
    private EditText editTextMean;

    public SeekingSevenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_seven, container, false);

        // Initialize the EditText views
        editTextYourself = view.findViewById(R.id.editTextYourself);
        editTextKeyword = view.findViewById(R.id.editTextKeyword);
        editTextRoom = view.findViewById(R.id.editTextRoom);
        editTextMean = view.findViewById(R.id.editTextMean);

//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 7", " " + fragmentDataList);
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Yourself")) {
                String yourself = fragmentDataList.get("Yourself");
                editTextYourself.setText(yourself);
            }
            if (fragmentDataList.containsKey("Keyword")) {
                String keyword = fragmentDataList.get("Keyword");
                editTextKeyword.setText(keyword);
            }
            if (fragmentDataList.containsKey("Room")) {
                String room = fragmentDataList.get("Room");
                editTextRoom.setText(room);
            }
            if (fragmentDataList.containsKey("Mean")) {
                String mean = fragmentDataList.get("Mean");
                editTextMean.setText(mean);
            }
        }
        return view;
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Yourself", getYourself());
            fragmentDataList.put("Keyword", getKeyword());
            fragmentDataList.put("Room", getRoom());
            fragmentDataList.put("Mean", getMean());

        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getYourself()) &&
                !TextUtils.isEmpty(getKeyword()) &&
                !TextUtils.isEmpty(getRoom()) &&
                !TextUtils.isEmpty(getMean());
    }

    public String getYourself() {
        return editTextYourself.getText().toString();
    }

    public String getKeyword() {
        return editTextKeyword.getText().toString();
    }

    public String getRoom() {
        return editTextRoom.getText().toString();
    }

    public String getMean() {
        return editTextMean.getText().toString();
    }

}