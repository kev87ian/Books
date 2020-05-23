package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindBooksButton;
    private EditText mTitleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleEditText= (EditText) findViewById(R.id.titleEditText);
       mFindBooksButton = (Button)findViewById(R.id.findBooksButton);
       mFindBooksButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String title = mTitleEditText.getText().toString();
               Log.d(TAG, title);
               Intent intent = new Intent(MainActivity.this, BookGenre.class);
               startActivity(intent);
           }
       });
    }
}
