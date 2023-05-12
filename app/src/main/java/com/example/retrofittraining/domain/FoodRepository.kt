package com.example.retrofittraining.domain

import com.example.retrofittraining.data.Hint
import kotlinx.coroutines.flow.Flow


interface FoodRepository {

     suspend fun getFoodReciep(foodName: String): List<Hint>
     suspend fun getSuggestFood(foodName: String): Flow<List<Hint>>



}