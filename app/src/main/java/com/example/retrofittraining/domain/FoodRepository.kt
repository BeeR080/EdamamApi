package com.example.retrofittraining.domain

import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.model.APIResponse
import kotlinx.coroutines.flow.Flow


interface FoodRepository {

     suspend fun getFoodReciep(foodName: String): APIResponse<FoodList>
     suspend fun getSuggestFood(foodName: String): Flow<APIResponse<FoodList>>



}