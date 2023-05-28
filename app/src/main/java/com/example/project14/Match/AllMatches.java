package com.example.project14.Match;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project14.R;

public class AllMatches extends AppCompatActivity {

    private ImageView imgArrowLeft;
    private ImageView imgArrowRight;
    private ImageView imgGreenLeft;
    private ImageView imgGreenRight;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_matches);

        imgArrowLeft = findViewById(R.id.img_arrow_left);
        imgArrowRight = findViewById(R.id.img_arrow_right);
        imgGreenLeft = findViewById(R.id.img_green_left);
        imgGreenRight = findViewById(R.id.img_green_right);
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

                // Show or hide the left arrow based on the scroll position
                imgArrowLeft.setVisibility(canScrollLeft ? View.VISIBLE : View.GONE);
                imgGreenLeft.setVisibility(canScrollLeft ? View.VISIBLE : View.GONE);

                // Show or hide the right arrow based on the scroll position
                imgArrowRight.setVisibility(canScrollRight ? View.VISIBLE : View.GONE);
                imgGreenRight.setVisibility(canScrollRight ? View.VISIBLE : View.GONE);
            }
        });

        imgGreenLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll the RecyclerView to the left
                recyclerView.smoothScrollBy(-500, 0);
            }
        });

        imgGreenRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll the RecyclerView to the right
                recyclerView.smoothScrollBy(500, 0);
            }
        });
    }
}
