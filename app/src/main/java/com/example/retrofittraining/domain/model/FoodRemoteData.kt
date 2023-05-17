package com.example.retrofittraining.domain.model

import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.presentation.APP_ID
import com.example.retrofittraining.presentation.APP_KEY
import kotlinx.coroutines.flow.Flow
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




sealed class APIResponse<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T): APIResponse<T>(data)
    class Error<T>(data: T? = null, message: String? = null): APIResponse<T>(data, message)
}




