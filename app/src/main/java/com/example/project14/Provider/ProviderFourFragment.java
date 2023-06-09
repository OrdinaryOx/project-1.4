package com.example.project14.Provider;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ProviderFourFragment extends Fragment {
    private Spinner spinnerProviderMonth;
    private Spinner spinnerProviderDays;
    private EditText editTextTypeRoom;

    public ProviderFourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_four, container, false);

        // Initialize the EditText views
        spinnerProviderMonth = view.findViewById(R.id.spinnerProviderMonth);
        spinnerProviderDays = view.findViewById(R.id.spinnerProviderDays);
        editTextTypeRoom = view.findViewById(R.id.editTextTypeRoom);

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("ProviderDays")) {
                String day = fragmentDataList.get("ProviderDays");
                setSpinnerSelection(spinnerProviderDays, day);
            }
            if (fragmentDataList.containsKey("ProviderMonth")) {
                String month = fragmentDataList.get("ProviderMonth");
                setSpinnerSelection(spinnerProviderMonth, month);
            }
            if (fragmentDataList.containsKey("TypeRoom")) {
                String typeRoom = fragmentDataList.get("TypeRoom");
                editTextTypeRoom.setText(typeRoom);
            }


        }

        return view;
    }

    public void saveData() {
        String providerMonth = getProviderMonth();
        String providerDays = getProviderDays();
        String typeRoom = getTypeRoom();

        User_Provider_Form activity = (User_Provider_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("ProviderMonth", getProviderMonth());
            fragmentDataList.put("ProviderDays", getProviderDays());
            fragmentDataList.put("TypeRoom", getTypeRoom());

        }

        // Pass the intent to the next fragment
        //    passDataToNextFragment(intent);
    }


    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Provider_Form) {
            ((User_Provider_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getProviderMonth()) &&
                !TextUtils.isEmpty(getProviderDays()) &&
                !TextUtils.isEmpty(getTypeRoom());
    }

    public String getProviderMonth() {
        return spinnerProviderMonth.getSelectedItem().toString();
    }

    public String getProviderDays() {
        return spinnerProviderDays.getSelectedItem().toString();
    }

    public String getTypeRoom() {
        return editTextTypeRoom.getText().toString();
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
        if (getProviderMonth().equals("Maak een keuze")) {
            spinnerProviderMonth.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerProviderMonth.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
        if (getProviderDays().equals("Maak een keuze")) {
            spinnerProviderDays.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerProviderDays.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
        if (TextUtils.isEmpty(getTypeRoom())) {
            editTextTypeRoom.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextTypeRoom.setBackgroundResource(R.drawable.border);
        }

    }


}