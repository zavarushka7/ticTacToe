package com.example.tictactoe.data.database;

public interface DatabaseCallback<T> {
    void onSuccess(T data);
    void onError(String errorMessage);
}
