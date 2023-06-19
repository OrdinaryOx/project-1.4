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

import com.example.project14.LanguageUtils;
import com.example.project14.MainActivity;
import com.example.project14.Match.Match;
import com.example.project14.OptionsActivity;
import com.example.project14.Provider.User_Provider_Form;
import com.example.project14.R;
import com.example.project14.Seeking.User_Seeking_Form;
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
    private String role;
    private String beliefIntent;
    private String otherIntent;
    private Match matchUser;
    private String skillIntent;
    private String overallCommentIntent;
    private String idealIntent;
    private String offerIntent;

    private String userDescriptionIntent;
    private String keywordsIntent;
    private String cityIntent;
    private String preferenceIntent;
    private String budgetIntent;
    private String monthIntent;
    private String NightsIntent;
    private String PetIntent;
    private String OwnPetIntent;
    private String OwnPetDescriptionIntent;

    private String startDateIntent;
    private String endDateIntent;
    private String reasonIntent;
    private String schoolFinishedIntent;
    private String schoolDoingIntent;
    private String workIntent;
    private String OtherOfferIntent;
    private String ImportantNoteIntent;
    private String VolunteerSelectionIntent;
    private String VolunteerIntent;
    private String workDescIntent;

    private String healthIntent;
    private String healthDescIntent;
    private JsonObject prefData;

    //PROVIDER
    //3
    private String situationIntent;
    private String houseIntent;
    private String foundIntent;
    private String providerMotivationIntent;
    //4
    private String providerDaysIntent;
    private String providerMonthIntent;
    private String typeRoomIntent;
    //5
    private String squareMeterIntent;
    private String furnishIntent;
    private String furnishedIntent;
    private String priceIntent;
    //6
    private String offerProvIntent;
    private String importantNoteProvIntent;
    private String volunteerProvIntent;
    private String CommentVolunteerIntent;
    //7
    private String providerWorkDetailsIntent;
    private String keywordIntent;
    private String hobbyIntent;
    private String petsIntent;
    private String providerWorkIntent;
    private String petsOwnIntent;

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
                prefData = preferenceData;

                String[] tokenParts = token.split("\\.");
                String payload = tokenParts[1];
                String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));


                Gson gson = new Gson();
                Type type = new TypeToken<Match>() {
                }.getType();
                Match match = gson.fromJson(profiledata, type);
                matchUser = match;
                Log.d("TAG", match.toString());

                JSONObject payloadJson = null;
                try {
                    payloadJson = new JSONObject(decodedPayload);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                try {
                    role = payloadJson.getString("role");
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
                        String skill = preferenceData.get("skill").toString();
                        String pet = (preferenceData.get("ownPet").toString());
                        String petDescription = preferenceData.get("ownPetDescription").toString();
                        String preference = preferenceData.get("liveWith").toString();
                        String month = preferenceData.get("period").toString();
                        String day = preferenceData.get("nights").toString();
                        String petYesNo = preferenceData.get("pet").toString();

                        String health = preferenceData.get("healthRisk").toString();
                        String healthDesc = preferenceData.get("healthRiskDescription").toString();
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


                        workDesc = workDesc.replace("\"", "");

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

                        skill.replace("\"", "");
                        if (skill.isEmpty() || skill == null || skill.equals("\"\"") || skill.equals("0")) {
                            skill = "Geen medische vaardigheden";
                        }
                        ehboTextView.setText(skill);
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


                        preference = preference.replace("\"", "");
                        month = month.replace("\"", "");
                        pet = pet.replace("\"", "");
                        petDescription = petDescription.replace("\"", "");
                        day = day.replace("\"", "");
                        petYesNo = petYesNo.replace("\"", "");

                        //SETTING PRIVATE
                        cityIntent = city;
                        preferenceIntent = preference;
                        budgetIntent = budget;
                        monthIntent = month;

                        NightsIntent = day;
                        PetIntent = petYesNo;
                        OwnPetIntent = pet;
                        OwnPetDescriptionIntent = petDescription;

                        String startDate = preferenceData.get("starDate").toString();
                        String endDate = preferenceData.get("endDate").toString();
                        String schoolFinished = preferenceData.get("schoolFinished").toString();
                        String schoolDoing = preferenceData.get("schoolDoing").toString();


                        startDate = startDate.replace("\"", "");
                        endDate = endDate.replace("\"", "");
                        schoolFinished = schoolFinished.replace("\"", "");
                        schoolDoing = schoolDoing.replace("\"", "");
                        health = health.replace("\"", "");
                        healthDesc = healthDesc.replace("\"", "");


                        startDateIntent = startDate;
                        endDateIntent = endDate;
                        reasonIntent = reden;
                        schoolFinishedIntent = schoolFinished;
                        schoolDoingIntent = schoolDoing;

                        workIntent = workString;
                        workDescIntent = workDesc;
                        skillIntent = skill;
                        healthIntent = health;
                        healthDescIntent = healthDesc;

                        userDescriptionIntent = description;
                        keywordsIntent = keywords;
                        idealIntent = ideal;
                        offerIntent = offer;


                        String otherOffer = preferenceData.get("offerYou").toString();
                        String importantNote = preferenceData.get("importantNote").toString();
                        String volunteerYes = preferenceData.get("volunteer").toString();
                        String volunteerDesc = preferenceData.get("volunteerDescription").toString();

                        otherOffer = otherOffer.replace("\"", "");
                        importantNote = importantNote.replace("\"", "");
                        volunteerYes = volunteerYes.replace("\"", "");
                        volunteerDesc = volunteerDesc.replace("\"", "");

                        OtherOfferIntent = otherOffer;
                        ImportantNoteIntent = importantNote;
                        VolunteerSelectionIntent = volunteerYes;
                        VolunteerIntent = volunteerDesc;


                        String belief = preferenceData.get("religion").toString();
                        String other = preferenceData.get("comment").toString();
                        belief = belief.replace("\"", "");
                        other = other.replace("\"", "");
                        beliefIntent = belief;
                        otherIntent = other;


                        String overallComment2 = preferenceData.get("overallcomment").toString();
                        overallComment2 = overallComment2.replace("\"", "");
                        overallCommentIntent = overallComment2;

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




                        workDesc = workDesc.replace("\"", "");

                        String houseIntentItem = preferenceData.get("house").toString();
                        String foundIntentItem = preferenceData.get("found").toString();
                        String providerMotivationIntentItem = preferenceData.get("motivation").toString();
                        String providerDaysIntentItem = preferenceData.get("period").toString();
                        String providerMonthIntentItem = preferenceData.get("nights").toString();
                        String typeRoomIntentItem = preferenceData.get("roomType").toString();
                        String priceIntentItem = preferenceData.get("price").toString();
                        String offerProvIntentItem = preferenceData.get("offer").toString();
                        String volunteerProvIntentItem = preferenceData.get("volunteer").toString();
                        String CommentVolunteerIntentItem = preferenceData.get("volunteerDescription").toString();
                        //WORK?
                        String providerWorkDetailsIntentItem = preferenceData.get("workDescription").toString();
                        String hobbyIntentItem = preferenceData.get("hobby").toString();
                        //?
                        String petsIntentItem = preferenceData.get("petDescription").toString();
                        String beliefIntentItem = preferenceData.get("religion").toString();
                        String otherIntentItem = preferenceData.get("comment").toString();
                        String overallCommentIntentItem = preferenceData.get("overallcomment").toString();


                        //
                        String providerWorkIntentItem = preferenceData.get("work").toString();
                        String petsOwnIntentItem = preferenceData.get("pet").toString();

                        houseIntentItem = houseIntentItem.replace("\"", "");
                        foundIntentItem = foundIntentItem.replace("\"", "");
                        providerMotivationIntentItem = providerMotivationIntentItem.replace("\"", "");
                        providerDaysIntentItem = providerDaysIntentItem.replace("\"", "");
                        providerMonthIntentItem = providerMonthIntentItem.replace("\"", "");
                        typeRoomIntentItem = typeRoomIntentItem.replace("\"", "");
                        priceIntentItem = priceIntentItem.replace("\"", "");
                        offerProvIntentItem = offerProvIntentItem.replace("\"", "");
                        volunteerProvIntentItem = volunteerProvIntentItem.replace("\"", "");
                        CommentVolunteerIntentItem = CommentVolunteerIntentItem.replace("\"", "");
                        providerWorkDetailsIntentItem = providerWorkDetailsIntentItem.replace("\"", "");
                        hobbyIntentItem = hobbyIntentItem.replace("\"", "");
                        petsIntentItem = petsIntentItem.replace("\"", "");
                        beliefIntentItem = beliefIntentItem.replace("\"", "");
                        otherIntentItem = otherIntentItem.replace("\"", "");
                        overallCommentIntentItem = overallCommentIntentItem.replace("\"", "");

                        providerWorkIntentItem = providerWorkIntentItem.replace("\"", "");
                        petsOwnIntentItem = petsOwnIntentItem.replace("\"", "");


                        situationIntent = situation;
                        houseIntent = houseIntentItem;
                        foundIntent = foundIntentItem;
                        providerMotivationIntent = providerMotivationIntentItem;
                        providerDaysIntent = providerDaysIntentItem;
                        providerMonthIntent = providerMonthIntentItem;
                        typeRoomIntent = typeRoomIntentItem;
                        squareMeterIntent = roomSize;
                        furnishIntent = furnished;
                        furnishedIntent = furnishedDesc;
                        priceIntent = priceIntentItem;
                        offerProvIntent = offerProvIntentItem;
                        importantNoteProvIntent = important;
                        volunteerProvIntent = volunteerProvIntentItem;
                        CommentVolunteerIntent = CommentVolunteerIntentItem;
                        providerWorkDetailsIntent = providerWorkDetailsIntentItem;
                        keywordIntent = keywords;
                        hobbyIntent = hobbyIntentItem;
                        petsIntent = petsIntentItem;
                        beliefIntent = beliefIntentItem;
                        otherIntent = otherIntentItem;
                        overallCommentIntent = overallCommentIntentItem;

                        providerWorkIntent = providerWorkIntentItem;
                        petsOwnIntent = petsOwnIntentItem;


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
        LanguageUtils.updateLanguage(this);

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

    public void editUser(View view) throws JSONException {
        //Ik heb alle data boven bij de if (role == huurder / == verhuurder) ik kan alles in een intent opsturen naar user_seeking_form (voor huurder), vervolgens alle intents opvragen en
        // in de hashmap gooien.
        // Aan het einde bij opsturen kan er een check worden gedaan voor hashmap.get("UPDATE") (ik kan een nieuw upadte onderdeel in de hashmap zetten) en ik kan het ID meegeven,
        // misschien checken op ID ipv UPDATE,
        // iig als het erin zit dan wordt er andere code uitgevoerd in de createUser.

//GET ID
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
        String id = payloadJson.getString("id");


        if (role.equals("Huurder")) {
            Intent intent = new Intent(ProfileUser.this, User_Seeking_Form.class);
            intent.putExtra("ID", id);
            intent.putExtra("ROLE", role);

            //PAGE1
            intent.putExtra("FirstName", matchUser.getFirstName());
            intent.putExtra("MiddleName", matchUser.getMiddleName());
            intent.putExtra("LastName", matchUser.getLastName());
            intent.putExtra("Email", matchUser.getEmailAddress());
            intent.putExtra("Password", matchUser.getPassword());
            intent.putExtra("Salutation", matchUser.getGender());

            //PAGE2
            intent.putExtra("Address", matchUser.getStreet());
            intent.putExtra("HouseNumber", matchUser.getHouseNumber());
            intent.putExtra("CityPersonal", matchUser.getCity());
            intent.putExtra("PostalCode", matchUser.getPostalCode());
            intent.putExtra("Country", matchUser.getCountry());
            intent.putExtra("PhoneNumber", matchUser.getPhoneNumber());
            intent.putExtra("BirthDate", matchUser.getDateOfBirth());

            //PAGE3
            intent.putExtra("City", cityIntent);
            intent.putExtra("Preference", preferenceIntent);
            intent.putExtra("Budget", budgetIntent);
            intent.putExtra("Month", monthIntent);
            //PAGE4
            intent.putExtra("Day", NightsIntent);
            intent.putExtra("Pets", PetIntent);
            intent.putExtra("SelfPets", OwnPetIntent);
            intent.putExtra("PetsComment", OwnPetDescriptionIntent);

            //PAGE5
            intent.putExtra("StartDate", startDateIntent);
            intent.putExtra("EndDate", endDateIntent);
            intent.putExtra("Reason", reasonIntent);
            intent.putExtra("Grade", schoolFinishedIntent);
            intent.putExtra("Course", schoolDoingIntent);

            //PAGE6
            Log.d("SkillIntent", skillIntent);
            intent.putExtra("Skill", skillIntent);
            intent.putExtra("SeekingWork", workIntent);
            intent.putExtra("Work", workDescIntent);
            intent.putExtra("Health", healthIntent);
            intent.putExtra("HealthInfo", healthDescIntent);

            //PAGE7
            intent.putExtra("Yourself", userDescriptionIntent);
            intent.putExtra("Keyword", keywordsIntent);
            intent.putExtra("Room", idealIntent);
            intent.putExtra("Mean", offerIntent);

            //PAGE 8
            intent.putExtra("OtherOffer", OtherOfferIntent);
            intent.putExtra("ImportantNote", ImportantNoteIntent);
            intent.putExtra("VolunteerSelection", VolunteerSelectionIntent);
            intent.putExtra("Volunteer", VolunteerIntent);

            //PAGE9
            intent.putExtra("Belief", beliefIntent);
            intent.putExtra("Other", otherIntent);

            //PAGE10
            intent.putExtra("Comment", overallCommentIntent);
            startActivity(intent);
        } else if (role.equals("Verhuurder")) {
            Intent intent = new Intent(ProfileUser.this, User_Provider_Form.class);
            intent.putExtra("ID", id);
            intent.putExtra("ROLE", role);

            //PAGE1
            intent.putExtra("FirstName", matchUser.getFirstName());
            intent.putExtra("MiddleName", matchUser.getMiddleName());
            intent.putExtra("LastName", matchUser.getLastName());
            intent.putExtra("Email", matchUser.getEmailAddress());
            intent.putExtra("Password", matchUser.getPassword());
            intent.putExtra("Salutation", matchUser.getGender());

            //PAGE2
            intent.putExtra("Address", matchUser.getStreet());
            intent.putExtra("HouseNumber", matchUser.getHouseNumber());
            intent.putExtra("CityPersonal", matchUser.getCity());
            intent.putExtra("PostalCode", matchUser.getPostalCode());
            intent.putExtra("Country", matchUser.getCountry());
            intent.putExtra("PhoneNumber", matchUser.getPhoneNumber());
            intent.putExtra("BirthDate", matchUser.getDateOfBirth());

            //PAGE3
            intent.putExtra("Situation", situationIntent);
            intent.putExtra("House", houseIntent);
            intent.putExtra("Found", foundIntent);
            intent.putExtra("ProviderMotivation", providerMotivationIntent);

            //PAGE4
            intent.putExtra("ProviderDays", providerDaysIntent);
            intent.putExtra("ProviderMonth", providerMonthIntent);
            intent.putExtra("TypeRoom", typeRoomIntent);

            //PAGE5
            intent.putExtra("SquareMeter", squareMeterIntent);
            intent.putExtra("Furnish", furnishIntent);
            intent.putExtra("Furnished", furnishedIntent);
            intent.putExtra("Price", priceIntent);

            //PAGE6
            intent.putExtra("Offer", offerProvIntent);
            intent.putExtra("ImportantNote", importantNoteProvIntent);
            intent.putExtra("Volunteer", volunteerProvIntent);
            intent.putExtra("CommentVolunteer", CommentVolunteerIntent);

            //PAGE7
            intent.putExtra("ProviderWorkDetails", providerWorkDetailsIntent);
            intent.putExtra("Keyword", keywordIntent);
            intent.putExtra("Hobby", hobbyIntent);
            intent.putExtra("Pets", petsIntent);
            intent.putExtra("ProviderWork", providerWorkIntent);
            intent.putExtra("PetsOwn", petsOwnIntent);

            //PAGE8
            intent.putExtra("Belief", beliefIntent);
            intent.putExtra("Other", otherIntent);

            //PAGE9
            intent.putExtra("Comment", overallCommentIntent);


            startActivity(intent);
        } else {
            Log.e("ERROR ROLE", "cant edit, cant find role");
        }
    }
}