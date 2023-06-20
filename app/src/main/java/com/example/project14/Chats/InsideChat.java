package com.example.project14.Chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project14.ActivitiesScreen;
//import com.example.project14.LoginActivity;
import com.example.project14.LanguageUtils;
import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;

public class InsideChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageUtils.updateLanguage(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);


        ImageView backButton = toolbar.findViewById(R.id.back_button);
        ImageView logoButton = toolbar.findViewById(R.id.MWG_logo_IV);
        ImageView optionsButton = toolbar.findViewById(R.id.options_button);
        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
               onBackPressed();
                finish();
            }
        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(InsideChat.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsideChat.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
        // Retrieve the data passed from the previous activity
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // Display the data in your views
        TextView usernameTextView = findViewById(R.id.chat_username);

        usernameTextView.setText(username);

    }
}