package com.example.retrofittraining.model

import com.example.retrofittraining.data.Hint

class FoodRepository() {


    suspend fun getFoodReciep(foodName: String): List<Hint>{
        var food = restFoodApi.getFoodRecipe(foodName)
        var foodList = food.body()!!.hints
        return foodList
    }

}