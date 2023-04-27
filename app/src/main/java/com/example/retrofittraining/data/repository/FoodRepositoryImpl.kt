package com.example.retrofittraining.data.repository

import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.FoodRepository
import com.example.retrofittraining.domain.model.restFoodApi

class FoodRepositoryImpl:FoodRepository {

    override suspend fun getFoodReciep(foodName: String): List<Hint> {
        val food = restFoodApi.getFoodRecipe(foodName)
        val foodList = food.body()!!.hints
        return foodList
    }


}