package com.example.yaali.chatroom.Data;

import android.util.Log;

import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.RoomResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomsController {

    ChatRoomAPI.GetRoomsCallback roomsCallback;

    public RoomsController(ChatRoomAPI.GetRoomsCallback roomsCallback) {
        this.roomsCallback = roomsCallback;
    }

    public void strat(String authorization){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomAPI chatRoomAPI=retrofit.create(ChatRoomAPI.class);
        Call<RoomResponse> call=chatRoomAPI.getRooms(authorization);
        Log.d("Tag","strat " + authorization );
        call.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                Log.d("Tag","onResponse " + response.body() );
                if(response.isSuccessful()){
                    roomsCallback.onResponse(response.body().getRooms());
                }else {}
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {
                Log.d("Tag","onFailure " + t.getMessage() );
                roomsCallback.onFailure(t.getMessage());
            }
        });
    }
}
