package com.example.retrofittraining.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.retrofittraining.R
import com.example.retrofittraining.databinding.ActivityMainBinding
import kotlin.system.exitProcess


const val APP_ID = "5ae50da9"
const val APP_KEY = "54ce7472b5ced85685ff91a880c4a224"
const val BASE_URI = "https://api.edamam.com/"



class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()

    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        exitProcess(0)

    }
}



