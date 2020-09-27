package com.downstairs.core.extensions

import android.app.Activity
import androidx.navigation.NavController
import com.downstairs.core.tools.Direction

fun NavController.navigateUp(activity: Activity) {
    if (!navigateUp()) {
        activity.finish()
    }
}

fun NavController.navigate(direction: Direction) {
    navigate(direction.destination, direction.bundledArgs)
}