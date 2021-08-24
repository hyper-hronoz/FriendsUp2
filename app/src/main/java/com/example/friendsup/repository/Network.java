package com.example.friendsup.repository;

import com.example.friendsup.api.JSONPlaceHolderApi;
import com.example.friendsup.config.NetworkConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Retrofit retrofit;
    private static JSONPlaceHolderApi jsonPlaceHolderApi;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(NetworkConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static JSONPlaceHolderApi getJSONPalaceHolderAPI() {
        if (jsonPlaceHolderApi == null) {
            jsonPlaceHolderApi = getRetrofit().create(JSONPlaceHolderApi.class);
        }
        return jsonPlaceHolderApi;
    }
}
