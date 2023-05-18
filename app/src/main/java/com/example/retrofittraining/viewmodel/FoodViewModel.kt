package com.example.retrofittraining.viewmodel




import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittraining.data.FoodList
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.domain.GetFoodUseCase
import com.example.retrofittraining.domain.GetSuggestFoodUseCase
import com.example.retrofittraining.domain.model.APIResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
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

     private var _errorMsg = MutableLiveData("")
    var errorMsg = _errorMsg






   fun getFoodReciep(foodName: String) {
       viewModelScope.launch {
           try {
               _isLoading.value = true
               _errorMsg.value =""

               when (val response = getFoodUseCase.getFoodReciep(foodName)) {
                   is APIResponse.Success -> {

                       if(response.data?.hints!!.isNotEmpty()) {
                           _foodList.value = response.data.hints

                       }else{
                           _foodList.value = emptyList()
                       }
                           _isLoading.value = false

                   }

                   is APIResponse.Error -> {
                       _errorMsg.value = response.message
                       _foodList.value = emptyList()
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

                    is APIResponse.Success-> {
                      flow{
                                emit(response.data!!)
                                }
                            .collect{ data ->
                            _suggestList.value = data.hints.map {
                                it.food.label

                            }
                        }

                    }

                    is APIResponse.Error->{
                        _suggestList.value = emptyList()
                    }

                }

            }catch (exception:Exception){
                _suggestList.value = emptyList()

            }
        }

    }






}