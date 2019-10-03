package com.downstairs.injection

import android.content.Context
import com.downstairs.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun providePlaceDAO(appDatabase: AppDatabase) = appDatabase.placeDAO()
}