package com.example.project14.Seeking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project14.ActivitiesScreen;
import com.example.project14.Login.LoginActivity;
import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.example.project14.RoleActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User_Seeking_Form extends AppCompatActivity {


    private HashMap<String, String> fragmentDataList;
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
            SeekingTenFragment.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_seeking_form);

        fragmentDataList = new HashMap<>();




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
                if (areAllFieldsFilled()) {
                    if (currentPageIndex < fragmentClasses.length - 1) {
                        currentPageIndex++;
                        setCurrentFragment();

                        // Enable forward button if it was disabled on the previous fragment/page
                        btnForward.setEnabled(true);
                        btnForward.setBackgroundColor(getResources().getColor(R.color.grey));
                    }
                } else {
                    // Display an error message or handle the case when not all fields are filled
                    Toast.makeText(com.example.project14.Seeking.User_Seeking_Form.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCurrentFragment() {
        try {
            Fragment fragment = (Fragment) fragmentClasses[currentPageIndex].newInstance();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean areAllFieldsFilled() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        if (currentFragment instanceof SeekingOneFragment) {
            SeekingOneFragment fragment = (SeekingOneFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingTwoFragment) {
            SeekingTwoFragment fragment = (SeekingTwoFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingThreeFragment) {
            SeekingThreeFragment fragment = (SeekingThreeFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingFourFragment) {
            SeekingFourFragment fragment = (SeekingFourFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingFiveFragment) {
            SeekingFiveFragment fragment = (SeekingFiveFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingSixFragment) {
            SeekingSixFragment fragment = (SeekingSixFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingSevenFragment) {
            SeekingSevenFragment fragment = (SeekingSevenFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingEightFragment) {
            SeekingEightFragment fragment = (SeekingEightFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingNineFragment) {
            SeekingNineFragment fragment = (SeekingNineFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingTenFragment) {
            SeekingTenFragment fragment = (SeekingTenFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();
        }


        return true; // Default case: allow proceeding if fragment type is unknown
    }

    public void passDataToNextFragment(Bundle data) {
        int nextFragmentIndex = currentPageIndex + 1;
        if (nextFragmentIndex < fragmentClasses.length) {
            try {
                Fragment nextFragment = (Fragment) fragmentClasses[nextFragmentIndex].getDeclaredConstructor().newInstance();
                nextFragment.setArguments(data);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, nextFragment)
                        .addToBackStack(null)
                        .commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verifyEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }


    public HashMap<String, String> getFragmentDataList() {
        return fragmentDataList;
    }

    public void opsturen(View view) {
        Intent intent = new Intent(this, ActivitiesScreen.class);
        Log.d("SIZE", " " + fragmentDataList.size());
        Log.d("TO BE SENT", fragmentDataList.toString());
        startActivity(intent);
    }
}
