package com.downstairs.dsplayer.engine

import com.downstairs.dsplayer.content.MediaState

class EngineObservable {

    private val observers = mutableListOf<EngineObserver>()

    fun observe(observer: EngineObserver) {
        observers.add(observer)
    }

    fun removeObservers() {
        observers.clear()
    }

    fun onEngineChanged(engine: PlayerEngine?) {
        for (observer in observers) {
            observer.onEngineChanged(engine!!)
        }
    }

    fun onStateChanged(state: MediaState?) {
        for (observer in observers) {
            observer.onStateChanged(state!!)
        }
    }
}