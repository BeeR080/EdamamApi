package com.example.retrofittraining.domain.model

import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.presentation.APP_ID
import com.example.retrofittraining.presentation.APP_KEY
import com.example.retrofittraining.presentation.BASE_URI
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApiService {
        @GET("api/food-database/v2/parser?")
       suspend fun getFoodRecipe(
            @Query("ingr") ingr: String,
            @Query("app_id") app_id: String = APP_ID,
            @Query("app_key") app_key: String = APP_KEY

            ): Response <FoodList>

    }
    var okHttpClient = OkHttpClient.Builder().build()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URI)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    var restFoodApi: FoodApiService = retrofit.create(FoodApiService::class.java)
