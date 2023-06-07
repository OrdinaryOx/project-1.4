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

import com.example.project14.R;

public class ProviderEightFragment extends Fragment {

    private EditText editTextBelief;
    private EditText editTextOther;

    public ProviderEightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_eight, container, false);

        // Initialize the EditText views
        editTextBelief = view.findViewById(R.id.editTextBelief);
        editTextOther = view.findViewById(R.id.editTextOther);

        return view;
    }

    public void saveData() {
        String belief = getBelief();
        String comment = getComment();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), ProviderNineFragment.class);
        intent.putExtra("belief", belief);
        intent.putExtra("comment", comment);

        // Pass the intent to the next fragment
        passDataToNextFragment(intent);
    }

    public void passDataToNextFragment(Intent intent) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(intent);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getBelief()) &&
                !TextUtils.isEmpty(getComment());
    }

    public String getBelief() {
        return editTextBelief.getText().toString();
    }

    public String getComment() {
        return editTextOther.getText().toString();
    }

}