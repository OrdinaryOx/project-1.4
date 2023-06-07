package com.example.project14.Chats;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project14.R;

import java.util.ArrayList;
import java.util.List;

public class AllChatsAdapter extends RecyclerView.Adapter<AllChatsAdapter.ChatViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Chat> chats;
    private Context context;

    public AllChatsAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.chats = createDummyData(); // Create dummy data
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.item_all_chats, parent, false);
        return new ChatViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chats.get(position);

        holder.chatUsername.setText(chat.getUsername());
        holder.chatTest.setText(chat.getTest());
        holder.chatAge.setText(String.valueOf(chat.getAge()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the InsideChatActivity with the appropriate data
                Intent intent = new Intent(context, InsideChat.class);
                intent.putExtra("username", chat.getUsername());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chats.size(); // Return the size of the dummy data list
    }

    private List<Chat> createDummyData() {
        List<Chat> dummyData = new ArrayList<>();

        // Create and add dummy Chat objects to the list
        dummyData.add(new Chat("David", 86, "Hello, I saw that you were interested in chess, I am too!"));
        dummyData.add(new Chat("Jenny", 79, "Hi there! I'm a nurse myself, maybe I can give you some pointers :)"));
        dummyData.add(new Chat("Mikey", 92, "I'm Mikey"));
        // Add more dummy data as needed

        return dummyData;
    }




    class ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView chatUsername;
        public TextView chatAge;
        public TextView chatTest;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.chatAge = itemView.findViewById(R.id.chat_age);
            this.chatUsername = itemView.findViewById(R.id.chat_username);
            this.chatTest = itemView.findViewById(R.id.chat_test);
        }
    }
}
