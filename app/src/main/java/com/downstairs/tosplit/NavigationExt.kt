package com.downstairs.core.extensions

import android.app.Activity
import androidx.navigation.NavController
import com.downstairs.core.tools.instruction.Direction

fun NavController.navigateUp(activity: Activity) {
    if (!navigateUp()) {
        activity.finish()
    }
}