package com.example.tictactoe.di;

import com.example.tictactoe.data.api.ApiService;
import com.example.tictactoe.data.database.DatabaseService;
import com.example.tictactoe.data.database.TicTacToeDatabase;
import com.example.tictactoe.data.network.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    DatabaseService provideDatabaseService(TicTacToeDatabase database) {
        return new DatabaseService(database);
    }

    @Provides
    @Singleton
    NetworkService provideNetworkService(ApiService apiService) {
        return new NetworkService(apiService);
    }
}