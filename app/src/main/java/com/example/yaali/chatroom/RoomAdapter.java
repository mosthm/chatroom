package com.example.yaali.chatroom;

import android.content.Context;
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
import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.Room;
import com.example.yaali.chatroom.Models.RoomMessage;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    List<Room> items;
    Intent intent = new Intent();
    private Context context;
   // private OnSelectedListener onSelectedListener;

    public RoomAdapter(List<Room> items,Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.template_room,viewGroup,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.name.setText(items.get(i).getName());
        viewHolder.id.setText(items.get(i).getId());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("intent_ok");

//                Intent intent = new Intent(context,RoomMessageFragment.class);
                intent.putExtra("idRoom",items.get(i).getId());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                MypreferenceManager.getInstance(context).putIdRoomMassege(items.get(i).getId());
                RoomMessage roomMessage=new RoomMessage();
                roomMessage.setRoomId(items.get(i).getId());
                Log.d("TAG", "Id_room : "+ roomMessage.getRoomId() );
                Log.d("TAG", "Id_room  & Name: "+ items.get(i).getName() );
//                context.startActivity(intent);

//                RoomMessageFragment roomMessageFragment=new RoomMessageFragment();
//                getFragmentManager().beginTransaction()
//                        .add(R.id.fragment_container,roomMessageFragment)
//                        .addToBackStack(null)
//                        .commit();

            }
        });
 //       onClick(viewHolder);
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
//            onClick(itemView,id);

        }
    }
}
