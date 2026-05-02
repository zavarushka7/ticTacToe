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

    private static volatile TicTacToeDatabase instance;

    public static TicTacToeDatabase getInstance(Context context){
        if (instance == null) {
            synchronized (TicTacToeDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TicTacToeDatabase.class,
                            "tictactoe_database"
                    ).build();
                }
            }
        }
        return instance;
    }
}
