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
import android.widget.TextView;

import com.example.project14.LanguageUtils;
import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SeekingSevenFragment extends Fragment {
    private EditText editTextYourself;
    private EditText editTextKeyword;
    private EditText editTextRoom;
    private EditText editTextMean;
    private TextView describeTextView;
    private TextView keywordTextView;
    private TextView describeRoomTextView;
    private TextView meanTextView;


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

        describeTextView = view.findViewById(R.id.textView17);
        keywordTextView = view.findViewById(R.id.textView28);
        describeRoomTextView = view.findViewById(R.id.textView29);
        meanTextView = view.findViewById(R.id.textView30);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String describeEdit = "";
        String keywordEdit = "";
        String describeRoomEdit = "";
        String meanEdit = "";

        String describeText = "";
        String keywordText = "";
        String describeRoomText = "";
        String meanText = "";

        if (languageCode.equals("nl")) {
            describeEdit = getResources().getString(R.string.seeking_type);
            keywordEdit = getResources().getString(R.string.seeking_keywordHint);
            describeRoomEdit = getResources().getString(R.string.seeking_idealRoomHint);
            meanEdit = getResources().getString(R.string.seeking_providerMeanHint);

            describeText = getResources().getString(R.string.seeking_describe);
            keywordText = getResources().getString(R.string.seeking_keyword);
            describeRoomText = getResources().getString(R.string.seeking_idealRoom);
            meanText = getResources().getString(R.string.seeking_providerMean);
        } else if (languageCode.equals("en")) {
            describeEdit = getResources().getString(R.string.seeking_type_en);
            keywordEdit = getResources().getString(R.string.seeking_keywordHint_en);
            describeRoomEdit = getResources().getString(R.string.seeking_idealRoomHint_en);
            meanEdit = getResources().getString(R.string.seeking_providerMeanHint_en);

            describeText = getResources().getString(R.string.seeking_describe_en);
            keywordText = getResources().getString(R.string.seeking_keyword_en);
            describeRoomText = getResources().getString(R.string.seeking_idealRoom_en);
            meanText = getResources().getString(R.string.seeking_providerMean_en);
        }

        editTextYourself.setHint(describeEdit);
        editTextKeyword.setHint(keywordEdit);
        editTextRoom.setHint(describeRoomEdit);
        editTextMean.setHint(meanEdit);

        describeTextView.setText(describeText);
        keywordTextView.setText(keywordText);
        describeRoomTextView.setText(describeRoomText);
        meanTextView.setText(meanText);

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

    public void highlightUnfilledFields() {

        if (getYourself().isEmpty()) {
            editTextYourself.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextYourself.setBackgroundResource(R.drawable.border);
        }
        if (getKeyword().isEmpty()) {
            editTextKeyword.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextKeyword.setBackgroundResource(R.drawable.border);
        }
        if (getRoom().isEmpty()) {
            editTextRoom.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextRoom.setBackgroundResource(R.drawable.border);
        }
        if (getMean().isEmpty()) {
            editTextMean.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextMean.setBackgroundResource(R.drawable.border);
        }


    }

}