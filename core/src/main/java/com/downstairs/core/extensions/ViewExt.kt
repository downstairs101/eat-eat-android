package com.downstairs.core.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.openSoftKeyBoard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}