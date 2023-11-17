package com.example.retrofittraining.di

import com.example.retrofittraining.domain.GetFoodUseCase
import com.example.retrofittraining.domain.GetSuggestFoodUseCase
import com.example.retrofittraining.domain.model.FoodRemoteData
import com.example.retrofittraining.presentation.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



    val domainModule = module {
        factory <GetFoodUseCase>{
            GetFoodUseCase(repository = get())
        }
        factory <GetSuggestFoodUseCase>{
            GetSuggestFoodUseCase(repository = get())
        }

        single <FoodRemoteData>{
            FoodRemoteData(foodApiService = get()) }
    }



