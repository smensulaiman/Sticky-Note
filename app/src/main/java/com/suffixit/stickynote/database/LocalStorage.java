package com.suffixit.stickynote.database;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {

    private static LocalStorage localStorage;
    private static SharedPreferences preferences;

    final String NAME_KEY = "name";

    public static synchronized LocalStorage getInstance(Context context){

        if(localStorage == null){
            localStorage = new LocalStorage();
            preferences = context.getSharedPreferences("Sticky-Note", Context.MODE_PRIVATE);
        }

        return localStorage;
    }

    public String getName() {
        return preferences.getString(NAME_KEY, "Mr. X");
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME_KEY, name);
        editor.commit();
    }
}
