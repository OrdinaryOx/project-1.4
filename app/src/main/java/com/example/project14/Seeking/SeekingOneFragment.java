package com.example.project14.Seeking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project14.Provider.ProviderTwoFragment;
import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;

public class SeekingOneFragment extends Fragment {

    private Spinner spinnerSalutation;
    private EditText editTextFirstName;
    private EditText editTextInfix;
    private EditText editTextLastName;
    private EditText editTextPassword;
    private EditText editTextPasswordAgain;

    public SeekingOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_one, container, false);

        // Initialize the EditText views

        spinnerSalutation = view.findViewById(R.id.spinnerSalutation);
        editTextFirstName = view.findViewById(R.id.editTextFirstName);
        editTextInfix = view.findViewById(R.id.editTextInfix);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextPasswordAgain = view.findViewById(R.id.editTextPasswordAgain);

        return view;
    }

    public void saveData() {
        String salutation = getSalutation();
        String firstName = getFirstName();
        String infix = getInfix();
        String lastName = getLastName();
        String password = getPassword();
        String passwordAgain = getPasswordAgain();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), SeekingTwoFragment.class);
        intent.putExtra("salutation", salutation);
        intent.putExtra("firstName", firstName);
        intent.putExtra("infix", infix);
        intent.putExtra("lastName", lastName);
        intent.putExtra("password", password);
        intent.putExtra("passwordAgain", passwordAgain);

        // Pass the intent to the next fragment
        passDataToNextFragment(intent);
    }
    public void passDataToNextFragment(Intent intent) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(intent);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getSalutation()) &&
                !TextUtils.isEmpty(getFirstName()) &&
                !TextUtils.isEmpty(getInfix()) &&
                !TextUtils.isEmpty(getLastName()) &&
                !TextUtils.isEmpty(getPassword()) &&
                !TextUtils.isEmpty(getPasswordAgain());
    }

    public String getSalutation() {
        return spinnerSalutation.getSelectedItem().toString();
    }

    public String getFirstName() {
        return editTextFirstName.getText().toString();
    }

    public String getInfix() {
        return editTextInfix.getText().toString();
    }

    public String getLastName() {
        return editTextLastName.getText().toString();
    }

    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    public String getPasswordAgain() {
        return editTextPasswordAgain.getText().toString();
    }
}
