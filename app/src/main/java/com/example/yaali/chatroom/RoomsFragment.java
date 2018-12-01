package com.example.yaali.chatroom;

import android.os.AsyncTask;
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
import android.widget.TextView;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.RoomsController;
import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoomsFragment extends Fragment {
    private RecyclerView rooms;
    private List<Room> roomList=new ArrayList<>();
    private RoomAdapter roomAdapter;
    private ProgressBar progressBar;
    private TextView progressUpdate;

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
        //progressBar.setVisibility(View.VISIBLE);
        RoomsController roomsController = new RoomsController(roomsCallback);
        roomsController.strat(
                "bearer "+MypreferenceManager.getInstance(getActivity()).getAccessToken()
        );
        Log.d("Tag","roomList " +roomList.size());
    }
    private void initRoomList(){
        roomAdapter=new RoomAdapter(roomList);
        rooms.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rooms.setAdapter(roomAdapter);
    }
    public void findview(View view){
        rooms=view.findViewById(R.id.rooms);
        progressBar=view.findViewById(R.id.progress_bar);
        progressUpdate=view.findViewById(R.id.progressUpdate);
    }
    ChatRoomAPI.GetRoomsCallback roomsCallback = new ChatRoomAPI.GetRoomsCallback() {
        @Override
        public void onResponse(List<Room> inputList) {

           Log.d("Tag","roomList " +roomList.size());

            new SortRoomsAsync(inputList).execute();
//            if(progressBar.isShown()){
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//            roomList.clear();
//
//            Collections.sort(inputList, new Comparator<Room>() {
//                @Override
//                public int compare(Room x, Room y) {
//                    return x.getName().compareTo(y.getName());
//                }
//            });
//
//            roomList.addAll(inputList);
//            roomAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(String cause) {

        }
    };
    private class SortRoomsAsync extends AsyncTask<Void ,Integer ,Boolean>{
        List<Room> rooms;

        public SortRoomsAsync(List<Room> rooms) {
            this.rooms = rooms;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i=0;i<400000;i++){
                Collections.sort(rooms, new Comparator<Room>() {
                    @Override
                    public int compare(Room x, Room y) {
                        return x.getName().compareTo(y.getName());
                    }
                });
                if(i%1000000==0){
                    Log.d("TAG","here "+i );
                    publishProgress(i/10000);
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressUpdate.setText(getString(R.string.progress_update_template,values[0],40));
        }

        @Override
        protected void onPostExecute(Boolean successful) {
            super.onPostExecute(successful);
            progressBar.setVisibility(View.INVISIBLE);
            roomList.clear();
            roomList.addAll(this.rooms);
            roomAdapter.notifyDataSetChanged();
            progressUpdate.setVisibility(View.INVISIBLE);
        }
    }
}
