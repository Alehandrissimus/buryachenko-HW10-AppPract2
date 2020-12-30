package com.example.buryachenko_hw10_apppract2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ConnectionStatusReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context.apply {
            Toast.makeText(this, "Connectivity change", Toast.LENGTH_LONG).show()
        }
    }
}