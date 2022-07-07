package com.example.retrofittraining.data

data class Food(
    val brand: String,
    val image: String?,
    val label: String,
    val nutrients: Nutrients,
    val foodContentsLabel: String = "No recipe for this food :("
)