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

    public UUID getUuidGame() { return uuidGame; }
    public void setUuidGame(UUID uuidGame) { this.uuidGame = uuidGame; }

    public int[][] getField() { return field; }
    public void setField(int[][] field) { this.field = field; }

    public UUID getFirstPlayer_X() { return firstPlayer_X; }
    public void setFirstPlayer_X(UUID firstPlayer_X) { this.firstPlayer_X = firstPlayer_X; }

    public UUID getSecondPlayer_O() { return secondPlayer_O; }
    public void setSecondPlayer_O(UUID secondPlayer_O) { this.secondPlayer_O = secondPlayer_O; }

    public Boolean getPlayingWithBot() { return playingWithBot; }
    public void setPlayingWithBot(Boolean playingWithBot) { this.playingWithBot = playingWithBot; }

    public String getStateGame() { return stateGame; }
    public void setStateGame(String stateGame) { this.stateGame = stateGame; }

    public Boolean getGameOver() { return gameOver; }
    public void setGameOver(Boolean gameOver) { this.gameOver = gameOver; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
}

