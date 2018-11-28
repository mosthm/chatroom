package com.example.yaali.chatroom.Models;

import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("_id")
    private String id;
    private String name;

    public Room() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
