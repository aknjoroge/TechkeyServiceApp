package com.xstudioo.welcomeapp;

import android.content.Context;
import android.content.SharedPreferences;

public class weblanchermanager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static String PREF_NAME = "weblanchermanager";
    private static String IS_FIRST_TIME = "isFirst";

    public weblanchermanager(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME,0);
        editor = sharedPreferences.edit();
    }

    public void setFirstLunch(boolean isFirst){
        editor.putBoolean(IS_FIRST_TIME,isFirst);
        editor.commit();
    }

    public boolean isFirstTime(){
        return sharedPreferences.getBoolean(IS_FIRST_TIME,true);
    }
}
