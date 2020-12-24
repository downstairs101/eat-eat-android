package com.downstairs.dsplayer.service

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.downstairs.dsplayer.SplitPlayer

object PlayerServiceController {

    fun connect(context: Context, onConnect: (SplitPlayer) -> Unit = {}) {
        startService(context)

        val connection = PlayerServiceConnection { player -> onConnect(player) }
        val intent = Intent(context, PlayerService::class.java)
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun disconnect(context: Context) {}

    fun startService(context: Context) {
        val intent = Intent(context, PlayerService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }
}

