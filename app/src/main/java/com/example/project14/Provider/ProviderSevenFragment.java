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
import android.widget.TextView;

import com.example.project14.LanguageUtils;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ProviderSevenFragment extends Fragment {

    private RadioGroup radioGroupProviderWork;
    private EditText editTextProviderWork;
    private EditText editTextKeyword;
    private EditText editTextHobby;
    private RadioGroup radioGroupSelfPets;
    private EditText editTextPets;
    private RadioButton workYes;
    private RadioButton workNo;
    private RadioButton petsYes;
    private RadioButton petsNo;
    private TextView workTextView;
    private TextView workMoreTextView;
    private TextView keywordTextView;
    private TextView hobbyTextView;
    private TextView petTextView;
    private TextView petMoreTextView;


    public ProviderSevenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_seven, container, false);

        // Initialize the EditText views
        radioGroupProviderWork = view.findViewById(R.id.radioGroupProviderWork);
        editTextProviderWork = view.findViewById(R.id.editTextProviderWork);
        editTextKeyword = view.findViewById(R.id.editTextKeyword);
        editTextHobby = view.findViewById(R.id.editTextHobby);
        radioGroupSelfPets = view.findViewById(R.id.radioGroupSelfPets);
        editTextPets = view.findViewById(R.id.editTextPets);

        workYes = view.findViewById(R.id.radio_button_provider_work_yes);
        workNo = view.findViewById(R.id.radio_button_provider_work_no);

        petsYes = view.findViewById(R.id.radio_button_yes);
        petsNo = view.findViewById(R.id.radio_button_no);

        workTextView = view.findViewById(R.id.textView15);
        workMoreTextView = view.findViewById(R.id.textView16);
        keywordTextView = view.findViewById(R.id.textView35);
        hobbyTextView = view.findViewById(R.id.textView36);
        petTextView = view.findViewById(R.id.textView12);
        petMoreTextView = view.findViewById(R.id.textView29);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String workEdit = "";
        String keywordEdit = "";
        String hobbyEdit = "";
        String petsEdit = "";

        String workYesRadio = "" ;
        String workNoRadio = "";

        String petsYesRadio = "";
        String petsNoRadio = "";

        String workText = "";
        String workMoreText = "";
        String keywordText = "";
        String hobbyText = "";
        String petText = "";
        String petMoreText = "";

        if (languageCode.equals("nl")) {
            workEdit = getResources().getString(R.string.provider_workCommentHint);
            keywordEdit = getResources().getString(R.string.provider_keywordHint);
            hobbyEdit = getResources().getString(R.string.provider_hobbyHint);
            petsEdit = getResources().getString(R.string.provider_petCommentHint);

            workYesRadio = getResources().getString(R.string.provider_workYes);
            workNoRadio = getResources().getString(R.string.provider_workNo);

            petsYesRadio = getResources().getString(R.string.providerPetsYes);
            petsNoRadio = getResources().getString(R.string.providerPetsNo);

            workText = getResources().getString(R.string.provider_work);
            workMoreText = getResources().getString(R.string.provider_workComment);
            keywordText = getResources().getString(R.string.provider_keyword);
            hobbyText = getResources().getString(R.string.provider_hobby);
            petText = getResources().getString(R.string.providerPets);
            petMoreText = getResources().getString(R.string.provider_petComment);

        } else if (languageCode.equals("en")) {
            workEdit = getResources().getString(R.string.provider_workCommentHint_en);
            keywordEdit = getResources().getString(R.string.provider_keywordHint_en);
            hobbyEdit = getResources().getString(R.string.provider_hobbyHint_en);
            petsEdit = getResources().getString(R.string.provider_petCommentHint_en);

            workYesRadio = getResources().getString(R.string.provider_workYes_en);
            workNoRadio = getResources().getString(R.string.provider_workNo_en);

            petsYesRadio = getResources().getString(R.string.providerPetsYes_en);
            petsNoRadio = getResources().getString(R.string.providerPetsNo_en);

            workText = getResources().getString(R.string.provider_work_en);
            workMoreText = getResources().getString(R.string.provider_workComment_en);
            keywordText = getResources().getString(R.string.provider_keyword_en);
            hobbyText = getResources().getString(R.string.provider_hobby_en);
            petText = getResources().getString(R.string.providerPets_en);
            petMoreText = getResources().getString(R.string.provider_petComment_en);
        }

        editTextProviderWork.setHint(workEdit);
        editTextKeyword.setHint(keywordEdit);
        editTextHobby.setHint(hobbyEdit);
        editTextPets.setHint(petsEdit);

        workYes.setText(workYesRadio);
        workNo.setText(workNoRadio);

        petsYes.setText(petsYesRadio);
        petsNo.setText(petsNoRadio);

        workTextView.setText(workText);
        workMoreTextView.setText(workMoreText);
        keywordTextView.setText(keywordText);
        hobbyTextView.setText(hobbyText);
        petTextView.setText(petText);
        petMoreTextView.setText(petMoreText);

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("ProviderWorkDetails")) {
                String workDetails = fragmentDataList.get("ProviderWorkDetails");
                editTextProviderWork.setText(workDetails);
            }
            if (fragmentDataList.containsKey("Keyword")) {
                String keyword = fragmentDataList.get("Keyword");
                editTextKeyword.setText(keyword);
            }
            if (fragmentDataList.containsKey("Hobby")) {
                String hobby = fragmentDataList.get("Hobby");
                editTextHobby.setText(hobby);
            }
            if (fragmentDataList.containsKey("Pets")) {
                String pets = fragmentDataList.get("Pets");
                editTextPets.setText(pets);
            }
            if (fragmentDataList.containsKey("ProviderWork")) {
                String work = fragmentDataList.get("ProviderWork");
                setRadioButtonSelection(radioGroupProviderWork, work);
            }
            if (fragmentDataList.containsKey("SelfPets")) {
                String pets = fragmentDataList.get("SelfPets");
                setRadioButtonSelection(radioGroupSelfPets, pets);
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

        // Create an intent and add the data as extras
        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("ProviderWork", getProviderWork());
            fragmentDataList.put("ProviderWorkDetails", getProviderWorkDetails());
            fragmentDataList.put("Keyword", getKeyword());
            fragmentDataList.put("Hobby", getHobby());
            fragmentDataList.put("SelfPets", getSelfPets());
            fragmentDataList.put("Pets", getPets());
        }


        // Pass the intent to the next fragment
        //  passDataToNextFragment(intent);
    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        if (getSelfPets().equals("Ja") && TextUtils.isEmpty(getPets())) {
            return false;
        }

        if (getProviderWork().equals("Ja") && TextUtils.isEmpty(getProviderWorkDetails())) {
            return false;
        }
        return !TextUtils.isEmpty(getProviderWork()) &&
                !TextUtils.isEmpty(getKeyword()) &&
                !TextUtils.isEmpty(getHobby()) &&
                !TextUtils.isEmpty(getSelfPets());
    }

    public String getProviderWork() {
        int checkedRadioButtonId = radioGroupProviderWork.getCheckedRadioButtonId();
        RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
        return radioButton.getText().toString();
    }

    public String getProviderWorkDetails() {
        return editTextProviderWork.getText().toString();
    }

    public String getKeyword() {
        return editTextKeyword.getText().toString();
    }

    public String getHobby() {
        return editTextHobby.getText().toString();
    }

    public String getSelfPets() {
        int checkedRadioButtonId = radioGroupSelfPets.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {

            RadioButton radioButton = getView().findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "-1";
    }

    public String getPets() {
        return editTextPets.getText().toString();
    }


    public void highlightUnfilledFields() {
        if (getProviderWork().equals("-1")) {
            workYes.setBackgroundResource(R.drawable.border_red);
            workNo.setBackgroundResource(R.drawable.border_red);
        } else {
            workYes.setBackgroundResource(R.drawable.border);
            workNo.setBackgroundResource(R.drawable.border);
        }
        if (getSelfPets().equals("-1")) {
            petsNo.setBackgroundResource(R.drawable.border_red);
            petsYes.setBackgroundResource(R.drawable.border_red);
        } else {
            petsYes.setBackgroundResource(R.drawable.border);
            petsNo.setBackgroundResource(R.drawable.border);

        }
        if (TextUtils.isEmpty(getKeyword())) {
            editTextKeyword.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextKeyword.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getHobby())) {
            editTextHobby.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextHobby.setBackgroundResource(R.drawable.border);
        }
        if (getSelfPets().equals("Ja") && TextUtils.isEmpty(getPets())) {
            editTextPets.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPets.setBackgroundResource(R.drawable.border);
        }
        if (getProviderWork().equals("Ja") && TextUtils.isEmpty(getProviderWorkDetails())) {
            editTextProviderWork.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextProviderWork.setBackgroundResource(R.drawable.border);
        }
    }

}