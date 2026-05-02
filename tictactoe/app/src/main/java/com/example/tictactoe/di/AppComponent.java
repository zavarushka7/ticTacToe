package com.example.tictactoe.di;

import android.app.Application;

import com.example.tictactoe.TicTacToeApp;
import com.example.tictactoe.data.database.DatabaseService;
import com.example.tictactoe.data.network.NetworkService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        DatabaseModule.class,
        NetworkModule.class,
        ServiceModule.class
})
public interface AppComponent {
    void inject(TicTacToeApp app);

    DatabaseService getDatabaseService();
    NetworkService getNetworkService();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}