package com.downstairs.injection

import android.content.Context
import com.downstairs.place.model.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun providePlaceDAO(appDatabase: AppDatabase) = appDatabase.placeDAO()
}