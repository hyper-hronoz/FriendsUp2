package com.example.friendsup.repository;

import android.content.Context;

import com.example.friendsup.model.JwtToken;
import com.example.friendsup.model.RegisteredUserModel;
import com.example.friendsup.model.UserModel;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationRepository {

    public HashMap<String, String> getRemotes() {
        return null;
    }

    public HashMap<String, String> getLocals(Context context) {
        return null;
    }

    public void setLocals(Context context, HashMap<String, String> arg) {

    }

    public void setRemotes(Context context, HashMap<String, String> arg) {
        System.out.println("Registering user on server...");
        UserModel user = new UserModel();
        user = new Gson().fromJson(arg.get("key"), RegisteredUserModel.class);

        Call<JwtToken> call = Network.getJSONPalaceHolderAPI().registerUser(user);

        call.enqueue(new Callback<JwtToken>() {
            @Override
            public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {
                System.out.println(response);
                System.out.println("Response body: " + response.body());
            }

            @Override
            public void onFailure(Call<JwtToken> call, Throwable t) {
                System.out.println("Networks error: " + t.getMessage());
            }
        });
    }
}
