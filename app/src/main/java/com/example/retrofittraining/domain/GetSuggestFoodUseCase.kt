package com.example.retrofittraining.domain

import com.example.retrofittraining.data.Hint
import kotlinx.coroutines.flow.Flow

class GetSuggestFoodUseCase(private val repository: FoodRepository) {
    suspend fun getSuggestFood(foodName: String): Flow<List<Hint>> {
        return repository.getSuggestFood(foodName)
    }
}