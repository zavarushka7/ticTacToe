package com.example.tictactoe.data.api;

import com.example.tictactoe.data.dto.GameModelDto;
import com.example.tictactoe.data.dto.SignUpRequestDto;
import com.example.tictactoe.data.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/auth/signup")
    Call<String> signup(@Body SignUpRequestDto request);
    @POST("/auth/login")
    Call<UUID> login(@Header("Authorization") String authHeader);
    @GET("/user/{uuidUser}")
    Call<UserResponseDto> getUserById(@Path("uuidUser") UUID uuidUser);
    @POST("/game/{uuidGame}")
    Call<GameModelDto> updateGame(@Path("uuidGame") UUID uuidGame, @Body GameModelDto gameModel);
    @GET("/games")
    Call<String> getAvailableGames();
    @POST("/game/create")
    Call<String> createGame(@Body boolean bot);
    @POST("/game/{uuidGame}/join")
    Call<String> joinGame(@Path("uuidGame") UUID uuidGame, @Body Integer position);
    @PATCH("/game/{uuidGame}/get")
    Call<GameModelDto> getCurrentGame(@Path("uuidGame") UUID uuidGame);
    @POST("/game/get_current")
    Call<List<GameModelDto>> getAllCurrentGames();
}
