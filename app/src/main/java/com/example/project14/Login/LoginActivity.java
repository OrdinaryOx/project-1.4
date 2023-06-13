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
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin, buttonRegister;
    private EditText editTextTextEmailAddress, editTextTextPassword;
    private TextView emptyEmail, emptyPassword, wrongCredentials;
    private ArrayList<Users> users;

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

    private void fetchDataUsers() {
        // ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterfaceLogin apiInterfaceLogin = retrofit.create(APIInterfaceLogin.class);

        Call<JsonObject> call = apiInterfaceLogin.getUsers();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Users>>() {}.getType();
                    List<Users> fetchedUsers = gson.fromJson(jsonArray, type);

                    if (fetchedUsers != null) {
                        if (users == null) {
                            users = new ArrayList<>();
                        } else {
                            users.clear();
                        }
                        users.addAll(fetchedUsers);
                        Log.d("TAG", users.toString());
                    } else {
                        Log.d("TAG", "Empty user list");
                    }
                } else {
                    Log.d("TAG", "Request not successful");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed");
            }
        });
    }

    private void loginUser() {
            try {
                // API endpoint URL
                URL url = new URL("https://hardy-stream-production.up.railway.app/api/login");

                // Create connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set request method to POST
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");

                // Request body parameters
                String email = editTextTextEmailAddress.toString();
                String password = editTextTextPassword.toString();
                String requestBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";

                // Enable sending request body
                connection.setDoOutput(true);

                // Write request body to the connection
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
                outputStream.close();

                // Get response code
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    // Process the response
                    String userToken = response.toString();
                    System.out.println("User token: " + userToken);
                } else {
                    System.out.println("Failed to obtain user token. Response code: " + responseCode);
                }

                // Close the connection
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private Boolean validateCredentials() {
        if (users.isEmpty()) {
            return false;
        } else {
            for (Users user : users) {
                System.out.println(user.getFirstName());
                if (user.getEmailAddress().equals(editTextTextEmailAddress.getEditableText().toString())) {
                    if (user.getPassword().equals(editTextTextPassword.getEditableText().toString())) {
                        loginUser();
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fetchDataUsers();

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
                } else if (validateCredentials() == false) {
                    emptyPassword.setVisibility(View.INVISIBLE);
                    emptyEmail.setVisibility(View.INVISIBLE);
                    wrongCredentials.setVisibility(View.VISIBLE);
                    wrongCredentials.postDelayed(new Runnable() {
                        public void run() {
                            wrongCredentials.setVisibility(View.GONE);
                        }
                    }, 3000);
                }else {
                    startActivity(new Intent (LoginActivity.this, ActivitiesScreen.class));
                }
            }
        });

    }

    public void CreateAccount(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        startActivity(intent);
    }
}