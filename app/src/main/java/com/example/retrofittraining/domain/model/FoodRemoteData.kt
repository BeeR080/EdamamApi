package com.example.retrofittraining.domain.model

import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.presentation.APP_ID
import com.example.retrofittraining.presentation.APP_KEY
import retrofit2.HttpException
import retrofit2.Response


class FoodRemoteData(
    private val foodApiService: FoodApiService
) {
    suspend fun getReciepe(foodName: String): FoodList =
        foodApiService.getFoodRecipe(
            foodName,
            APP_ID,
            APP_KEY
        )
}








