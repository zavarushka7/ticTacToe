package com.example.tictactoe.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthPreferences {
    private SharedPreferences getSharedPreferences(Context context) {
        // имя файла будет auth_prefs.xml
        // Context.MODE_PRIVATE - доступ только у вашего приложения
        return context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
    }

    public void saveCredentials(Context context, String login, String password){
        SharedPreferences prefs = getSharedPreferences(context);
        // Editor - внутренний класс SharedPreferences, который позволяет изменять данные.
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("login", login);
        editor.putString("password", password);
        // сохраняет асинхронно (быстрее)
        editor.apply();
    }

    public String getLogin(Context context){
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getString("login", null);
    }

    public String getPassword(Context context){
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getString("password", null);
    }

    public boolean isLoggedIn(Context context) {
        SharedPreferences prefs = getSharedPreferences(context);
        String login = prefs.getString("login", null);
        String password = prefs.getString("password", null);
        return login != null && password != null;
    }

    public void clearCredentials(Context context){
        SharedPreferences prefs = getSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("login");
        editor.remove("password");
        editor.apply();
    }
}
