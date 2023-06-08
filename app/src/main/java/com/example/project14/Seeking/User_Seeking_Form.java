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
import com.example.project14.LoginActivity;
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

                    }
                } else {
                    if (currentPageIndex != 0 && currentPageIndex != 1) {
                        Toast.makeText(com.example.project14.Seeking.User_Seeking_Form.this, "Vul eerst alle onderdelen in.", Toast.LENGTH_SHORT).show();
                    }
                    // Display an error message or handle the case when not all fields are filled
//                  btnForward.setBackgroundColor(getResources().getColor(R.color.grey));
                    highlightUnfilledFields();
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

            // Set visibility of buttons based on current fragment
            Button btnBack = findViewById(R.id.button_back_form);
            Button btnForward = findViewById(R.id.button_forward_form);

            if (fragment instanceof SeekingOneFragment) {
                btnBack.setVisibility(View.INVISIBLE); // Hide "Terug" button in fragmentOne
            } else if (fragment instanceof SeekingTenFragment) {
                btnForward.setVisibility(View.INVISIBLE); // Hide "Vooruit" button in fragmentTen
            } else {
                btnBack.setVisibility(View.VISIBLE);
                btnForward.setVisibility(View.VISIBLE);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean areAllFieldsFilled() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        if (currentFragment instanceof SeekingOneFragment) {
            SeekingOneFragment fragment = (SeekingOneFragment) currentFragment;
            if (fragment.isEmailValid()) {
                if (fragment.arePasswordsMatching()) {
                    if (fragment.isDataValid()) {
                        fragment.saveData();
                    } else {
                        Toast.makeText(com.example.project14.Seeking.User_Seeking_Form.this, "Vul eerst alle onderdelen in.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof SeekingTwoFragment) {
            SeekingTwoFragment fragment = (SeekingTwoFragment) currentFragment;

            if (fragment.isPhoneValid()) {

                if (fragment.isDataValid()) {
                    fragment.saveData();
                } else {
                    Toast.makeText(com.example.project14.Seeking.User_Seeking_Form.this, "Vul eerst alle onderdelen in.", Toast.LENGTH_SHORT).show();
                }
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

    private void highlightUnfilledFields() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        if (currentFragment instanceof SeekingOneFragment) {
            SeekingOneFragment fragment = (SeekingOneFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingTwoFragment) {
            SeekingTwoFragment fragment = (SeekingTwoFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingThreeFragment) {
            SeekingThreeFragment fragment = (SeekingThreeFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingFourFragment) {
            SeekingFourFragment fragment = (SeekingFourFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingFiveFragment) {
            SeekingFiveFragment fragment = (SeekingFiveFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingSixFragment) {
            SeekingSixFragment fragment = (SeekingSixFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingSevenFragment) {
            SeekingSevenFragment fragment = (SeekingSevenFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingEightFragment) {
            SeekingEightFragment fragment = (SeekingEightFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingNineFragment) {
            SeekingNineFragment fragment = (SeekingNineFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof SeekingTenFragment) {
            SeekingTenFragment fragment = (SeekingTenFragment) currentFragment;
            fragment.highlightUnfilledFields();
        }

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
