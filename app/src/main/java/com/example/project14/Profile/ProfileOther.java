package com.example.project14.Profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project14.LanguageUtils;
import com.example.project14.R;

public class ProfileOther extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageUtils.updateLanguage(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_other);
    }
}