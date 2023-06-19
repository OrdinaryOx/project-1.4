package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ProfileVerhuurder extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;
    String phoneNumber;
    String emailString;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_verhuurder);

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
                Intent intent = new Intent(ProfileVerhuurder.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileVerhuurder.this, OptionsActivity.class);
                startActivity(intent);
            }
        });

        // INTENT
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstname");
        String middleName = intent.getStringExtra("middlename");
        String lastName = intent.getStringExtra("lastname");
        String imageURL = intent.getStringExtra("profileImageURL");
        String age = intent.getStringExtra("age");
        Log.d("AGE IN THIS", "" + age);
        String city = intent.getStringExtra("city");
        String gender = intent.getStringExtra("gender");

        String pet = intent.getStringExtra("pet");
        String petDesc = intent.getStringExtra("petdesc");

        String isVisible = intent.getStringExtra("isVisible");

        String keywords = intent.getStringExtra("keywords");
        String help = intent.getStringExtra("help");
        String motivation = intent.getStringExtra("motivation");
        //   String description = intent.getStringExtra("selfDescription");
        String important = intent.getStringExtra("important");
        String roomSize = intent.getStringExtra("roomSize");
        String situation = intent.getStringExtra("situation");
        String price = intent.getStringExtra("price");


        TextView ageTV = findViewById(R.id.age);
        TextView genderTV = findViewById(R.id.gender);
        TextView priceTV = findViewById(R.id.rentalPrice);
        TextView roomSizeTV = findViewById(R.id.livingSpace);
        TextView situationTV = findViewById(R.id.Woonsituatie);
        TextView petTV = findViewById(R.id.Pets);
        TextView petDescTV = findViewById(R.id.petDesc);
        TextView helpTV = findViewById(R.id.remarks);
        TextView selfDescTV = findViewById(R.id.userDescription);
        TextView keywordsTV = findViewById(R.id.userKeywords);
        TextView userNameTV = findViewById(R.id.username);

        userName = firstName;
        phoneNumber = intent.getStringExtra("phonenumber");
        emailString = intent.getStringExtra("email");


        if (middleName == null) {
            userNameTV.setText(firstName + " " + lastName);
        } else {
            userNameTV.setText(firstName + " " + middleName + " " + lastName);
        }

        ageTV.setText("(" + age + ")");

        String genderString = "";
        if (gender.equals("F") || gender.equals("V")) {
            genderString = "Vrouw";
        } else if (gender.equals("M")) {
            genderString = "Man";
        } else {
            genderString = "Anders";
        }
        genderTV.setText("Geslacht: " + genderString);

        priceTV.setText("Huurprijs: " + price + " p/m");
        roomSizeTV.setText("Kamergrootte: " + roomSize + " m2");
        situationTV.setText("Woonsituatie: " + situation);

        if (pet.equals("1")) {
            pet = "Ja";
        } else {
            pet = "Nee";
        }
        petTV.setText("Huisdieren: " + pet);

        if (petDesc != null) {

            if (petDesc.equals("") || petDesc.equals("null") || petDesc.trim().isEmpty()) {
                petDesc = "";
            } else {
                petDescTV.setText(petDesc);
            }

        }
        if (pet == "Nee") {
            petDescTV.setText("-");
        }


        helpTV.setText("Motivatie: " + motivation);
        selfDescTV.setText(important);
        keywordsTV.setText(keywords);

//        ImageView profileImageIV = findViewById(R.id.ProfilePicture);
//        Glide.with(this)
//                .load(imageURL)
//                .centerCrop()
//                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                .into(profileImageIV);

        // EMAIL & CALL
        ImageView email = findViewById(R.id.imageViewChat);
        ImageView phone = findViewById(R.id.imageViewCall);


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailString});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Mijn Woongenoot");
                intent.putExtra(Intent.EXTRA_TEXT, "Hallo " + userName + ", \n");

                startActivity(intent);


            }
        });

        if (isVisible.equals("1")) {
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ContextCompat.checkSelfPermission(ProfileVerhuurder.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProfileVerhuurder.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
                    } else {
                        makePhoneCall();
                    }
                }
            });
        } else {
            // Disable the OnClickListener for the phone ImageView
            phone.setOnClickListener(null);

            phone.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            // Show a toast to indicate disabled state
            Toast.makeText(ProfileVerhuurder.this, "Deze gebruiker heeft telefoon uitgezet", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle the permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(ProfileVerhuurder.this, "Phone call permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to initiate the phone call
    private void makePhoneCall() {
        String phoneNumber = "0616090398";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}
