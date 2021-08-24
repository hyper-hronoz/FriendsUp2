package com.example.friendsup.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.friendsup.R;

import java.util.HashMap;

public class UserData {
    private static String JWT;
    private static SharedPreferences sharedPreferences;

    public void setJWT(Context context, String jwt) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(R.string.jwt_token), jwt);
        editor.commit();
    }

    public String getJWT(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        }
        String jwt = sharedPreferences.getString(String.valueOf(R.string.jwt_token), "");
        return "Bearer: " + jwt;
    }

    public void setUserAuthData(Context context, HashMap<String, String> hashMap) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println("this is time: " + hashMap.get(String.valueOf(R.string.user_email)));
        editor.putString(String.valueOf(R.string.user_email), hashMap.get(String.valueOf(R.string.user_email)));
        editor.putString(String.valueOf(R.string.user_password), hashMap.get(String.valueOf(R.string.user_password)));
        editor.commit();
    }

    public HashMap<String, String> getUserAuthData(Context context) {
        if (sharedPreferences == null) {
             sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(String.valueOf(R.string.user_email), sharedPreferences.getString(String.valueOf(R.string.user_email), ""));
        map.put(String.valueOf(R.string.user_password), sharedPreferences.getString(String.valueOf(R.string.user_password), ""));
        return  map;
    }
}
