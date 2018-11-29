package com.example.yaali.chatroom.Data;

import com.example.yaali.chatroom.Models.Room;
import com.example.yaali.chatroom.Models.RoomResponse;
import com.example.yaali.chatroom.Models.TokenResponse;
import com.example.yaali.chatroom.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatRoomAPI {
    String BASE_URL = "https://api.backtory.com/";
    @Headers("X-Backtory-Authentication-Id:5a1d4b3de4b0afa16474fabd")
    @POST("auth/users")
    Call<User> registerUser(@Body User user);

    @Headers({
            "X-Backtory-Authentication-Id:5a1d4b3de4b0afa16474fabd",
            "X-Backtory-Authentication-Key:5a1d4b3de4b0ce09cd4655c8"
    })
    @FormUrlEncoded
    @POST("auth/login")
    Call<TokenResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
            );

    @Headers("X-Backtory-Object-Storage-Id:5a1d4b3de4b03ffa047badf5")
    @POST("object-storage/classes/query/Room")
    Call<RoomResponse> getRooms(
            @Header("Authorization") String authorization
    );

    interface RegisterUserCallback{
        void onResponse(boolean successful,String errorMessage,User user);
        void onFailure(String cause);
    }
    interface LoginUserCallback{
        void onResponse(boolean successful,String errorDescription,TokenResponse tokenResponse);
        void onFailure(String cause);
    }
    interface GetRoomsCallback{
        void onResponse(List<Room> roomList);
        void onFailure(String cause);
    }
}
