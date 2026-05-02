package com.example.tictactoe.data.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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

    public GameEntity() {}

    @Ignore
    public GameEntity(String gameUuid, String field, String status, String winner, String mySymbol, long timestamp) {
        this.gameUuid = gameUuid;
        this.field = field;
        this.status = status;
        this.winner = winner;
        this.mySymbol = mySymbol;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public String getGameUuid() { return gameUuid; }
    public String getField() { return field; }
    public String getStatus() { return status; }
    public String getWinner() { return winner; }
    public String getMySymbol() { return mySymbol; }
    public long getTimestamp() { return timestamp; }

    public void setId(Long id) { this.id = id; }
    public void setGameUuid(String gameUuid) { this.gameUuid = gameUuid; }
    public void setField(String field) { this.field = field; }
    public void setStatus(String status) { this.status = status; }
    public void setWinner(String winner) { this.winner = winner; }
    public void setMySymbol(String mySymbol) { this.mySymbol = mySymbol; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
