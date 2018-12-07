package com.example.yaali.chatroom.Data;

import android.util.Log;

import com.example.yaali.chatroom.Models.ErrorResponse;
import com.example.yaali.chatroom.Models.RoomMessage;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendMessageController {

    ChatRoomAPI.SendRoomMessageCallback sendRoomMessageCallback;

    public SendMessageController(ChatRoomAPI.SendRoomMessageCallback sendRoomMessageCallback) {
        this.sendRoomMessageCallback = sendRoomMessageCallback;
    }

    public void start(String authorization, RoomMessage roomMessage){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatRoomAPI.BASE_URL)
                //convert API to json
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatRoomAPI chatRoomAPI=retrofit.create(ChatRoomAPI.class);
        Call<RoomMessage> call=chatRoomAPI.sendMessageRoom(authorization,roomMessage);
        call.enqueue(new Callback<RoomMessage>() {
            @Override
            public void onResponse(Call<RoomMessage> call, Response<RoomMessage> response) {
                if (response.isSuccessful()){
                    sendRoomMessageCallback.onResponse(true,null,response.body());
                }else {
                    try {
                        String errorBodyJson=response.errorBody().string();
                        Gson gson = new Gson();
                        ErrorResponse errorResponse=gson.fromJson(errorBodyJson,ErrorResponse.class);
                        sendRoomMessageCallback.onResponse(false,errorResponse.getMessage(),null);
                    }catch (IOException e){}
                }
            }

            @Override
            public void onFailure(Call<RoomMessage> call, Throwable t) {
                Log.d("TAG","onResponseNewRoom" + t.getMessage());
                sendRoomMessageCallback.onFailure(t.getMessage());

            }
        });
    }
}
