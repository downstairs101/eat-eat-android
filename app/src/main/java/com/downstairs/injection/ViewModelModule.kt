package com.downstairs.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.downstairs.place.details.PlaceDetailsViewModel
import com.downstairs.place.list.PlaceListVIewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PlaceDetailsViewModel::class)
    internal abstract fun placeDetailsViewModel(viewModel: PlaceDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceListVIewModel::class)
    internal abstract fun placeListViewModel(viewModel: PlaceListVIewModel): ViewModel
}

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}
