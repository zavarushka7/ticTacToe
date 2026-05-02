package com.example.tictactoe.data.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class GameEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "game_uuid")
    private String gameUuid;

    @ColumnInfo(name = "field")
    private String field;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "winner")
    private String winner;
    @ColumnInfo(name = "my_symbol")
    private String mySymbol;
    @ColumnInfo(name = "timestamp")
    private long timestamp;
}
