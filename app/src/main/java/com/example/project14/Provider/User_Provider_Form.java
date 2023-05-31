package com.example.project14.Provider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.project14.R;

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
        setContentView(R.layout.activity_user_seeking_form);

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
