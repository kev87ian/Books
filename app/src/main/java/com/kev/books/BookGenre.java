package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class BookGenre extends AppCompatActivity {
    private TextView mTitleTextView;
    private ListView mListView;
    private String[] Books = new String[] {"The Fault in Our Stars", "The Subtle Art of Not Giving"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_genre);

        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mListView = (ListView)findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Books);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        mTitleTextView.setText("Here are all the titles available related to " + title);
    }
}
