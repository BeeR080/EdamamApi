package com.example.retrofittraining.domain

import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.domain.model.APIResponse

import kotlinx.coroutines.flow.Flow

class GetSuggestFoodUseCase(private val repository: FoodRepository) {
    suspend fun getSuggestFood(foodName: String): Flow<APIResponse<FoodList>> {
        return repository.getSuggestFood(foodName)
    }
}