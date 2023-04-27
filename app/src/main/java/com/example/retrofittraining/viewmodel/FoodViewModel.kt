package com.example.retrofittraining.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.GetFoodUseCase
import kotlinx.coroutines.launch


class FoodViewModel(
    private val getFoodUseCase: GetFoodUseCase,
) : ViewModel() {

    private val _foodList = MutableLiveData<List<Hint>>()
    val foodList = _foodList



   fun getFoodReciep(foodName: String) {
      viewModelScope.launch {
         _foodList.value =  getFoodUseCase.getFoodReciep(foodName)
      }
 }


    

}