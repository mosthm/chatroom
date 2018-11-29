package com.example.yaali.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.RoomsController;
import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomsFragment extends Fragment {
    private RecyclerView rooms;
    private List<Room> roomList=new ArrayList<>();
    private RoomAdapter roomAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rooms,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
        initRoomList();
        progressBar.setVisibility(View.VISIBLE);
        RoomsController roomsController = new RoomsController(roomsCallback);
        roomsController.strat(
                "bearer "+MypreferenceManager.getInstance(getActivity()).getAccessToken()
        );
    }
    private void initRoomList(){
        roomAdapter=new RoomAdapter(roomList);
        rooms.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rooms.setAdapter(roomAdapter);
    }
    public void findview(View view){
        rooms=view.findViewById(R.id.rooms);
        progressBar=view.findViewById(R.id.progress_bar);
    }
    ChatRoomAPI.GetRoomsCallback roomsCallback = new ChatRoomAPI.GetRoomsCallback() {
        @Override
        public void onResponse(List<Room> inputList) {

            Log.d("Tag","roomList " +roomList.size());
            if(progressBar.isShown()){
                progressBar.setVisibility(View.INVISIBLE);
            }
            roomList.clear();
            roomList.addAll(inputList);
            roomAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(String cause) {

        }
    };
}
