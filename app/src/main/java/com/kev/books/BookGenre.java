package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookGenre extends AppCompatActivity {
   @BindView(R.id.titleTextView) TextView mTitleTextView;
   @BindView(R.id.listView) ListView mListView;
    private String[] Books = new String[] {"The Fault in Our Stars", "The Subtle Art of Not Giving a F*#", "The Sudden Appearance of Hope"};
    @BindView(R.id.contactUsButton) Button contactUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_genre);
        Intent intent = new Intent(BookGenre.this, ContactUs.class);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Books);
        mListView.setAdapter(adapter);

        intent= getIntent();
        String title = intent.getStringExtra("title");
        mTitleTextView.setText("Here are all the titles available related to " + title);


        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactUs();
            }
        });
    }

    public void openContactUs(){
        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }
}
