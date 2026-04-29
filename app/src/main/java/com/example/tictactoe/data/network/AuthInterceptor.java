package com.example.tictactoe.data.network;

import android.content.Context;
import android.content.SharedPreferences;



import java.io.IOException;
import android.util.Base64;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    // храним Context для доступа к SharedPreferences
    private final Context context;

    public AuthInterceptor(Context context) {
        // берем ApplicationContext чтобы избежать утечек памяти
        this.context = context.getApplicationContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        SharedPreferences prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);

        String login = prefs.getString("login", null);
        String password = prefs.getString("password", null);

        Request modifiedRequest = originalRequest;
        if (login != null && password != null){
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
