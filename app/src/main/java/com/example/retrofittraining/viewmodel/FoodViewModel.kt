package com.example.retrofittraining.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.GetFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch



class FoodViewModel(
    private val getFoodUseCase: GetFoodUseCase,
) : ViewModel() {

    private val _foodList = MutableLiveData<List<Hint>>()
    val foodList = _foodList

    private val _suggestList = MutableStateFlow<List<String>>(emptyList())
     val suggestList = _suggestList.asStateFlow()



   fun getFoodReciep(foodName: String) {
      viewModelScope.launch {
         _foodList.value =  getFoodUseCase.getFoodReciep(foodName)
      }
 }

    fun getSuggetList(foodName: String) {
        viewModelScope.launch {
           getFoodUseCase.getSuggestFood(foodName)
                .flowOn(Dispatchers.IO)
                .collect{dataSuggestFood->
                    _suggestList.value = dataSuggestFood.map {
                        it.food.label
                    }
                }
        }
    }





}