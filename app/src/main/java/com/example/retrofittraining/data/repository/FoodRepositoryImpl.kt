package com.example.retrofittraining.data.repository


import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.domain.FoodRepository
import com.example.retrofittraining.domain.model.APIResponse
import com.example.retrofittraining.domain.model.FoodRemoteData
import org.koin.core.component.getScopeId


class FoodRepositoryImpl(private val remoteData: FoodRemoteData):FoodRepository {

    override suspend fun getFoodReciep(foodName: String): APIResponse<FoodList> {
        return try {
            val foodReciepe = remoteData.getReciepe(foodName)
            APIResponse.Success(data = foodReciepe)

        }catch (exception:Exception){
            APIResponse.Error(message = "Error: ${exception.localizedMessage}")

        }



    }

    override suspend fun getSuggestFood(foodName: String): APIResponse<FoodList> {

        return try {
          APIResponse.Success(remoteData.getReciepe(foodName))

        }
        catch (exception:Exception){
            APIResponse.Error(message = "Error: ${exception.message}")


        }

    }

}


