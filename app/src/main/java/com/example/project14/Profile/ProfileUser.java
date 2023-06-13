package com.example.project14.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;

public class ProfileUser extends AppCompatActivity {

    private void getUserProfile() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView backButton = toolbar.findViewById(R.id.back_button);
        ImageView logoButton = toolbar.findViewById(R.id.MWG_logo_IV);
        ImageView optionsButton = toolbar.findViewById(R.id.options_button);
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
                Intent intent = new Intent(ProfileUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileUser.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
    }
}