package com.example.yaali.chatroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.RegisterUserController;
import com.example.yaali.chatroom.Models.MypreferenceManager;
import com.example.yaali.chatroom.Models.RoomMessage;
import com.example.yaali.chatroom.Models.User;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        if(MypreferenceManager.getInstance(MainActivity.this).getAccessToken() != null){
            openOtherFragments();
        }else {
            openLoginFragment();
        }
    }

    public void findview(){
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
    }
    public void openLoginFragment(){

        LoginFragment loginFragment=new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,loginFragment)
                .commit();

    }
    public void openOtherFragments(){

        myFragmentPagerAdapter =new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);
        //connect tablayout to viewpager
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }
    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(broadcastReceiver);
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(broadcastReceivermessage);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(
                broadcastReceiver,new IntentFilter("login_ok")
        );
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(
                broadcastReceivermessage,new IntentFilter("intent_ok")
        );
    }

    //define an object of type registerUserCallback

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

                //delet afther fragmnet
                getSupportFragmentManager().popBackStack();
                //add new fragment
                openOtherFragments();

        }
    };
    private BroadcastReceiver broadcastReceivermessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

                openMessageFragments();

        }
    };
    public void openMessageFragments(){

                RoomMessageFragment roomMessageFragment=new RoomMessageFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container,roomMessageFragment)
                        .addToBackStack(null)
                        .commit();
        Log.d("TAG", "Id_room_pass : "+
                MypreferenceManager.getInstance(this).getIdRoomMassege() );
    }

}
