package com.downstairs.functions

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

fun <T : ViewDataBinding> FragmentActivity.bindLayout(activity: Activity, layoutId: Int): T {
    return DataBindingUtil.setContentView(activity, layoutId)
}



