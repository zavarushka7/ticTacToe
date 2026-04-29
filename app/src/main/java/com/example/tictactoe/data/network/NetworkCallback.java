package com.example.tictactoe.data.network;

public interface NetworkCallback<T> {
    void onSuccess(T data);
    void onError(String errorMessage);
    void onNetworkError();
}
