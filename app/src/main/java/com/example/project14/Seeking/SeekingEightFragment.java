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
import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class SeekingEightFragment extends Fragment {
    private EditText editTextOtherOffer;
    private EditText editTextImportantNote;
    private RadioGroup radioGroupVolunteer;
    private EditText editTextVolunteer;
    private RadioButton volunteerYes;
    private RadioButton volunteerNo;
    private TextView meanTextView;
    private TextView noteTextView;
    private TextView volunteerTextView;
    private TextView volunteerMoreTextView;


    public SeekingEightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_eight, container, false);

        // Initialize the EditText views
        editTextOtherOffer = view.findViewById(R.id.editTextOtherOffer);
        editTextImportantNote = view.findViewById(R.id.editTextImportantNote);
        radioGroupVolunteer = view.findViewById(R.id.radioGroupVolunteer);
        editTextVolunteer = view.findViewById(R.id.editTextVolunteer);

        volunteerYes = view.findViewById(R.id.radio_button_volunteer_yes);
        volunteerNo = view.findViewById(R.id.radio_button_volunteer_no);

        meanTextView = view.findViewById(R.id.textView17);
        noteTextView = view.findViewById(R.id.textView28);
        volunteerTextView = view.findViewById(R.id.textView29);
        volunteerMoreTextView = view.findViewById(R.id.textView31);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String offerEdit = "";
        String importantNoteEdit = "";
        String volunteerEdit = "";

        String volunteerRadioYes = "";
        String volunteerRadioNo = "";

        String meanText = "";
        String noteText = "";
        String volunteerText = "";
        String volunteerMoreText = "";

        if (languageCode.equals("nl")) {
            offerEdit = getResources().getString(R.string.provider_meanHint);
            importantNoteEdit = getResources().getString(R.string.provider_noteHint);
            volunteerEdit = getResources().getString(R.string.provider_type);

            volunteerRadioYes = getResources().getString(R.string.provider_volunteerYes);
            volunteerRadioNo = getResources().getString(R.string.provider_volunteerNo);

            meanText = getResources().getString(R.string.provider_mean);
            noteText = getResources().getString(R.string.provider_note);
            volunteerText = getResources().getString(R.string.provider_volunteer);
            volunteerMoreText = getResources().getString(R.string.provider_noteComment);
        } else if (languageCode.equals("en")) {
            offerEdit = getResources().getString(R.string.provider_meanHint_en);
            importantNoteEdit = getResources().getString(R.string.provider_noteHint_en);
            volunteerEdit = getResources().getString(R.string.provider_type_en);

            volunteerRadioYes = getResources().getString(R.string.provider_volunteerYes_en);
            volunteerRadioNo = getResources().getString(R.string.provider_volunteerNo_en);

            meanText = getResources().getString(R.string.provider_mean_en);
            noteText = getResources().getString(R.string.provider_note_en);
            volunteerText = getResources().getString(R.string.provider_volunteer_en);
            volunteerMoreText = getResources().getString(R.string.provider_noteComment_en);
        }

        editTextOtherOffer.setHint(offerEdit);
        editTextImportantNote.setHint(importantNoteEdit);
        editTextVolunteer.setHint(volunteerEdit);

        volunteerYes.setText(volunteerRadioYes);
        volunteerNo.setText(volunteerRadioNo);

        meanTextView.setText(meanText);
        noteTextView.setText(noteText);
        volunteerTextView.setText(volunteerText);
        volunteerMoreTextView.setText(volunteerMoreText);

//        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
//        ArrayList<String> fragmentDataList = activity.getFragmentDataList();
//        Log.d("ARRAYLIST FRAGMENT 8", " " + fragmentDataList);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("OtherOffer")) {
                String otherOffer = fragmentDataList.get("OtherOffer");
                editTextOtherOffer.setText(otherOffer);
            }
            if (fragmentDataList.containsKey("ImportantNote")) {
                String importantNote = fragmentDataList.get("ImportantNote");
                editTextImportantNote.setText(importantNote);
            }
            if (fragmentDataList.containsKey("VolunteerSelection")) {
                String volunteerSelection = fragmentDataList.get("VolunteerSelection");
                setRadioGroupSelection(radioGroupVolunteer, volunteerSelection);
            }
            if (fragmentDataList.containsKey("Volunteer")) {
                String volunteer = fragmentDataList.get("Volunteer");
                editTextVolunteer.setText(volunteer);
            }
        }

        return view;
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("OtherOffer", getOtherOffer());
            fragmentDataList.put("ImportantNote", getImportantNote());
            fragmentDataList.put("VolunteerSelection", getVolunteerSelection());
            fragmentDataList.put("Volunteer", getVolunteer());
        }
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        if (getVolunteerSelection().equals("Ja") && TextUtils.isEmpty(getVolunteer())) {
            return false;
        }

        return !getVolunteerSelection().equals("-1") &&
                !TextUtils.isEmpty(getOtherOffer()) &&
                !TextUtils.isEmpty(getImportantNote());
    }


    public String getOtherOffer() {
        return editTextOtherOffer.getText().toString();
    }

    public String getImportantNote() {
        return editTextImportantNote.getText().toString();
    }

    public String getVolunteerSelection() {
        int checkedRadioButtonId = radioGroupVolunteer.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public String getVolunteer() {
        return editTextVolunteer.getText().toString();
    }

    private void setRadioGroupSelection(RadioGroup radioGroup, String value) {
        int count = radioGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;
                if (radioButton.getText().toString().equals(value)) {
                    radioButton.setChecked(true);
                    break;
                }
            }
        }
    }

    public void highlightUnfilledFields() {

        if (getVolunteerSelection().equals("-1")) {
            volunteerNo.setBackgroundResource(R.drawable.border_red);
            volunteerYes.setBackgroundResource(R.drawable.border_red);
        } else {
            volunteerNo.setBackgroundResource(R.drawable.border);
            volunteerYes.setBackgroundResource(R.drawable.border);
        }
        if (getVolunteerSelection().equals("Ja") && TextUtils.isEmpty(getVolunteer())) {
            editTextVolunteer.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextVolunteer.setBackgroundResource(R.drawable.border);
        }
        if (getOtherOffer().isEmpty()) {
            editTextOtherOffer.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextOtherOffer.setBackgroundResource(R.drawable.border);
        }
        if(getImportantNote().isEmpty()) {
            editTextImportantNote.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextImportantNote.setBackgroundResource(R.drawable.border);
        }


    }
}