package com.example.project14.Seeking;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project14.LanguageUtils;
import com.example.project14.Provider.ProviderTwoFragment;
import com.example.project14.Seeking.User_Seeking_Form;
import com.example.project14.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SeekingOneFragment extends Fragment {

    private Spinner spinnerSalutation;
    private EditText editTextFirstName;
    private EditText editTextInfix;
    private EditText editTextLastName;
    private EditText editTextPassword;
    private EditText editTextPasswordAgain;
    private EditText editTextEmail;
    private ImageView imageView;
    private ImageView buttonPass;
    private Button uploadImage;
    private String base64Image = "0";
    private TextView titleTextView;
    private TextView salutationTextView;
    private TextView firstNameTextView;
    private TextView infixTextView;
    private TextView lastNameTextView;
    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView passwordAgainTextView;
    private TextView buttonTextView;

    public SeekingOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeking_one, container, false);


        buttonPass= view.findViewById(R.id.passwordVisibilityButton);
        buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set inputType of password fields to text
                // Set inputType of password fields to text and adjust font
                togglePasswordVisibility(editTextPassword);
                togglePasswordVisibility(editTextPasswordAgain);

            }
        });


        //INIT VIEWS
        spinnerSalutation = view.findViewById(R.id.spinnerSalutation);
        editTextFirstName = view.findViewById(R.id.editTextFirstName);
        editTextInfix = view.findViewById(R.id.editTextInfix);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextPasswordAgain = view.findViewById(R.id.editTextPasswordAgain);
        editTextEmail = view.findViewById(R.id.editTextEmail);

        imageView = view.findViewById(R.id.imageGallery);
        uploadImage = view.findViewById(R.id.buttonUploadPicture);

        titleTextView = view.findViewById(R.id.textView5);
        salutationTextView = view.findViewById(R.id.textView3);
        firstNameTextView = view.findViewById(R.id.textView7);
        infixTextView = view.findViewById(R.id.textView8);
        lastNameTextView = view.findViewById(R.id.textView9);
        emailTextView = view.findViewById(R.id.textViewEmail);
        passwordTextView = view.findViewById(R.id.textView10);
        passwordAgainTextView = view.findViewById(R.id.textView13);
        buttonTextView = view.findViewById(R.id.textView4);

        LanguageUtils.updateLanguage(requireContext());

        String languageCode = LanguageUtils.getLanguagePreference(requireContext());
        String firstNameEdit = "";
        String infixEdit = "";
        String lastNameEdit = "";
        String passwordEdit = "";
        String passwordAgainEdit = "";
        String emailEdit = "";

        String uploadImageText = "";

        String titleText = "";
        String salutationText = "";
        String firstNameText = "";
        String infixText = "";
        String lastNameText = "";
        String emailText = "";
        String passwordText = "";
        String passwordAgainText = "";

        String buttonText = "";

        if (languageCode.equals("nl")) {
            firstNameEdit = getResources().getString(R.string.provider_firstNameHint);
            infixEdit = getResources().getString(R.string.provider_infixHint);
            lastNameEdit = getResources().getString(R.string.provider_lastNameHint);
            passwordEdit = getResources().getString(R.string.provider_passwordHint);
            passwordAgainEdit = getResources().getString(R.string.provider_passwordHint);
            emailEdit = getResources().getString(R.string.provider_emailHint);

            uploadImageText = getResources().getString(R.string.provider_profilePictureBtn);

            titleText = getResources().getString(R.string.provider_title);
            salutationText = getResources().getString(R.string.provider_salutation);
            firstNameText = getResources().getString(R.string.provider_firstName);
            infixText = getResources().getString(R.string.provider_infix);
            lastNameText = getResources().getString(R.string.provider_lastName);
            emailText = getResources().getString(R.string.provider_email);
            passwordText = getResources().getString(R.string.provider_password);
            passwordAgainText = getResources().getString(R.string.provider_passwordAgain);

            buttonText = getResources().getString(R.string.provider_profilePicture);

        } else if (languageCode.equals("en")) {
            firstNameEdit = getResources().getString(R.string.provider_firstNameHint_en);
            infixEdit = getResources().getString(R.string.provider_infixHint_en);
            lastNameEdit = getResources().getString(R.string.provider_lastNameHint_en);
            passwordEdit = getResources().getString(R.string.provider_passwordHint_en);
            passwordAgainEdit = getResources().getString(R.string.provider_passwordHint_en);
            emailEdit = getResources().getString(R.string.provider_emailHint_en);

            uploadImageText = getResources().getString(R.string.provider_profilePictureBtn_en);

            titleText = getResources().getString(R.string.provider_title_en);
            salutationText = getResources().getString(R.string.provider_salutation_en);
            firstNameText = getResources().getString(R.string.provider_firstName_en);
            infixText = getResources().getString(R.string.provider_infix_en);
            lastNameText = getResources().getString(R.string.provider_lastName_en);
            emailText = getResources().getString(R.string.provider_email_en);
            passwordText = getResources().getString(R.string.provider_password_en);
            passwordAgainText = getResources().getString(R.string.provider_passwordAgain_en);

            buttonText = getResources().getString(R.string.provider_profilePicture_en);
        }

        editTextFirstName.setHint(firstNameEdit);
        editTextInfix.setHint(infixEdit);
        editTextLastName.setHint(lastNameEdit);
        editTextPassword.setHint(passwordEdit);
        editTextPasswordAgain.setHint(passwordAgainEdit);
        editTextEmail.setHint(emailEdit);

        uploadImage.setText(uploadImageText);

        titleTextView.setText(titleText);
        salutationTextView.setText(salutationText);
        firstNameTextView.setText(firstNameText);
        infixTextView.setText(infixText);
        lastNameTextView.setText(lastNameText);
        emailTextView.setText(emailText);
        passwordTextView.setText(passwordText);
        passwordAgainTextView.setText(passwordAgainText);

        buttonTextView.setText(buttonText);

        //This checks if hashmap with all the data already contains values for these views. If so it places them there (works for backwards button in userform)
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            if (fragmentDataList.containsKey("Salutation")) {
                String salutation = fragmentDataList.get("Salutation");
                setSpinnerSelection(spinnerSalutation, salutation);
            }
            if (fragmentDataList.containsKey("Email")) {
                String email = fragmentDataList.get("Email");
                editTextEmail.setText(email);
            }
            if (fragmentDataList.containsKey("FirstName")) {
                String firstName = fragmentDataList.get("FirstName");
                editTextFirstName.setText(firstName);
            }
            if (fragmentDataList.containsKey("Infix")) {
                String infix = fragmentDataList.get("Infix");
                editTextInfix.setText(infix);
            }
            if (fragmentDataList.containsKey("LastName")) {
                String lastName = fragmentDataList.get("LastName");
                editTextLastName.setText(lastName);
            }
            if (fragmentDataList.containsKey("Password")) {
                String password = fragmentDataList.get("Password");
                editTextPassword.setText(password);
            }
            if (fragmentDataList.containsKey("PasswordAgain")) {
                String passwordAgain = fragmentDataList.get("PasswordAgain");
                editTextPasswordAgain.setText(passwordAgain);
            }
        }

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPicture();
            }
        });

        return view;
    }

    private void togglePasswordVisibility(EditText passwordEditText) {
        if (passwordEditText.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        // Move the cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.length());
    }

    //Save data to HashMap
    public void saveData() {
        User_Seeking_Form activity = (User_Seeking_Form) getActivity();
        if (activity != null) {
            HashMap<String, String> fragmentDataList = activity.getFragmentDataList();
            fragmentDataList.put("Salutation", getSalutation());
            fragmentDataList.put("FirstName", getFirstName());
            fragmentDataList.put("Infix", getInfix());
            fragmentDataList.put("LastName", getLastName());
            fragmentDataList.put("Email", getEmail());
            fragmentDataList.put("Password", getPassword());
            fragmentDataList.put("ProfileImage", getImage());
            fragmentDataList.put("PasswordAgain", getPasswordAgain());
        }
    }


    //Checks if data is valid
    public boolean isDataValid() {
        return !TextUtils.isEmpty(getSalutation()) &&
                !TextUtils.isEmpty(getFirstName()) &&
                !TextUtils.isEmpty(getEmail()) &&
                !TextUtils.isEmpty(getLastName()) &&
                !TextUtils.isEmpty(getPassword()) &&
                !TextUtils.isEmpty(getPasswordAgain()) &&
                getPassword().equals(getPasswordAgain()) &&
                verifyEmail(getEmail());

    }


    public String getImage() {
        return base64Image;
    }

    private boolean verifyEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);

    }


    public String getSalutation() {
        return spinnerSalutation.getSelectedItem().toString();
    }

    public String getFirstName() {
        return editTextFirstName.getText().toString();
    }

    public String getInfix() {
        return editTextInfix.getText().toString();
    }

    public String getLastName() {
        return editTextLastName.getText().toString();
    }

    private String getEmail() {
        return editTextEmail.getText().toString();
    }

    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    public String getPasswordAgain() {
        return editTextPasswordAgain.getText().toString();
    }


    private void setSpinnerSelection(Spinner spinner, String value) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinner.getAdapter();
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if (adapter.getItem(i).toString().equals(value)) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    //Change border color to red, if it isn't filled in when clicking on next page
    public void highlightUnfilledFields() {
        if (getSalutation().equals("Maak een keuze")) {
            spinnerSalutation.setBackgroundResource(R.drawable.combined_spinner_drawable_red);
        } else {
            spinnerSalutation.setBackgroundResource(R.drawable.combined_spinner_drawable);
        }
        if (TextUtils.isEmpty(getFirstName())) {
            editTextFirstName.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextFirstName.setBackgroundResource(R.drawable.border);
        }
        if (TextUtils.isEmpty(getLastName())) {
            editTextLastName.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextLastName.setBackgroundResource(R.drawable.border);
        }

        if (TextUtils.isEmpty(getEmail())) {
            editTextEmail.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextEmail.setBackgroundResource(R.drawable.border);
        }
//        if (TextUtils.isEmpty((getImage()))) {
//            Drawable borderDrawable = ContextCompat.getDrawable(getContext(), R.drawable.button_red);
//            uploadImage.setBackground(borderDrawable);
//        } else {
//            Drawable borderDrawable = ContextCompat.getDrawable(getContext(), R.drawable.border);
//            uploadImage.setBackground(borderDrawable);
//        }
        if (!arePasswordsMatching()) {
            editTextPassword.setBackgroundResource(R.drawable.border_red);
            editTextPasswordAgain.setBackgroundResource(R.drawable.border_red);
        } else {
            editTextPassword.setBackgroundResource(R.drawable.border);
            editTextPasswordAgain.setBackgroundResource(R.drawable.border);
        }

    }

    public void uploadPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                // Convert the selected image to a Base64 string
                base64Image = convertImageToBase64(imageUri);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String convertImageToBase64(Uri imageUri) throws IOException {
        InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Log.d("TAG", "Image converted");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private Bitmap decodeBase64ToBitmap(String base64Image) {
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public boolean isEmailValid() {
        if (!verifyEmail(getEmail())) {
            Toast.makeText(getContext(), "Email adres klopt niet", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean arePasswordsMatching() {
        if (TextUtils.isEmpty(getPassword()) || TextUtils.isEmpty(getPasswordAgain())) {
            Toast.makeText(getContext(), "Wachtwoorden mogen niet leeg zijn", Toast.LENGTH_SHORT).show();

         return false;
        }
        if (!getPassword().equals(getPasswordAgain())) {
            Toast.makeText(getContext(), "Wachtwoorden komen niet overeen", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
