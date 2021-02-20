package com.downstairs.tosplit.injection

import com.downstairs.tosplit.user.injection.UserModule
import dagger.Module


@Module(includes = [UserModule::class])
class AppModule