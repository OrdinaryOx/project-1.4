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


    //TODO: THIS
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;
    String phoneNumber;
    String emailString;
    String userName;
    private String phoneNumberVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_verhuurder);

        Toolbar toolbar = findViewById(R.id.toolbar);


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
        String petDesc = intent.getStringExtra("petDesc");

        String isVisible = intent.getStringExtra("isVisible");

        String keywords = intent.getStringExtra("keywords");
        String help = intent.getStringExtra("help");
        String motivation = intent.getStringExtra("motivation");
        //   String description = intent.getStringExtra("selfDescription");

        String phoneNumberVisible = intent.getStringExtra("phoneNumberVisible");
        String important = intent.getStringExtra("important");
        String roomSize = intent.getStringExtra("roomSize");
        String situation = intent.getStringExtra("situation");
        String price = intent.getStringExtra("price");

        String furniture = intent.getStringExtra("furniture");
        String furnitureDesc = intent.getStringExtra("furnitureDesc");

        String work = intent.getStringExtra("work");
        String workDesc = intent.getStringExtra("workDesc");

        TextView ageTV = findViewById(R.id.age);
        TextView cityTV = findViewById(R.id.placeOfResidenceDesc);
        TextView genderTV = findViewById(R.id.gender);
        TextView priceTV = findViewById(R.id.rentalPriceDesc);
        TextView roomSizeTV = findViewById(R.id.livingSpaceDesc);
        TextView situationTV = findViewById(R.id.WoonsituatieDesc);
        TextView petTV = findViewById(R.id.Pets);
        TextView petDescTV = findViewById(R.id.petDesc);
        TextView motivationTV = findViewById(R.id.remarks);
        TextView importantTV = findViewById(R.id.userDescription);
        TextView keywordsTV = findViewById(R.id.userKeywords);
        TextView userNameTV = findViewById(R.id.username);
        TextView furnitureTV = findViewById(R.id.meubilairDesc);
        TextView furnitureTextTV = findViewById(R.id.meubilairDescDesc);
        TextView workDescTV = findViewById(R.id.workDesc);
        ImageView profilePictureIM = findViewById(R.id.ProfilePicture);

        userName = firstName;
        phoneNumber = intent.getStringExtra("phonenumber");
        emailString = intent.getStringExtra("email");

        if (work.equals("1")) {
            work = "Ja";
        } else {
            work = "Nee";
        }

        if (workDesc != null) {
            if (workDesc.equals("") || workDesc.equals("null") || workDesc.trim().isEmpty()) {
                workDesc = "";
            } else {
                workDesc = " - " + workDesc;
            }

        } else {
            workDesc = "";
        }
        workDescTV.setText(work + workDesc);


        cityTV.setText(city);

        if (furniture.equals("1")) {
            furniture = "Ja";
        } else {
            furniture = "Nee";
        }

        if (furniture.equals("Nee")) {
            furnitureTextTV.setVisibility(View.GONE);
        }
        furnitureTV.setText(furniture);
        furnitureTextTV.setText(furnitureDesc);

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
        genderTV.setText("- " + genderString);

        priceTV.setText("€" + price + " p/m");
        roomSizeTV.setText(roomSize + " m2");
        situationTV.setText(situation);

        if (pet.equals("1")) {
            pet = "Ja";
        } else {
            pet = "Nee";
        }

        if (petDesc != null) {

            if (petDesc.equals("") || petDesc.equals("null") || petDesc.trim().isEmpty()) {
                petDesc = "";
            } else {
                petDesc = " - " + petDesc;
            }

        }
        petDescTV.setText(pet + petDesc);


        if (pet == "Nee") {

            petDescTV.setText("Nee");
        }


        motivationTV.setText(motivation);
        importantTV.setText(important);
        keywordsTV.setText(keywords);


        String picture = intent.getStringExtra("picture");

        Glide.with(profilePictureIM)
                .load(picture)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(profilePictureIM);


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

        if (phoneNumberVisible.equals("1")) {
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
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ProfileVerhuurder.this, "Deze gebruiker heeft telefoon uitgezet", Toast.LENGTH_SHORT).show();

                }
            });
            // Disable the OnClickListener for the phone ImageView
//            phone.setOnClickListener(null);
            phone.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            // Show a toast to indicate disabled state

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
