package com.example.yaali.chatroom.Data;

import android.util.Log;

import com.example.yaali.chatroom.Models.Room;
import com.example.yaali.chatroom.Models.RoomMessageResponse;
import com.example.yaali.chatroom.Models.RoomResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomMessageController {
    ChatRoomAPI.GetRoomMessageCallback getRoomMessageCallback;

    public RoomMessageController(ChatRoomAPI.GetRoomMessageCallback getRoomMessageCallback) {
        this.getRoomMessageCallback = getRoomMessageCallback;
    }

    public void strat(String authorization,Room room){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI=retrofit.create(ChatRoomAPI.class);
        Call<RoomMessageResponse> call=chatRoomAPI.getMessageRoom(authorization,room);
        Log.d("Tag","strat " + authorization );
        call.enqueue(new Callback<RoomMessageResponse>() {
            @Override
            public void onResponse(Call<RoomMessageResponse> call, Response<RoomMessageResponse> response) {
                Log.d("Tag","onResponse " + response.body() );
                if(response.isSuccessful()){
                    getRoomMessageCallback.onResponse(response.body().getMessageRoomList());
                }else {}
            }

            @Override
            public void onFailure(Call<RoomMessageResponse> call, Throwable t) {
                Log.d("Tag","onFailure " + t.getMessage() );
                getRoomMessageCallback.onFailure(t.getMessage());
            }
        });
    }
}
