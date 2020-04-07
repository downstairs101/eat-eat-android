package com.downstairs.eatat.core.extensions

import android.content.Context
import android.content.ContextWrapper
import android.util.TypedValue
import com.downstairs.eatat.core.injection.CoreComponent
import com.downstairs.eatat.core.injection.CoreInjector
import kotlin.math.roundToInt

fun Context.dipToPixels(dipValue: Float): Int {
    val metrics = this.resources.displayMetrics
    val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    return pixels.roundToInt()
}

fun ContextWrapper.dipToPixels(dipValue: Float): Int {
    val metrics = this.resources.displayMetrics
    val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    return pixels.roundToInt()
}



fun Context.getCoreComponent(): CoreComponent {
    return (applicationContext as CoreInjector).coreComponent
}

fun ContextWrapper.getCoreComponent(): CoreComponent {
    return (applicationContext as CoreInjector).coreComponent
}

