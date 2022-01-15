package com.example.android3lesson11;

import android.app.Application;
import com.example.android3lesson11.data.remote.FilmApi;
import com.example.android3lesson11.data.remote.FilmsApiService;
import com.example.android3lesson11.data.remote.RetrofitClient;

public class App extends Application {
    private RetrofitClient client;
    public static FilmApi api;
    public static FilmsApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new RetrofitClient();
        api = client.provideFilmApi();
        apiService = new FilmsApiService();
    }
}
