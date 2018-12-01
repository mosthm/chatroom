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
import android.widget.TableLayout;

import com.example.yaali.chatroom.Data.ChatRoomAPI;
import com.example.yaali.chatroom.Data.RegisterUserController;
import com.example.yaali.chatroom.Models.MypreferenceManager;
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
//        RegisterFragment registerFragment=new RegisterFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_container,registerFragment)
//                .addToBackStack(null)
//                .commit();
    }
    public void openOtherFragments(){

//        RoomsFragment roomsFragment =new RoomsFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_container,roomsFragment)
//                .commit();
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
            openOtherFragments();

        }
    };

}
