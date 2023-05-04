package com.example.retrofittraining.domain

import com.example.retrofittraining.data.Hint
import kotlinx.coroutines.flow.Flow

class GetFoodUseCase(private val repository: FoodRepository) {

    suspend fun getFoodReciep(foodName: String): List<Hint>{
        return repository.getFoodReciep(foodName)

    }

 /*   suspend fun getSuggestFood(foodName: String): Flow<List<Hint>>{
        return repository.getSuggestFood(foodName)
    }*/
}