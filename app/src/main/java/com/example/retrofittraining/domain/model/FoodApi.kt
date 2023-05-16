package com.example.retrofittraining.domain.model


import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.presentation.APP_ID
import com.example.retrofittraining.presentation.APP_KEY
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApiService {
        @GET("api/food-database/v2/parser?")
       suspend fun getFoodRecipe(
            @Query("ingr") ingr: String,
            @Query("app_id") app_id: String =APP_ID,
            @Query("app_key") app_key: String =APP_KEY,
            ): FoodList

    }



