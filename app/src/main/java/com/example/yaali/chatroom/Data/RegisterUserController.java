package com.example.yaali.chatroom.Data;

import android.util.Log;

import com.example.yaali.chatroom.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUserController {

    //
    ChatRoomAPI.RegisterUserCallback registerUserCallback;

    public RegisterUserController(ChatRoomAPI.RegisterUserCallback registerUserCallback) {
        this.registerUserCallback = registerUserCallback;
    }

    //I will user send to server & I do enter user
    public void start(User user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI=retrofit.create(ChatRoomAPI.class);
        Call<User> call=chatRoomAPI.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse" + response.code());
                if (response.isSuccessful()){
                    registerUserCallback.onResponse(response.body());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG","onResponse" + t.getCause());
                registerUserCallback.onFailure(t.getCause().getMessage());
            }
        });
    }
}
