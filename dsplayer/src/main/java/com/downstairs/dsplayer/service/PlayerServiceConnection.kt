package com.downstairs.dsplayer.service

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import com.downstairs.dsplayer.SplitPlayer

class PlayerServiceConnection(
    private val onDisconnect: () -> Unit = {},
    private val onConnect: (SplitPlayer) -> Unit = {}
) : ServiceConnection {

    override fun onServiceConnected(componentName: ComponentName?, serviceBinder: IBinder?) {
        (serviceBinder as? PlayerService.PLayerServiceBinder)?.also { binder ->
            onConnect(binder.getPlayer())
        }
    }

    override fun onServiceDisconnected(componentName: ComponentName?) {
        onDisconnect()
    }
}