package com.example.buryachenko_hw10_apppract2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ButtonClickReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ButtonClickReceiver", "ButtonClickReceiver - user tap on button")
        context.apply {
            Toast.makeText(this, "ButtonClickReceiver - user tap on button", Toast.LENGTH_LONG).show()
        }
    }
}