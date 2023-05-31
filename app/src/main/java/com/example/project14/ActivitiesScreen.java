package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project14.Chats.AllChats;
import com.example.project14.Match.AllMatches;

public class ActivitiesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_screen);
    }


    public void startMatches(View view) {
        Intent intent = new Intent(this, AllMatches.class);
        startActivity(intent);
    }

//    public void startProfile(View view) {
//        Intent intent = new Intent(this, ProfileActivity.class);
//        startActivity(intent);
//    }

    public void startChats(View view) {
        Intent intent = new Intent(this, AllChats.class);
        startActivity(intent);
    }
}