package com.downstairs.dsplayer.service

import android.content.Context
import com.downstairs.dsplayer.SplitPlayer

class PlayerServiceController(context: Context) {

    private val connection = PlayerServiceConnection(context)

    fun connect(onConnected: (SplitPlayer) -> Unit = {}) {
        connection.connect(onConnected)
    }

    fun disconnect() {
        connection.disconnect()
    }
}

