package com.downstairs.injection

import com.downstairs.MainActivity
import com.downstairs.place.details.PlaceDetailsActivity
import com.downstairs.place.list.PlaceListFragment
import com.downstairs.place.list.PlacePageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injectPlaceDetailsActivity(): PlaceDetailsActivity

    @ContributesAndroidInjector
    abstract fun injectPlaceListFragment(): PlaceListFragment

    @ContributesAndroidInjector
    abstract fun injectPlacePageFragment(): PlacePageFragment
}