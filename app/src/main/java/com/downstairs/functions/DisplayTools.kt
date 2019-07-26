package com.downstairs.functions

import android.util.DisplayMetrics
import android.view.WindowManager

class DisplayTools private constructor(private val displayMetrics: DisplayMetrics) {

    companion object {
        @Volatile
        private var INSTANCE: DisplayTools? = null

        fun getInstance(windowManager: WindowManager): DisplayTools {
            if (INSTANCE == null) {
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)

                INSTANCE = DisplayTools(displayMetrics)
                return INSTANCE!!
            }

            return INSTANCE!!
        }
    }

    fun displayHeightInPixel() = displayMetrics.heightPixels
}