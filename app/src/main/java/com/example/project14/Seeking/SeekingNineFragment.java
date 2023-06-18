package com.example.project14.Seeking;

import android.content.Intent;
import android.net.Uri;
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
import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SeekingNineFragment extends Fragment {
    private EditText editTextBelief;
    private EditText editTextOther;
    private TextView beliefTextView;
    private TextView otherTextView;

    public SeekingNineFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_nine, container, false);

        // Initialize the EditText views
        editTextBelief = view.findViewById(R.id.editTextBelief);
        editTextOther = view.findViewById(R.id.editTextOther);
        beliefTextView = view.findViewById(R.id.textView17);
        otherTextView = view.findViewById(R.id.textView31);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String beliefReligion = "";
        String otherComment = "";
        String beliefLabel = "";
        String otherLabel = "";

        if (languageCode.equals("nl")) {
            beliefReligion = getResources().getString(R.string.provider_type);
            otherComment = getResources().getString(R.string.provider_type);
            beliefLabel = getResources().getString(R.string.provider_belief);
            otherLabel = getResources().getString(R.string.provider_otherComment);
        } else if (languageCode.equals("en")) {
            beliefReligion = getResources().getString(R.string.provider_type_en);
            otherComment = getResources().getString(R.string.provider_type_en);
            beliefLabel = getResources().getString(R.string.provider_belief_en);
            otherLabel = getResources().getString(R.string.provider_otherComment_en);
        }

        editTextBelief.setHint(beliefReligion);
        editTextOther.setHint(otherComment);
        beliefTextView.setText(beliefLabel);
        otherTextView.setText(otherLabel);

//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 9", " " + fragmentDataList);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Belief")) {
                String belief = fragmentDataList.get("Belief");
                editTextBelief.setText(belief);
            }
            if (fragmentDataList.containsKey("Other")) {
                String other = fragmentDataList.get("Other");
                editTextOther.setText(other);
            }
        }
        return view;
    }

    public void saveData() {

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Belief", getBelief());
            fragmentDataList.put("Other", getOther());
        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return true;
    }

    public String getBelief() {
        return editTextBelief.getText().toString();
    }

    public String getOther() {
        return editTextOther.getText().toString();
    }

    public void highlightUnfilledFields() {
    }
}