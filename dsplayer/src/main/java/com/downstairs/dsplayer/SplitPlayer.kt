package com.downstairs.dsplayer

import com.downstairs.dsplayer.content.Content
import com.downstairs.dsplayer.content.MediaAction
import com.downstairs.dsplayer.engine.EngineManager
import com.downstairs.dsplayer.engine.EngineObserver
import com.downstairs.dsplayer.engine.PlayerEngine
import com.downstairs.dsplayer.playlist.ContentChangeListener
import com.downstairs.dsplayer.playlist.Playlist
import javax.inject.Inject

class SplitPlayer @Inject constructor(private val engineManager: EngineManager) {

    private val playList = Playlist()

    fun addContent(content: Content) {
        playList.set(content)
    }

    fun addEngineListener(engineObserver: EngineObserver) {
        engineManager.observe(engineObserver)
    }

    init {
        playList.addContentChangeListener(ContentChangeListener { content ->
            engineManager.prepare(content)
        })

        engineManager.observe(object : EngineObserver {
            override fun onEngineChanged(engine: PlayerEngine) {
                playList.current()
            }
        })
    }

    fun performAction(action: MediaAction) {
        engineManager.performAction(action)
    }

    fun release() {
        engineManager.release()
    }
}
