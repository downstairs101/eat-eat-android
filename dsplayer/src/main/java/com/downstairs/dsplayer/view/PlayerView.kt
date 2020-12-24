package com.downstairs.dsplayer.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.downstairs.dsplayer.R
import com.downstairs.dsplayer.SplitPlayer
import com.downstairs.dsplayer.content.Content
import com.downstairs.dsplayer.engine.EngineObserver
import com.downstairs.dsplayer.engine.PlayerEngine
import com.downstairs.dsplayer.service.PlayerServiceController
import com.google.android.gms.cast.framework.CastButtonFactory
import kotlinx.android.synthetic.main.player_controller_view.view.*
import kotlinx.android.synthetic.main.player_view.view.*

class PlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), LifecycleObserver {

    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    init {
        inflate(context, R.layout.player_view, this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        CastButtonFactory.setUpMediaRouteButton(context, castButton)
    }

    fun load(vararg content: Content) {
        PlayerServiceController.connect(context) { player ->
            bindView(player)
            player.addContent(content[0])
        }
    }

    private fun bindView(splitPlayer: SplitPlayer) {
        splitPlayer.addEngineListener(object : EngineObserver {
            override fun onEngineChanged(engine: PlayerEngine) {
                playerViewSurface.setPlayer(engine)
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        PlayerServiceController.disconnect(context)
    }
}