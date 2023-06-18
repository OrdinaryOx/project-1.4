package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.Seeking.User_Seeking_Form;

public class RoleActivity extends AppCompatActivity {

    private TextView providerTextView;
    private TextView seekingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView backButton = toolbar.findViewById(R.id.back_button);
        ImageView logoButton = toolbar.findViewById(R.id.MWG_logo_IV);
        ImageView optionsButton = toolbar.findViewById(R.id.options_button);

        providerTextView = findViewById(R.id.provider_TextView);
        seekingTextView = findViewById(R.id.seeking_TextView);


        LanguageUtils.updateLanguage(this);

        String languageCode = LanguageUtils.getLanguagePreference(RoleActivity.this);
        String providerText = "";
        String seekingText = "";

        if (languageCode.equals("nl")) {
            providerText = getResources().getString(R.string.role_provider);
            seekingText = getResources().getString(R.string.role_seeking);


        } else if (languageCode.equals("en")) {
            providerText = getResources().getString(R.string.role_provider_en);
            seekingText = getResources().getString(R.string.role_seeking_en);
        }

        providerTextView.setText(providerText);
        seekingTextView.setText(seekingText);

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
                onBackPressed();
            }
        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(RoleActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleActivity.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Verhuurder(View view) {
        Intent intent = new Intent(this, User_Provider_Form.class);
        startActivity(intent);
        finish();
    }


    public void Huurder(View view) {
        Intent intent = new Intent(this, User_Seeking_Form.class);
        startActivity(intent);
        finish();
    }
}