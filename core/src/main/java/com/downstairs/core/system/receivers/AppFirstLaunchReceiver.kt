package com.downstairs.core.system.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.downstairs.core.notification.NotificationChannelManager

class AppFirstLaunchReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_PACKAGE_FIRST_LAUNCH) {
            NotificationChannelManager(context).createPlayerChannel()
        }
    }
}
