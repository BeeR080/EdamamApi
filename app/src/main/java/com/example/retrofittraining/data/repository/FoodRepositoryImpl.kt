package com.example.retrofittraining.data.repository

import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.FoodRepository
import com.example.retrofittraining.domain.model.restFoodApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl:FoodRepository {

    override suspend fun getFoodReciep(foodName: String): List<Hint> {
        val food = restFoodApi.getFoodRecipe(foodName)
        return food.body()!!.hints
    }

    override suspend fun getSuggestFood(foodName: String): Flow<List<Hint>> = flow {
        val getFood = restFoodApi.getFoodRecipe(foodName)
        val result = getFood.body()!!.hints
        emit(result)
        delay(300)

    }


}