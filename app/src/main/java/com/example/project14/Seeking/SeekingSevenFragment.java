package com.example.project14.Seeking;

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

import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

public class SeekingSevenFragment extends Fragment {
    private EditText editTextYourself;
    private EditText editTextKeyword;
    private EditText editTextRoom;
    private EditText editTextMean;

    public SeekingSevenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_seven, container, false);

        // Initialize the EditText views
        editTextYourself = view.findViewById(R.id.editTextYourself);
        editTextKeyword = view.findViewById(R.id.editTextKeyword);
        editTextRoom = view.findViewById(R.id.editTextRoom);
        editTextMean = view.findViewById(R.id.editTextMean);

        return view;
    }

    public void saveData() {
        String yourself = getYourself();
        String keyword = getKeyword();
        String room = getRoom();
        String mean = getMean();

        // Create an intent and add the data as extras
        Intent intent = new Intent(getContext(), SeekingEightFragment.class);
        intent.putExtra("yourself", yourself);
        intent.putExtra("keyword", keyword);
        intent.putExtra("room", room);
        intent.putExtra("mean", mean);

        // Pass the intent to the next fragment
     //   passDataToNextFragment(intent);
    }

    public void passDataToNextFragment(Bundle data) {
        if (getActivity() instanceof User_Seeking_Form) {
            ((User_Seeking_Form) getActivity()).passDataToNextFragment(data);
        }
    }

    public boolean isDataValid() {
        return !TextUtils.isEmpty(getYourself()) &&
                !TextUtils.isEmpty(getKeyword()) &&
                !TextUtils.isEmpty(getRoom()) &&
                !TextUtils.isEmpty(getMean());
    }

    public String getYourself() {
        return editTextYourself.getText().toString();
    }

    public String getKeyword() {
        return editTextKeyword.getText().toString();
    }

    public String getRoom() {
        return editTextRoom.getText().toString();
    }

    public String getMean() {
        return editTextMean.getText().toString();
    }

}