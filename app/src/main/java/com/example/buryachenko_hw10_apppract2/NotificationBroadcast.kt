package com.example.buryachenko_hw10_apppract2

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput

class NotificationBroadcast : BroadcastReceiver() {
    private val KEY_REPLY = "KEY_REPLY"

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.also {
            Toast.makeText(context, "Reply: ${getMessageText(intent)}", Toast.LENGTH_LONG).show()

            context?.apply {
                val notificationId = intent.getIntExtra("KEY_NOTIFICATION_ID", 0)
                val channelId = intent.getStringExtra("KEY_CHANNEL_ID")

                val builder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Reply")
                        .setContentText("Reply processed!")

                NotificationManagerCompat.from(this).apply {
                    notify(notificationId, builder.build())
                }
            }
        }
    }

    private fun getMessageText(intent: Intent): CharSequence? {
        return RemoteInput.getResultsFromIntent(intent)?.getCharSequence(KEY_REPLY)
    }
}