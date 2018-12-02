package com.example.yaali.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.NewRoomController;
import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.Room;

public class NewRoomFragment extends Fragment {
    private TextView nameroom;
    private Button savenewroom;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnet_newroom,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        savenewroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRoomController newRoomController=new NewRoomController(newRoomCallback);
                Room room=new Room();
                room.setName(nameroom.getText().toString());
                newRoomController.start("bearer "+MypreferenceManager.getInstance(getActivity()).getAccessToken(),room);
            }
        });
    }

    ChatRoomAPI.NewRoomCallback newRoomCallback=new ChatRoomAPI.NewRoomCallback() {
        @Override
        public void onResponse(boolean successful, String errorMessage, Room room) {
            if(successful){
                Log.d("TAG", "onResponse" + errorMessage);
                Toast.makeText(getActivity(),"Done " + room.getName(),Toast.LENGTH_SHORT).show();
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(
                        new Intent("login_ok")
                );
            }else {
                Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(String cause) {
//            Toast.makeText(getActivity(),cause.getUsername(),Toast.LENGTH_SHORT).show();
            Log.d("TAG", "onResponse" + cause);
        }
    };

    private void findViews(View view){
        nameroom=view.findViewById(R.id.nameroom);
        savenewroom=view.findViewById(R.id.savenewroom);
    }
}
