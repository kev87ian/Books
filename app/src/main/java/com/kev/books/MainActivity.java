package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private Button mFindBooksButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFindBooksButton = (Button)findViewById(R.id.findBooksButton);

        mFindBooksButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookGenre.class);
                startActivity(intent);
                Log.d(TAG, "Malaya");
                Toast.makeText(MainActivity.this,"And so, your journey of literature begins!", Toast.LENGTH_SHORT).show();
            }
        }));

    }
}