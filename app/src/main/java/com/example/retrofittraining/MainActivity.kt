package com.example.retrofittraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.retrofittraining.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

const val APP_ID = "5ae50da9"
const val APP_KEY = "54ce7472b5ced85685ff91a880c4a224"
const val BASE_URI = "https://api.edamam.com/api/food-database/"



class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val restFoodApiService = restFoodApi.getFoodRecipe("pizza")
            val food = restFoodApiService[0]
            Log.d("FOOD","${food.hints.toString()}")
        }


    }


}