package com.example.retrofittraining.viewmodel



import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.GetFoodUseCase
import com.example.retrofittraining.domain.GetSuggestFoodUseCase
import com.example.retrofittraining.domain.model.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch



class FoodViewModel(
    private val getFoodUseCase: GetFoodUseCase,
    private val getSuggestFoodUseCase: GetSuggestFoodUseCase,
) : ViewModel() {

    private val _foodList = MutableLiveData<List<Hint>>()
    val foodList = _foodList

    private val _suggestList = MutableStateFlow<List<String>>(emptyList())
     val suggestList = _suggestList.asStateFlow()

    private var _isLoading = MutableLiveData(false)
     var isLoading = _isLoading



   fun getFoodReciep(foodName: String) {
       viewModelScope.launch {
           try {
               _isLoading.value = true
               when (val response = getFoodUseCase.getFoodReciep(foodName)) {
                   is APIResponse.Success -> {
                       _foodList.value = response.data?.hints!!
                       _isLoading.value = false

                   }

                   is APIResponse.Error -> {
                       _isLoading.value = false
                   }
               }

           } catch (exception: Exception) {
               _isLoading.value = false
           }

       }
   }

    fun getSuggetList(foodName: String) {
        viewModelScope.launch {
            try{
                when(val response = getSuggestFoodUseCase.getSuggestFood(foodName)){
                    is APIResponse.Success<*> -> {
                        response
                            .flowOn(Dispatchers.IO)
                            .collect { dataSuggestFood ->
                                _suggestList.value = dataSuggestFood.data?.hints!!.map {
                                    it.food.label

                                }
                            }
                    }

                }

            }catch (exception:Exception){
                APIResponse.Error(null,message = "Error in VM ${exception.message}")
            }
        }
    }






}