package com.downstairs.dsplayer.notification

import android.app.Notification

interface NotificationListener {
    fun onNotificationPosted(notificationId: Int, notification: Notification) {}
    fun onNotificationRemoved() {}
}