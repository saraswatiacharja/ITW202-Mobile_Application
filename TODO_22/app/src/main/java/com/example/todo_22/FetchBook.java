package com.example.todo_22;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> TitleText;
    private WeakReference<TextView> AuthorText;

    FetchBook(TextView titleText, TextView authorText) {
        this.TitleText = new WeakReference<>(titleText);
        this.AuthorText = new WeakReference<>(authorText);
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            //parse the data using json object and jsonArrays
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String title = null;
            String authors = null;
            while (i < itemsArray.length() &&
                    authors == null && title == null) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            i++;

            if (title != null && authors != null) {
                TitleText.get().setText(title);
                AuthorText.get().setText(authors);
            } else {
                TitleText.get().setText("No Result Found");
                AuthorText.get().setText("");
            }

        } catch (JSONException e) {
            TitleText.get().setText("No Result Found");
            AuthorText.get().setText("");
            e.printStackTrace();
        }
    }


    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }
}
