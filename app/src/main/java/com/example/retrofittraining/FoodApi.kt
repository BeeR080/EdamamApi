package com.example.retrofittraining

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FoodApiService {
        @GET("v2/parser")
       suspend fun getFoodRecipe(
            @Query("ingr") ingr: String,
            @Query("app_id") app_id:String = APP_ID,
            @Query("app_key") app_key:String = APP_KEY,
            ): List<FoodsList>

    }

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var restFoodApi: FoodApiService = retrofit.create(FoodApiService::class.java)
