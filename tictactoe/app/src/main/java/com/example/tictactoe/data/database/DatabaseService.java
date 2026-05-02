package com.example.tictactoe.data.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.tictactoe.data.database.entity.CurrentUserEntity;
import com.example.tictactoe.data.database.entity.GameEntity;
import com.example.tictactoe.data.database.entity.UserEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseService {

    private final TicTacToeDatabase database;
    // Выполняет тяжелую работу в фоновом потоке
    // ExecutorService управляет пулом потоков
    // Executors.newSingleThreadExecutor создает один фоновый поток для всех задач
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    // Возвращает результат в главный поток
    // Handler позволяет выполнить код в определенном потоке
    // Looper.getMainLooper получает главный поток
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public DatabaseService(TicTacToeDatabase database){
        this.database = database;
    }


    // CURRENT USER --------------------------------------------------------------------------------
    public void getCurrentUser(DatabaseCallback<CurrentUserEntity> callback){
        // отправляет задачу в фоновый поток
        executor.execute(() -> {
            try {
                CurrentUserEntity user = database.currentUserDao().getCurrentUser();
                // переключается на главный поток и вызывает callback.onSuccess() с результатом
                mainHandler.post(() -> callback.onSuccess(user));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void isUserLoggedIn(DatabaseCallback<Boolean> callback){
        executor.execute(() -> {
            try {
                Boolean isUserLoggedIn = database.currentUserDao().isUserLoggedIn();
                mainHandler.post(() -> callback.onSuccess(isUserLoggedIn));
            } catch (Exception e){
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void clearCurrentUser(DatabaseCallback<Void> callback){
        executor.execute(() -> {
            try {
                database.currentUserDao().clear();
                mainHandler.post(() -> callback.onSuccess(null));
            } catch (Exception e){
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    // GAME  ---------------------------------------------------------------------------------------
    public void getGameByUuid(String uuid, DatabaseCallback<GameEntity> callback){
        executor.execute(() -> {
            try {
                GameEntity gameByUuid = database.gameDao().getGameByUuid(uuid);
                mainHandler.post(() -> callback.onSuccess(gameByUuid));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }
    public void getAllGames(DatabaseCallback<List<GameEntity>> callback){
        executor.execute(() -> {
            try {
                List<GameEntity> allGames = database.gameDao().getAllGames();
                mainHandler.post(() -> callback.onSuccess(allGames));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void deleteAllGames(DatabaseCallback<Void> callback){
        executor.execute(() -> {
            try {
                database.gameDao().deleteAll();
                mainHandler.post(() -> callback.onSuccess(null));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void deleteGame(GameEntity game, DatabaseCallback<Void> callback){
        executor.execute(() -> {
            try {
                database.gameDao().delete(game);
                mainHandler.post(() -> callback.onSuccess(null));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }
    public void saveGame(GameEntity game, DatabaseCallback<Void> callback){
        executor.execute(() -> {
            try {
                database.gameDao().insert(game);
                mainHandler.post(() -> callback.onSuccess(null));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    // USERS  ---------------------------------------------------------------------------------------
    public void saveUser(UserEntity user, DatabaseCallback<Void> callback) {
        executor.execute(() -> {
            try {
                database.userDao().insert(user);
                mainHandler.post(() -> callback.onSuccess(null));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void getUserByUuid(String uuid, DatabaseCallback<UserEntity> callback) {
        executor.execute(() -> {
            try {
                UserEntity user = database.userDao().getUserByUuid(uuid);
                mainHandler.post(() -> callback.onSuccess(user));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void getAllUsers(DatabaseCallback<List<UserEntity>> callback) {
        executor.execute(() -> {
            try {
                List<UserEntity> users = database.userDao().getAllUsers();
                mainHandler.post(() -> callback.onSuccess(users));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

}
