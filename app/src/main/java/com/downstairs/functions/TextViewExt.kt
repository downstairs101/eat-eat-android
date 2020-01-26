package com.downstairs.functions

import android.widget.TextView

fun TextView.stringText(): String {
    return this.text.toString()
}