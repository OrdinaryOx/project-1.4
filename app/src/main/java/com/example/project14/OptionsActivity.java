package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private String currentLanguageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        currentLanguageCode = "nl"; // Default language code
        RadioGroup languageRadioGroup = findViewById(R.id.languageRadioGroup);


        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.d("TOKEN OPTIONS", token);

        Button logout = findViewById(R.id.logoutButton);

        if (token.equals("null")) {
            logout.setVisibility(View.INVISIBLE);
        }


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
                Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Start MainActivity when logo button is clicked
//                Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        languageRadioGroup = findViewById(R.id.languageRadioGroup);

        // Set the initial checked state based on the language preference
        String currentLanguageCode = LanguageUtils.getLanguagePreference(this);
        if (currentLanguageCode.equals("nl")) {
            languageRadioGroup.check(R.id.languageDutch);
        } else if (currentLanguageCode.equals("en")) {
            languageRadioGroup.check(R.id.languageEnglish);
        }

        LanguageUtils.updateLanguage(this);

        languageRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                String selectedLanguage = selectedRadioButton.getText().toString();

                if (selectedLanguage.equals(getString(R.string.language_english))) {
                    LanguageUtils.setLanguagePreference(OptionsActivity.this, "en");
                } else if (selectedLanguage.equals(getString(R.string.language_Dutch))) {
                    LanguageUtils.setLanguagePreference(OptionsActivity.this, "nl");
                }

                LanguageUtils.updateLanguage(OptionsActivity.this);

                // Restart the activity to apply the language changes
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


        findViewById(R.id.Privacyverklaring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://mijnwoongenoot.nl/privacy/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData((Uri.parse(url)));
                startActivity(i);
            }
        });


    }

    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String emailString = "info@mijnwoongenoot.nl";
        String userName = "Kitty";

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailString});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mijn Woongenoot");
        intent.putExtra(Intent.EXTRA_TEXT, "Hallo " + userName + ", \n");

        startActivity(intent);


    }


    public void dialPhoneNumber(View view) {
        String phoneNumber = "06 11120415";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void logout(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "null");
        editor.apply();

        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openPrivacy(View view) {
        Uri uri = Uri.parse("https://mijnwoongenoot.nl/privacy/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}