package com.downstairs.functions

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

fun Context.dipToPixels(dipValue: Float): Int {
    val metrics = this.resources.displayMetrics
    val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    return pixels.roundToInt()
}