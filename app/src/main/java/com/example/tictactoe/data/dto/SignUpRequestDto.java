package com.example.tictactoe.data.dto;

import com.google.gson.annotations.SerializedName;

public class SignUpRequestDto {
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;


    public SignUpRequestDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
