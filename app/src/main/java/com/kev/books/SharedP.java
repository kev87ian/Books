package com.kev.books;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class SharedP {
    private SharedP() {
    }

    public static final String PREF_NAME = "BooksPreferences";
    public static final String POSITION = "position";
    public static final String QUERY = "query";


    public static SharedPreferences getPrefs(Context co) {
        return co.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getPreferencesString(Context context, String key) {
        return getPrefs(context).getString(key, "");
    }

    public static int getPreferencesInt(Context context, String key) {
        return getPrefs(context).getInt(key, 0);
    }

    public static void setPreferenceString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setPreferenceInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static ArrayList<String> getQueryList(Context context) {
        ArrayList<String> queryList = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {

            String query = SharedP.getPrefs(context).getString(QUERY+ String.valueOf(i),"");
            if (!query.isEmpty()) {
                query = query.replace(",", " ");
                queryList.add(query.trim());
            }
        }

        return queryList;
    }
}
