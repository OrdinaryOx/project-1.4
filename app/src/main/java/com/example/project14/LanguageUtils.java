package com.example.project14;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageUtils {

    private static final String LANGUAGE_PREFERENCE_KEY = "language_preference";
    private static final String DEFAULT_LANGUAGE_CODE = "nl";

    public static void setLanguagePreference(Context context, String languageCode) {
        SharedPreferences preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LANGUAGE_PREFERENCE_KEY, languageCode);
        editor.apply();
    }

    public static String getLanguagePreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        return preferences.getString(LANGUAGE_PREFERENCE_KEY, DEFAULT_LANGUAGE_CODE);
    }

    public static void updateLanguage(Context context) {
        String languageCode = getLanguagePreference(context);
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }


//    private static Locale getPreferredLocale() {
//        // Check if Dutch is the preferred language
//        if (Locale.getDefault().getLanguage().equals(DUTCH_LANGUAGE_CODE)) {
//            return new Locale(DUTCH_LANGUAGE_CODE); // Return Dutch locale
//        } else {
//            // Fallback to English
//            return new Locale(ENGLISH_LANGUAGE_CODE);
//        }
//    }


//    private static void setLocale(Context context, Locale locale) {
//        Resources resources = context.getResources();
//        Configuration configuration = resources.getConfiguration();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//
//        configuration.setLocale(locale);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            context.getApplicationContext().createConfigurationContext(configuration);
//        } else {
//            resources.updateConfiguration(configuration, displayMetrics);
//        }
//    }
}
