package com.example.project14.Provider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project14.ActivitiesScreen;
import com.example.project14.LanguageUtils;
import com.example.project14.R;
import com.example.project14.Seeking.SeekingTwoFragment;
import com.example.project14.Seeking.User_Seeking_Form;

import java.util.ArrayList;
import java.util.HashMap;


public class ProviderNineFragment extends Fragment {

    private CheckBox checkBoxTruth;
    private CheckBox checkBoxPermission;
    private CheckBox checkBoxTerms;
    private EditText editTextComment;

    private TextView commentTextView;
    private TextView privacyTextView;
    private TextView cookieTextView;
    private Button sendBtn;


    public ProviderNineFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_nine, container, false);

        // Initialize the EditText views
        checkBoxTruth = view.findViewById(R.id.checkBoxTruth);
        checkBoxPermission = view.findViewById(R.id.checkBoxPermission);
        checkBoxTerms = view.findViewById(R.id.checkBoxTerms);
        editTextComment = view.findViewById(R.id.editTextComment);

        commentTextView = view.findViewById(R.id.textView31);
        privacyTextView = view.findViewById(R.id.textViewTerms);
        cookieTextView = view.findViewById(R.id.textViewCookies);

        sendBtn = view.findViewById(R.id.opsturenButtonProvider);


        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String truthCheck = "";
        String permissionCheck = "";
        String termsCheck = "";

        String commentEdit = "";

        String commentText = "";
        String privacyText = "";
        String cookieText = "";

        String sendButton = "";


        if (languageCode.equals("nl")) {
            truthCheck = getResources().getString(R.string.provider_checkTruth);
            permissionCheck = getResources().getString(R.string.provider_checkPicture);
            termsCheck = getResources().getString(R.string.provider_checkPrivacy);

            commentEdit = getResources().getString(R.string.provider_type);

            commentText = getResources().getString(R.string.provider_comment);
            privacyText = getResources().getString(R.string.provider_linkPrivacy);
            cookieText = getResources().getString(R.string.privacy_linkCookie);

            sendButton = getResources().getString(R.string.provider_send);
        } else if (languageCode.equals("en")) {
            truthCheck = getResources().getString(R.string.provider_checkTruth_en);
            permissionCheck = getResources().getString(R.string.provider_checkPicture_en);
            termsCheck = getResources().getString(R.string.provider_checkPrivacy_en);

            commentEdit = getResources().getString(R.string.provider_type_en);

            commentText = getResources().getString(R.string.provider_comment_en);
            privacyText = getResources().getString(R.string.provider_linkPrivacy_en);
            cookieText = getResources().getString(R.string.privacy_linkCookie_en);

            sendButton = getResources().getString(R.string.provider_send_en);
        }

        checkBoxTruth.setText(truthCheck);
        checkBoxPermission.setText(permissionCheck);
        checkBoxTerms.setText(termsCheck);

        editTextComment.setHint(commentEdit);

        commentTextView.setText(commentText);
        privacyTextView.setText(privacyText);
        cookieTextView.setText(cookieText);

        sendBtn.setText(sendButton);

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
        Log.d("ARRAYLIST FRAGMENT 10", " " + fragmentDataList);

        return view;
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Comment", getComment());
        }
    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return isTruthChecked() &&
                isPermissionChecked() &&
                isTermsChecked() &&
                !TextUtils.isEmpty(getComment());
    }

    public boolean isTruthChecked() {
        return checkBoxTruth.isChecked();
    }

    public boolean isPermissionChecked() {
        return checkBoxPermission.isChecked();
    }

    public boolean isTermsChecked() {
        return checkBoxTerms.isChecked();
    }

    public String getComment() {
        return editTextComment.getText().toString();
    }

    public void highlightUnfilledFields() {
        if (isTruthChecked()) {
            checkBoxTruth.setBackgroundResource(R.drawable.border);
        } else {
            checkBoxTruth.setBackgroundResource(R.drawable.border_red);
        }
        if (isPermissionChecked()) {
            checkBoxPermission.setBackgroundResource(R.drawable.border);
        } else {
            checkBoxPermission.setBackgroundResource(R.drawable.border_red);
        }
        if (isTermsChecked()) {
            checkBoxTerms.setBackgroundResource(R.drawable.border);
        } else {
            checkBoxTerms.setBackgroundResource(R.drawable.border_red);
        }
    }
}