package com.example.tictactoe.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tictactoe.data.database.entity.GameEntity;

import java.util.List;

@Dao
public interface GameDao {
    @Insert
    void insert(GameEntity game);

    @Update
    void update(GameEntity game);

    @Delete
    void delete(GameEntity game);

    @Query("SELECT * FROM games WHERE game_uuid = :uuid")
    GameEntity getGameByUuid(String uuid);

    @Query("SELECT * FROM games")
    List<GameEntity> getAllGames();

    @Query("DELETE FROM games")
    void deleteAll();
}
