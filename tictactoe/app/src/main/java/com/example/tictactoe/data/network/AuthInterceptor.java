package com.example.tictactoe.data.network;

import android.content.Context;
import android.content.SharedPreferences;



import java.io.IOException;
import java.util.Currency;

import android.util.Base64;

import com.example.tictactoe.data.database.TicTacToeDatabase;
import com.example.tictactoe.data.database.entity.CurrentUserEntity;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final TicTacToeDatabase database;
    public AuthInterceptor(TicTacToeDatabase database) {
        this.database = database;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        CurrentUserEntity currentUser = database.currentUserDao().getCurrentUser();



        Request modifiedRequest = originalRequest;
        if (currentUser!= null){
            String login = currentUser.getLogin();
            String password = currentUser.getPassword();
            String credentials = login + ":" + password;

            // Кодируем в Base64. credentials.getBytes() - превращает строку в массив байтов
            // Base64.NO_WRAP - не добавлять переносы строк
            String base64Credentials = Base64.encodeToString(
                    credentials.getBytes(),
                    Base64.NO_WRAP
            );

            String authHeader = "Basic " + base64Credentials;

            modifiedRequest = originalRequest.newBuilder()
                    .addHeader("Authorization", authHeader)
                    .build();
        }
        // Отправляем запрос на сервер и получаем ответ. chain.proceed() - продолжает выполнение запроса
        Response response = chain.proceed(modifiedRequest);
        return response;
    }
}
