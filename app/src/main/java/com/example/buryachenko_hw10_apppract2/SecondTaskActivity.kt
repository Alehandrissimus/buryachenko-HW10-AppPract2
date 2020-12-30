package com.example.buryachenko_hw10_apppract2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.buryachenko_hw10_apppract2.databinding.ActivitySecondtaskBinding
import kotlin.random.Random

class SecondTaskActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SecondTaskActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivitySecondtaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = ActivitySecondtaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnSecond1.setOnClickListener {
            SecondTaskAdditionalActivity.start(this, "Extra String", 8800)
        }
        binding.btnSecond2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=50.4454025333673, 30.54382918898921"))
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent)
        }
    }


}