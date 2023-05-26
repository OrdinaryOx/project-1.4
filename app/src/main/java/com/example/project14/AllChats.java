package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AllChats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chats);

       AllChatsAdapter allChatAdapter = new AllChatsAdapter(this);

       RecyclerView recyclerView = findViewById(R.id.recyclerViewChat);
        recyclerView.setAdapter(allChatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

