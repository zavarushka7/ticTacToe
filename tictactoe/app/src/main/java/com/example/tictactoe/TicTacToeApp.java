package com.example.tictactoe;

import android.app.Application;

import com.example.tictactoe.di.AppComponent;
import com.example.tictactoe.di.DaggerAppComponent;

public class TicTacToeApp extends Application {
    // компонент Dagger (будет создана автоматически)
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.b
    }
}
