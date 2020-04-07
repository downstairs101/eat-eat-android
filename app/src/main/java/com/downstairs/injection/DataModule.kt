package com.downstairs.injection

import android.content.Context
import com.downstairs.data.AppDatabase
import com.downstairs.eatat.core.injection.FeatureScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @FeatureScope
    fun provideAppDatabase(context: Context) = AppDatabase.getInstance(context)

    @Provides
    @FeatureScope
    fun providePlaceDAO(appDatabase: AppDatabase) = appDatabase.placeDAO()
}