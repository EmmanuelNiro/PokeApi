package com.example.pokeapi.api;

import okhttp3.OkHttpClient;
import  okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCliente {
    private static Retrofit instancia;

    public static Retrofit obtener(){
        if (instancia == null) {
            HttpLoggingInterceptor log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient cliente = new OkHttpClient.Builder()
                    .addInterceptor(log)
                    .build();
            instancia = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .client(cliente)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instancia;
    }
}
