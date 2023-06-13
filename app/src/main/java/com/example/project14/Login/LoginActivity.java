package com.example.project14.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Base64;

import com.example.project14.ActivitiesScreen;
import com.example.project14.MainActivity;
import com.example.project14.Match.AllMatches;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.example.project14.RoleActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin, buttonRegister;
    private EditText editTextTextEmailAddress, editTextTextPassword;
    private TextView emptyEmail, emptyPassword, wrongCredentials;
    private SharedPreferences sharedPreferences;



    private Boolean validateEmail(){
        String val = editTextTextEmailAddress.getEditableText().toString();

        if (val.isEmpty()) {
            editTextTextEmailAddress.setError("Email adress is leeg");
            return false;
        } else {
            editTextTextEmailAddress.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = editTextTextPassword.getEditableText().toString();

        if (val.isEmpty()) {
            editTextTextEmailAddress.setError("Wachtwoord is leeg");
            return false;
        } else {
            editTextTextEmailAddress.setError(null);
            return true;
        }
    }

    private void loginUser() {
        String email = editTextTextEmailAddress.getEditableText().toString();
        String password = editTextTextPassword.getEditableText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterfaceLogin apiInterfaceLogin = retrofit.create(APIInterfaceLogin.class);

        // Create a HashMap to hold the email and password
        HashMap<String, String> loginData = new HashMap<>();
        loginData.put("emailAddress", email);
        loginData.put("password", password);

        Log.d("inside", loginData.toString());
        Call<JsonObject> call = apiInterfaceLogin.postLogin(loginData);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                   String token = jsonObject.get("token").toString();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.apply();


                    Intent intent = new Intent(LoginActivity.this, ActivitiesScreen.class);
                    startActivity(intent);
                } else {
                    emptyPassword.setVisibility(View.INVISIBLE);
                    emptyEmail.setVisibility(View.INVISIBLE);
                    wrongCredentials.setVisibility(View.VISIBLE);
                    wrongCredentials.postDelayed(new Runnable() {
                        public void run() {
                            wrongCredentials.setVisibility(View.GONE);
                        }
                    }, 3000);
                    Log.d("TAG", "Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed", t);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        //Set login actions
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        emptyEmail = findViewById(R.id.emptyEmail);
        emptyPassword = findViewById(R.id.emptyPassword);
        wrongCredentials = findViewById(R.id.wrongCredentials);

        //SET TOOLBAR
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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OptionsActivity.class);
                startActivity(intent);
            }
        });

        //Sends user to register screen
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (LoginActivity.this, RoleActivity.class));
            }
        });

        //Login user
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail() == false) {
                    emptyPassword.setVisibility(View.INVISIBLE);
                    wrongCredentials.setVisibility(View.INVISIBLE);
                    emptyEmail.setVisibility(View.VISIBLE);
                    emptyEmail.postDelayed(new Runnable() {
                        public void run() {
                            emptyEmail.setVisibility(View.GONE);
                        }
                    }, 3000);
                } else if (validatePassword() == false){
                    emptyEmail.setVisibility(View.INVISIBLE);
                    wrongCredentials.setVisibility(View.INVISIBLE);
                    emptyPassword.setVisibility(View.VISIBLE);
                    emptyPassword.postDelayed(new Runnable() {
                        public void run() {
                            emptyPassword.setVisibility(View.GONE);
                        }
                    }, 3000);
                }else {
                    loginUser();
                }
            }
        });

    }

    public void CreateAccount(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        startActivity(intent);
    }
}