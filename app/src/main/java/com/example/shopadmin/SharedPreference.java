package com.example.shopadmin;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference
{
    public static final String APP_NAME="Cake App";
    public static final String Key_Name="User_Name";
    public static final String Key_Email="Email";
    public static final String Key_Pin="PIN";
    public static final String Key_Status="Status";

    public SharedPreference()
    {
        super();
    }

    public void SaveString(Context context,String value,String key)
    {
        SharedPreferences preferences=context.
                getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void SaveInt(Context context,int value, String key)
    {
        SharedPreferences preferences=context.
                getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public String GetString(Context context, String key)
    {
        SharedPreferences preferences=context.
                getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        return preferences.getString(key,null);
    }


    public int GetInt(Context context, String key)
    {
        SharedPreferences preferences=context.
                getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        return preferences.getInt(key,-1);
    }


    public void SaveBoolean(Context context,Boolean value, String key)
    {
        SharedPreferences preferences=context.
                getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean GetBoolean(Context context, String key)
    {
        SharedPreferences preferences=context.
                getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }


}
