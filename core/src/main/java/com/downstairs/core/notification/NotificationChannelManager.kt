package com.downstairs.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat

class NotificationChannelManager(context: Context) {

    private val notificationService =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val PLAYER_CHANNEL_ID = "playerChannelId"
    }

    fun createPlayerChannel() {
        createNotificationChannel(
            PLAYER_CHANNEL_ID,
            "Player", NotificationManagerCompat.IMPORTANCE_NONE
        )
    }

    private fun createNotificationChannel(id: String, name: String, importance: Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(id, name, importance)
            notificationService.createNotificationChannel(channel)
        }
    }
}