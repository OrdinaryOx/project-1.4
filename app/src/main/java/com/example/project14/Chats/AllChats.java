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

                // Get the last visible item position
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                // Check if it's the last item in the list
                if (lastVisibleItemPosition == allChatAdapter.getItemCount() - 1) {
                    arrowImageView.setVisibility(View.GONE);
                    darkBorder.setVisibility(View.GONE);

                } else {
                    arrowImageView.setVisibility(View.VISIBLE);
                    darkBorder.setVisibility(View.VISIBLE);
                }
            }
        });

        darkBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scroll to the bottom of the RecyclerView
                recyclerView.smoothScrollToPosition(allChatAdapter.getItemCount() - 1);
            }
        });
    }
}
