package com.example.yaali.chatroom.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomMessageResponse {
    public RoomMessageResponse() {
    }

    @SerializedName("results")
    List<MessageRoom> messageRoomList;

    public List<MessageRoom> getMessageRoomList() {
        return messageRoomList;
    }

    public void setMessageRoomList(List<MessageRoom> messageRoomList) {
        this.messageRoomList = messageRoomList;
    }
}
