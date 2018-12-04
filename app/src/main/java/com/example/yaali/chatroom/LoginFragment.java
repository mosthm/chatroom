package com.example.yaali.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.LoginUserController;
import com.example.yaali.chatroom.Data.RegisterUserController;
import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.TokenResponse;
import com.example.yaali.chatroom.Models.User;

public class LoginFragment extends Fragment {


    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registreButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findViews(view);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        registreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        RegisterFragment registerFragment=new RegisterFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container,registerFragment)
                .addToBackStack(null)
                .commit();
            }
        });

    }



    private void loginUser(){
        LoginUserController loginUserController = new LoginUserController(loginUserCallback);
        loginUserController.start(
                username.getText().toString(),
                password.getText().toString()
        );

    }
    ChatRoomAPI.LoginUserCallback loginUserCallback =new ChatRoomAPI.LoginUserCallback() {
        @Override
        public void onResponse(boolean successful, String errorDescription, TokenResponse tokenResponse) {
            if(successful){
                //Toast.makeText(getActivity(),"Login Successfull " + tokenResponse.getAccessToken() ,Toast.LENGTH_SHORT).show();
                MypreferenceManager.getInstance(getActivity()).putUsername(username.getText().toString());
                MypreferenceManager.getInstance(getActivity()).putPassword(password.getText().toString());
                MypreferenceManager.getInstance(getActivity()).putAccessToken(tokenResponse.getAccessToken());
//


            }else {
                //Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_SHORT).show();
            }

            if(successful){
                //Toast.makeText(getActivity(),"Done " + user.getUsername(),Toast.LENGTH_SHORT).show();
                loginUser();
            }else {
                Toast.makeText(getActivity(),errorDescription,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(String cause) {

        }
    };
    public void findViews(View view){
        username=view.findViewById(R.id.username);
        password=view.findViewById(R.id.pasword);
        loginButton=view.findViewById(R.id.login);
        registreButton=view.findViewById(R.id.registre);
    }
}
