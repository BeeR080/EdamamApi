package com.example.retrofittraining.di

import com.example.retrofittraining.domain.model.FoodApiService
import com.example.retrofittraining.presentation.BASE_URI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Gson> {
        GsonBuilder().create()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .client(get<OkHttpClient>())
            .build()

}

    single{
        get<Retrofit>().create(FoodApiService::class.java)
    }

}