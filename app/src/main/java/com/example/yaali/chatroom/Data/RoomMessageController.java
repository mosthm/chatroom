package com.example.yaali.chatroom.Data;

import android.util.Log;

import com.example.yaali.chatroom.Models.ErrorResponse;
import com.example.yaali.chatroom.Models.Room;
import com.example.yaali.chatroom.Models.RoomMessage;
import com.example.yaali.chatroom.Models.RoomMessageResponse;
import com.example.yaali.chatroom.Models.RoomResponse;
import com.google.gson.Gson;

import java.io.IOException;

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

    //I will user send to server & I do enter user
    public void strat(String authorization,RoomMessage roomMessage){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                //convert API to json
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatRoomAPI chatRoomAPI=retrofit.create(ChatRoomAPI.class);
        Call<RoomMessageResponse> call=chatRoomAPI.getMessageRoom(authorization,roomMessage);
        Log.d("Tag","strat " + authorization );
        //call API User
        call.enqueue(new Callback<RoomMessageResponse>() {
            @Override
            public void onResponse(Call<RoomMessageResponse> call, Response<RoomMessageResponse> response) {
                Log.d("Tag","onResponse12 " + response.body() );
                if(response.isSuccessful()){
                    getRoomMessageCallback.onResponse(response.body().getMessageRoomList());
                }else {
                    try {
                    String errorBodyJson=response.errorBody().string();
                    Gson gson = new Gson();
                    ErrorResponse errorResponse=gson.fromJson(errorBodyJson,ErrorResponse.class);
                    getRoomMessageCallback.onResponse(response.body().getMessageRoomList());
                }catch (IOException e){}
                }
            }

            @Override
            public void onFailure(Call<RoomMessageResponse> call, Throwable t) {
                Log.d("Tag","onFailure " + t.getMessage() );
                getRoomMessageCallback.onFailure(t.getMessage());
            }
        });
    }
}
