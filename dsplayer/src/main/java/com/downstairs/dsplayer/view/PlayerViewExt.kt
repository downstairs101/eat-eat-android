package com.downstairs.dsplayer.view

import com.downstairs.dsplayer.engine.PlayerEngine
import com.google.android.exoplayer2.ui.PlayerView


fun PlayerView.setPlayer(playerEngine: PlayerEngine) {
    this.player = playerEngine.player
}