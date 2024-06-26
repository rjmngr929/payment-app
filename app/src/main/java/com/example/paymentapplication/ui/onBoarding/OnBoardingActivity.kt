package com.example.paymentapplication.ui.onBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    companion object{
        private val TAG = "OnBoardingActivity"
    }

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}