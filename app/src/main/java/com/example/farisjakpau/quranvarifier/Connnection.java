package com.example.farisjakpau.quranvarifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static java.security.AccessController.getContext;

/**
 * Created by FarisJakpau on 9/17/2017.
 */

public class Connnection {

    String token = "";
    String name ;



    public String getToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        token = prefs.getString("token","");
        return token;
    }

    public void setToken(String token,Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token",token);
        editor.commit();
        this.token = token;
    }

    public String getUserName(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        token = prefs.getString("token","");
        return token;
    }

    public void setUserName(String token,Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token",token);
        editor.commit();
        this.token = token;
    }
}
