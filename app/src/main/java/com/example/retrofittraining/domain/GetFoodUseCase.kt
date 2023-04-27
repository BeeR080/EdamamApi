package com.example.retrofittraining.domain

import com.example.retrofittraining.data.Hint

class GetFoodUseCase(private val repository: FoodRepository) {

    suspend fun getFoodReciep(foodName: String): List<Hint>{
        return repository.getFoodReciep(foodName)

    }
}