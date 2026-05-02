package com.example.tictactoe.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.tictactoe.data.database.TicTacToeDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    TicTacToeDatabase provideTicTacToeDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                TicTacToeDatabase.class,
                "tictactoe_database"
        ).build();
    }
}