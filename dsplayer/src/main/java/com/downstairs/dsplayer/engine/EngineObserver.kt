package com.downstairs.dsplayer.engine

import com.downstairs.dsplayer.content.MediaState

interface EngineObserver {
    fun onEngineChanged(engine: PlayerEngine) {}
    fun onStateChanged(mediaState: MediaState) {}
}