package com.downstairs.dsplayer

import com.downstairs.dsplayer.content.MediaAction
import com.downstairs.dsplayer.content.MediaState
import com.downstairs.dsplayer.notification.NotificationListener
import com.downstairs.dsplayer.notification.PlayerNotification
import com.downstairs.dsplayer.tools.ArtworkLoader
import javax.inject.Inject

class PlaybackSessionManager @Inject constructor(
    private val mediaSession: PlayerMediaSession,
    private val artworkLoader: ArtworkLoader,
    private val playerNotification: PlayerNotification
) {

    fun setNotificationListener(listener: NotificationListener) {
        playerNotification.setNotificationListener(listener)
    }

    fun setMediaActionListener(listener: (MediaAction) -> Unit) {
        mediaSession.setMediaActionListener(listener)
    }

    fun post(mediaState: MediaState) {
        loadArtwork(mediaState)
    }

    private fun loadArtwork(mediaState: MediaState) {
        artworkLoader.load(mediaState.artworkUrl) { artwork ->
            onArtLoaded(mediaState.copy(artwork = artwork))
        }
    }

    private fun onArtLoaded(mediaState: MediaState) {
        mediaSession.setMediaState(mediaState)
        playerNotification.prepare(mediaState)
    }

    fun release() {
        playerNotification.removeNotification()
        mediaSession.release()
    }
}