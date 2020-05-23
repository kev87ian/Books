package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BookGenre extends AppCompatActivity {
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_genre);

        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        mTitleTextView.setText("These are all the books with " + title + " " + "in their titles");

    }
}
