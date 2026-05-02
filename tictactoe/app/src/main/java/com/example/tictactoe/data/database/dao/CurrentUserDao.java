package com.example.tictactoe.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tictactoe.data.database.entity.CurrentUserEntity;

@Dao
public interface CurrentUserDao {
    @Insert
    void insert(CurrentUserEntity currentUser);

    @Update
    void update(CurrentUserEntity currentUser);

    @Delete
    void delete(CurrentUserEntity currentUser);

    @Query("SELECT * FROM current_user LIMIT 1")
    CurrentUserEntity getCurrentUser();

    @Query("SELECT COUNT(*) > 0 FROM current_user")
    boolean isUserLoggedIn();

    @Query("DELETE FROM current_user")
    void clear();
}
