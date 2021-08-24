package com.example.friendsup.repository;

import android.content.Context;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.friendsup.R;
import com.example.friendsup.config.NetworkConfig;
import com.example.friendsup.model.JwtToken;
import com.example.friendsup.model.UserModel;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository<T> {
    private static UserData userData;

    public HashMap<String, String> getRemotes() {
        return null;
    }

    public HashMap<String, String> getLocals(Context context) {
        if (userData == null) {
            userData = new UserData();
        }
        return userData.getUserAuthData(context);
    }

    public void setLocals(Context context, HashMap<String, String> map) {
        if (userData == null) {
            userData = new UserData();
        }
        System.out.println("Hello porni" + map.get(R.string.user_email));
        userData.setUserAuthData(context, map);
    }

    public void setRemotes(Context context, HashMap<String, String> map) {
        UserModel user = new UserModel();
        user.setEmail(map.get(String.valueOf(R.string.user_email)));
        user.setPassword(map.get(String.valueOf(R.string.user_password)));
        System.out.println("Repository data: " + user.getEmail());

        Call<JwtToken> call = Network.getJSONPalaceHolderAPI().loginUser(user);

        System.out.println(NetworkConfig.BASE_URL);

        call.enqueue(new Callback<JwtToken>() {
            @Override
            public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {
                Log.d("Login status code is", String.valueOf(response.code()));
                Log.d("Login response body is", String.valueOf(response.body()));

                System.out.println("Message is: " + response.message());
                System.out.println("Response body is: " + response.body());

                if (response.code() == 400) {
                    return;
                }
                if (response.code() == 403) {
                    return;
                }
                if (response.code() == 404) {
                    return;
                }
                String jwt = new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getToken()).replaceAll("^.|.$", "");
                if (jwt == "" || jwt == null) {
                    return;
                }
                System.out.println(jwt);
            }

            @Override
            public void onFailure(Call<JwtToken> call, Throwable t) {
                Log.e("Login error:", t.getMessage());
            }
        });


    }
}
