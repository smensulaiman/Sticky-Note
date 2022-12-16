package com.acjl.foodmenu.database;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {

    private static LocalStorage localStorage;
    private static SharedPreferences preferences;

    final String NAME_KEY = "name";
    final String DISTRICT_KEY = "district";

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

    public void setDistrict(String district){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DISTRICT_KEY,district);
        editor.commit();
    }

    public String getDistrict(){
        return preferences.getString(DISTRICT_KEY,null);
    }

}
