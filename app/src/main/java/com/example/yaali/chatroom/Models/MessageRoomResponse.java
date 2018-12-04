package com.example.yaali.chatroom.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageRoomResponse {
    @SerializedName("results")
    List<RoomMessage> roomMessageList;

    public MessageRoomResponse() {
    }

    public List<RoomMessage> getRoomMessageList() {
        return roomMessageList;
    }

    public void setRoomMessageList(List<RoomMessage> messageRoomList) {
        this.roomMessageList = messageRoomList;
    }
}
