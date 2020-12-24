package com.downstairs.core.extensions

import android.widget.TextView

val TextView.value: String
    get() = this.text.toString()