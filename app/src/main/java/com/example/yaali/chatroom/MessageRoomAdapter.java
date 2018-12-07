package com.example.yaali.chatroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yaali.chatroom.Models.Room;
import com.example.yaali.chatroom.Models.RoomMessage;

import java.util.List;

public class MessageRoomAdapter extends RecyclerView.Adapter<MessageRoomAdapter.ViewHolder> {
    List<RoomMessage> items;
    private Context context;

    public MessageRoomAdapter(List<RoomMessage> items,Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.template_message,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.text.setText(items.get(i).getText());
//        viewHolder.username.setText(items.get(i).getUsername());
      //  viewHolder.createdAt.setText(items.get(i).getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public String roomId;
        public String nameRoom;
        public TextView text;
        public TextView username;
        public TextView createdAt;
        public ViewHolder(View itemView){
            super(itemView);
           // username=itemView.findViewById(R.id.usernamemessage);
            text =itemView.findViewById(R.id.message);
           // createdAt =itemView.findViewById(R.id.datamessage);
//            onClick(itemView,id);

        }
    }
}
