package com.example.intervalltimerfrontend.Service;

import android.content.Context;

import com.example.intervalltimerfrontend.R;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String USERNAME = "user"; // Dein Benutzername
    private static final String PASSWORD = "password"; // Dein Passwort


    //region HTTP-Client Schnittstelle
    public static Retrofit getClient() {
        // Erstellen des Interceptors für Basic Authentication
        Interceptor authInterceptor = chain -> {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
                    .build();
            return chain.proceed(authenticatedRequest);
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(authInterceptor) // Hinzufügen des Authentifizierungsinterceptors
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // Optional: Logging für Debugging
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // Localhost Server-URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static ExerciseService getExerciseService() {
        return getClient().create(ExerciseService.class);
    }

    //endregion

    //region HTTPS-Client Schnittstelle

    // Not being used, because HTTPS didn't work properly for testing
    public static Retrofit getClientHttps(Context context) {
        //Https-Usage
        OkHttpClient okHttpClient = getHttpsClient(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost:8080/") // Localhost Server-URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static OkHttpClient getHttpsClient(Context context) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            InputStream cert = context.getResources().openRawResource(R.raw.certificate);
            Certificate ca;
            try {
                ca = cf.generateCertificate(cert);
            } finally {
                cert.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            // Erstellen des Interceptors für Basic Authentication
            Interceptor authInterceptor = chain -> {
                Request request = chain.request();
                Request authenticatedRequest = request.newBuilder()
                        .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
                        .build();
                return chain.proceed(authenticatedRequest);
            };

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(authInterceptor) // Hinzufügen des Authentifizierungsinterceptors
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

            builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) tmf.getTrustManagers()[0]);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Not being used, because HTTPS didn't work properly for testing
    public static ExerciseService getExerciseServiceHttps(Context context) {
        return getClientHttps(context).create(ExerciseService.class);

    }

    //endregion

}


