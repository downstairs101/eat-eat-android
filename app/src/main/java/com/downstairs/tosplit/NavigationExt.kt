package com.downstairs.tosplit

import android.app.Activity
import androidx.navigation.NavController

fun NavController.navigateUp(activity: Activity) {
    when (currentDestination?.id) {
        R.id.loginFragment, R.id.userRegisterFragment -> activity.finish()
        else -> navigateUp()
    }
}