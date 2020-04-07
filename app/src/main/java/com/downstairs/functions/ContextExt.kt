package com.downstairs.functions

import android.content.Context
import android.content.ContextWrapper
import android.util.TypedValue
import com.downstairs.injection.ApplicationComponent
import com.downstairs.injection.CoreInjector
import kotlin.math.roundToInt

fun Context.dipToPixels(dipValue: Float): Int {
    val metrics = this.resources.displayMetrics
    val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    return pixels.roundToInt()
}

fun ContextWrapper.getApplicationComponent(): ApplicationComponent {
    return (applicationContext as CoreInjector).applicationComponent
}

fun Context.getApplicationComponent(): ApplicationComponent {
    return (applicationContext as CoreInjector).applicationComponent
}