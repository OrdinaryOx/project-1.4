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
import com.example.project14.Match.Match;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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


                Gson gson = new Gson();
                Type type = new TypeToken<Match>() {
                }.getType();
                Match match = gson.fromJson(profiledata, type);
                Log.d("TAG", match.toString());

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
                        TextView residenceTextView = findViewById(R.id.placeOfResidenceDesc);
                        TextView budgetTextView = findViewById(R.id.budgetDesc);
                        TextView workTextView = findViewById(R.id.workDesc);
                        TextView workDescTextView = findViewById(R.id.workDesc);
                        TextView ehboTextView = findViewById(R.id.EHBODesc);
                        TextView petsTextView = findViewById(R.id.Pets);
                        TextView petDescTextView = findViewById(R.id.petDesc);
                        TextView remarksTextView = findViewById(R.id.remarks);
                        TextView userDescriptionTextView = findViewById(R.id.userDescription);
                        TextView userKeywordsTextView = findViewById(R.id.userKeywords);

                        TextView idealWoonomgeving = findViewById(R.id.idealeDesc);
                        TextView redenWonen = findViewById(R.id.redenDesc);

//                        String firstName = profiledata.get("firstName").toString();
//                        String middleName = profiledata.get("middleName") != null ? profiledata.get("middleName").toString() : "";
//                        String lastName = profiledata.get("lastName") != null ? profiledata.get("lastName").toString() : "-";
//                        String gender = profiledata.get("gender") != null ? profiledata.get("gender").toString() : "-";
//                        String city = profiledata.get("city") != null ? profiledata.get("city").toString() : "-";
                        String budget = preferenceData.get("budget") != null ? preferenceData.get("budget").toString() : "-";
//                        String work = (preferenceData.get("work") != null && preferenceData.get("work").getAsInt() == 1) ? "ja" : "nee";
                        String workDesc = preferenceData.get("workDescription").toString();
                        String healthRisk = preferenceData.get("healthRisk").toString();
                        String pet = (preferenceData.get("ownPet").toString());
                        String petDescription = preferenceData.get("ownPetDescription").toString();
//                        String overallComment = profiledata.get("overallcomment") != null ? profiledata.get("overallcomment").toString() : "-";
//                        String description = profiledata.get("selfDescription").toString();
//                        Log.d("Description", description);
//                        String keywords = profiledata.get("selfWords").toString();
//                        String dob = profiledata.get("dateOfBirth").toString();

                        String keywords = preferenceData.get("selfWords").toString();
                        String description = preferenceData.get("selfDescription").toString();

                        String offer = preferenceData.get("offer").toString();
                        offer = offer.replace("\"", "");

                        keywords = keywords.replace("\"", "");
                        description = description.replace("\"", "");

                        String reden = preferenceData.get("reason").toString();
                        String ideal = preferenceData.get("idealSpace").toString();


                        String firstName = match.getFirstName();
                        String middleName = match.getMiddleName();
                        String lastName = match.getLastName();
                        String gender = match.getGender();
                        String city = match.getCity();
                        int work = match.getWork();
                        //                String workDesc = match.getWorkDescription();
                        //   String healthRisk = match.getHealthRisk();
                        //String pet = match.getPet();
                        //String petDescription = match.getOwnPetDescription();
                        String overallComment = match.getOverallcomment();

                        String dob = match.getDateOfBirth();


                        reden = reden.replace("\"", "");
                        ideal = ideal.replace("\"", "");

                        idealWoonomgeving.setText(ideal);
                        redenWonen.setText(reden);


                        firstName = firstName.replace("\"", "");
                        middleName = middleName.replace("\"", "");
                        lastName = lastName.replace("\"", "");

                        name.setText(firstName + " " + middleName + " " + lastName);
                        if (gender.contains("M")) {
                            gender = "Man";
                        } else if (gender.contains("V")) {
                            gender = "Vrouw";
                        } else {
                            gender = "Anders";
                        }


                        genderTextView.setText("- " + gender);

                        int age = calculateAge(dob);
                        ageTextView.setText("(" + age + ")");


                        city = city.replace("\"", "");
                        residenceTextView.setText(city);
                        budgetTextView.setText("€" + budget + " p/m");
                        //         workTextView.setText("Werk: " + work);

                        String workString = "";
                        if (work == 0) {
                            workString = "Nee";
                        } else {
                            workString = "Ja - ";
                        }




                        workDesc.replace("\"", "");

                        if (workDesc != null) {
                            if (workDesc.equals("") || workDesc.equals("null") || workDesc.trim().isEmpty() || workDesc.equals("\"\"")) {
                                workDesc = "";
                            } else {
                                workDesc = " - " + workDesc;
                            }

                        } else {
                            workDesc = "";
                        }

                        Log.d("WORKDESC", workDesc);





                        workDescTextView.setText(workString + workDesc);

                        healthRisk.replace("\"", "");
                        if (healthRisk.isEmpty() || healthRisk == null || healthRisk.equals("\"\"") || healthRisk.equals("0")) {
                            healthRisk = "Geen medische vaardigheden";
                        }
                        ehboTextView.setText(healthRisk);
                        // petsTextView.setText("Huisdieren: " + pet);

                        if (pet == "0") {
                            pet = "Nee";
                        } else {
                            pet = "Ja - ";
                        }

                        petDescription = petDescription.replace("\"", "");

                        petDescTextView.setText(pet + petDescription);
                        remarksTextView.setText(offer);
                        userDescriptionTextView.setText(description);
                        userKeywordsTextView.setText(keywords);
                    } else if (payloadJson.getString("role").equals("Verhuurder")) {
                        Log.d("check body", preferenceData.toString());
                        Log.d("check body", profiledata.toString());
                        //Change verhuurder data
                        TextView nameverhuurder = findViewById(R.id.username);
                        TextView genderTextViewverhuurder = findViewById(R.id.gender);
                        TextView ageTextViewverhuurder = findViewById(R.id.age);
                        TextView residenceTextViewverhuurder = findViewById(R.id.placeOfResidenceDesc);
                        TextView budgetTextViewverhuurder = findViewById(R.id.rentalPriceDesc);
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
                        String petverhuurder = profiledata.get("pet") != null && preferenceData.get("pet").getAsInt() == 1 ? "Ja" : "Nee";
                        String petDescriptionverhuurder = profiledata.get("petDescription") != null ? preferenceData.get("PetDescription").toString() : "";

                        //                    String overallCommentverhuurder = preferenceData.get("overallcomment") != null ? profiledata.get("overallcomment").toString() : "-";
                        //                    String descriptionverhuurder = preferenceData.get("describe") != null ? profiledata.get("description").toString() : "-";
                        //                    String keywordsverhuurder = preferenceData.get("keywords") != null ? profiledata.get("keywords").toString() : "-";


                        String dob = match.getDateOfBirth();
                        int age = calculateAge(dob);
                        ageTextViewverhuurder.setText("(" + age + ")");

//TODO: furnished, furnishedDesc, woonsituatie,            --> ImportantNote at userdescription, maybe place at bottom. What goes in userDescription ?
                        String keywords = preferenceData.get("describe").toString();
                        String important = preferenceData.get("importantNote").toString();
                        String roomSize = preferenceData.get("roomSize").toString();
                        String furnished = preferenceData.get("furniture") != null && preferenceData.get("furniture").getAsInt() == 1 ? "Ja" : "Nee";
                        String furnishedDesc = preferenceData.get("furnitureDescription").toString();
                        String situation = preferenceData.get("situation").toString();
                        String workDesc = preferenceData.get("workDescription").toString();
                        String work = preferenceData.get("work") != null && preferenceData.get("work").getAsInt() == 1 ? "Ja" : "Nee";
                        String motivation = preferenceData.get("motivation").toString();


                        TextView livingSpaceTV = findViewById(R.id.livingSpaceDesc);
                        TextView keywordsTV = findViewById(R.id.userKeywords);
                        TextView importantNote = findViewById(R.id.userDescription);
                        TextView furnishedDescTV = findViewById(R.id.meubilairDesc);
                        TextView situationTV = findViewById(R.id.WoonsituatieDesc);
                        TextView furnishedTextTV = findViewById(R.id.meubilairDescDesc);
                        TextView motivationTV = findViewById(R.id.remarks);





                        if (furnished.equals("Nee")) {
                            furnishedTextTV.setVisibility(View.GONE);
                        }
                        //REMOVING ""
                        workDesc = workDesc.replace("\"", "");
                        roomSize = roomSize.replace("\"", "");
                        keywords = keywords.replace("\"", "");
                        important = important.replace("\"", "");
                        furnished = furnished.replace("\"", "");
                        furnishedDesc = furnishedDesc.replace("\"", "");
                        situation = situation.replace("\"", "");
                        motivation = motivation.replace("\"", "");

                        situationTV.setText(situation);
                        motivationTV.setText(motivation);


                        if (workDesc != null) {
                            if (workDesc.equals("") || workDesc.equals("null") || workDesc.trim().isEmpty() || workDesc.equals("\"\"")) {
                                workDesc = "";
                            } else {
                                workDesc = " - " + workDesc;
                            }

                        } else {
                            workDesc = "";
                        }

                        Log.d("WORKDESC", workDesc);

                        workDescTextViewverhuurder.setText(work + workDesc);
                        livingSpaceTV.setText(roomSize + " m2");
                        keywordsTV.setText(keywords);
                        importantNote.setText(important);

                        if (furnishedDesc.isEmpty() || furnishedDesc == null || furnishedDesc.equals("\"\"")) {
                            furnishedDesc = "";
                        }


                        furnishedDescTV.setText(furnished);
                        furnishedTextTV.setText(furnishedDesc);


                        String firstName = match.getFirstName();
                        String middleName = match.getMiddleName();
                        String lastName = match.getLastName();
                        String gender = match.getGender();
                        String city = match.getCity();

                        nameverhuurder.setText(firstName + " " + middleName + " " + lastName);
                        if (gender.contains("M")) {
                            gender = "Man";
                        } else if (gender.contains("V")) {
                            gender = "Vrouw";
                        } else {
                            gender = "Anders";
                        }
                        genderTextViewverhuurder.setText("- " + gender);
                        city = city.replace("\"", "");
                        residenceTextViewverhuurder.setText(city);


//                        nameverhuurder.setText(firstNameverhuurder + " " + middleNameverhuurder + " " + lastNameverhuurder);
//                        genderTextViewverhuurder.setText("Geslacht: " + genderverhuurder);
//                        residenceTextViewverhuurder.setText("Woonplaats: " + cityverhuurder);

                        budgetTextViewverhuurder.setText("€" + budgetverhuurder + " p/m excl");
                        //                ehboTextViewverhuurder.setText("Medische hulp: " + healthRiskverhuurder);
                        petDescTextViewverhuurder.setText(petverhuurder + petDescriptionverhuurder);
                        remarksTextViewverhuurder.setText(remarksTextViewverhuurder.getText());
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


    private static int calculateAge(String dateOfBirth) {
        if (dateOfBirth.length() > 10) {
            dateOfBirth = dateOfBirth.substring(0, 10);
        }

        Log.d("DOB2", dateOfBirth);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date birthDate = format.parse(dateOfBirth);
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);

            Calendar currentDate = Calendar.getInstance();
            int age = currentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

            if (currentDate.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            return age;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}