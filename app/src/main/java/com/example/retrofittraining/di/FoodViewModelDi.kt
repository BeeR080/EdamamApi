package com.example.retrofittraining.di

import com.example.retrofittraining.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<FoodViewModel>{
        FoodViewModel(getFoodUseCase = get())
    }

}