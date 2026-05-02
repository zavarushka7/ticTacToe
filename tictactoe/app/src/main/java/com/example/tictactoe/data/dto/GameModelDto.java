package com.example.tictactoe.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class GameModelDto {
    @SerializedName("uuidGame")
    private UUID uuidGame;
    @SerializedName("field")

    private int[][] field;
    @SerializedName("FirstPlayer_X")

    private UUID firstPlayer_X;
    @SerializedName("SecondPlayer_O")

    private UUID secondPlayer_O;
    @SerializedName("playingWithBot")

    private Boolean playingWithBot;
    @SerializedName("stateGame")

    private String stateGame;
    @SerializedName("gameOver")

    private Boolean gameOver;
    @SerializedName("status")
    private String status;
    @SerializedName("winner")
    private String winner;
}
