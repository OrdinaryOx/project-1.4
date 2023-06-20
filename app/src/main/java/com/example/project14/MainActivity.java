package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.Login.LoginActivity;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView headerOneTextView;
    private TextView textOneTextView;
    private TextView moreInformationTextView;
    private TextView clickableTextTextView;
    private Button buttonButton;
    private String tokenString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //LanguageUtils.updateLanguage(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//
//        Intent intent = new Intent(MainActivity.this, ActivitiesScreen.class);
//        startActivity(intent);

        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("token", "null");
//        editor.apply();


        SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.d("TOKEN MAIN", token);
        tokenString = token;


        //SET TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);


        ImageView backButton = toolbar.findViewById(R.id.back_button);
        ImageView logoButton = toolbar.findViewById(R.id.MWG_logo_IV);
        ImageView optionsButton = toolbar.findViewById(R.id.options_button);

        // Set click listener for the back button
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle back button click
//                onBackPressed();
//            }
//        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });




        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(intent);
            }
        });

        headerOneTextView = findViewById(R.id.header1);
        textOneTextView = findViewById(R.id.text1);
        moreInformationTextView = findViewById(R.id.moreInformation);
        clickableTextTextView = findViewById(R.id.clickableText);
        buttonButton = findViewById(R.id.button);

        LanguageUtils.updateLanguage(this);

        String languageCode = LanguageUtils.getLanguagePreference(MainActivity.this);
        String headerOne = "";
        String textOne = "";
        String moreInformation = "";
        String clickableText= "";
        String button = "";

        if (languageCode.equals("nl")) {
            headerOne = getResources().getString(R.string.main_title);
            textOne  = getResources().getString(R.string.main_info);
            moreInformation = getResources().getString(R.string.main_moreInfo);
            clickableText = getResources().getString(R.string.main_link);
            button = getResources().getString(R.string.main_button);
        } else if (languageCode.equals("en")) {
            headerOne = getResources().getString(R.string.main_title_en);
            textOne  = getResources().getString(R.string.main_info_en);
            moreInformation = getResources().getString(R.string.main_moreInfo_en);
            clickableText = getResources().getString(R.string.main_link_en);
            button = getResources().getString(R.string.main_button_en);
        }

        headerOneTextView.setText(headerOne);
        textOneTextView.setText(textOne);
        moreInformationTextView.setText(moreInformation);
        clickableTextTextView.setText(clickableText);
        buttonButton.setText(button);



        TextView mainLink = findViewById(R.id.clickableText);

        mainLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://mijnwoongenoot.nl";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }

    public void Continue(View view) {
        if (tokenString.length() > 10){
            Log.d("TOKENSTRING", tokenString);
            Intent intent = new Intent(this, ActivitiesScreen.class);
            startActivity(intent);
        } else {
         Log.d("TOKENSTRING NO", tokenString);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        }
    }
}
