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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.project14.Match.ApiService;
import com.example.project14.Provider.User_Provider_Form;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OptionsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private String currentLanguageCode;

    private TextView languageTextView;
    private TextView dutchTextView;
    private TextView englishTextView;
    private TextView themeTextView;
    private TextView lightTextView;
    private TextView darkTextView;
    private TextView contactTextView;
    private TextView phoneNumberTextView;
    private TextView emailTextView;
    private TextView phoneVisibleTextView;
    private TextView phoneYesTextView;
    private TextView phoneNoTextView;
    private TextView linkTextView;
    private Button logOutBtn;


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

        Switch switchButton = findViewById(R.id.PrivacySwitch);

        languageTextView = findViewById(R.id.languageTextView);
        dutchTextView = findViewById(R.id.languageDutch);
        englishTextView = findViewById(R.id.languageEnglish);
        themeTextView = findViewById(R.id.themeTextView);
        lightTextView = findViewById(R.id.light_radio);
        darkTextView = findViewById(R.id.dark_radio);
        contactTextView = findViewById(R.id.contactTextView);
        phoneNumberTextView = findViewById(R.id.phoneNumberTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneVisibleTextView = findViewById(R.id.textForSwitchPrivacy);
        phoneYesTextView = findViewById(R.id.textView39);
        phoneNoTextView = findViewById(R.id.phone_no);
        linkTextView = findViewById(R.id.Privacyverklaring);
        logOutBtn = findViewById(R.id.logoutButton);

        LanguageUtils.updateLanguage(this);

        String languageCode = LanguageUtils.getLanguagePreference(OptionsActivity.this);
        String languageText = "";
        String dutchText = "";
        String englishText = "";
        String themeText = "";
        String lightText = "";
        String darkText = "";
        String contactText = "";
        String phoneNumberText = "";
        String emailText = "";
        String phoneVisibleText = "";
        String phoneYesText = "";
        String phoneNoText = "";
        String linkText = "";
        String logOutButton = "";


        if (languageCode.equals("nl")) {
            languageText = getResources().getString(R.string.options_language);
            dutchText = getResources().getString(R.string.language_Dutch);
            englishText = getResources().getString(R.string.language_english);
            themeText = getResources().getString(R.string.options_theme);
            lightText = getResources().getString(R.string.options_themeLight);
            darkText = getResources().getString(R.string.options_themeDark);

            contactText = getResources().getString(R.string.options_contact);
            phoneNumberText = getResources().getString(R.string.options_phone);
            emailText = getResources().getString(R.string.options_email);
            phoneVisibleText = getResources().getString(R.string.options_phoneTitle);
            phoneYesText = getResources().getString(R.string.options_phoneYes);
            phoneNoText = getResources().getString(R.string.options_phoneNo);
            linkText = getResources().getString(R.string.options_link);

            logOutButton = getResources().getString(R.string.options_logOut);
        } else if (languageCode.equals("en")) {
            languageText = getResources().getString(R.string.options_language_en);
            dutchText = getResources().getString(R.string.language_Dutch);
            englishText = getResources().getString(R.string.language_english);
            themeText = getResources().getString(R.string.options_theme_en);
            lightText = getResources().getString(R.string.options_themeLight_en);
            darkText = getResources().getString(R.string.options_themeDark_en);

            contactText = getResources().getString(R.string.options_contact_en);
            phoneNumberText = getResources().getString(R.string.options_phone_en);
            emailText = getResources().getString(R.string.options_email_en);
            phoneVisibleText = getResources().getString(R.string.options_phoneTitle_en);
            phoneYesText = getResources().getString(R.string.options_phoneYes_en);
            phoneNoText = getResources().getString(R.string.options_phoneNo_en);
            linkText = getResources().getString(R.string.options_link_en);

            logOutButton = getResources().getString(R.string.options_logOut_en);
        }

        languageTextView.setText(languageText);
        dutchTextView.setText(dutchText);
        englishTextView.setText(englishText);
        themeTextView.setText(themeText);
        lightTextView.setText(lightText);
        darkTextView.setText(darkText);
        contactTextView.setText(contactText);
        phoneNumberTextView.setText(phoneNumberText);
        emailTextView.setText(emailText);
        phoneVisibleTextView.setText(phoneVisibleText);
        phoneYesTextView.setText(phoneYesText);
        phoneNoTextView.setText(phoneNoText);
        linkTextView.setText(linkText);
        logOutBtn.setText(logOutButton);

        //Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
                onBackPressed();
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the switch state change here
                if (isChecked) {
                    SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString("token", "");
                    String completeToken = token.substring(1, token.length() - 1);
                    Log.d("Check token", token);

                    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
                    httpClientBuilder.addInterceptor(chain -> {
                        Request originalRequest = chain.request();
                        Request.Builder requestBuilder = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + completeToken);
                        Request newRequest = requestBuilder.build();
                        return chain.proceed(newRequest);
                    });

                    OkHttpClient httpClient = httpClientBuilder.build();


                    MediaType mediaType = MediaType.parse("application/json");
                    String json = "{\"phoneNumberVisible\": 1}";
                    Log.d("JSON String", json);
                    RequestBody body = RequestBody.create(json, mediaType);


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://hardy-stream-production.up.railway.app/api/user/")
                            .client(httpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    ApiService apiService = retrofit.create(ApiService.class);

// Making the PUT request
                    Call<Void> call = apiService.updatePhoneNumberVisibility(body);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                // Handle successful response
                                Log.d("API", "PUT request successful");
                            } else {
                                // Handle error response
                                Log.e("API", "PUT request failed: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Handle network failures or other errors
                            Log.e("API", "PUT request failed: " + t.getMessage());
                        }
                    });

                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString("token", "");
                    String completeToken = token.substring(1, token.length() - 1);
                    Log.d("Check token", token);

                    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
                    httpClientBuilder.addInterceptor(chain -> {
                        Request originalRequest = chain.request();
                        Request.Builder requestBuilder = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + completeToken);
                        Request newRequest = requestBuilder.build();
                        return chain.proceed(newRequest);
                    });

                    OkHttpClient httpClient = httpClientBuilder.build();

                    MediaType mediaType = MediaType.parse("application/json");
                    String json = "{\"phoneNumberVisible\": 0}";
                    Log.d("JSON String", json);
                    RequestBody body = RequestBody.create(json, mediaType);


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://hardy-stream-production.up.railway.app/api/user/")
                            .client(httpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    ApiService apiService = retrofit.create(ApiService.class);

// Making the PUT request
                    Call<Void> call = apiService.updatePhoneNumberVisibility(body);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                // Handle successful response
                                Log.d("API", "PUT request successful");
                            } else {
                                // Handle error response
                                Log.e("API", "PUT request failed: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Handle network failures or other errors
                            Log.e("API", "PUT request failed: " + t.getMessage());
                        }
                    });
                }
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