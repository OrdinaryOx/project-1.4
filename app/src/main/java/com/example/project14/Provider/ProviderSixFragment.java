package com.example.project14.Provider;

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
import android.widget.TextView;

import com.example.project14.LanguageUtils;
import com.example.project14.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ProviderSixFragment extends Fragment {
    private EditText editTextOffer;
    private EditText editTextImportantNote;
    private RadioGroup radioGroupVolunteer;
    private EditText editTextCommentVolunteer;
    private RadioButton vrijwilligYes;
    private RadioButton vrijwilligNo;
    private TextView meanTextView;
    private TextView noteTextView;
    private TextView volunteerTextView;
    private TextView volunteerMoreTextView;

    public ProviderSixFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_six, container, false);

        // Initialize the EditText views
        editTextOffer = view.findViewById(R.id.editTextOffer);
        editTextImportantNote = view.findViewById(R.id.editTextImportantNote);

        radioGroupVolunteer = view.findViewById(R.id.radioGroupVolunteer);
        editTextCommentVolunteer = view.findViewById(R.id.editTextCommentVolunteer);
        vrijwilligNo = view.findViewById(R.id.radio_button_volunteer_no);
        vrijwilligYes = view.findViewById(R.id.radio_button_volunteer_yes);

        meanTextView = view.findViewById(R.id.textView17);
        noteTextView = view.findViewById(R.id.textView28);
        volunteerTextView = view.findViewById(R.id.textView29);
        volunteerMoreTextView = view.findViewById(R.id.textView31);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String offerEdit = "";
        String importantNoteEdit = "";
        String volunteerEdit = "";

        String volunteerYes = "";
        String volunteerNo = "";

        String meanText = "";
        String noteText = "";
        String volunteerText = "";
        String volunteerMoreText = "";

        if (languageCode.equals("nl")) {
            offerEdit = getResources().getString(R.string.provider_meanHint);
            importantNoteEdit = getResources().getString(R.string.provider_noteHint);
            volunteerEdit = getResources().getString(R.string.provider_type);

            volunteerYes = getResources().getString(R.string.provider_volunteerYes);
            volunteerNo = getResources().getString(R.string.provider_volunteerNo);

            meanText = getResources().getString(R.string.provider_mean);
            noteText = getResources().getString(R.string.provider_note);
            volunteerText = getResources().getString(R.string.provider_volunteer);
            volunteerMoreText = getResources().getString(R.string.provider_noteComment);
        } else if (languageCode.equals("en")) {
            offerEdit = getResources().getString(R.string.provider_meanHint_en);
            importantNoteEdit = getResources().getString(R.string.provider_noteHint_en);
            volunteerEdit = getResources().getString(R.string.provider_type_en);

            volunteerYes = getResources().getString(R.string.provider_volunteerYes_en);
            volunteerNo = getResources().getString(R.string.provider_volunteerNo_en);

            meanText = getResources().getString(R.string.provider_mean_en);
            noteText = getResources().getString(R.string.provider_note_en);
            volunteerText = getResources().getString(R.string.provider_volunteer_en);
            volunteerMoreText = getResources().getString(R.string.provider_noteComment_en);
        }

        editTextOffer.setHint(offerEdit);
        editTextImportantNote.setHint(importantNoteEdit);
        editTextCommentVolunteer.setHint(volunteerEdit);

        vrijwilligYes.setText(volunteerYes);
        vrijwilligNo.setText(volunteerNo);

        meanTextView.setText(meanText);
        noteTextView.setText(noteText);
        volunteerTextView.setText(volunteerText);
        volunteerMoreTextView.setText(volunteerMoreText);

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Situation")) {
                String offer = fragmentDataList.get("Offer");
                editTextOffer.setText(offer);
            }
            if (fragmentDataList.containsKey("ImportantNote")) {
                String note = fragmentDataList.get("ImportantNote");
                editTextImportantNote.setText(note);
            }
            if (fragmentDataList.containsKey("Volunteer")) {
                String volunteer = fragmentDataList.get("Volunteer");
                setRadioButtonSelection(radioGroupVolunteer, volunteer);
            }
            if (fragmentDataList.containsKey("CommentVolunteer")) {
                String volunteerComment = fragmentDataList.get("CommentVolunteer");
                editTextCommentVolunteer.setText(volunteerComment);
            }

        }


        return view;
    }

    private void setRadioButtonSelection(RadioGroup radioGroup, String value) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
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

    public void saveData() {

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Offer", getOffer());
            fragmentDataList.put("ImportantNote", getImportantNote());
            fragmentDataList.put("Volunteer", getVolunteer());
            fragmentDataList.put("CommentVolunteer", getCommentVolunteer());

        }


    }


    public boolean isDataValid() {
        if (getVolunteer().equals("Ja") && TextUtils.isEmpty(getCommentVolunteer())) {
            return false;
        }


        return !TextUtils.isEmpty(getOffer()) &&
                !TextUtils.isEmpty(getImportantNote()) &&
                !TextUtils.isEmpty(getVolunteer());
    }

    public String getOffer() {
        return editTextOffer.getText().toString();
    }

    public String getImportantNote() {
        return editTextImportantNote.getText().toString();
    }

    public String getVolunteer() {
        int checkedRadioButtonId = radioGroupVolunteer.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {

            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public String getCommentVolunteer() {
        return editTextCommentVolunteer.getText().toString();
    }


    public void highlightUnfilledFields() {

        if (getVolunteer().equals("-1")) {
            vrijwilligNo.setBackgroundResource(R.drawable.border_red);
            vrijwilligYes.setBackgroundResource(R.drawable.border_red);
        } else {
            vrijwilligNo.setBackgroundResource(R.drawable.border);
            vrijwilligYes.setBackgroundResource(R.drawable.border);

        }
        if (TextUtils.isEmpty(getOffer())) {
            editTextOffer.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextOffer.setBackgroundResource(R.drawable.border);
        }
        if (getVolunteer().equals("Ja") && TextUtils.isEmpty(getCommentVolunteer())) {
            editTextCommentVolunteer.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextCommentVolunteer.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getImportantNote())) {
            editTextImportantNote.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextImportantNote.setBackgroundResource(R.drawable.border);
        }
    }
}