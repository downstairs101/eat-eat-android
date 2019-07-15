package com.downstairs.injection

import android.app.Application
import com.downstairs.place.model.AppDatabase
import com.downstairs.place.model.PlaceDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Singleton
    @Provides
    fun providePlaceDao(database:AppDatabase): PlaceDAO{
        return database.placeDAO()
    }
}