package com.example.tictactoe.data.network;

import android.content.Context;
import android.os.Looper;
import android.os.Handler;
import com.example.tictactoe.data.api.ApiService;
import com.example.tictactoe.data.dto.GameModelDto;
import com.example.tictactoe.data.dto.SignUpRequestDto;
import com.example.tictactoe.data.dto.UserResponseDto;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    // статическая переменная для хранения единственного экземпляра класса (в приложении должен быть только один сетевой сервис, чтобы не создавать множество подключений)
    private static NetworkService instance;
    // интерфейс с аннотациями Retrofit (final - переменная инициализируется один раз и не меняется)
    private final ApiService apiService;
    // обработчик для переключения на главный поток. Handler позволяет выполнить код в определенном потоке. Looper.getMainLooper() - получает главный поток (UI) Android. mainHandler - будет отправлять результаты в UI поток. Нужно т.к. Retrofit вызывает колбэки в фоновом потоке, а обновлять UI можно только из главного потока
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private NetworkService(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(context))
                .build();
        // создание Retorfit через билдер (паттерн строитель)
        Retrofit retrofit = new Retrofit.Builder()
                // устанавливаем базовый URL сервера
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                // добавляем конвертер для превращения JSON в Java обьекты и обратно
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Retrofit автоматически создает реализацию интерфейса ApiService. все методы будут выполнять HTTP запросы
        apiService = retrofit.create(ApiService.class);
    }

    // публичный статический метод для получения экземпляра. static - вызывается через класс NetworkService.getInstance(). synchronized - защита от одновременного вызова из разных потоков
    public static synchronized NetworkService getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkService(context);
        }
        return instance;
    }

    public void signUp(String login, String password, NetworkCallback<String> callback){
        // создаем DTO из простых параметров. Пользователь передал login, password, а мы создаем обьект, который Retrofit отправит на сервер
        SignUpRequestDto request = new SignUpRequestDto(login, password);

        // apiService.siqnup(request) - вызывает метод из ApiService, возвращает Call<String>
        // .enqueue(...) - выполняет запрос асинхронно (в фоновом потоке)
        // new Callback<String>() - создаем анонимный класс для обработки ответа
        apiService.signup(request).enqueue(new Callback<String>() {
            @Override
            // метод вызывается, когда сервер ответил (неважно, с ошибкой или успехом)
            public void onResponse(Call<String> call, Response<String> response) {
                // переключаемся на главный поток
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Ошибка регистрации: " + response.code();
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e){
                                errorMsg = "Неизвестная ошибка";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            // метод вызывается при сетевой ошибке (нет интернета, таймаут, сервер недоступен)
            public void onFailure(Call<String> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });


    }

    public void login(String authHeader, NetworkCallback<UUID> callback){
        apiService.login(authHeader).enqueue(new Callback<UUID>() {
            @Override
            public void onResponse(Call<UUID> call, Response<UUID> response) {
                mainHandler.post(() -> {
                   if (response.isSuccessful() && response.body() != null){
                       callback.onSuccess(response.body());
                   } else {
                       String errorMsg = "Ошибка авторизации: " + response.code();
                       if (response.errorBody() != null){
                           try {
                               errorMsg = response.errorBody().string();
                           } catch (IOException e) {
                               errorMsg = "Неверный логин или пароль";
                           }
                       }
                       callback.onError(errorMsg);
                   }
                });
            }

            @Override
            public void onFailure(Call<UUID> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }

    public void getUserById(UUID uuidGame, NetworkCallback<UserResponseDto> callback){
        apiService.getUserById(uuidGame).enqueue(new Callback<UserResponseDto>() {
            @Override
            public void onResponse(Call<UserResponseDto> call, Response<UserResponseDto> response) {
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Пользователь не найден";
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e) {
                                errorMsg = "Ошибка получения пользователя";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            public void onFailure(Call<UserResponseDto> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }
    public void updateGame(UUID uuiGame, GameModelDto gameModel, NetworkCallback<GameModelDto> callback){
        apiService.updateGame(uuiGame, gameModel).enqueue(new Callback<GameModelDto>() {
            @Override
            public void onResponse(Call<GameModelDto> call, Response<GameModelDto> response) {
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Ошибка обновления игры";
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e) {
                                errorMsg = "Не удалось обновить игру";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            public void onFailure(Call<GameModelDto> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }
    public void getAvailableGames(NetworkCallback<String> callback){
        apiService.getAvailableGames().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Ошибка получения доступных игр";
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e) {
                                errorMsg = "Не удалось получить список игр";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }
    public void createGame(Boolean bot, NetworkCallback<String> callback){
        apiService.createGame(bot).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Ошибка создания игры";
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e) {
                                errorMsg = "Не удалось создать игру";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }

    public void joinGame(UUID uuidGame, Integer position, NetworkCallback<String> callback){
        apiService.joinGame(uuidGame, position).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Ошибка подключения к игре";
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e) {
                                errorMsg = "Не удалось подключиться к игре";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }

    public void getCurrentGame(UUID uuidGame, NetworkCallback<GameModelDto> callback){
        apiService.getCurrentGame(uuidGame).enqueue(new Callback<GameModelDto>() {
            @Override
            public void onResponse(Call<GameModelDto> call, Response<GameModelDto> response) {
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Ошибка получения игры";
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e) {
                                errorMsg = "Не удалось получить текущую игру";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            public void onFailure(Call<GameModelDto> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }

    public void getAllCurrentGames(NetworkCallback<List<GameModelDto>> callback){
        apiService.getAllCurrentGames().enqueue(new Callback<List<GameModelDto>>() {
            @Override
            public void onResponse(Call<List<GameModelDto>> call, Response<List<GameModelDto>> response) {
                mainHandler.post(() -> {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    } else {
                        String errorMsg = "Ошибка получения текущих игр";
                        if (response.errorBody() != null){
                            try {
                                errorMsg = response.errorBody().string();
                            } catch (IOException e) {
                                errorMsg = "Не удалось получить текущие игры";
                            }
                        }
                        callback.onError(errorMsg);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<GameModelDto>> call, Throwable t) {
                mainHandler.post(() -> {
                    callback.onNetworkError();
                });
            }
        });
    }


}
