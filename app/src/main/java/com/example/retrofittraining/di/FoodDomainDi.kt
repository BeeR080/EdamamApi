package com.example.retrofittraining.di

import com.example.retrofittraining.domain.GetFoodUseCase
import com.example.retrofittraining.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



    val domainModule = module {
        factory <GetFoodUseCase>{
            GetFoodUseCase(repository = get())
        }
    }



