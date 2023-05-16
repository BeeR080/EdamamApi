package com.example.retrofittraining.domain

import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.data.Hint
import kotlinx.coroutines.flow.Flow


interface FoodRepository {

     suspend fun getFoodReciep(foodName: String): FoodList
     suspend fun getSuggestFood(foodName: String): Flow<FoodList>



}