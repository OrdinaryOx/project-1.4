package com.example.project14.Chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project14.R;

import java.nio.file.Files;

public class AllChats extends AppCompatActivity {

    private ImageView arrowImageView;
    private ImageView darkBorder;
    private RecyclerView recyclerView;
    private AllChatsAdapter allChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chats);

        arrowImageView = findViewById(R.id.img_arrow);
        darkBorder = findViewById(R.id.img_green);

        allChatAdapter = new AllChatsAdapter(this);

        recyclerView = findViewById(R.id.recyclerViewChat);
        recyclerView.setAdapter(allChatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Check if the RecyclerView can scroll vertically
                boolean canScrollVertically = recyclerView.canScrollVertically(1);

                // Show or hide the arrow and dark border based on the vertical scroll position
                arrowImageView.setVisibility(canScrollVertically ? View.VISIBLE : View.GONE);
                darkBorder.setVisibility(canScrollVertically ? View.VISIBLE : View.GONE);
            }
            
        });

        darkBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll to the bottom of the RecyclerView
                recyclerView.smoothScrollBy(0, 800);
            }
        });
    }
}
