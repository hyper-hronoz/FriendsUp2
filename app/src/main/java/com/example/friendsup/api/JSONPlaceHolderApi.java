package com.example.friendsup.api;

import com.example.friendsup.model.JwtToken;
import com.example.friendsup.model.RegisteredUserModel;
import com.example.friendsup.model.UserModel;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface JSONPlaceHolderApi {
    @POST("/auth/registration")
    Call<JwtToken> registerUser(@Body UserModel userModel);

    @POST("/auth/login")
    Call<JwtToken> loginUser(@Body UserModel userModel);
    //
    @GET("/user-data/user")
    Call<RegisteredUserModel> getCurrentUserData(@Header("Authorization") String token);

    @PUT("/user-data/user")
    Call<RegisteredUserModel> updateCurrentUserData(@Header("Authorization") String token, @Body RegisteredUserModel registeredUserModel);
    @GET("/find/user")
    Call<RegisteredUserModel> findUser(@Header("Authorization") String token);

    @POST("/messages/room")
    Call<String> createChatRoom(@Header("Authorization") String token, @Body RegisteredUserModel registeredUserModel);

    @Multipart
    @POST("/user-data/upload-image")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part part, @Header("Authorization") String token);

//    @GET("/messages/rooms")
//    Call<List<Chat>> getUsersChatRooms(@Header("Authorization") String token);
    //
//    @POST("/auth/upload")
//    Call<UploadImage> postImage(@Header("Authorization") String token, @Body UploadImage uploadImage);
//
//
//    @POST("/action/like")
//    Call<RegisteredUserModel> likeUser(@Header("Authorization") String token, @Body RegisteredUserModel registeredUser);
//
//    @GET("/action/liked")
//    Call<RegisteredUsers> getLikedUsers(@Header("Authorization") String token);
//

//    @POST("/auth/confirm")
//    Call<UserModel> confirmEmail(UserModel user);

}
