package com.example.retrofittraining.data.repository

import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.domain.FoodRepository
import com.example.retrofittraining.domain.model.FoodRemoteData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl(private val remoteData: FoodRemoteData):FoodRepository {

    override suspend fun getFoodReciep(foodName: String): FoodList {
        val food = remoteData.getReciepe(foodName)
        return food
    }

    override suspend fun getSuggestFood(foodName: String): Flow<FoodList> = flow {
        val getFood = remoteData.getReciepe(foodName)
        emit(getFood)
        delay(300)

    }


}