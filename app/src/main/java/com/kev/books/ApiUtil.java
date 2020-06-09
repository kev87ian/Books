package com.kev.books;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {

    public static final String QUERY_PARAMETER_KEY = "q";

    public static final String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes";

    public static final String KEY = "key";
    public static final String API_KEY = "AIzaSyA_isYd-wTcFflQZxeGW0q6wqbEVCTrZX0";
    public static final String DESCRIPTION = "description";
    public static final String TITLE = "intitle:";
    public static final String AUTHOR = "inauthor:";
    public static final String PUBLISHER = "inpublisher:";
    public static final String ISBN = "isbn:";


    private ApiUtil() {
    }


    public static URL buildUrl(String title) {
        Uri fullUrl = Uri.parse(BASE_API_URL).buildUpon().
                appendQueryParameter(QUERY_PARAMETER_KEY, title).
                appendQueryParameter(KEY, API_KEY).build();
        URL url = null;

        try {
            url = new URL(fullUrl.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildUrl(String title, String author, String publisher, String isbn) {
        URL url = null;

        StringBuilder stringBuilder = new StringBuilder();
        if (!title.isEmpty()) stringBuilder.append(TITLE + title + "+");
        if (!author.isEmpty()) stringBuilder.append(AUTHOR + author + "+");
        if (!publisher.isEmpty()) stringBuilder.append(PUBLISHER + publisher + "+");
        if (!isbn.isEmpty()) stringBuilder.append(ISBN + isbn + "+");
        stringBuilder.setLength(stringBuilder.length() - 1);

        String query = stringBuilder.toString();

        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY, query)
                .appendQueryParameter(KEY, API_KEY)
                .build();

        try {
            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            boolean hasData = scanner.hasNext();

            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        } finally {
            connection.disconnect();
        }
    }

    public static ArrayList<Book> getBooksFromJson(String json) {

        final String ID = "id";
        final String TITLE = "title";
        final String SUBTITLE = "subtitle";
        final String AUTHORS = "authors";
        final String PUBLISHER = "publisher";
        final String PUBLISHED_DATE = "publishedDate";
        final String ITEMS = "items";
        final String VOLUME_INFO = "volumeInfo";
        final String IMAGE_LINKS = "imageLinks";
        final String THUMBNAIL = "thumbnail";

        ArrayList<Book> books = new ArrayList<>();
        try {
            JSONObject jsonBooks = new JSONObject(json);
            JSONArray arrayBooks = jsonBooks.getJSONArray(ITEMS);

            int numberOfBooks = arrayBooks.length();

            for (int i = 0; i < numberOfBooks; i++) {
                JSONObject booksJson = arrayBooks.getJSONObject(i);
                JSONObject volumeInfoJson = booksJson.getJSONObject(VOLUME_INFO);

                JSONObject imageJson = null;
                if (volumeInfoJson.has(IMAGE_LINKS)) {
                    imageJson = volumeInfoJson.getJSONObject(IMAGE_LINKS);
                }
                int authorNum;

                try {
                    authorNum = volumeInfoJson.getJSONArray(AUTHORS).length();

                } catch (Exception e) {
                    authorNum = 0;
                    Log.d("Error", e.getMessage());
                }

                String[] authors = new String[authorNum];
                for (int j = 0; j < authorNum; j++) {
                    authors[j] = volumeInfoJson.getJSONArray(AUTHORS).get(j).toString();
                }

                Book book = new Book(
                        booksJson.getString(ID),
                        volumeInfoJson.getString(TITLE),
                        (volumeInfoJson.isNull(SUBTITLE) ? "" : volumeInfoJson.getString(SUBTITLE)),
                        authors,
                        (volumeInfoJson.isNull(PUBLISHER) ? "" : volumeInfoJson.getString(PUBLISHER)),
                        (volumeInfoJson.isNull(PUBLISHED_DATE) ? "" : volumeInfoJson.getString(PUBLISHED_DATE)),
                        (volumeInfoJson.isNull(DESCRIPTION) ? "" : volumeInfoJson.getString(DESCRIPTION)),
                        (imageJson == null ? "" : imageJson.getString(THUMBNAIL))
                );

                books.add(book);
            }

        } catch (JSONException e) {
            Log.d("Error", e.getMessage());
        }

        return books;
    }
}
