package com.example.buryachenko_hw10_apppract2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.buryachenko_hw10_apppract2.databinding.ActivitySecondtaskAdditionalBinding
import com.example.buryachenko_hw10_apppract2.databinding.ActivitySecondtaskBinding

class SecondTaskAdditionalActivity : AppCompatActivity() {

    companion object {
        private const val STRING_KEY = "STRING_KEY"
        private const val INT_KEY = "INT_KEY"

        fun start(context: Context) {
            val intent = Intent(context, SecondTaskAdditionalActivity::class.java)
            context.startActivity(intent)
        }

        fun start(context: Context, arg_str: String, arg_int: Int) {
            val intent = Intent(context, SecondTaskAdditionalActivity::class.java)
            intent.putExtra("STRING_KEY", arg_str)
            intent.putExtra("INT_KEY", arg_int)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivitySecondtaskAdditionalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        updateUI()
    }

    private fun setupBinding() {
        binding = ActivitySecondtaskAdditionalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun updateUI() {
        val string = intent.getStringExtra(STRING_KEY)
        val integer = intent.getIntExtra(INT_KEY, -1)
        binding.textViewSecondAddInt.text = integer.toString()
        binding.textViewSecondAddString.text = string
    }
}