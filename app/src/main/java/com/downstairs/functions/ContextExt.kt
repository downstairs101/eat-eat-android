package com.downstairs.functions

import android.content.Context
import android.content.ContextWrapper
import android.util.TypedValue
import com.downstairs.injection.PlaceComponent
import kotlin.math.roundToInt

fun Context.dipToPixels(dipValue: Float): Int {
    val metrics = this.resources.displayMetrics
    val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    return pixels.roundToInt()
}

fun ContextWrapper.getApplicationComponent(): PlaceComponent {
    return (applicationContext as CoreInjector).applicationComponent
}

fun Context.getApplicationComponent(): PlaceComponent {
    return (applicationContext as CoreInjector).applicationComponent
}