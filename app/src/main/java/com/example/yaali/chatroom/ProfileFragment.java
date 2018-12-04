package com.example.yaali.chatroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yaali.chatroom.Models.MypreferenceManager;

public class ProfileFragment extends Fragment {
    private TextView username;
    private Button logout;
    private Button savenewroom;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmenrt_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        username.setText(MypreferenceManager.getInstance(getActivity()).getUsername());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MypreferenceManager.getInstance(getActivity()).clearEverything();
                //close all activity
                getActivity().finish();
            }
        });

        savenewroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRoomFragment newRoomFragment=new NewRoomFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_container,newRoomFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void findViews(View view){
        username=view.findViewById(R.id.username);
        logout=view.findViewById(R.id.logout);
        savenewroom=view.findViewById(R.id.savenewroom);
    }
}
