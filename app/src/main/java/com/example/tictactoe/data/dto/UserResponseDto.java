package com.example.tictactoe.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class UserResponseDto {
    @SerializedName("login")
    private String login;
    @SerializedName("uuid")
    private UUID uuid;
}
