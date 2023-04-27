package com.example.retrofittraining.presentation

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofittraining.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            binding.splashProgress.max = 1000
            val value = 1000
            ObjectAnimator.ofInt(binding.splashProgress, "progress", value)
                .setDuration(2000).start()

            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
    }



}