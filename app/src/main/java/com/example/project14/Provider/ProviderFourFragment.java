package com.example.project14.Provider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.project14.R;


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

        return view;
    }

    public void saveData() {
        // Get the data from the fragment
        String providerMonth = getProviderMonth();
        String providerDays = getProviderDays();
        String typeRoom = getTypeRoom();
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

}