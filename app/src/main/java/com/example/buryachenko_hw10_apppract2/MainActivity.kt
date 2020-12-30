package com.example.buryachenko_hw10_apppract2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buryachenko_hw10_apppract2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnFirstTask.setOnClickListener {
            FirstTaskActivity.start(this)
        }
        binding.btnSecondTask.setOnClickListener {
            SecondTaskActivity.start(this)
        }
        binding.btnThirdTask.setOnClickListener {
            ThirdTaskActivity.start(this)
        }
    }
}