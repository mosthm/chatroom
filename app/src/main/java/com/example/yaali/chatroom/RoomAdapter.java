package com.example.yaali.chatroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yaali.chatroom.Data.OnSelectedListener;
import com.example.yaali.chatroom.Models.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    List<Room> items;
//    Intent intent = new Intent();
    private OnSelectedListener onSelectedListener;

    public RoomAdapter(List<Room> items) {
        this.items = items;
    }
//    public RoomAdapter(List<Room> items, OnSelectedListener onSelectedListener) {
//        this.items = items;
//        this.onSelectedListener=onSelectedListener;
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.template_room,viewGroup,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.name.setText(items.get(i).getName());
        viewHolder.id.setText(items.get(i).getId());
//        intent.setAction(items.get(i).getId());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView name;
        public ViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.name);
            id =itemView.findViewById(R.id.idroom);

        }
    }
}
