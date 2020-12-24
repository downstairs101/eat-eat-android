package com.downstairs.dsplayer.injection

import android.content.Context
import com.downstairs.core.injection.FeatureScope
import com.downstairs.dsplayer.PlayerMediaSession
import com.downstairs.dsplayer.content.CustomHeaderMediaSourceFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @FeatureScope
    @Provides
    fun providesMediaSession(context: Context): PlayerMediaSession {
        return PlayerMediaSession(context)
    }

    @FeatureScope
    @Provides
    fun providesExoplayer(context: Context): SimpleExoPlayer {
        return SimpleExoPlayer.Builder(context)
            .setMediaSourceFactory(CustomHeaderMediaSourceFactory())
            .build()
    }
}