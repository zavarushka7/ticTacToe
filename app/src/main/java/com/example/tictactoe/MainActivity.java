package com.example.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Создаём TextView программно (без XML-файла)
        TextView textView = new TextView(this);
        textView.setText("Hello, World!");

        // Устанавливаем его как интерфейс приложения
        setContentView(textView);
    }
}