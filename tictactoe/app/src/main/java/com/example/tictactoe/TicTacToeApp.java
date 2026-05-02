package com.example.tictactoe;

import android.app.Application;

import com.example.tictactoe.di.AppComponent;
import com.example.tictactoe.di.DaggerAppComponent;

public class TicTacToeApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}