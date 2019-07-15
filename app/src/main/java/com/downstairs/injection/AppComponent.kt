package com.downstairs.injection

import com.downstairs.EatEatApplication
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, DatabaseModule::class])
class AppComponent {
    interface AppComponent {
        @Component.Builder
        interface Builder {
            @BindsInstance
            fun application(application: EatEatApplication): Builder

            fun build(): AppComponent
        }
    }
}