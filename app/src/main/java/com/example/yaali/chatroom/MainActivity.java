package com.example.yaali.chatroom;

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

    //define an object of type registerUserCallback


}
