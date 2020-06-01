package com.kev.books;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android. widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class FetchBook extends AsyncTask<String, Void, String> {

    private TextView mTitleText;
    private TextView mAuthorText;



    public FetchBook(TextView mTitleText, TextView mAuthorText){
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
    }

    @Override
    public String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray itemsArray = null;
        try {
            itemsArray = jsonObject.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < itemsArray.length(); i++){
            JSONObject book = null;
            try {
                book = itemsArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String title = null;
            String author = null;

            JSONObject volumeInfo = null;
            try {
                volumeInfo = book.getJSONObject("volumeInfo");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                title = volumeInfo.getString("title");
                author = volumeInfo.getString("authors");
            } catch (Exception e){
                e.printStackTrace();
            }

            if (title != null && author !=null) {
                mTitleText.setText(title);
                mAuthorText.setText(author);
                return;
            }

        }

    }
}