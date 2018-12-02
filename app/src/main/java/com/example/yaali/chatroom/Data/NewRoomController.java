package com.example.yaali.chatroom.Data;

import android.util.Log;

import com.example.yaali.chatroom.Models.ErrorResponse;
import com.example.yaali.chatroom.Models.Room;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewRoomController {
    ChatRoomAPI.NewRoomCallback newRoomCallback;

    public NewRoomController(ChatRoomAPI.NewRoomCallback newRoomCallback) {
        this.newRoomCallback = newRoomCallback;
    }
    public void start(String authorization, Room room){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                //convert API to json
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatRoomAPI chatRoomAPI=retrofit.create(ChatRoomAPI.class);
        Call<Room> call=chatRoomAPI.newRoom(authorization,room);
        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()){
                    newRoomCallback.onResponse(true,null,response.body());
                }else {
                    try {
                        String errorBodyJson=response.errorBody().string();
                        Gson gson = new Gson();
                        ErrorResponse errorResponse=gson.fromJson(errorBodyJson,ErrorResponse.class);
                        newRoomCallback.onResponse(false,errorResponse.getMessage(),null);
                    }catch (IOException e){}
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Log.d("TAG","onResponseNewRoom" + t.getMessage());
                newRoomCallback.onFailure(t.getMessage());

            }
        });
    }
}
