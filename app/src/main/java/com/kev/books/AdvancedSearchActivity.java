package com.kev.books;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.net.URL;

public class AdvancedSearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        final EditText etTitle = findViewById(R.id.editTitle);
        final EditText etAuthor = findViewById(R.id.editAuthor);
        final EditText etPublisher = findViewById(R.id.editPub);
        final EditText etISBN = findViewById(R.id.editISBN);
        final Button search = findViewById(R.id.btnSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String author = etAuthor.getText().toString().trim();
                String pub = etPublisher.getText().toString().trim();
                String isbn = etISBN.getText().toString().trim();

                if (title.isEmpty() && author.isEmpty() && pub.isEmpty() && isbn.isEmpty()) {
                    Snackbar.make(v, R.string.empty_search_data, Snackbar.LENGTH_LONG).show();
                } else {

                    URL url = ApiUtil.buildUrl(title, author, pub, isbn);
                    String stringUrl = url.toString();

                    //Shared preferences
                    Context context = getApplicationContext();
                    int position = SharedP.getPreferencesInt(context, SharedP.POSITION);
                    if (position == 0 || position == 5) {
                        position = 1;
                    } else {
                        position++;
                    }

                    String key = SharedP.QUERY + String.valueOf(position);
                    String value = title + "," + author + "," + pub + "," + isbn;
                    SharedP.setPreferenceString(context, key, value);
                    SharedP.setPreferenceInt(context, SharedP.POSITION, position);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("QUERY", stringUrl);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));

                break;
        }

        return true;
    }

}