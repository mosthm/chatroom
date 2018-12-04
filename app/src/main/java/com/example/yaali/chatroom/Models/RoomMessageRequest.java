package com.example.yaali.chatroom.Models;

public class RoomMessageRequest {
    private String roomId;

    public RoomMessageRequest(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
