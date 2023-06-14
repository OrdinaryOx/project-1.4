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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AllMatches extends AppCompatActivity {


    //TODO: CHANGE TOKEN IN AUTH
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_matches);


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
        //


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

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
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
               Log.d("USER ROLE", "Huurder");
                fetchMatchingHuurder();

            } else {
                Log.d("USER ROLE", "Verhuurder");
                fetchMatchingVerhuurder();
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

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
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MzUsInJvbGUiOiJIdXVyZGVyIiwiaWF0IjoxNjg2NjUxNjE5fQ.JsliAy0LXHVgku2qbGEkR_TacwDSDZTjEjrmxwkqQDg");
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
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MzUsInJvbGUiOiJIdXVyZGVyIiwiaWF0IjoxNjg2NjUxNjE5fQ.JsliAy0LXHVgku2qbGEkR_TacwDSDZTjEjrmxwkqQDg");
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


    private void showPopupWindow(View anchorView) {
        // Inflate the popup layout
        View popupView = getLayoutInflater().inflate(R.layout.filter_popup_provider, null);

        // Create the popup window
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
}


//SLIDER



