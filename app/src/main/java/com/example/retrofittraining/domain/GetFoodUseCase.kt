package com.example.retrofittraining.domain



import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.domain.model.APIResponse


class GetFoodUseCase(private val repository: FoodRepository) {

    suspend fun getFoodReciep(foodName: String): APIResponse<FoodList> {
        return repository.getFoodReciep(foodName)

    }


}