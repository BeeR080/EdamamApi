package com.example.retrofittraining.domain



import com.example.retrofittraining.data.FoodList




class GetFoodUseCase(private val repository: FoodRepository) {

    suspend fun getFoodReciep(foodName: String): FoodList{
        return repository.getFoodReciep(foodName)

    }


}