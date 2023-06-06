package com.example.project14.Seeking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project14.Provider.ProviderEightFragment;
import com.example.project14.Provider.ProviderFiveFragment;
import com.example.project14.Provider.ProviderFourFragment;
import com.example.project14.Provider.ProviderNineFragment;
import com.example.project14.Provider.ProviderOneFragment;
import com.example.project14.Provider.ProviderSevenFragment;
import com.example.project14.Provider.ProviderSixFragment;
import com.example.project14.Provider.ProviderThreeFragment;
import com.example.project14.Provider.ProviderTwoFragment;
import com.example.project14.R;

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

                        // Enable forward button if it was disabled on previous fragment/page
                        btnForward.setEnabled(true);
                        btnForward.setBackgroundColor(getResources().getColor(R.color.grey));
                    }
                } else {
                    // Display an error message or handle the case when not all fields are filled
                    Toast.makeText(User_Seeking_Form.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
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

    private boolean areAllFieldsFilled() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        if (currentFragment instanceof SeekingOneFragment) {
            SeekingOneFragment fragment = (SeekingOneFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getSalutation()) &&
                    !TextUtils.isEmpty(fragment.getFirstName()) &&
                    !TextUtils.isEmpty(fragment.getInfix()) &&
                    !TextUtils.isEmpty(fragment.getLastName()) &&
                    !TextUtils.isEmpty(fragment.getPassword()) &&
                    !TextUtils.isEmpty(fragment.getPasswordAgain());
        } else if (currentFragment instanceof SeekingTwoFragment) {
            SeekingTwoFragment fragment = (SeekingTwoFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getAddress()) &&
                    !TextUtils.isEmpty(fragment.getCity()) &&
                    !TextUtils.isEmpty(fragment.getPostalCode()) &&
                    !TextUtils.isEmpty(fragment.getCountry()) &&
                    !TextUtils.isEmpty(fragment.getPhoneNumber()) &&
                    !TextUtils.isEmpty(fragment.getBirthDate());
        } else if (currentFragment instanceof SeekingThreeFragment) {
            SeekingThreeFragment fragment = (SeekingThreeFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getCity()) &&
                    !TextUtils.isEmpty(fragment.getPreference()) &&
                    !TextUtils.isEmpty(fragment.getBudget()) &&
                    !TextUtils.isEmpty(fragment.getMonth());
        } else if (currentFragment instanceof SeekingFourFragment) {
            SeekingFourFragment fragment = (SeekingFourFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getDay()) &&
                    !TextUtils.isEmpty(fragment.getPets()) &&
                    !TextUtils.isEmpty(fragment.getSelfPets()) &&
                    !TextUtils.isEmpty(fragment.getPetsComment());
        } else if (currentFragment instanceof SeekingFiveFragment) {
            SeekingFiveFragment fragment = (SeekingFiveFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getStartDate()) &&
                    !TextUtils.isEmpty(fragment.getEndDate()) &&
                    !TextUtils.isEmpty(fragment.getReason()) &&
                    !TextUtils.isEmpty(fragment.getGrade()) &&
                    !TextUtils.isEmpty(fragment.getCourse());
        } else if (currentFragment instanceof SeekingSixFragment) {
            SeekingSixFragment fragment = (SeekingSixFragment) currentFragment;

            fragment.saveData();

            return (fragment.isEhboSelected() || fragment.isBhvSelected() || fragment.isReanimationSelected()) &&
                    !TextUtils.isEmpty(fragment.getSeekingWork()) &&
                    !TextUtils.isEmpty(fragment.getWork()) &&
                    !TextUtils.isEmpty(fragment.getHealth()) &&
                    !TextUtils.isEmpty(fragment.getHealthInfo());
        } else if (currentFragment instanceof SeekingSevenFragment) {
            SeekingSevenFragment fragment = (SeekingSevenFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getYourself()) &&
                    !TextUtils.isEmpty(fragment.getKeyword()) &&
                    !TextUtils.isEmpty(fragment.getRoom()) &&
                    !TextUtils.isEmpty(fragment.getMean());
        } else if (currentFragment instanceof SeekingEightFragment) {
            SeekingEightFragment fragment = (SeekingEightFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getOtherOffer()) &&
                    !TextUtils.isEmpty(fragment.getImportantNote()) &&
                    !TextUtils.isEmpty(fragment.getVolunteerSelection()) &&
                    !TextUtils.isEmpty(fragment.getVolunteer());
        } else if (currentFragment instanceof SeekingNineFragment) {
            SeekingNineFragment fragment = (SeekingNineFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getBelief()) &&
                    !TextUtils.isEmpty(fragment.getOther());
        } else if (currentFragment instanceof SeekingTenFragment) {
            SeekingTenFragment fragment = (SeekingTenFragment) currentFragment;

            fragment.saveData();

            return !TextUtils.isEmpty(fragment.getComment()) &&
                    fragment.isTruthChecked() &&
                    fragment.isPermissionChecked() &&
                    fragment.isTermsChecked();
        }

        return true; // Default case: allow proceeding if fragment type is unknown
    }


    private boolean verifyEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
}