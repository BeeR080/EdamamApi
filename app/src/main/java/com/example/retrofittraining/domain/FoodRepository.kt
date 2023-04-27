package com.example.retrofittraining.domain

import com.example.retrofittraining.data.Hint

interface FoodRepository {

     suspend fun getFoodReciep(foodName: String): List<Hint>

}