package com.example.yaali.chatroom.Data;

import com.example.yaali.chatroom.Models.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUserController {
    ChatRoomAPI.LoginUserCallback loginUserCallback;

    public LoginUserController(ChatRoomAPI.LoginUserCallback loginUserCallback) {
        this.loginUserCallback = loginUserCallback;
    }
    public void start(String username,String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatRoomAPI chatRoomAPI=retrofit.create(ChatRoomAPI.class);
        Call<TokenResponse> call=chatRoomAPI.loginUser(username,password);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()){
                    loginUserCallback.onResponse(true,null,response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });

    }
}
