package com.example.project14.Match;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project14.R;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Match> matches;

    public MatchAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.item_match,
                parent, false);
        return new MatchViewHolder(mItemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);

        holder.username(match.getName());
        holder.age(match.getAge());
        Glide.with(holder.profileImage)
                .load(match.getImageURL())
                .centerCrop()
                .into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView age;
        public ImageView profileImage;

        public MatchViewHolder(@NonNull View ItemView) {
            super(ItemView);
            this.username = ItemView.findViewById(R.id.match_username);
            this.age = ItemView.findViewById(R.id.match_age);
            this.profileImage = ItemView.findViewById(R.id.match_pfp);
        }

    }
}
