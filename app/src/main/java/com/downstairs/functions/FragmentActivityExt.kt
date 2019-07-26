package com.downstairs.functions

import androidx.fragment.app.FragmentActivity

fun FragmentActivity.displayHeight() = DisplayTools
        .getInstance(windowManager)
        .displayHeightInPixel()