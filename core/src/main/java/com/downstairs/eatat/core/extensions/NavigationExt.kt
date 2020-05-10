package com.downstairs.eatat.core.extensions

import android.app.Activity
import androidx.navigation.NavController

fun NavController.navigateUp(activity: Activity) {
    if (!navigateUp()) {
        activity.finish()
    }
}