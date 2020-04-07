package com.downstairs.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.downstairs.eatat.core.injection.FeatureScope
import com.downstairs.eatat.core.injection.ViewModelFactory
import com.downstairs.eatat.core.injection.ViewModelKey
import com.downstairs.place.data.PlaceRepository
import com.downstairs.place.details.PlaceDetailsViewModel
import com.downstairs.place.list.PlaceListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class ViewModelModule {

    @Provides
    @FeatureScope
    internal fun viewModelFactory(providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PlaceListViewModel::class)
    internal fun placeListViewModel(placeRepository: PlaceRepository): ViewModel {
        return PlaceListViewModel(placeRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PlaceDetailsViewModel::class)
    internal fun placeDetailsViewModel(placeRepository: PlaceRepository): ViewModel {
        return PlaceDetailsViewModel(placeRepository)
    }
}