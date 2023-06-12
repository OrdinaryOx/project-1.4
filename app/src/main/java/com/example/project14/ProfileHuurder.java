package com.example.project14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class ProfileHuurder extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;
    String phoneNumber;
    String emailString;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_huurder);
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
                Intent intent = new Intent(ProfileHuurder.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHuurder.this, OptionsActivity.class);
                startActivity(intent);
            }
        });

        // INTENT
        Intent intent = getIntent();
        userName = intent.getStringExtra("firstname");
        String middleName = intent.getStringExtra("middlename");
        String lastName = intent.getStringExtra("lastname");
        String age = intent.getStringExtra("age");
        String city = intent.getStringExtra("city");
        String gender = intent.getStringExtra("gender");
        String work = intent.getStringExtra("work");
        String budget = intent.getStringExtra("budget");
        String medical = intent.getStringExtra("medical");
        String workDesc = intent.getStringExtra("workdesc");
        String pet = intent.getStringExtra("pet");
        String petDesc = intent.getStringExtra("petdesc");

        String keywords = intent.getStringExtra("keywords");
        String offer = intent.getStringExtra("offer");
        String description = intent.getStringExtra("description");
        phoneNumber = intent.getStringExtra("phonenumber");

        emailString = intent.getStringExtra("email");


        TextView userNameTV = findViewById(R.id.username);
        if (middleName == null) {
            userNameTV.setText(userName + " " + lastName);
        } else {
            userNameTV.setText(userName + " " + middleName + " " + lastName);
        }

        TextView ageTV = findViewById(R.id.age);
        ageTV.setText("(" + age + ")");

        TextView cityTV = findViewById(R.id.placeOfResidence);
        cityTV.setText("Woonplaats: " + city);


        TextView genderTV = findViewById(R.id.gender);
        String genderString = "";
        if (gender.equals("F") || gender.equals("V")) {
            genderString = "Vrouw";
        } else if (gender.equals("M")) {
            genderString = "Man";
        } else {
            genderString = "Anders";
        }
        genderTV.setText("Geslacht: " + genderString);


        TextView budgetTV = findViewById(R.id.budget);
        budgetTV.setText("Budget: €" + budget + " p/m");


        TextView medicalTV = findViewById(R.id.EHBO);
        if (medical != null) {

            if (medical.equals("") || medical.equals("null")) {
                medical = "Geen medische vaardigheden";
            }
            medicalTV.setText("Vaardigheden: " + medical);
        } else {
            medicalTV.setText("Vaardigheden: Geen medische vaardigheden");
        }


        TextView workTV = findViewById(R.id.work);
        if (work.equals("1")) {
            work = "Ja";
        } else {
            work = "Nee";
        }
        workTV.setText("Werk: " + work);


        TextView petTV = findViewById(R.id.Pets);
        if (pet.equals("1")) {
            pet = "Ja";
        } else {
            pet = "Nee";
        }
        petTV.setText("Huisdieren: " + pet);

        TextView workDescTV = findViewById(R.id.workDesc);
        if (workDesc != null) {

            if (workDesc.equals("") || workDesc.equals("null") || workDesc.trim().isEmpty()) {
                workDescTV.setText("-");
            } else {
                workDescTV.setText(workDesc);
            }
        }


        if (work == "Nee") {
            workDescTV.setText("-");
        }

        TextView petDescTV = findViewById(R.id.petDesc);


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


        TextView descriptionTV = findViewById(R.id.userDescription);
        TextView keywordsTV = findViewById(R.id.userKeywords);
        TextView offerTV = findViewById(R.id.remarks);

        descriptionTV.setText(description);
        keywordsTV.setText(keywords);
        offerTV.setText(offer);


        ImageView profileImageIV = findViewById(R.id.ProfilePicture);
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

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ProfileHuurder.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProfileHuurder.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
                } else {
                    makePhoneCall();
                }
            }
        });
    }

    // Handle the permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(ProfileHuurder.this, "Phone call permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to initiate the phone call
    private void makePhoneCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}
