package com.downstairs.injection

import android.content.Context
import android.content.ContextWrapper

fun ContextWrapper.getApplicationComponent(): ApplicationComponent {
    return (applicationContext as CoreInjector).applicationComponent
}

fun Context.getApplicationComponent(): ApplicationComponent {
    return (applicationContext as CoreInjector).applicationComponent
}