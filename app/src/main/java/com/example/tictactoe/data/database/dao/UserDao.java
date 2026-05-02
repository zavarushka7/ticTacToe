package com.example.tictactoe.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tictactoe.data.database.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM users WHERE user_uuid = :uuid")
    UserEntity getUserByUuid(String uuid);

    @Query("SELECT * FROM users")
    List<UserEntity> getAllUsers();

    @Query("DELETE FROM users")
    void deleteAll();

}