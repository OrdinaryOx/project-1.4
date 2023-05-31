package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.Seeking.User_Seeking_Form;

public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
    }

    public void Verhuurder(View view) {
        Intent intent = new Intent(this, User_Provider_Form.class);
        startActivity(intent);
    }


    public void Huurder(View view) {
        Intent intent = new Intent(this, User_Seeking_Form.class);
        startActivity(intent);
    }
}