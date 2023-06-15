package com.example.project14.Match;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AllMatches extends AppCompatActivity {


    //TODO: CHANGE TOKEN IN AUTH
    String token;
    private ImageView imgArrowLeft;
    private ImageView imgArrowRight;
    private ImageView imgGreenLeft;
    private ImageView imgGreenRight;
    private Button buttonLeft;
    private Button buttonRight;
    private RecyclerView recyclerView;
    private List<Match> matches;
    private MatchAdapter matchAdapter;
    private PopupWindow popupWindow;
    private RadioGroup petsRadioGroup;
    private RadioGroup genderRadioGroup;
    private CheckBox ehboCheckBox;
    private CheckBox bhvCheckBox;
    private CheckBox reanimationCheckBox;
    private RadioGroup seekingPetsRadioGroup;
    private RadioGroup seekingFurnishedRadioGroup;
    private Spinner seekingMonthsSpinner;
    private Spinner seekingDaysSpinner;
    private Spinner seekingSituationSpinner;
    private EditText seekingRoomSpaceEditText;
    private EditText seekingBudgetEditText;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_matches);



        Toolbar toolbar = findViewById(R.id.toolbar);


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
                Intent intent = new Intent(AllMatches.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllMatches.this, OptionsActivity.class);
                startActivity(intent);
            }
        });

        //FILTER
        ImageView filter = findViewById(R.id.filter_icon);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });

        imgArrowLeft = findViewById(R.id.img_arrow_left);
        imgArrowRight = findViewById(R.id.img_arrow_right);
        imgGreenLeft = findViewById(R.id.img_green_left);
        imgGreenRight = findViewById(R.id.img_green_right);
//
//        buttonLeft = findViewById(R.id.scrollButtonL);
//        buttonRight = findViewById(R.id.scrollButtonR);

        recyclerView = findViewById(R.id.recyclerView_matches);

        matches = new ArrayList<>();
        matchAdapter = new MatchAdapter(AllMatches.this, matches);

        recyclerView = findViewById(R.id.recyclerView_matches);
        recyclerView.setAdapter(matchAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

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
               Log.d("USER ROLE", "Huurder");
                fetchMatchingHuurder();

            } else {
                Log.d("USER ROLE", "Verhuurder");
                fetchMatchingVerhuurder();
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Log.d("TOKEN", token);
        token = token.substring(1,token.length()-1);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Check if the RecyclerView can scroll left
                boolean canScrollLeft = recyclerView.canScrollHorizontally(-1);
                // Check if the RecyclerView can scroll right
                boolean canScrollRight = recyclerView.canScrollHorizontally(1);

                int grayColor = Color.parseColor("#5A5A5A");
                int defaultColor = Color.parseColor("#006430");
                imgArrowLeft.setVisibility(canScrollLeft ? View.VISIBLE : View.GONE);
                imgGreenLeft.setVisibility(canScrollLeft ? View.VISIBLE : View.GONE);

                imgArrowRight.setVisibility(canScrollRight ? View.VISIBLE : View.GONE);
                imgGreenRight.setVisibility(canScrollRight ? View.VISIBLE : View.GONE);

//                buttonLeft.setBackgroundColor(canScrollLeft ? defaultColor : grayColor);
//                buttonRight.setBackgroundColor(canScrollRight ? defaultColor : grayColor);
            }
        });

        imgGreenLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll the RecyclerView to the left
                recyclerView.smoothScrollBy(-900, 0);
            }
        });

        imgGreenRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll the RecyclerView to the right
                recyclerView.smoothScrollBy(900, 0);
            }
        });


    }


    private void fetchMatchingHuurder() {
        Toast.makeText(this, "Informatie wordt opgehaald...", Toast.LENGTH_SHORT).show();


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + token);
            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        });

        OkHttpClient httpClient = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<JsonObject> call = apiService.getHuurderMatch();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Match>>() {
                    }.getType();
                    List<Match> matches = gson.fromJson(jsonArray, type);
                    Log.d("TAG", matches.toString());
                    // Clear previous data and add the new data
                    AllMatches.this.matches.clear();
                    AllMatches.this.matches.addAll(matches);


                    matchAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
                } else {
                    Log.d("TAG", "Request not successful: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed");
            }
        });
    }

    private void fetchMatchingVerhuurder() {
        Toast.makeText(this, "Informatie wordt opgehaald...", Toast.LENGTH_SHORT).show();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + token);
            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        });

        OkHttpClient httpClient = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<JsonObject> call = apiService.getVerhuurderMatch();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Match>>() {
                    }.getType();
                    List<Match> matches = gson.fromJson(jsonArray, type);
                    Log.d("TAG", matches.toString());
                    // Clear previous data and add the new data
                    AllMatches.this.matches.clear();
                    AllMatches.this.matches.addAll(matches);


                    matchAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
                } else {
                    Log.d("TAG", "Request not successful: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed");
            }
        });
    }

    private void fetchDataVerhuurder() {
        Toast.makeText(this, "Informatie wordt opgehaald...", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<JsonObject> call = apiService.getVerhuurders();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Match>>() {
                    }.getType();
                    List<Match> matches = gson.fromJson(jsonArray, type);
                    Log.d("TAG", matches.toString());
                    // Clear previous data and add the new data
                    AllMatches.this.matches.clear();
                    AllMatches.this.matches.addAll(matches);


                    matchAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
                } else {
                    Log.d("TAG", "Request not successful");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed");
            }
        });
    }

    private void fetchDataHuurder() {
        // ...
        Toast.makeText(this, "Informatie wordt opgehaald...", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<JsonObject> call = apiService.getItems();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Match>>() {
                    }.getType();
                    List<Match> matches = gson.fromJson(jsonArray, type);
                    Log.d("TAG", matches.toString());
                    // Clear previous data and add the new data
                    AllMatches.this.matches.clear();
                    AllMatches.this.matches.addAll(matches);


                    matchAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
                } else {
                    Log.d("TAG", "Request not successful");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed");
            }
        });
    }

    private String createUrlHuurder() {
        Log.d("CALLED HUURDER FILTER", "CALLED");
        String baseUrl = "";

        int selectedRadioButtonIdPet = petsRadioGroup.getCheckedRadioButtonId();
        boolean petsCheckedYes = (selectedRadioButtonIdPet == R.id.pets_yes); // Replace with the actual ID of the "Yes" RadioButton
        boolean petsCheckedNo = (selectedRadioButtonIdPet == R.id.pets_no);

        baseUrl+="?";
        if (petsCheckedYes) {
            baseUrl += "&ownPet=1";
        } else if (petsCheckedNo) {
            baseUrl += "&ownPet=0";
        } else {
            baseUrl += "";
        }

        String gender = "";

        int selectedRadioButtonIdGender = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonIdGender == R.id.radioButton_Man) {
            baseUrl += "&gender=M";
        } else if (selectedRadioButtonIdGender == R.id.radioButton_Woman) {
            baseUrl += "&gender=F";
        } else if (selectedRadioButtonIdGender == R.id.radioButton_Other) {
            baseUrl += "&gender=O";
        }

        boolean ehboChecked = ehboCheckBox.isChecked();

        if (ehboChecked) {
            baseUrl += "&EHBO=1";
        } else {
            baseUrl += "";
        }

        boolean bhvChecked = bhvCheckBox.isChecked();

        if (bhvChecked) {
            baseUrl += "&BHV=1";
        } else {
            baseUrl += "";
        }

        boolean reanimationChecked = reanimationCheckBox.isChecked();

        if (reanimationChecked) {
            baseUrl += "&Reanimatie=1";
        } else {
            baseUrl += "";
        }

        return baseUrl;

    }

    private String createUrlVerhuurder() {

        String baseUrl = "?";
        int selectedRadioButtonIdPet = seekingPetsRadioGroup.getCheckedRadioButtonId();
        boolean petsCheckedYes = (selectedRadioButtonIdPet == R.id.radioButton_seeking_pets_yes); // Replace with the actual ID of the "Yes" RadioButton
        boolean petsCheckedNo = (selectedRadioButtonIdPet == R.id.radioButton_seeking_pets_no);
        if (petsCheckedYes) {
            baseUrl += "&pet=1";
        } else if (petsCheckedNo) {
            baseUrl += "&pet=0";
        } else {
            baseUrl += "";
        }

        int selectedRadioButtonIdFurnished = seekingFurnishedRadioGroup.getCheckedRadioButtonId();
        boolean furnishedCheckedYes = (selectedRadioButtonIdFurnished == R.id.radioButton_furnished_yes); // Replace with the actual ID of the "Yes" RadioButton
        boolean furnishedCheckedNo = (selectedRadioButtonIdFurnished == R.id.radioButton_furnished_no);
        if (furnishedCheckedYes) {
            baseUrl += "&furniture=1";
        } else if (furnishedCheckedNo) {
            baseUrl += "&furniture=0";
        } else {
            baseUrl += "";
        }

        String selectedMonthString = seekingMonthsSpinner.getSelectedItem().toString();
        Log.d("TAG MONTH STRING", selectedMonthString);


        if (!selectedMonthString.equals("Maak een keuze")) {
            int selectedMonth = -1;

            // Extract the numerical value from the string
            String[] parts = selectedMonthString.split(" "); // Split the string at spaces
            if (parts.length > 0) {
                try {
                    selectedMonth = Integer.parseInt(parts[0]); // Parse the first part as an integer
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // Handle or log the parsing error
                }
            }


            if (selectedMonth != -1) {
                baseUrl += "&period=" + selectedMonth;
            } else {
                baseUrl += "";
            }
        }

        Log.d("TAG BASEURL", baseUrl);

        String selectedDayString = seekingDaysSpinner.getSelectedItem().toString();
        if (!selectedDayString.equals("Maak een keuze")) {

            Log.d("SELCETEDDAYSTRING", selectedDayString);

            int selectedDay = -1;
            String[] partsDays = selectedDayString.split(" "); // Split the string at spaces
            Log.d("TAG PARTS DAYS", partsDays[0]);
            if (partsDays.length > 0) {
                try {
                    selectedDay = Integer.parseInt(partsDays[0]); // Parse the first part as an integer
                    Log.d("PARSED SELECTED", "" + selectedDay);
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // Handle or log the parsing error
                }
            }

            if (selectedDay != -1) {
                baseUrl += "&nights=" + selectedDay;
            } else {
                baseUrl += "";
            }

        }


        String selectedSituation = seekingSituationSpinner.getSelectedItem().toString();

        if(!selectedSituation.equals("Maak een keuze")) {
            if (!selectedSituation.isEmpty()) {
                baseUrl += "&situation=" + selectedSituation;
            } else {
                baseUrl += "";
            }
        }

        int minRoomSpace = -1;
        String minRoomSpaceString = seekingRoomSpaceEditText.getText().toString().trim();
        if (!minRoomSpaceString.isEmpty()) {
            minRoomSpace = Integer.parseInt(minRoomSpaceString);
        } else {
            minRoomSpace = -1;
        }

        if (minRoomSpace != -1) {
            baseUrl += "&roomSize=" + minRoomSpace;
        } else {
            baseUrl += "";
        }

        int maxBudget = -1;
        String maxBudgetString = seekingBudgetEditText.getText().toString().trim();

        if (!maxBudgetString.isEmpty()) {
            maxBudget = Integer.parseInt(maxBudgetString);
        } else {
            maxBudget = -1;
        }
        if (maxBudget != -1) {
            baseUrl += "&price=" + maxBudget;
        } else {
            baseUrl += "";
        }


        return baseUrl;

    }

    private void fetchFilterDataHuurder() {
        // ...
        Toast.makeText(this, "Informatie wordt opgehaald...", Toast.LENGTH_SHORT).show();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + token);
            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        });

        OkHttpClient httpClient = httpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/user/verhuurder/match/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<JsonObject> call = apiService.getFilterHuurder(createUrlHuurder());
        Log.d("TAG HUURDER URL CREATION", createUrlHuurder());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Match>>() {
                    }.getType();
                    List<Match> matches = gson.fromJson(jsonArray, type);
                    Log.d("TAG", matches.toString());
                    // Clear previous data and add the new data
                    AllMatches.this.matches.clear();
                    AllMatches.this.matches.addAll(matches);


                    matchAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
                } else {
                    Log.d("TAG", "Request not successful fetchFilterDataHuurder");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed");
            }
        });
    }


    private void fetchFilterDataVerhuurder() {
        // ...
        Toast.makeText(this, "Informatie wordt opgehaald...", Toast.LENGTH_SHORT).show();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + token);
            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        });

        OkHttpClient httpClient = httpClientBuilder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardy-stream-production.up.railway.app/api/user/huurder/match/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<JsonObject> call = apiService.getFilterVerhuurder(createUrlVerhuurder());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    Log.d("TAG JSON OBJECT", jsonObject.toString());

                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                    Log.d("TAG JSON ARRAY", "" + jsonArray.size());
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Match>>() {
                    }.getType();
                    List<Match> matches = gson.fromJson(jsonArray, type);
                    Log.d("TAG", matches.toString());
                    // Clear previous data and add the new data
                    AllMatches.this.matches.clear();
                    AllMatches.this.matches.addAll(matches);


                    matchAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
                } else {
                    Log.d("TAG", "Request not successful fetchFilterDataVerhuurder");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "Request failed" + t.getMessage());
            }
        });
    }


    private void showPopupWindow(View anchorView) {
        // Inflate the popup layout




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
                Log.d("USER ROLE POPUP", "Huurder");
                View popupView = getLayoutInflater().inflate(R.layout.filter_popup_seeking, null);

                seekingPetsRadioGroup = popupView.findViewById(R.id.radioGroup_seeking_pets);
                seekingFurnishedRadioGroup = popupView.findViewById(R.id.radioGroup_seeking_furnished);
                seekingMonthsSpinner = popupView.findViewById(R.id.spinner_filter_seeking_months);
                seekingDaysSpinner = popupView.findViewById(R.id.spinner_filter_seeking_days);
                seekingSituationSpinner = popupView.findViewById(R.id.spinner_filter_seeking_situation);
                seekingRoomSpaceEditText = popupView.findViewById(R.id.editText_room_space);
                seekingBudgetEditText = popupView.findViewById(R.id.editText_cost);

                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                // Set the background drawable for the popup window
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                // Disable interaction with views outside the popup
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(false);

                // Show the popup window anchored to the filter button
                popupWindow.showAsDropDown(anchorView);

                // Dismiss the popup window when the user clicks a close button
                ImageView closeButton = popupView.findViewById(R.id.filter_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                // Enable interaction with views outside the popup
                popupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                            return true;
                        }
                        return false;
                    }
                });

            } else {
                Log.d("USER ROLE POPUP", "Verhuurder");
                View popupView = getLayoutInflater().inflate(R.layout.filter_popup_provider, null);
                petsRadioGroup = popupView.findViewById(R.id.radioGroup_Pets);
                genderRadioGroup = popupView.findViewById(R.id.radioGroup_Gender);
                ehboCheckBox = popupView.findViewById(R.id.checkBox_EHBO);
                bhvCheckBox = popupView.findViewById(R.id.checkBox_BHV);
                reanimationCheckBox = popupView.findViewById(R.id.checkBox_Reanimation);

                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                // Set the background drawable for the popup window
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                // Disable interaction with views outside the popup
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(false);

                // Show the popup window anchored to the filter button
                popupWindow.showAsDropDown(anchorView);

                // Dismiss the popup window when the user clicks a close button
                ImageView closeButton = popupView.findViewById(R.id.filter_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                // Enable interaction with views outside the popup
                popupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



    }

    public void filterHuurder(View view) {
        fetchFilterDataHuurder();
    }

    public void filterVerhuurder(View view) {
        fetchFilterDataVerhuurder();
    }
}


//SLIDER



