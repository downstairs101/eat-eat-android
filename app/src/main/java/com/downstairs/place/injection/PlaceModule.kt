package com.downstairs.place.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.downstairs.injection.FeatureScope
import com.downstairs.injection.ViewModelFactory
import com.downstairs.injection.ViewModelKey
import com.downstairs.place.data.PlaceRepository
import com.downstairs.place.details.PlaceDetailsViewModel
import com.downstairs.place.list.PlaceListVIewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class PlaceModule {

    @Provides
    fun providesString(): String {
        return "bosta"
    }

    @Provides
    @FeatureScope
    internal fun viewModelFactory(providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PlaceListVIewModel::class)
    internal fun placeListViewModel(placeRepository: PlaceRepository): ViewModel {
        return PlaceListVIewModel(placeRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PlaceDetailsViewModel::class)
    internal fun placeDetailsViewModel(placeRepository: PlaceRepository): ViewModel {
        return PlaceDetailsViewModel(placeRepository)
    }
}