package com.example.tictactoe.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tictactoe.data.database.dao.CurrentUserDao;
import com.example.tictactoe.data.database.dao.GameDao;
import com.example.tictactoe.data.database.dao.UserDao;
import com.example.tictactoe.data.database.entity.CurrentUserEntity;
import com.example.tictactoe.data.database.entity.GameEntity;
import com.example.tictactoe.data.database.entity.UserEntity;

@Database(
        entities = {
                UserEntity.class,
                CurrentUserEntity.class,
                GameEntity.class
        },
        version = 1,
        exportSchema = false
)
public abstract class TicTacToeDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CurrentUserDao currentUserDao();
    public abstract GameDao gameDao();

}
