package com.example.retrofittraining.di

import com.example.retrofittraining.data.repository.FoodRepositoryImpl
import com.example.retrofittraining.domain.FoodRepository
import org.koin.dsl.module

val dataModule = module {
    single<FoodRepository>{
        FoodRepositoryImpl(restFoodApiService = get())
    }
}