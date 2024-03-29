package com.example.retrofittraining.data.repository

import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.FoodRepository
import com.example.retrofittraining.domain.model.FoodApiService
import com.example.retrofittraining.domain.model.FoodRemoteData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl(private val remoteData: FoodRemoteData):FoodRepository {

    override suspend fun getFoodReciep(foodName: String): List<Hint> {
        val food = remoteData.getReciepe(foodName)

        return food.body()!!.hints
    }

    override suspend fun getSuggestFood(foodName: String): Flow<List<Hint>> = flow {
        val getFood = remoteData.getReciepe(foodName)
        val result = getFood.body()!!.hints
        emit(result)
        delay(300)

    }


}