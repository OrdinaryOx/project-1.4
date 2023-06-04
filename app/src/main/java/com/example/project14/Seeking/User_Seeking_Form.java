package com.example.project14.Seeking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.project14.LoginActivity;
import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.example.project14.RoleActivity;

public class User_Seeking_Form extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private int currentPageIndex = 0;
    private Class<?>[] fragmentClasses = {
            SeekingOneFragment.class,
            SeekingTwoFragment.class,
            SeekingThreeFragment.class,
            SeekingFourFragment.class,
            SeekingFiveFragment.class,
            SeekingSixFragment.class,
            SeekingSevenFragment.class,
            SeekingEightFragment.class,
            SeekingNineFragment.class,
            SeekingTenFragment.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_seeking_form);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView backButton = toolbar.findViewById(R.id.back_button);
        ImageView logoButton = toolbar.findViewById(R.id.MWG_logo_IV);
        ImageView optionsButton = toolbar.findViewById(R.id.options_button);
        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(User_Seeking_Form.this, RoleActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(User_Seeking_Form.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Seeking_Form.this, OptionsActivity.class);
                startActivity(intent);
            }
        });





        fragmentManager = getSupportFragmentManager();

        setCurrentFragment();

        // Back button click listener
        Button btnBack = findViewById(R.id.button_back_form);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPageIndex > 0) {
                    currentPageIndex--;
                    setCurrentFragment();
                }
            }
        });

        // Forward button click listener
        Button btnForward = findViewById(R.id.button_forward_form);
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPageIndex < fragmentClasses.length - 1) {
                    currentPageIndex++;
                    setCurrentFragment();
                }
            }
        });
    }

    private void setCurrentFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, (Class<? extends Fragment>) fragmentClasses[currentPageIndex], null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

    private boolean verifyEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
}