package com.example.project14.Match;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.project14.MainActivity;
import com.example.project14.OptionsActivity;
import com.example.project14.R;
import com.example.project14.RoleActivity;

public class AllMatches extends AppCompatActivity {

    private ImageView imgArrowLeft;
    private ImageView imgArrowRight;
    private ImageView imgGreenLeft;
    private ImageView imgGreenRight;

    private Button buttonLeft;
    private Button buttonRight;
    private RecyclerView recyclerView;

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








//        imgArrowLeft = findViewById(R.id.img_arrow_left);
//        imgArrowRight = findViewById(R.id.img_arrow_right);
//        imgGreenLeft = findViewById(R.id.img_green_left);
//        imgGreenRight = findViewById(R.id.img_green_right);

        buttonLeft = findViewById(R.id.scrollButtonL);
        buttonRight = findViewById(R.id.scrollButtonR);

        recyclerView = findViewById(R.id.recyclerView_matches);

        MatchAdapter matchAdapter = new MatchAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_matches);
        recyclerView.setAdapter(matchAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

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


                buttonLeft.setBackgroundColor(canScrollLeft ? defaultColor : grayColor);
                buttonRight.setBackgroundColor(canScrollRight ? defaultColor : grayColor);
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll the RecyclerView to the left
                recyclerView.smoothScrollBy(-900, 0);
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll the RecyclerView to the right
                recyclerView.smoothScrollBy(900, 0);
            }
        });
    }
}
