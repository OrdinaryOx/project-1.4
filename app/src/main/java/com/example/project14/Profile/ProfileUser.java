package com.example.project14.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileUser extends AppCompatActivity {
//TODO: Verhuurder Layout, User Verhuurder Layout
    private JsonObject userprofile;

    private void getUserProfile() {
        Toast.makeText(this, "Informatie wordt opgehaald...", Toast.LENGTH_SHORT).show();
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/user/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterfaceProfile apiService = retrofit.create(ApiInterfaceProfile.class);

        Call<JsonObject> call = apiService.getProfile();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();
                JsonObject profiledata = jsonObject.getAsJsonObject("user");

                JsonObject preferenceData = jsonObject.getAsJsonObject("preferences");

                String[] tokenParts = token.split("\\.");
                String payload = tokenParts[1];
                String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));

                JSONObject payloadJson = null;
                try {
                    payloadJson = new JSONObject(decodedPayload);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                try {
                    if (payloadJson.getString("role").equals("Huurder")) {
                        //change huurder data
                        TextView name = findViewById(R.id.username);
                        TextView genderTextView = findViewById(R.id.gender);
                        TextView ageTextView = findViewById(R.id.age);
                        TextView residenceTextView = findViewById(R.id.placeOfResidence);
                        TextView budgetTextView = findViewById(R.id.budget);
                        TextView workTextView = findViewById(R.id.work);
                        TextView workDescTextView = findViewById(R.id.workDesc);
                        TextView ehboTextView = findViewById(R.id.EHBO);
                        TextView petsTextView = findViewById(R.id.Pets);
                        TextView petDescTextView = findViewById(R.id.petDesc);
                        TextView remarksTextView = findViewById(R.id.remarks);
                        TextView userDescriptionTextView = findViewById(R.id.userDescription);
                        TextView userKeywordsTextView = findViewById(R.id.userKeywords);

                        String firstName = profiledata.get("firstName") != null ? profiledata.get("firstName").toString() : "-";
                        String middleName = profiledata.get("middleName") != null ? profiledata.get("middleName").toString() : "-";
                        String lastName = profiledata.get("lastName") != null ? profiledata.get("lastName").toString() : "-";
                        String gender = profiledata.get("gender") != null ? profiledata.get("gender").toString() : "-";
                        String city = profiledata.get("city") != null ? profiledata.get("city").toString() : "-";
                        String budget = preferenceData.get("budget") != null ? preferenceData.get("budget").toString() : "-";
                        String work = (preferenceData.get("work") != null && preferenceData.get("work").getAsInt() == 1) ? "ja" : "nee";
                        String workDescription = preferenceData.get("workDescription") != null ? preferenceData.get("workDescription").toString() : "-";
                        String healthRisk = profiledata.get("healthRisk") != null ? profiledata.get("healthRisk").toString() : "-";
                        String pet = (preferenceData.get("pet") != null && preferenceData.get("pet").getAsInt() == 1) ? "ja" : "nee";
                        String petDescription = preferenceData.get("PetDescription") != null ? preferenceData.get("PetDescription").toString() : "-";
                        String overallComment = profiledata.get("overallcomment") != null ? profiledata.get("overallcomment").toString() : "-";
                        String description = profiledata.get("describe") != null ? profiledata.get("description").toString() : "-";
                        String keywords = profiledata.get("keywords") != null ? profiledata.get("keywords").toString() : "-";

                        name.setText(firstName + " " + middleName + " " + lastName);
                        genderTextView.setText("gender: " + gender);
                        residenceTextView.setText("Woonplaats: " + city);
                        budgetTextView.setText("Budget: €" + budget + " p/m");
                        workTextView.setText("Werk: " + work);
                        workDescTextView.setText("Beschrijving werk: " + workDescription);
                        ehboTextView.setText("Medische hulp: " + healthRisk);
                        petsTextView.setText("Huisdieren: " + pet);
                        petDescTextView.setText("Huisdier omschrijving: " + petDescription);
                        remarksTextView.setText("Taken waarbij hulp kan worden geboden: " + overallComment);
                        userDescriptionTextView.setText("Beschrijving van jezelf: " + description);
                        userKeywordsTextView.setText("Kernwoorden over jezelf: " + keywords);
                    } else if(payloadJson.getString("role").equals("Verhuurder"))  {
                        Log.d("check body", preferenceData.toString());
                        Log.d("check body", profiledata.toString());
                        //Change verhuurder data
                        TextView nameverhuurder = findViewById(R.id.username);
                        TextView genderTextViewverhuurder = findViewById(R.id.gender);
                        TextView ageTextViewverhuurder = findViewById(R.id.age);
                        TextView residenceTextViewverhuurder = findViewById(R.id.placeOfResidence);
                        TextView budgetTextViewverhuurder = findViewById(R.id.rentalPrice);
                        TextView workDescTextViewverhuurder = findViewById(R.id.workDesc);
                        TextView ehboTextViewverhuurder = findViewById(R.id.EHBO);
                        TextView petsTextViewverhuurder = findViewById(R.id.Pets);
                        TextView petDescTextViewverhuurder = findViewById(R.id.petDesc);
                        TextView remarksTextViewverhuurder = findViewById(R.id.remarks);
                        TextView userDescriptionTextViewverhuurder = findViewById(R.id.userDescription);
                        TextView userKeywordsTextViewverhuurder = findViewById(R.id.userKeywords);

                        String firstNameverhuurder = profiledata.get("firstName") != null ? profiledata.get("firstName").toString() : "-";
                        String middleNameverhuurder = profiledata.get("middleName") != null ? profiledata.get("middleName").toString() : "-";
                        String lastNameverhuurder = profiledata.get("lastName") != null ? profiledata.get("lastName").toString() : "-";
                        String genderverhuurder = profiledata.get("gender") != null ? profiledata.get("gender").toString() : "-";
                        String cityverhuurder = profiledata.get("city") != null ? profiledata.get("city").toString() : "-";
                        String budgetverhuurder = preferenceData.get("price") != null ? preferenceData.get("price").toString() : "-";
                        String healthRiskverhuurder = profiledata.get("healthRisk") != null ? profiledata.get("healthRisk").toString() : "-";
                        String petverhuurder = preferenceData.get("pet") != null && preferenceData.get("pet").getAsInt() == 1 ? "ja" : "nee";
                        String petDescriptionverhuurder = preferenceData.get("PetDescription") != null ? preferenceData.get("PetDescription").toString() : "-";
    //                    String overallCommentverhuurder = preferenceData.get("overallcomment") != null ? profiledata.get("overallcomment").toString() : "-";
    //                    String descriptionverhuurder = preferenceData.get("describe") != null ? profiledata.get("description").toString() : "-";
    //                    String keywordsverhuurder = preferenceData.get("keywords") != null ? profiledata.get("keywords").toString() : "-";

                        nameverhuurder.setText(firstNameverhuurder + " " + middleNameverhuurder + " " + lastNameverhuurder);
                        genderTextViewverhuurder.setText("Geslacht: " + genderverhuurder);
                        residenceTextViewverhuurder.setText("Woonplaats: " + cityverhuurder);
                        Log.d("check budget", budgetverhuurder);
                        budgetTextViewverhuurder.setText("Huurprijs: €" + budgetverhuurder + " p/m excl");
    //                ehboTextViewverhuurder.setText("Medische hulp: " + healthRiskverhuurder);
                        petsTextViewverhuurder.setText("Huisdieren: " + petverhuurder);
                        petDescTextViewverhuurder.setText("Huisdier omschrijving: " + petDescriptionverhuurder);
                        remarksTextViewverhuurder.setText("Taken waarbij hulp kan worden geboden: " + remarksTextViewverhuurder.getText());
    //                    userDescriptionTextViewverhuurder.setText("Beschrijving van jezelf: " + descriptionverhuurder);
    //                    userKeywordsTextViewverhuurder.setText("Kernwoorden over jezelf: " + keywordsverhuurder);
                    } else {
                        Log.e("ERROR profile", "ERROR profile");
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Error", "Request failed:" + t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getUserProfile();
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        String[] tokenParts = token.split("\\.");
        String payload = tokenParts[1];
        String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));
        JSONObject payloadJson = null;
        try {
            payloadJson = new JSONObject(decodedPayload);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        try {
            if (payloadJson.getString("role").equals("Huurder")) {
                setContentView(R.layout.activity_profile_user_huurder);

            } else {
                setContentView(R.layout.activity_profile_user_verhuurder);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //  setContentView(R.layout.activity_profile_user_huurder);
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
                onBackPressed();
            }
        });

        // Set click listener for the logo button
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when logo button is clicked
                Intent intent = new Intent(ProfileUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileUser.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
    }
}