package com.example.yaali.chatroom;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.OnSelectedListener;
import com.example.yaali.chatroom.Data.RoomMessageController;
import com.example.yaali.chatroom.Data.SendMessageController;
import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.RoomMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoomMessageFragment extends Fragment {
    private RecyclerView roommessages;
    private List<RoomMessage> roomMessageList=new ArrayList<>();
    private MessageRoomAdapter messageRoomAdapter;
    private ProgressBar progressBar;
    private TextView progressUpdate;
    private EditText textmessage;
    private Button send;
    private OnSelectedListener onSelectedListener;
    private String idRoom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_roommessage,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();
        String idRoom=intent.getStringExtra("idRoom");
        Log.d("TAG", "Id_room_pass : "+ idRoom );
        findview(view);
        initRoomMessageList();
        //macke object of roomController Class
        RoomMessageController roomMessageController = new RoomMessageController(getRoomMessageCallback);
        RoomMessage roomMessage=new RoomMessage();
        roomMessage.setRoomId(MypreferenceManager.getInstance(getActivity()).getIdRoomMassege());
        roomMessageController.strat(
                "bearer "+MypreferenceManager.getInstance(getActivity()).getAccessToken(),
                roomMessage
        );
        Log.d("Tag","roomMessageList " + roomMessageList.size());


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendMessageController sendMessageController=new SendMessageController(sendRoomMessageCallback);
                RoomMessage roomMessage1=new RoomMessage();
                //roomMessage1.setText(nameroom.getText().toString());
                sendMessageController.start("bearer "+MypreferenceManager.getInstance(getActivity()).getAccessToken(),roomMessage1);
            }
        });

    }

    // Call list Messages get of server by API
    ChatRoomAPI.GetRoomMessageCallback getRoomMessageCallback = new ChatRoomAPI.GetRoomMessageCallback() {
        @Override
        public void onResponse(List<RoomMessage> inputList) {

            Log.d("Tag","roomMessageList " +roomMessageList.size());

            new SortRoomsAsync(inputList).execute();
        }

        @Override
        public void onFailure(String cause) {

        }
    };
    ChatRoomAPI.SendRoomMessageCallback sendRoomMessageCallback=new ChatRoomAPI.SendRoomMessageCallback() {
        @Override
        public void onResponse(boolean successful, String errorMessage, RoomMessage roomMessage) {
            if(successful){
                Log.d("TAG", "onResponse" + errorMessage);
          //      Toast.makeText(getActivity(),"Done " + roomMessage.setText(),Toast.LENGTH_SHORT).show();

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

    //sort list messages
    private class SortRoomsAsync extends AsyncTask<Void ,Integer ,Boolean> {
        List<RoomMessage> roomMessages;

        public SortRoomsAsync(List<RoomMessage> roomMessages) {
            this.roomMessages = roomMessages;
        }

        //sort list rooms in thread
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i=0;i<400000;i++){
                Collections.sort(roomMessages, new Comparator<RoomMessage>() {
                    @Override
                    public int compare(RoomMessage x, RoomMessage y) {
                        return x.getCreatedAt().compareTo(y.getCreatedAt());
                    }
                });
                if(i%100000==0){
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
            roomMessageList.clear();
            roomMessageList.addAll(this.roomMessages);
            messageRoomAdapter.notifyDataSetChanged();
            progressUpdate.setVisibility(View.INVISIBLE);
        }

    }

    //find view in items viewfragment/

    public void findview(View view){
        roommessages=view.findViewById(R.id.roommessages);
        progressBar=view.findViewById(R.id.progress_bar);
        progressUpdate=view.findViewById(R.id.progressUpdate);
        textmessage=view.findViewById(R.id.textmessage);
        send=view.findViewById(R.id.send);
    }

    private void initRoomMessageList(){
        messageRoomAdapter=new MessageRoomAdapter(roomMessageList,getActivity());
        roommessages.setLayoutManager(new LinearLayoutManager(getActivity()));
        roommessages.setAdapter(messageRoomAdapter);
    }
}
