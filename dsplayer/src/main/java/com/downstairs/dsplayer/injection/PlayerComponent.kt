package com.downstairs.dsplayer.injection

import com.downstairs.core.injection.CoreComponent
import com.downstairs.core.injection.FeatureScope
import com.downstairs.dsplayer.service.PlayerService
import dagger.Component

@FeatureScope
@Component(modules = [PlayerModule::class], dependencies = [CoreComponent::class])
interface PlayerComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): PlayerComponent
    }

    fun inject(service: PlayerService)
}