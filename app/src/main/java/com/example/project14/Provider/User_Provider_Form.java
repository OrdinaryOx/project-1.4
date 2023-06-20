package com.example.project14.Provider;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.project14.ActivitiesScreen;
import com.example.project14.LanguageUtils;
import com.example.project14.Login.LoginActivity;
import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.Profile.ApiInterfaceProfile;
import com.example.project14.Profile.ProfileUser;
import com.example.project14.R;
import com.example.project14.RoleActivity;
import com.example.project14.Seeking.SeekingOneFragment;
import com.example.project14.Seeking.SeekingTenFragment;
import com.example.project14.Seeking.User_Seeking_Form;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.Provider;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User_Provider_Form extends AppCompatActivity {


    private String idSave = "";

    private HashMap<String, String> fragmentDataList;
    private FragmentManager fragmentManager;
    private int currentPageIndex = 0;
    private Button forwardFormBtn;
    private Button backFormBtn;

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

        fragmentDataList = new HashMap<>();
        Intent intent = getIntent();
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
            String situation = extras.getString("Situation");
            String house = extras.getString("House");
            String found = extras.getString("Found");
            String providerMotivation = extras.getString("ProviderMotivation");
            //PAGE4
            String providerDays = extras.getString("ProviderDays");
            String providerMonth = extras.getString("ProviderMonth");
            String typeRoom = extras.getString("TypeRoom");
//PAGE5
            String squareMeter = extras.getString("SquareMeter");
            String furnish = extras.getString("Furnish");
            String furnished = extras.getString("Furnished");
            String price = extras.getString("Price");
            //PAGE6
            String offer = extras.getString("Offer");
            String importantNote = extras.getString("ImportantNote");
            String volunteer = extras.getString("Volunteer");
            String commentVolunteer = extras.getString("CommentVolunteer");
            //PAGE7
            String providerWork = extras.getString("ProviderWork");
            String petsOwn = extras.getString("PetsOwn");
            String providerWorkDetails = extras.getString("ProviderWorkDetails");
            String keyword = extras.getString("Keyword");
            String hobby = extras.getString("Hobby");
            String pets = extras.getString("Pets");
            //PAGE8
            String belief = extras.getString("Belief");
            String other = extras.getString("Other");
//PAGE9
            String comment = extras.getString("Comment");//8
            //Code
            //GENDER
            if (gender.equals("M")) {
                gender = "Dhr";
            } else if (gender.equals("F")) {
                gender = "Mvr";
            } else {
                gender = "Anders";
            }

            if (house.equals("1")) {
                house = "Ja";
            } else {
                house = "Nee";
            }


            if (furnish.equals("1")) {
                furnish = "Ja";
            } else {
                furnish = "Nee";
            }

            if (providerDays.equals("1")) {
                providerDays = providerDays + " dag";
            } else {
                providerDays = providerDays + " dagen";
            }


            if (petsOwn.equals("1")) {
                petsOwn = "Ja";
            } else {
                petsOwn = "Nee";
            }

            if (providerWork.equals("1")) {
                providerWork = "Ja";
            }else{
                providerWork = "Nee";
            }

            if (volunteer.equals("1")) {
                volunteer = "Ja";
            } else {
                volunteer = "Nee";
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

            fragmentDataList.put("Situation", situation);
            fragmentDataList.put("House", house);
            fragmentDataList.put("Found", found);
            fragmentDataList.put("ProviderMotivation", providerMotivation);

            //PAGE4

            fragmentDataList.put("ProviderDays", providerDays);
            fragmentDataList.put("ProviderMonth", providerMonth + " maand(en)");
            fragmentDataList.put("TypeRoom", typeRoom);

            //PAGE5
            fragmentDataList.put("SquareMeter", squareMeter);
            fragmentDataList.put("Furnish", furnish);
            fragmentDataList.put("Furnished", furnished);
            fragmentDataList.put("Price", price);


            //PAGE6
            fragmentDataList.put("Offer", offer);
            fragmentDataList.put("ImportantNote", importantNote);
            fragmentDataList.put("Volunteer", volunteer);
            fragmentDataList.put("CommentVolunteer", commentVolunteer);


            //PAGE7
            fragmentDataList.put("ProviderWork", providerWork);
            fragmentDataList.put("ProviderWorkDetails", providerWorkDetails);
            fragmentDataList.put("Keyword", keyword);
            fragmentDataList.put("Hobby", hobby);
            fragmentDataList.put("SelfPets", petsOwn);
            fragmentDataList.put("Pets", pets);
            //PAGE8
            fragmentDataList.put("Belief", belief);
            fragmentDataList.put("Other", other);

            //PAGE9
            fragmentDataList.put("Comment", comment);
        } else {
            Log.d("INTENT EXTRAS NOT FOUND", "Cannot retrieve ID");
        }


        Toolbar toolbar = findViewById(R.id.toolbar);


        ImageView backButton = toolbar.findViewById(R.id.back_button);
        ImageView logoButton = toolbar.findViewById(R.id.MWG_logo_IV);
        ImageView optionsButton = toolbar.findViewById(R.id.options_button);

        forwardFormBtn = findViewById(R.id.button_forward_form);
        backFormBtn = findViewById(R.id.button_back_form);

        LanguageUtils.updateLanguage(this);

        String languageCode = LanguageUtils.getLanguagePreference(User_Provider_Form.this);
        String forwardFormButton = "";
        String backFormButton = "";


        if (languageCode.equals("nl")) {
            forwardFormButton = getResources().getString(R.string.form_nextBtn);
            backFormButton = getResources().getString(R.string.form_backBtn);
        } else if (languageCode.equals("en")) {
            forwardFormButton = getResources().getString(R.string.form_nextBtn_en);
            backFormButton = getResources().getString(R.string.form_backBtn_en);
        }

        forwardFormBtn.setText(forwardFormButton);
        backFormBtn.setText(backFormButton);

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
                Intent intent = new Intent(User_Provider_Form.this, RoleActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(User_Provider_Form.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Provider_Form.this, OptionsActivity.class);
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
                        Toast.makeText(com.example.project14.Provider.User_Provider_Form.this, "Vul eerst alle onderdelen in.", Toast.LENGTH_SHORT).show();
                    }
                    // Display an error message or handle the case when not all fields are filled
//                  btnForward.setBackgroundColor(getResources().getColor(R.color.grey));
                    highlightUnfilledFields();
                }

            }
        });
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

    private void setCurrentFragment() {
        try {
            Fragment fragment = (Fragment) fragmentClasses[currentPageIndex].newInstance();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();

            Button btnBack = findViewById(R.id.button_back_form);
            Button btnForward = findViewById(R.id.button_forward_form);

            if (fragment instanceof ProviderOneFragment) {
                btnBack.setVisibility(View.INVISIBLE); // Hide "Terug" button in fragmentOne
            } else if (fragment instanceof ProviderNineFragment) {
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

        if (currentFragment instanceof ProviderOneFragment) {
            ProviderOneFragment fragment = (ProviderOneFragment) currentFragment;
            if (fragment.isEmailValid()) {
                if (fragment.arePasswordsMatching()) {
                    if (fragment.isDataValid()) {
                        fragment.saveData();
                    } else {
                        Toast.makeText(com.example.project14.Provider.User_Provider_Form.this, "Vul eerst alle onderdelen in.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            return fragment.isDataValid();
        } else if (currentFragment instanceof ProviderTwoFragment) {
            ProviderTwoFragment fragment = (ProviderTwoFragment) currentFragment;

            if (fragment.isPhoneValid()) {

                if (fragment.isDataValid()) {
                    fragment.saveData();
                } else {
                    Toast.makeText(com.example.project14.Provider.User_Provider_Form.this, "Vul eerst alle onderdelen in.", Toast.LENGTH_SHORT).show();
                }
            }
            return fragment.isDataValid();

        } else if (currentFragment instanceof ProviderThreeFragment) {
            ProviderThreeFragment fragment = (ProviderThreeFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();

        } else if (currentFragment instanceof ProviderFourFragment) {
            ProviderFourFragment fragment = (ProviderFourFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();

        } else if (currentFragment instanceof ProviderFiveFragment) {
            ProviderFiveFragment fragment = (ProviderFiveFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();

        } else if (currentFragment instanceof ProviderSixFragment) {
            ProviderSixFragment fragment = (ProviderSixFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();

        } else if (currentFragment instanceof ProviderSevenFragment) {
            ProviderSevenFragment fragment = (ProviderSevenFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();

        } else if (currentFragment instanceof ProviderEightFragment) {
            ProviderEightFragment fragment = (ProviderEightFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();

        } else if (currentFragment instanceof ProviderNineFragment) {
            ProviderNineFragment fragment = (ProviderNineFragment) currentFragment;
            if (fragment.isDataValid()) {
                fragment.saveData();
            }
            return fragment.isDataValid();

        }

        return true; // Default case: allow proceeding if fragment type is unknown
    }


    public void openPrivacy(View view) {
        Uri uri = Uri.parse("https://mijnwoongenoot.nl/privacy/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void highlightUnfilledFields() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        if (currentFragment instanceof ProviderOneFragment) {
            ProviderOneFragment fragment = (ProviderOneFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof ProviderTwoFragment) {
            ProviderTwoFragment fragment = (ProviderTwoFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof ProviderThreeFragment) {
            ProviderThreeFragment fragment = (ProviderThreeFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof ProviderFourFragment) {
            ProviderFourFragment fragment = (ProviderFourFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof ProviderFiveFragment) {
            ProviderFiveFragment fragment = (ProviderFiveFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof ProviderSixFragment) {
            ProviderSixFragment fragment = (ProviderSixFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof ProviderSevenFragment) {
            ProviderSevenFragment fragment = (ProviderSevenFragment) currentFragment;
            fragment.highlightUnfilledFields();
        } else if (currentFragment instanceof ProviderEightFragment) {
            ProviderEightFragment fragment = (ProviderEightFragment) currentFragment;
        } else if (currentFragment instanceof ProviderNineFragment) {
            ProviderNineFragment fragment = (ProviderNineFragment) currentFragment;
            fragment.highlightUnfilledFields();

        }
    }


    public void passDataToNextFragment(Bundle data) {

        Log.d("TAG BUNDLE PASSING", data.toString());
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


    private boolean verifyEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public void opsturen(View view) throws JSONException {
        if (idSave.equals("")) {
            Log.d("Not editing", "Not editing");
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            String json = buildProviderPreferences().toString();
            Log.d("JSON String", json);
            RequestBody body = RequestBody.create(json, mediaType);

            Request request = new Request.Builder()
                    .url("https://hardy-stream-production.up.railway.app/api/user/verhuurder")
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
                        Intent intent = new Intent(User_Provider_Form.this, LoginActivity.class);
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
            String json = buildProviderPreferences().toString();
            Log.d("JSON String", json);
            RequestBody body = RequestBody.create(json, mediaType);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://hardy-stream-production.up.railway.app/api/user/")
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterfaceProfile service = retrofit.create(ApiInterfaceProfile.class);


            // Make the request with the extracted userID
            retrofit2.Call<JsonObject> call = service.updateVerhuurder(idSave, body);
            call.enqueue(new retrofit2.Callback<JsonObject>() {
                @Override
                public void onResponse(retrofit2.Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        JsonObject jsonObject = response.body();
                        Intent intent = new Intent(User_Provider_Form.this, ProfileUser.class);
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

    private Object buildProviderPreferences() throws JSONException {
        JSONObject userPreferences = new JSONObject();

        JSONObject user = new JSONObject();
        user.put("emailAddress", fragmentDataList.get("Email"));
        user.put("password", fragmentDataList.get("Password"));
        user.put("dateOfBirth", fragmentDataList.get("BirthDate"));

        user.put("firstName", fragmentDataList.get("FirstName"));
        user.put("middleName", fragmentDataList.get("Infix"));
        user.put("lastName", fragmentDataList.get("LastName"));
        user.put("picture", "temp");

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
        preferences.put("situation", fragmentDataList.get("Situation"));
        preferences.put("house", fragmentDataList.get("House"));
        preferences.put("found", fragmentDataList.get("Found"));
        preferences.put("motivation", fragmentDataList.get("ProviderMotivation"));
        preferences.put("housePicture", "Temp");

//MONTH
        String[] periodSplit = fragmentDataList.get("ProviderMonth").split(" ");
        String periodGood = periodSplit[0];

//DAY
        String[] nightSplit = fragmentDataList.get("ProviderDays").split(" ");
        String nightGood = nightSplit[0];


        preferences.put("period", periodGood);
        preferences.put("nights", nightGood);
        preferences.put("roomType", fragmentDataList.get("TypeRoom"));
        preferences.put("roomSize", fragmentDataList.get("SquareMeter"));
        preferences.put("furniture", fragmentDataList.get("Furnish"));
        preferences.put("furnitureDescription", fragmentDataList.get("Furnished"));
        preferences.put("price", fragmentDataList.get("Price"));
        preferences.put("offer", fragmentDataList.get("Offer"));
        preferences.put("importantNote", fragmentDataList.get("ImportantNote"));
        preferences.put("volunteer", fragmentDataList.get("Volunteer"));
        preferences.put("volunteerDescription", fragmentDataList.get("CommentVolunteer"));
        preferences.put("work", fragmentDataList.get("ProviderWork"));
        preferences.put("workDescription", fragmentDataList.get("ProviderWorkDetails"));
        preferences.put("describe", fragmentDataList.get("Keyword"));
        preferences.put("hobby", fragmentDataList.get("Hobby"));
        preferences.put("pet", fragmentDataList.get("SelfPets"));
        preferences.put("petDescription", fragmentDataList.get("Pets"));
        preferences.put("religion", fragmentDataList.get("Belief"));
        preferences.put("comment", fragmentDataList.get("Comment"));
        preferences.put("overallcomment", fragmentDataList.get("Comment"));


        userPreferences.put("user", user);
        userPreferences.put("preferences", preferences);


        return userPreferences;
    }

}


