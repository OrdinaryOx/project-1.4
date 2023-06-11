package com.example.project14.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project14.ActivitiesScreen;
import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.example.project14.RoleActivity;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin, buttonRegister;
    private EditText editTextTextEmailAddress, editTextTextPassword;
    private TextView emptyEmail, emptyPassword, wrongCredentials;

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

    private Boolean validateCredentials(){
            if (1+1 == 2) {
                return true;
        } else {
                return false;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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