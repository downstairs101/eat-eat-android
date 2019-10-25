package com.downstairs.injection

import androidx.lifecycle.ViewModel
import com.downstairs.place.details.PlaceDetailsViewModel
import com.downstairs.place.list.PlaceListVIewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlaceDetailsViewModel::class)
    internal abstract fun placeDetailsViewModel(viewModel: PlaceDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceListVIewModel::class)
    internal abstract fun placeListViewModel(viewModel: PlaceListVIewModel): ViewModel
}
