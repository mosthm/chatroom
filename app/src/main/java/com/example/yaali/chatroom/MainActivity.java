package com.example.yaali.chatroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.RegisterUserController;
import com.example.yaali.chatroom.Models.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RegisterFragment registerFragment=new RegisterFragment();
        getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment_container,registerFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(
                broadcastReceiver,new IntentFilter("login_ok")
        );
    }

    //define an object of type registerUserCallback
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //delet afther fragmnet
            getSupportFragmentManager().popBackStack();
            //add new fragment
            RoomsFragment roomsFragment =new RoomsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,roomsFragment)
                    .commit();
        }
    };

}
