package com.downstairs.dsplayer.content

sealed class MediaAction {
    object Play : MediaAction()
    object Pause : MediaAction()
    object Stop : MediaAction()
    object Forward : MediaAction()
    object Backward : MediaAction()
    data class SeekTo(val position: Long) : MediaAction()
}