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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Call;
import okhttp3.Callback;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
import com.example.project14.RoleActivity;

import org.json.JSONException;
import org.json.JSONObject;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
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

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();




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

    public void opsturen(View view) throws JSONException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String json = buildUserPreferences().toString();
        Log.d("JSON String", json);
        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url("https://hardy-stream-production.up.railway.app/api/user/huurder")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // Handle the request failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Request successful
                    String responseBody = response.body().string();
                    System.out.println("Response: " + responseBody);
                } else {
                    // Request failed
                    System.out.println("Request failed with code: " + response.code());
                }
            }
        });
    }

    private JSONObject buildUserPreferences() throws JSONException {
        JSONObject userPreferences = new JSONObject();

        JSONObject user = new JSONObject();
        user.put("emailAddress", fragmentDataList.get("Email"));
        user.put("password", fragmentDataList.get("Password"));
        user.put("dateOfBirth", fragmentDataList.get("BirthDate"));
//        user.put("dateOfBirth", "2020-12-12");
        user.put("firstName", fragmentDataList.get("FirstName"));
        user.put("middleName", fragmentDataList.get("Infix"));
        user.put("lastName", fragmentDataList.get("LastName"));
        user.put("picture", fragmentDataList.get("ProfileImage"));

        String gender = "";

        if (fragmentDataList.get("Salutation").equals("Dhr")) {
            gender = "M";
        } else if(fragmentDataList.get("Salutation").equals("Mvr")) {
            gender = "F";
        } else {
            gender = "O";
        }


        user.put("gender", gender);
        user.put("phoneNumber", fragmentDataList.get("PhoneNumber"));
        user.put("postalCode", fragmentDataList.get("PostalCode"));
        user.put("street", fragmentDataList.get("Street"));
        user.put("city", fragmentDataList.get("CityPersonal"));
        user.put("houseNumber", fragmentDataList.get("HouseNumber"));
        user.put("country", fragmentDataList.get("Country"));



        JSONObject preferences = new JSONObject();
        // Populate the preferences JSON object with data
        preferences.put("seekingCity", fragmentDataList.get("City"));
       String liveWith = "";

       Log.d("TAG", fragmentDataList.get("Preference") );

       if (fragmentDataList.get("Preference").equals("Man")) {
           liveWith = "M";
       } else if (fragmentDataList.get("Preference").equals("Vrouw")) {
           liveWith = "V";
       } else if (fragmentDataList.get("Preference").equals("Koppel")) {
           liveWith = "K";
       } else {
           liveWith = "";
       }

        Log.d("TAG", liveWith);

        preferences.put("liveWith", liveWith);
        preferences.put("budget", fragmentDataList.get("Budget"));

        String[] periodSplit = fragmentDataList.get("Month").split(" ");
        String periodGood = periodSplit[0];

        preferences.put("period", periodGood);

        String[] nightSplit = fragmentDataList.get("Day").split(" ");
        String nightGood = nightSplit[0];


        preferences.put("nights", nightGood);
        preferences.put("pet", fragmentDataList.get("Pets"));
        preferences.put("ownPet", fragmentDataList.get("SelfPets"));
        preferences.put("ownPetDescription", fragmentDataList.get("PetsComment"));
        preferences.put("starDate", fragmentDataList.get("StartDate"));
        preferences.put("endDate", fragmentDataList.get("EndDate"));
//        preferences.put("starDate", "2012-12-12");
//        preferences.put("endDate", "2013-12-12");

        preferences.put("reason", fragmentDataList.get("Reason"));
        preferences.put("schoolFinished", fragmentDataList.get("Grade"));
        preferences.put("schoolDoing", fragmentDataList.get("Course"));

        ArrayList<String> skills = new ArrayList<>();
        if  (fragmentDataList.get("EHBO").equals("EHBO")) {
            skills.add("EHBO");
        }
        if  (fragmentDataList.get("BHV").equals("BHV")) {
            skills.add("BHV");
        }
        if  (fragmentDataList.get("Reanimation").equals("Reanimatie")) {
            skills.add("Reanimatie");
        }
        String skillString = "";

        for (int i = 0; i < skills.size(); i++) {
            if (i == 0) {
                skillString += skills.get(i);
            }
            skillString += ", " + skills.get(i);
        }

        preferences.put("skill", skillString);
        preferences.put("work", fragmentDataList.get("SeekingWork"));
        preferences.put("workDescription", fragmentDataList.get("Work"));
        preferences.put("healthRisk", fragmentDataList.get("Health"));
        preferences.put("healthRiskDescription", fragmentDataList.get("HealthInfo"));
        preferences.put("selfDescription", fragmentDataList.get("Yourself"));
        preferences.put("selfWords", fragmentDataList.get("Keyword"));
        preferences.put("idealSpace", fragmentDataList.get("Room"));
        preferences.put("offer", fragmentDataList.get("Mean"));
        preferences.put("offerYou", fragmentDataList.get("OtherOffer"));
        preferences.put("importantNote", fragmentDataList.get("ImportantNote"));
        preferences.put("volunteer", fragmentDataList.get("VolunteerSelection"));
        preferences.put("volunteerDescription", fragmentDataList.get("Volunteer"));
        preferences.put("religion", fragmentDataList.get("Belief"));
        preferences.put("comment", fragmentDataList.get("Other"));
        preferences.put("overallcomment", fragmentDataList.get("Comment"));

        userPreferences.put("user", user);
        userPreferences.put("preferences", preferences);

        return userPreferences;
    }
    public void uploadPicture() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        SeekingOneFragment fragment = (SeekingOneFragment) currentFragment;

        Log.d("TAG", "UPLOAD PICTURE FORM CALLED");
        fragment.uploadPicture();
    }
}
