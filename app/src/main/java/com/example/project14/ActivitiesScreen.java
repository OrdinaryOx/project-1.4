package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project14.Chats.AllChats;
import com.example.project14.Login.LoginActivity;
import com.example.project14.Match.AllMatches;

public class ActivitiesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_screen);

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
                Intent intent = new Intent(ActivitiesScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(ActivitiesScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitiesScreen.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
    }


    public void startMatches(View view) {
        Intent intent = new Intent(this, AllMatches.class);
        startActivity(intent);
    }

    public void startProfile(View view) {
        Intent intent = new Intent(this, ProfileUser.class);
        startActivity(intent);
    }

    public void startChats(View view) {
        Intent intent = new Intent(this, AllChats.class);
        startActivity(intent);
    }
}