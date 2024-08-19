package com.example.intervalltimerfrontend.Service;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String USERNAME = "user"; // Dein Benutzername
    private static final String PASSWORD = "password"; // Dein Passwort

    public static Retrofit getClient() {
        // Erstellen des Interceptors für Basic Authentication
        Interceptor authInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request authenticatedRequest = request.newBuilder()
                        .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
                        .build();
                return chain.proceed(authenticatedRequest);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(authInterceptor) // Hinzufügen des Authentifizierungsinterceptors
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // Optional: Logging für Debugging
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // Localhost Server-URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static ExerciseService getExerciseService() {
        return getClient().create(ExerciseService.class);
    }
}


