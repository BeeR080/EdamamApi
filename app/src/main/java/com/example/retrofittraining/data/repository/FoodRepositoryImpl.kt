package com.example.retrofittraining.data.repository

import android.util.Log
import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.domain.FoodRepository
import com.example.retrofittraining.domain.model.APIResponse
import com.example.retrofittraining.domain.model.FoodRemoteData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl(private val remoteData: FoodRemoteData):FoodRepository {

    override suspend fun getFoodReciep(foodName: String): APIResponse<FoodList> {
        return try {
            val foodReciepe = remoteData.getReciepe(foodName)

            APIResponse.Success(data = foodReciepe)
        }catch (exception:Exception){
            APIResponse.Error(message = "Error ${exception.message}")

        }



    }

    override suspend fun getSuggestFood(foodName: String): Flow<APIResponse<FoodList>> {

        return try {
            flow{
                val getFood = remoteData.getReciepe(foodName)
                Log.d("Mylog","$getFood")
                emit(APIResponse.Success(data = getFood))
                delay(300)

            }
        }catch (exception:Exception){
            flow{
              emit(APIResponse.Error(null,message = "Error ${exception.message}"))

            }
        }
    }

}


