package com.example.buryachenko_hw10_apppract2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_DEFAULT
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.example.buryachenko_hw10_apppract2.databinding.ActivityFirsttaskBinding

class FirstTaskActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, FirstTaskActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityFirsttaskBinding
    private val KEY_REPLY = "KEY_REPLY"
    private val REQUEST_CODE = 11
    private val CHANNEL_ID = "CHANNEL_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = ActivityFirsttaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        createNotificationChannel()

        binding.btnFirst1.setOnClickListener {
            sendSimpleNotification()
        }
        binding.btnFirst2.setOnClickListener {
            sendNotificationWithAlert()
        }
        binding.btnFirst3.setOnClickListener {
            sendNotificationWithReply()
        }
        binding.btnFirst4.setOnClickListener {
            sendNotificationWithProgress()
        }
    }

    private fun sendSimpleNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Simple Notification")
                .setContentText("You have received first simple notification")
                .setPriority(PRIORITY_DEFAULT)

        notificationManager.notify(notificationId, builder.build())
    }

    private fun sendNotificationWithAlert() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val emptyIntent = PendingIntent.getActivity(
                this,
                0,
                Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification with Action")
                .setContentText("You have received second notification with action")
                .addAction(R.drawable.ic_launcher_foreground, "Action", emptyIntent)
                .setPriority(PRIORITY_DEFAULT)

        notificationManager.notify(notificationId, builder.build())
    }

    private fun sendNotificationWithReply() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1

        val intent = Intent(this, NotificationBroadcast::class.java)
        intent.putExtra("KEY_NOTIFICATION_ID", notificationId)
        intent.putExtra("KEY_CHANNEL_ID", CHANNEL_ID)

        val resultPendingIntent2 = PendingIntent.getBroadcast(
                this,
                REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        val remoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Reply")
            build()
        }

        val replyAction = NotificationCompat.Action.Builder(
                R.drawable.ic_launcher_foreground, "Reply", resultPendingIntent2)
                .addRemoteInput(remoteInput)
                .build()

        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification with Reply")
                .setContentText("You have received third notification with reply")
                .addAction(replyAction)
                .setPriority(PRIORITY_DEFAULT)

        notificationManager.notify(notificationId, builder.build())
    }

    private fun sendNotificationWithProgress() {
        val notificationId = 1
        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Download")
            setContentText("Download in progress")
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setPriority(NotificationCompat.PRIORITY_LOW)
        }
        val PROGRESS_MAX = 100
        var PROGRESS_CURRENT = 0
        NotificationManagerCompat.from(this).apply {
            builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
            notify(notificationId, builder.build())

            Thread {
                SystemClock.sleep(1000)
                while (PROGRESS_CURRENT <= PROGRESS_MAX) {
                    SystemClock.sleep(500)
                    PROGRESS_CURRENT += 10
                    builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
                    notify(notificationId, builder.build())
                }
                builder.setContentText("Download complete")
                        .setProgress(0, 0, false)
                notify(notificationId, builder.build())
            }.start()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    "CHANNEL_ID",
                    "notifChannel",
                    NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "channelDescription"
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}