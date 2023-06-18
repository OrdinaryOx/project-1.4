package com.example.project14.Seeking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project14.Login.LoginActivity;
import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.Profile.ApiInterfaceProfile;
import com.example.project14.Profile.ProfileUser;
import com.example.project14.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
import com.example.project14.RoleActivity;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class User_Seeking_Form extends AppCompatActivity {


    private HashMap<String, String> fragmentDataList;
    private FragmentManager fragmentManager;
    private int currentPageIndex = 0;

    private String idSave = "";

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


        Intent intent = getIntent();
        Log.d("INTENT", intent.toString());


        //CHECK IF INTENT CONTAINS EXTRAS
        Bundle extras = intent.getExtras();
        if (extras != null && extras.containsKey("ID")) {
            String id = extras.getString("ID");
            idSave = id;
            //PAGE1
            String firstName = extras.getString("FirstName");
            String middleName = extras.getString("MiddleName");
            String lastName = extras.getString("LastName");
            String email = extras.getString("Email");
            String password = extras.getString("Password");
            String gender = extras.getString("Salutation");
            //PAGE2
            String address = extras.getString("Address");
            String houseNumber = extras.getString("HouseNumber");
            String cityPersonal = extras.getString("CityPersonal");
            String postalCode = extras.getString("PostalCode");
            String country = extras.getString("Country");
            String phoneNumber = extras.getString("PhoneNumber");
            String birthDate = extras.getString("BirthDate");
            //PAGE3
            String city = extras.getString("City");
            String preference = extras.getString("Preference");
            String budget = extras.getString("Budget");
            String month = extras.getString("Month");
            //PAGE4
            String day = extras.getString("Day");
            String pets = extras.getString("Pets");
            String selfPets = extras.getString("SelfPets");
            String petsComments = extras.getString("PetsComment");
//PAGE5
            String startDate = extras.getString("StartDate");
            String endDate = extras.getString("EndDate");
            String reason = extras.getString("Reason");
            String grade = extras.getString("Grade");
            String course = extras.getString("Course");
            //PAGE6
            String skill = extras.getString("Skill");
            String seekingWork = extras.getString("SeekingWork");
            String work = extras.getString("Work");
            String health = extras.getString("Health");
            String healthInfo = extras.getString("HealthInfo");
            //PAGE7
            String yourself = extras.getString("Yourself");
            String keyword = extras.getString("Keyword");
            String room = extras.getString("Room");
            String mean = extras.getString("Mean");
            //PAGE8
            String otherOffer = extras.getString("OtherOffer");
            String importantNote = extras.getString("ImportantNote");
            String volunteerSelection = extras.getString("VolunteerSelection");
            String volunteer = extras.getString("Volunteer");
            //PAGE9
            String belief = extras.getString("Belief");
            String other = extras.getString("Other");
            //PAGE10
            String comment = extras.getString("Comment");
            //Code
            //GENDER
            if (gender.equals("M")) {
                gender = "Dhr";
            } else if (gender.equals("F")) {
                gender = "Mvr";
            } else {
                gender = "Anders";
            }

            if (preference.equals("M")) {
                preference = "Man";
            } else if (preference.equals("V")) {
                preference = "Vrouw";
            } else if (preference.equals("K")) {
                preference = "Koppel";
            } else if (preference.equals("")) {
                preference = "";
            } else {
                preference = "";
            }

            if (selfPets.contains("Ja")) {
                selfPets = "Ja";
            } else {
                selfPets = "Nee";
            }

            if (pets.equals("1")) {
                pets = "Ja";
            } else {
                pets = "Nee";
            }

            if (day.equals("1")) {
                day = day + " dag";
            } else {
                day = day + " dagen";
            }

            String EHBO = "0";
            String BHV = "0";
            String Reanimation = "0";
            if (skill.contains("EHBO")) {
                EHBO = "1";
            }
            if (skill.contains("BHV")) {
                BHV = "1";
            }
            if (skill.contains("Reanimatie")) {
                Reanimation = "1";
            }

            if (health.equals("1")) {
                health = "Ja";
            } else {
                health = "Nee";
            }

            if (volunteerSelection.equals("1")) {
                volunteerSelection = "Ja";
            } else {
                volunteerSelection = "Nee";
            }

            //ADD ID
            fragmentDataList.put("ID", id);

            //PAGE1
            fragmentDataList.put("FirstName", firstName);
            fragmentDataList.put("MiddleName", middleName);
            fragmentDataList.put("LastName", lastName);
            fragmentDataList.put("Email", email);
            fragmentDataList.put("Salutation", gender);
//            fragmentDataList.put("Password", password);
//            fragmentDataList.put("PasswordAgain", password);

            //PAGE2
            fragmentDataList.put("Address", address);
            fragmentDataList.put("HouseNumber", houseNumber);
            fragmentDataList.put("CityPersonal", cityPersonal);
            fragmentDataList.put("PostalCode", postalCode);
            fragmentDataList.put("Country", country);
            fragmentDataList.put("PhoneNumber", phoneNumber);
            fragmentDataList.put("BirthDate", convertDate(birthDate));

            //PAGE3
            fragmentDataList.put("City", city);
            fragmentDataList.put("Preference", preference);
            fragmentDataList.put("Budget", budget);
            fragmentDataList.put("Month", month + " maand(en)");

            //PAGE4
            fragmentDataList.put("Day", day);
            fragmentDataList.put("Pets", pets);
            fragmentDataList.put("SelfPets", selfPets);
            fragmentDataList.put("PetsComment", petsComments);

            //PAGE5
            fragmentDataList.put("StartDate", convertDate(startDate));
            fragmentDataList.put("EndDate", convertDate(endDate));
            fragmentDataList.put("Reason", reason);
            fragmentDataList.put("Grade", grade);
            fragmentDataList.put("Course", course);

            //PAGE6
            fragmentDataList.put("EHBO", EHBO);
            fragmentDataList.put("BHV", BHV);
            fragmentDataList.put("Reanimation", Reanimation);
            fragmentDataList.put("SeekingWork", seekingWork);
            fragmentDataList.put("Work", work);
            fragmentDataList.put("Health", health);
            fragmentDataList.put("HealthInfo", healthInfo);

            //PAGE7
            fragmentDataList.put("Yourself", yourself);
            fragmentDataList.put("Keyword", keyword);
            fragmentDataList.put("Room", room);
            fragmentDataList.put("Mean", mean);

            //PAGE8
            fragmentDataList.put("OtherOffer", otherOffer);
            fragmentDataList.put("ImportantNote", importantNote);
            fragmentDataList.put("VolunteerSelection", volunteerSelection);
            fragmentDataList.put("Volunteer", volunteer);

            //PAGE9
            fragmentDataList.put("Belief", belief);
            fragmentDataList.put("Other", other);

            //PAGE10
            fragmentDataList.put("Comment", comment);
        } else {
            Log.d("INTENT EXTRAS NOT FOUND", "Cannot retrieve ID");
        }


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

    public void openPrivacy(View view) {
        Uri uri = Uri.parse("https://mijnwoongenoot.nl/privacy/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public HashMap<String, String> getFragmentDataList() {
        return fragmentDataList;
    }

    public void opsturen(View view) throws JSONException {

        if (idSave.equals("")) {
            Log.d("Not editing", "Not editing");
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

                        Intent intent = new Intent(User_Seeking_Form.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        // Request failed
                        System.out.println("Request failed with code: " + response.code());
                    }
                }
            });

        } else {
            Log.d("idSave", "" + idSave);
            Log.d("Editing", "Editing");
            // Create a Retrofit instance and the service interface

            SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");
            String completeToken = token.substring(1, token.length() - 1);
            Log.d("Check token", token);

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.addInterceptor(chain -> {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("Authorization", "Bearer " + completeToken);
                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            });

            OkHttpClient httpClient = httpClientBuilder.build();


            MediaType mediaType = MediaType.parse("application/json");
            String json = buildUserPreferences().toString();
            Log.d("JSON String", json);
            RequestBody body = RequestBody.create(json, mediaType);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://hardy-stream-production.up.railway.app/api/user/")
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterfaceProfile service = retrofit.create(ApiInterfaceProfile.class);


            // Make the request with the extracted userID
            retrofit2.Call<JsonObject> call = service.updateHuurder(idSave, body);
            call.enqueue(new retrofit2.Callback<JsonObject>() {
                @Override
                public void onResponse(retrofit2.Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        JsonObject jsonObject = response.body();
                        Intent intent = new Intent(User_Seeking_Form.this, ProfileUser.class);
                        startActivity(intent);
                    } else {

                        Log.d("Error?", response.message());

                    }
                }

                @Override
                public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {
Log.e("ERROR Updating", "" + t.getMessage());
                }


            });

        }


    }

    private JSONObject buildUserPreferences() throws JSONException {
        JSONObject userPreferences = new JSONObject();

        JSONObject user = new JSONObject();
        user.put("emailAddress", fragmentDataList.get("Email"));
        user.put("password", fragmentDataList.get("Password"));
        user.put("dateOfBirth", fragmentDataList.get("BirthDate"));
        user.put("firstName", fragmentDataList.get("FirstName"));
        user.put("middleName", fragmentDataList.get("Infix"));
        user.put("lastName", fragmentDataList.get("LastName"));
        user.put("picture", fragmentDataList.get("ProfileImage"));

        String gender = "";

        if (fragmentDataList.get("Salutation").equals("Dhr")) {
            gender = "M";
        } else if (fragmentDataList.get("Salutation").equals("Mvr")) {
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

        Log.d("TAG", fragmentDataList.get("Preference"));

        if (fragmentDataList.get("Preference").equals("Man")) {
            liveWith = "M";
        } else if (fragmentDataList.get("Preference").equals("Vrouw")) {
            liveWith = "V";
        } else if (fragmentDataList.get("Preference").equals("Koppel")) {
            liveWith = "K";
        } else if (fragmentDataList.get("Preference").equals("Geen voorkeur")) {
            liveWith = "";
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

//        if (fragmentDataList.get("Pets").isEmpty())


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
        if (fragmentDataList.get("EHBO").equals("EHBO")) {
            skills.add("EHBO");
        }
        if (fragmentDataList.get("BHV").equals("BHV")) {
            skills.add("BHV");
        }
        if (fragmentDataList.get("Reanimation").equals("Reanimatie")) {
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


    public String convertDate(String dateString) {
        // Parse the input date string as an Instant
        Instant instant = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            instant = Instant.parse(dateString);
        }

        // Convert the Instant to a LocalDate
        LocalDate date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = instant.atZone(java.time.ZoneOffset.UTC).toLocalDate();
        }

        // Format the LocalDate using the desired pattern
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        }
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = date.format(formatter);
        }

        return formattedDate;
    }
}
