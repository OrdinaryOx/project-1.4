package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileOther extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_other);



        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");

        TextView userNameTV = findViewById(R.id.username);
        userNameTV.setText(userName);
    }
}