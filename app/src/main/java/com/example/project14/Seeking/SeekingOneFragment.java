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
import android.widget.Spinner;

import com.example.project14.Provider.ProviderTwoFragment;
import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SeekingOneFragment extends Fragment {

    private Spinner spinnerSalutation;
    private EditText editTextFirstName;
    private EditText editTextInfix;
    private EditText editTextLastName;
    private EditText editTextPassword;
    private EditText editTextPasswordAgain;

    private EditText editTextEmail;

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
        editTextEmail = view.findViewById(R.id.editTextEmail);

        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Salutation")) {
                String salutation = fragmentDataList.get("Salutation");
                setSpinnerSelection(spinnerSalutation, salutation);
            }
            if (fragmentDataList.containsKey("Email")) {
                String email = fragmentDataList.get("Email");
                editTextEmail.setText(email);
            }
            if (fragmentDataList.containsKey("FirstName")) {
                String firstName = fragmentDataList.get("FirstName");
                editTextFirstName.setText(firstName);
            }
            if (fragmentDataList.containsKey("Infix")) {
                String infix = fragmentDataList.get("Infix");
                editTextInfix.setText(infix);
            }
            if (fragmentDataList.containsKey("LastName")) {
                String lastName = fragmentDataList.get("LastName");
                editTextLastName.setText(lastName);
            }
            if (fragmentDataList.containsKey("Password")) {
                String password = fragmentDataList.get("Password");
                editTextPassword.setText(password);
            }
            if (fragmentDataList.containsKey("PasswordAgain")) {
                String passwordAgain = fragmentDataList.get("PasswordAgain");
                editTextPasswordAgain.setText(passwordAgain);
            }
        }

        return view;
    }

    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Salutation", getSalutation());
            fragmentDataList.put("FirstName", getFirstName());
            fragmentDataList.put("Infix", getInfix());
            fragmentDataList.put("LastName", getLastName());
            fragmentDataList.put("Email", getEmail());
            fragmentDataList.put("Password", getPassword());
            fragmentDataList.put("PasswordAgain", getPasswordAgain());
        }
        // Pass the data bundle to the next fragment
    }


    public boolean isDataValid() {
        return !TextUtils.isEmpty(getSalutation()) &&
                !TextUtils.isEmpty(getFirstName()) &&
                !TextUtils.isEmpty(getEmail()) &&
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

    private String getEmail() {
        return editTextEmail.getText().toString();
    }

    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    public String getPasswordAgain() {
        return editTextPasswordAgain.getText().toString();
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
        // Reset the border color of all EditText views

        if (getSalutation().equals("Maak een keuze")) {
            spinnerSalutation.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerSalutation.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
        if (TextUtils.isEmpty(getFirstName())) {
            editTextFirstName.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextFirstName.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getLastName())) {
            editTextLastName.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextLastName.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getPassword())) {
            editTextPassword.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPassword.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getPasswordAgain())) {
            editTextPasswordAgain.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPasswordAgain.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getEmail())) {
            editTextEmail.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextEmail.setBackgroundResource(R.drawable.border);
        }
    }

    public void uploadPicture() {

    }

}
