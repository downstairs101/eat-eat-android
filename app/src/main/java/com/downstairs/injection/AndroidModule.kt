package com.downstairs.injection

import com.downstairs.MainActivity
import com.downstairs.place.details.PlaceDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injectPlaceDetailsActivity(): PlaceDetailsActivity
}