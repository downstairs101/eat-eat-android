package com.downstairs.core.tools.instruction

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf

class Direction(@IdRes val destination: Int) : Instruction() {

    private var arguments: Array<out Pair<String, Any?>> = arrayOf(Pair("", ""))

    val bundledArgs: Bundle
        get() = bundleOf(*arguments)

    fun putArguments(vararg arguments: Pair<String, Any?>) {
        this.arguments = arguments
    }
}