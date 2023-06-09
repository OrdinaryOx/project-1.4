package com.example.project14.Seeking;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class SendJsonSeeking extends AsyncTask<Void, Void, String> {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String URL = "https://hardy-stream-production.up.railway.app/api/user/huurder";
    private JSONObject userPreferences;

    public SendJsonSeeking(JSONObject userPreferences) {
        this.userPreferences = userPreferences;
    }

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, userPreferences.toString());
        Log.d("TAG BODY", body.toString());
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.d("RESPONSE BODY", response.body().string());
                return response.body().string();
            } else {
                return null; // Request failed
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Exception occurred
        }
    }

    @Override
    protected void onPostExecute(String response) {
        // Handle the response here
        if (response != null) {
            // Request was successful, use the response data
            try {
                JSONObject jsonResponse = new JSONObject(response);
                // Process the jsonResponse as needed
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing exception
            }
        } else {
            // Request failed or exception occurred
            // Handle the failure case
        }
    }
}
