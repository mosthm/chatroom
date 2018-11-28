package com.example.yaali.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.RoomsController;
import com.example.yaali.chatroom.Models.Room;

import java.util.List;

public class RoomsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rooms,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoomsController roomsController = new RoomsController(roomsCallback);
        roomsController.strat();
    }
    ChatRoomAPI.GetRoomsCallback roomsCallback = new ChatRoomAPI.GetRoomsCallback() {
        @Override
        public void onResponse(List<Room> roomList) {
            Log.d("Tag","roomList " +roomList.size());
        }

        @Override
        public void onFailure(String cause) {

        }
    };
}
