package com.example.friendsup.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.friendsup.model.JwtToken;
import com.example.friendsup.model.RegisteredUserModel;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailConfirmationRepository {
    public RegisteredUserModel getRemotes() {
        return null;
    }

    public RegisteredUserModel getLocals(Context context) {
        return null;
    }

    public void setLocals(Context context, String email) {

    }

    public void getJwtToken(MutableLiveData<JwtToken> jwtTokenMutableLiveData, RegisteredUserModel arg) {
        Call<JwtToken> call = Network.getJSONPalaceHolderAPI().loginUser(arg);

        call.enqueue(new Callback<JwtToken>() {
            @Override
            public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {

                if (response.code() == 200) {
                    String jwt = new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getToken()).replaceAll("^.|.$", "");
                    System.out.println(jwt);
                } else {
                    System.out.println("Что-то пошло по пизде");
                }
            }

            @Override
            public void onFailure(Call<JwtToken> call, Throwable t) {
                System.out.println("Network error: " + t.getMessage());
            }
        });

    }
}
