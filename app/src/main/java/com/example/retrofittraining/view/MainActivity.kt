package com.example.retrofittraining.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.databinding.ActivityMainBinding
import com.example.retrofittraining.model.restFoodApi
import com.example.retrofittraining.view.Adapter.FoodAdapter
import kotlinx.coroutines.launch

const val APP_ID = "5ae50da9"
const val APP_KEY = "54ce7472b5ced85685ff91a880c4a224"
const val BASE_URI = "https://api.edamam.com/"



class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = FoodAdapter()
        val recyclerView = binding.recyclerFood
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        lifecycleScope.launch {

                val restFoodApiService = restFoodApi.getFoodRecipe("pie")
                val food = restFoodApiService.body()!!.hints
            adapter.setData(food)
                Log.d("FOOD", "${food}")
    }
    }


}


