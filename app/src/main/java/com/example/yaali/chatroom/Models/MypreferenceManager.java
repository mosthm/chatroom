package com.example.yaali.chatroom.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MypreferenceManager {
    private static MypreferenceManager instance =null;
    private SharedPreferences sharedPreferences=null;
    private SharedPreferences.Editor editor=null;
    public static MypreferenceManager getInstance(Context context){
        if(instance==null){
            instance=new MypreferenceManager(context);
        }
        return instance;
    }
    private MypreferenceManager(Context context){
        sharedPreferences =context.getSharedPreferences("my_preference",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //editor.pu
    }

    //******************************************************************

    public void clearEverything(){
        editor.clear();
        editor.apply();
    }

    //******************************************************************

    public String getUsername(){
        return sharedPreferences.getString("username",null);
    }
    public  void putUsername(String highScore){
        //use of editor then write
        editor.putString("username",highScore);
        editor.apply();
    }
    //******************************************************************

    public String getPassword(){
        return sharedPreferences.getString("password",null);
    }
    public  void putPassword(String pass){
        //use of editor then write
        editor.putString("password",pass);
        editor.apply();
    }
    //******************************************************************

    public String getAccessToken(){
        return sharedPreferences.getString("access_token",null);
    }
    public  void putAccessToken(String accessToken){
        //use of editor then write
        editor.putString("access_token",accessToken);
        editor.apply();
    }
    //******************************************************************

    public String getIdRoomMassege(){
        return sharedPreferences.getString("idroommassege",null);
    }
    public  void putIdRoomMassege(String idroommassege){
        //use of editor then write
        editor.putString("idroommassege",idroommassege);
        editor.apply();
    }
}
