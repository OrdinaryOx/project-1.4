package com.example.project14.Match;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.project14.ProfileHuurder;
import com.example.project14.ProfileVerhuurder;
import com.example.project14.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {
    //TODO: Verhuurder
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Match> matches;
    private ImageView test;

    public MatchAdapter(Context context, List<Match> matches) {
        this.layoutInflater = LayoutInflater.from(context);
        this.matches = matches;
        this.context = context;
    }


    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.item_match,
                parent, false);

        test = parent.findViewById(R.id.testImage);

        return new MatchViewHolder(mItemView);


    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);

        Log.d("TAG", position + " " + match.toString());
        int age = calculateAge(match.getDateOfBirth());

        holder.username.setText(match.getFirstName());
        holder.age.setText(" (" + age + ")");
        holder.city.setText(match.getCity());
        holder.match.setText(" " + match.getMatchingScore() + "%");


//        byte[] imageData = match.getPicture().getData();
     //   String base64Image = Base64.encodeToString(imageData, Base64.DEFAULT);


//        Glide.with(holder.profileImage)
//                .load(decodeBase64ToBitmap(base64Image))
//                .centerCrop()
//                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                .into(holder.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the InsideChatActivity with the appropriate data

                String role = match.getRole();
                if (role != null) {
                    if (role.equals("Huurder")) {
                        Intent intent = new Intent(context, ProfileHuurder.class);
                        String firstName = match.getFirstName();
                        if (firstName != null) {
                            intent.putExtra("firstname", firstName);
                            intent.putExtra("middlename", match.getMiddleName());
                            intent.putExtra("lastname", match.getLastName());
                        }
                        intent.putExtra("age", "" + age);
                        String gender = match.getGender();
                        if (gender != null) {
                            intent.putExtra("gender", gender);
                        }
                        String city = match.getCity();
                        if (city != null) {
                            intent.putExtra("city", city);
                        }
                        String budget = match.getBudget();
                        if (budget != null) {
                            intent.putExtra("budget", budget);
                        }
                        String skill = match.getSkill();
                        if (skill != null) {
                            intent.putExtra("medical", "" + skill);
                        }
                        int work = match.getWork();
                        if (work != -1) {
                            intent.putExtra("work", "" + work);
                            intent.putExtra("workdesc", match.getWorkDescription());

                        }
                        String pet = match.getOwnPet();
                        if (pet != null) {
                            intent.putExtra("pet", pet);
                            intent.putExtra("petdesc", match.getOwnPetDescription());
                        }

                        intent.putExtra("offer", match.getOffer());
                        intent.putExtra("keywords", match.getSelfWords());
                        intent.putExtra("description", match.getSelfDescription());
                        intent.putExtra("phonenumber", "" + match.getPhoneNumber());
                        intent.putExtra("email", match.getEmailAddress());

                        intent.putExtra("reden", match.getReason());
                        intent.putExtra("ideaal", match.getIdealSpace());

                        context.startActivity(intent);
                    }
                    if (role.equals("Verhuurder")) {
                        Intent intent = new Intent(context, ProfileVerhuurder.class);
                        String firstName = match.getFirstName();
                        if (firstName != null) {
                            intent.putExtra("firstname", firstName);
                            intent.putExtra("middlename", match.getMiddleName());
                            intent.putExtra("lastname", match.getLastName());
                        }
                        intent.putExtra("age", "" + age);

                        intent.putExtra("furniture", match.getFurniture());
                        intent.putExtra("furnitureDesc", match.getFurnitureDescription());



                        String gender = match.getGender();
                        if (gender != null) {
                            intent.putExtra("gender", gender);
                        }
                        String city = match.getCity();
                        if (city != null) {
                            intent.putExtra("city", city);
                        }


                        intent.putExtra("price", "" + match.getPrice());
                        intent.putExtra("roomSize", "" + match.getRoomSize());
                        intent.putExtra("situation", match.getSituation());
                        intent.putExtra("pet", "" + match.getPet());

                        intent.putExtra("petDesc", match.getPetDescription());
                        intent.putExtra("help", match.getOffer());
                        intent.putExtra("motivation", match.getMotivation());
                        intent.putExtra("important", match.getImportantNote());
                        intent.putExtra("selfDescription", match.getSelfDescription());
                        intent.putExtra("keywords", match.getDescribe());
intent.putExtra("work", "" + match.getWork());
intent.putExtra("workDesc", match.getWorkDescription());

                        intent.putExtra("phonenumber", "" + match.getPhoneNumber());
                        intent.putExtra("email", match.getEmailAddress());

                        context.startActivity(intent);
                    }
                }


            }
        });
    }

    private Bitmap decodeBase64ToBitmap(String base64Image) {
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }


    private static int calculateAge(String dateOfBirth) {
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

    @Override
    public int getItemCount() {
        return matches.size();
    }


    private List<Match> createDummyData() {
        List<Match> dummyData = new ArrayList<>();

        // Add more dummy data as needed

        return dummyData;
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView age;
        public ImageView profileImage;

        public TextView city;
        public TextView match;

        public MatchViewHolder(@NonNull View ItemView) {
            super(ItemView);
            this.username = ItemView.findViewById(R.id.match_username);
            this.age = ItemView.findViewById(R.id.match_age);
            this.city = ItemView.findViewById(R.id.match_city);
            this.profileImage = ItemView.findViewById(R.id.match_pfp);
            this.match = ItemView.findViewById(R.id.match_percentage);
        }

    }
}
