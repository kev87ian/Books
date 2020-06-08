package com.kev.books;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ProgressBar mProgress;
    private RecyclerView mBooksView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(this);

        ArrayList<String> recentList = SharedP.getQueryList(getApplicationContext());
        int itemNum = recentList.size();

        MenuItem recentMenu;

        for (int i = 0; i < itemNum; i++) {
            recentMenu = menu.add(Menu.NONE, i, Menu.NONE, recentList.get(i));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_advanced_search:
                Intent intent = new Intent(this, AdvancedSearchActivity.class);
                startActivity(intent);
                return true;
            default:

                int position = item.getItemId() + 1;
                String preferenceName = SharedP.QUERY + String.valueOf(position);
                String query = SharedP.getPreferencesString(getApplicationContext(), preferenceName);
                String[] prefParams = query.split("\\,");
                String[] queryParams = new String[4];

                for (int i = 0; i < prefParams.length; i++) {
                    queryParams[i] = prefParams[i];
                }

                URL bookUrl = ApiUtil.buildUrl(
                        (queryParams[0] == null ? "" : queryParams[0]),
                        (queryParams[1] == null ? "" : queryParams[1]),
                        (queryParams[2] == null ? "" : queryParams[2]),
                        (queryParams[3] == null ? "" : queryParams[3]));

                new BooksQueryTask().execute(bookUrl);

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        try {
            URL bookUrl = ApiUtil.buildUrl(query);
            new BooksQueryTask().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = findViewById(R.id.progressBar);
        mBooksView = findViewById(R.id.mRecyclerView);

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this);

        mBooksView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String url = intent.getStringExtra("QUERY");
        URL bookUrl;

        try {
            if (url == null || url.isEmpty()) {
                bookUrl = ApiUtil.buildUrl("flower");
            } else {
                bookUrl = new URL(url);
            }
            new BooksQueryTask().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(searchUrl);
            } catch (IOException e) {
                Log.d("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView mErrorText = findViewById(R.id.tvError);
            mProgress.setVisibility(View.INVISIBLE);

            if (result == null) {
                mBooksView.setVisibility(View.INVISIBLE);
                mErrorText.setVisibility(View.VISIBLE);
            } else {
                mErrorText.setVisibility(View.INVISIBLE);
                mBooksView.setVisibility(View.VISIBLE);

                ArrayList<Book> mBooks = ApiUtil.getBooksFromJson(result);
                BooksAdapter booksAdapter = new BooksAdapter(mBooks);
                mBooksView.setAdapter(booksAdapter);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgress.setVisibility(View.VISIBLE);
            mBooksView.setVisibility(View.INVISIBLE);
        }

    }
}
