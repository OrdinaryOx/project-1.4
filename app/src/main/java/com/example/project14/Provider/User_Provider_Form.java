package com.example.project14.Provider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.project14.R;
import com.example.project14.Provider.ProviderOneFragment;
import com.example.project14.Provider.ProviderTwoFragment;
import com.example.project14.Provider.User_Provider_Form;

public class User_Provider_Form extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private int currentPageIndex = 0;
    private Class<?>[] fragmentClasses = {
            ProviderOneFragment.class,
            ProviderTwoFragment.class,
            ProviderThreeFragment.class,
            ProviderFourFragment.class,
            ProviderFiveFragment.class,
            ProviderSixFragment.class,
            ProviderSevenFragment.class,
            ProviderEightFragment.class,
            ProviderNineFragment.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_provider_form);

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
                    } else {
                        // All fragments have been filled and validated, proceed to final submission
                        submitForm();
                    }
                } else {
                    // Display an error message or handle the case when not all fields are filled
                    Toast.makeText(User_Provider_Form.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
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
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainerView);
        if (currentFragment != null) {
            if (currentFragment instanceof ProviderOneFragment) {
                ProviderOneFragment fragment = (ProviderOneFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderTwoFragment) {
                ProviderTwoFragment fragment = (ProviderTwoFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderThreeFragment) {
                ProviderThreeFragment fragment = (ProviderThreeFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderFourFragment) {
                ProviderFourFragment fragment = (ProviderFourFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderFiveFragment) {
                ProviderFiveFragment fragment = (ProviderFiveFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderSixFragment) {
                ProviderSixFragment fragment = (ProviderSixFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderSevenFragment) {
                ProviderSevenFragment fragment = (ProviderSevenFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderEightFragment) {
                ProviderEightFragment fragment = (ProviderEightFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            } else if (currentFragment instanceof ProviderNineFragment) {
                ProviderNineFragment fragment = (ProviderNineFragment) currentFragment;
                fragment.saveData();
                return fragment.isDataValid();
            }
        }
        return true; // Default case: allow proceeding if fragment type is unknown
    }

    private void submitForm() {
        // All fragments have been filled and validated, perform final submission
        // Implement your submission logic here
    }
}

