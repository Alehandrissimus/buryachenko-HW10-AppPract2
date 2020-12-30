package com.example.buryachenko_hw10_apppract2

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.buryachenko_hw10_apppract2.databinding.ActivitySecondtaskBinding
import com.example.buryachenko_hw10_apppract2.databinding.ActivityThirdtaskBinding

class ThirdTaskActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ThirdTaskActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityThirdtaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(ConnectionStatusReceiver(), IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        registerReceiver(ButtonClickReceiver(), IntentFilter("CUSTOM_ACTION"))
    }

    private fun setupBinding() {
        binding = ActivityThirdtaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnThird.setOnClickListener {
            sendBroadcast(Intent("CUSTOM_ACTION"))
        }
    }
}
