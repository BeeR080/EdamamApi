package com.example.retrofittraining.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittraining.data.Hint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {

private var repository: FoodRepository


init {
        repository= FoodRepository()
    }


 suspend fun getFoodReciep(foodName: String): List<Hint> {
     viewModelScope.launch(Dispatchers.IO) {

     }
      return repository.getFoodReciep(foodName)
 }

}