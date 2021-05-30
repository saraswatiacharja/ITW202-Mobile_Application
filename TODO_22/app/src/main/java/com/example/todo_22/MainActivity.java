package com.example.todo_22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Bookinput;
    private TextView TitleText;
    private TextView AuthorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bookinput = findViewById(R.id.bookInput);
        TitleText = findViewById(R.id.titleText);
        AuthorText = findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        String queryString = Bookinput.getText().toString();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() !=0) {
            new FetchBook(TitleText, AuthorText).execute(queryString);
            AuthorText.setText("");
            TitleText.setText("Loading.....");
        }
        else {
            if (queryString.length() == 0) {
                AuthorText.setText("");
                TitleText.setText("Please enter a search term");
            } else {
                AuthorText.setText("");
                TitleText.setText("Please check your network connection");
            }
        }
    }
}