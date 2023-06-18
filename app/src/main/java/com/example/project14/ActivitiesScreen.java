package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project14.Chats.AllChats;
import com.example.project14.Login.LoginActivity;
import com.example.project14.Match.AllMatches;
import com.example.project14.Profile.ProfileUser;

public class ActivitiesScreen extends AppCompatActivity {

    private TextView matchTextView;
    private TextView profileTextView;
    private TextView chatTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.d("TAG ACTIVITY 2", token);


        ImageView backButton = toolbar.findViewById(R.id.back_button);
        ImageView logoButton = toolbar.findViewById(R.id.MWG_logo_IV);
        ImageView optionsButton = toolbar.findViewById(R.id.options_button);

        matchTextView = findViewById(R.id.textView3);
        profileTextView = findViewById(R.id.textView2);
        chatTextView = findViewById(R.id.textView);


        LanguageUtils.updateLanguage(this);

        String languageCode = LanguageUtils.getLanguagePreference(ActivitiesScreen.this);
        String matchText = "";
        String profileText = "";
        String chatText = "";


        if (languageCode.equals("nl")) {
            matchText = getResources().getString(R.string.matchesText);
            profileText = getResources().getString(R.string.profielText);
            chatText = getResources().getString(R.string.chatText);
        } else if (languageCode.equals("en")) {
            matchText = getResources().getString(R.string.matchesText_en);
            profileText = getResources().getString(R.string.profielText_en);
            chatText = getResources().getString(R.string.chatText_en);
        }

        matchTextView.setText(matchText);
        profileTextView.setText(profileText);
        chatTextView.setText(chatText);

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