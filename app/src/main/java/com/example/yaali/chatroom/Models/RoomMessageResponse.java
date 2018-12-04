package com.example.yaali.chatroom.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomMessageResponse {
    @SerializedName("results")
    List<RoomMessage> roomMessages;

    public RoomMessageResponse() {
    }

    public List<RoomMessage> getMessageRoomList() {
        return roomMessages;
    }

    public void setMessageRoomList(List<RoomMessage> roomMessages) {
        this.roomMessages = roomMessages;
    }
}
